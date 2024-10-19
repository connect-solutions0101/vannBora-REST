package school.sptech.vannbora.dto.feriado;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeriadoDto {
    @JsonAlias("date")
    private String data;

    @JsonAlias("name")
    private String nome;

    @JsonAlias("type")
    private String tipo;
}