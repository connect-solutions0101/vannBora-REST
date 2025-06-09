package school.sptech.vannbora.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.vannbora.service.NotificacoesService;

import java.util.Set;

@RestController
@RequestMapping("/notificacoes")
public class NotificacoesController {
    private final NotificacoesService notificacoesService;

    public NotificacoesController(NotificacoesService notificacoesService) {
        this.notificacoesService = notificacoesService;
    }

    @PostMapping("/enviar")
    public ResponseEntity<String> enviar(@RequestBody String mensagem){
        boolean sucesso = notificacoesService.enviarMensagem(mensagem);
        if (sucesso) {
            return ResponseEntity.ok("Mensagem enviada com sucesso!");
        } else {
            return ResponseEntity.status(500).body("Falha ao enviar mensagem.");
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarChatId(@RequestParam String chatId) {
        notificacoesService.registrarChatId(chatId);
        return ResponseEntity.ok("Chat ID registrado com sucesso!");
    }

    @GetMapping("/chat-ids")
    public ResponseEntity<Set<String>> listarChatIds(){
        return ResponseEntity.ok(notificacoesService.listarChatIds());
    }
}
