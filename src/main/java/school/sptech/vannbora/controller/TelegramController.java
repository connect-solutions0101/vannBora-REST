package school.sptech.vannbora.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.vannbora.service.TelegramService;

@RestController
@RequestMapping("/notificacoes")
public class TelegramController {
    private final TelegramService telegramService;

    public TelegramController(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @PostMapping("/enviar")
    public ResponseEntity<String> enviar(@RequestParam String mensagem){
        boolean enviado = telegramService.enviarMensagem(mensagem);
        return enviado ? ResponseEntity.ok("Mensagem enviada com sucesso!") : ResponseEntity.status(500).body("Falha ao enviar mensagem.");
    }

}
