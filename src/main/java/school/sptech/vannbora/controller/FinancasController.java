package school.sptech.vannbora.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.vannbora.service.FaturaService;
import school.sptech.vannbora.service.FinancasService;

import java.io.IOException;

@RestController
@RequestMapping("/financas")
@RequiredArgsConstructor
public class FinancasController {

    private final FinancasService financasService;

    @GetMapping("/download-csv")
    public ResponseEntity<byte[]> downloadCsv() {
        try {
            byte[] csvContent = financasService.getFaturasCsv();

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