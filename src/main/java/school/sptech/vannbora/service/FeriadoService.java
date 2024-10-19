package school.sptech.vannbora.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.vannbora.dto.feriado.FeriadoDto;
import school.sptech.vannbora.exception.RegistroNaoEncontradoException;

import java.time.LocalDate;
import java.util.List;

@Service
public class FeriadoService {

    private static final String BASE_URL = "https://brasilapi.com.br/api/feriados/v1/";
    private static final Logger log = LoggerFactory.getLogger(FeriadoService.class);

    public List<FeriadoDto> buscarFeriadosPorAno(int ano, Boolean ordenarPorNome){
        RestClient client = RestClient.builder()
                .baseUrl(BASE_URL)
                .messageConverters(httpMessageConverters -> httpMessageConverters.add(new MappingJackson2HttpMessageConverter()))
                .build();

        try{
            String uri = BASE_URL + ano;

            List<FeriadoDto> feriados = client.get()
                    .uri(uri)
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<FeriadoDto>>() {
                    });

            if(ordenarPorNome){
                assert feriados != null;
                ordenarFeriadosPorNome(feriados, 0, feriados.size() - 1);
            }

            return feriados;
        } catch(RestClientException exception){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao chamar a API externa: " + exception);
        }
    }

    public FeriadoDto buscarFeriadoHoje(){
        LocalDate hoje = LocalDate.now();
        List<FeriadoDto> feriados = buscarFeriadosPorAno(hoje.getYear(), false);

        int i = 0, j = feriados.size() - 1;

        while(j >= i){
            int meio = (i + j) / 2;

            if (feriados.get(meio).getData().equals(hoje.toString())) {
                return feriados.get(meio);
            }

            if (feriados.get(meio).getData().compareTo(hoje.toString()) < 0) {
                i = meio + 1;
            } else {
                j = meio - 1;
            }
        }
        throw new RegistroNaoEncontradoException("Hoje não é feriado.");
    }

    public void ordenarFeriadosPorNome(List<FeriadoDto> feriados, int inicio, int fim){
        int i = inicio, j = fim;
        FeriadoDto pivo = feriados.get((inicio + fim) / 2);

        while(i <= j){
            while(i < fim + 1 && feriados.get(i).getNome().compareTo(pivo.getNome()) < 0){
                i++;
            }

            while(j > inicio - 1 && feriados.get(j).getNome().compareTo(pivo.getNome()) > 0){
                j--;
            }

            if(i <= j){
                trocar(feriados, i, j);
                i++;
                j--;
            }
        }

        if(inicio < j){
            ordenarFeriadosPorNome(feriados, inicio, j);
        }

        if(i < fim){
            ordenarFeriadosPorNome(feriados, i, fim);
        }
    }

    private void trocar(List<FeriadoDto> feriados, int i, int j){
        FeriadoDto temp = feriados.get(i);
        feriados.set(i, feriados.get(j));
        feriados.set(j, temp);
    }
}
