package school.sptech.vannbora.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import io.github.cdimascio.dotenv.Dotenv;
import school.sptech.vannbora.dto.ClimaApiExternaDto;
import school.sptech.vannbora.dto.ClimaApiExternaDto.Results.Forecast;
import school.sptech.vannbora.interfaces.ISorter;
import school.sptech.vannbora.dto.ClimaDto;

import java.util.List;
import java.util.Arrays;

@Service
public class ClimaService implements ISorter<Forecast> {

    private static final Logger log = LoggerFactory.getLogger(ClimaService.class);
    
    public ResponseEntity<ClimaDto> buscarClima(String city) {
        if (city == null || city.isBlank()) return ResponseEntity.badRequest().build();

        Dotenv dotenv = Dotenv.load();

        String key = dotenv.get("API_HGBRASIL_KEY");

        RestClient client = RestClient.builder()
                .baseUrl("https://api.hgbrasil.com/weather")
                .messageConverters(httpMessageConverters -> httpMessageConverters.add(new MappingJackson2HttpMessageConverter()))
                .build();

        String uri = "?key=" + key + "&city_name=" + city;

        try {
            
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
                    clima.getResults().getHumidity(),
                    clima.getResults().getForecast());

            return ResponseEntity.ok(resposta);
        } catch (RestClientException e) {
            log.error("Erro ao chamar a API externa: ", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    public ResponseEntity<List<Forecast>> buscarHistoricoClima(String city, boolean sort) {
        if(city == null || city.isBlank()) return ResponseEntity.badRequest().build();

        Dotenv dotenv = Dotenv.load();

        String key = dotenv.get("API_HGBRASIL_KEY");

        RestClient client = RestClient.builder()
                .baseUrl("https://api.hgbrasil.com/weather")
                .messageConverters(httpMessageConverters -> httpMessageConverters.add(new MappingJackson2HttpMessageConverter()))
                .build();

        String uri = "?key=" + key + "&city_name=" + city;

        try {
            ClimaApiExternaDto clima = client.get()
                    .uri(uri)
                    .retrieve()
                    .body(ClimaApiExternaDto.class);

            if (clima == null) {
                return ResponseEntity.noContent().build();
            }

            Forecast[] forecast = clima.getResults().getForecast().toArray(new Forecast[0]);

            if (sort) sort(forecast);

            return ResponseEntity.ok(Arrays.asList(forecast));

        } catch (RestClientException e) {
            log.error("Erro ao chamar a API externa: ", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @Override
    public void sort(Forecast[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].getHumidity() > array[j].getHumidity()) {
                    Forecast temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}
