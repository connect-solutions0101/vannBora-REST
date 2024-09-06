package school.sptech.vannbora.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.web.client.RestClientException;
import school.sptech.vannbora.dto.ClimaApiExternaDto;
import school.sptech.vannbora.dto.ClimaDto;

@RestController
@RequestMapping("/climas")
public class ClimaController {

    private static final Logger log = LoggerFactory.getLogger(ClimaController.class);
    
    @GetMapping
    public ResponseEntity<ClimaDto> buscarClima(@RequestParam String city) {

        Dotenv dotenv = Dotenv.load();

        String key = dotenv.get("API_HGBRASIL_KEY");

        RestClient client = RestClient.builder()
                .baseUrl("https://api.hgbrasil.com/weather")
                .messageConverters(httpMessageConverters -> httpMessageConverters.add(new MappingJackson2HttpMessageConverter()))
                .build();

        String uri = "?key=" + key + "&city_name=" + city;

        try {
            String raw = client.get()
                    .uri(uri)
                    .retrieve()
                    .body(String.class);

            log.info("Resposta da API: " + raw);

            ClimaApiExternaDto clima = client.get()
                    .uri(uri)
                    .retrieve()
                    .body(ClimaApiExternaDto.class);

            if (clima == null) {
                return ResponseEntity.noContent().build();
            }

            ClimaDto resposta = new ClimaDto(
                    clima.getResults().getTemp(),
                    clima.getResults().getCity(),
                    clima.getResults().getDescription(),
                    clima.getResults().getCurrently(),
                    clima.getResults().getHumidity());

            return ResponseEntity.ok(resposta);
        } catch (RestClientException e) {
            log.error("Erro ao chamar a API externa: ", e);
            return ResponseEntity.status(500).body(null);
        }
    }
}
