package school.sptech.vannbora.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Periodo {
    MANHA("M", "Manhã"),
    TARDE("T", "Tarde"),
    NOITE("N", "Noite");

    private final String valor;
    private final String descricao;
}
