package school.sptech.vannbora.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Pago {
    NAO_PAGO(0, "Não Pago"),
    PAGO(1, "Pago");

    private final int valor;
    private final String descricao;
}
