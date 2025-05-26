package school.sptech.vannbora.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class NotificacoesService {

    private final TelegramBot bot;
    private final Set<String> chatIds = ConcurrentHashMap.newKeySet();
    private int lastUpdate = 0;

    public NotificacoesService(@Value("${telegram.bot.token}") String botToken) {
        this.bot = new TelegramBot(botToken);
    }

    @PostConstruct
    public void init(){
        System.out.println("[NotificacoesService] Iniciando polling automático do Telegram: ");
    }

    @Scheduled(fixedDelay = 3000)
    public void buscarAtualizacoes(){
        GetUpdates request = new GetUpdates()
                .limit(100)
                .offset(lastUpdate + 1)
                .timeout(0);

        GetUpdatesResponse updatesResponse = bot.execute(request);

        if (updatesResponse != null && updatesResponse.isOk()){
            for (Update update : updatesResponse.updates()) {
                if (update.message() != null && update.message().chat() != null) {
                    String chatId = String.valueOf(update.message().chat().id());

                    if(chatIds.add(chatId)) {
                        System.out.println("[NotificacoesService] Novo chat ID registrado: " + chatId);
                    } else {
                        System.out.println("[NotificacoesService] Chat ID já registrado: " + chatId);
                    }
                }
                lastUpdate = update.updateId();
            }
        }
    }

    public void registrarChatId(String chatId){
        chatIds.add(chatId);
    }

    public Set<String> listarChatIds() {
        return chatIds;
    }

    public boolean enviarMensagem(String mensagem){
        int totalChatIds = chatIds.size();
        int threadPoolSize = Math.min(chatIds.size(), 32);
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
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

