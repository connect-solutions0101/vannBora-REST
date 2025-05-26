package school.sptech.vannbora.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TelegramService {

    private final TelegramBot bot;
    private final String chatId;

    public TelegramService(
            @Value("${telegram.bot.token}") String botToken,
            @Value("${telegram.chat.id}") String chatId) {
        this.bot = new TelegramBot(botToken);
        this.chatId = chatId;
    }

    public boolean enviarMensagem(String mensagem) {
        SendMessage request = new SendMessage(chatId, mensagem);
        SendResponse response = bot.execute(request);
        return response.isOk();
    }
}

