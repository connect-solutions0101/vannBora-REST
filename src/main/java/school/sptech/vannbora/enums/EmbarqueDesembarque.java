package school.sptech.vannbora.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmbarqueDesembarque {
    DESEMBARQUE(0, "Desembarque"),
    EMBARQUE(1, "Embarque");

    private final int valor;
    private final String descricao;
}
