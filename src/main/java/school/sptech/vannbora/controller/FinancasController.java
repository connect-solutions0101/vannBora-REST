package school.sptech.vannbora.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.vannbora.service.FinancasService;

import java.io.IOException;

@RestController
@RequestMapping("/financas")
@RequiredArgsConstructor
public class FinancasController {

    private final FinancasService financasService;

    @GetMapping("/download-csv/{id}")
    public ResponseEntity<byte[]> downloadCsv(
        @PathVariable Integer id
    ) {
        try {
            byte[] csvContent = financasService.getFaturasCsv(id);

            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=faturas.csv");
            headers.setContentType(MediaType.TEXT_PLAIN);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(csvContent);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}