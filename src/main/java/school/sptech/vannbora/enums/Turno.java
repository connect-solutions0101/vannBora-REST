package school.sptech.vannbora.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Turno {
    PADRAO("P", "Padrão"),
    INTEGRAL("I", "Integral");

    private final String valor;
    private final String descricao;
}