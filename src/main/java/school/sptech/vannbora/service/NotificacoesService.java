package school.sptech.vannbora.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class NotificacoesService {

    private final TelegramBot bot;
    private final Set<String> chatIds = new HashSet<>();

    public NotificacoesService(@Value("${telegram.bot.token}") String botToken) {
        this.bot = new TelegramBot(botToken);
    }

    public void registrarChatId(String chatId){
        chatIds.add(chatId);
    }

    public Set<String> listarChatIds() {
        return chatIds;
    }

    public boolean enviarMensagem(String mensagem){
        int idealThreads = Math.min(chatIds.size(), 32);
        ExecutorService executor = Executors.newFixedThreadPool(idealThreads);
        List<CompletableFuture<Boolean>> futures = new ArrayList<>();

        for (String chatId: chatIds){
            CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
                try {
                    SendMessage request = new SendMessage(chatId, mensagem);
                    SendResponse response = bot.execute(request);

                    if (!response.isOk()){
                        throw new RuntimeException("Erro ao enviar: " + response.description());
                    }
                    return true;
                } catch (Exception e) {
                    System.err.println("Erro ao enviar mensagem para o chat " + chatId + ": " + e.getMessage());
                    return false;
                }
            }, executor);
            futures.add(future);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        executor.shutdown();
        return futures.stream().allMatch(CompletableFuture::join);
    }
}

