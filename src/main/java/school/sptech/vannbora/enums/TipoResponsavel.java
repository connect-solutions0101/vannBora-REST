package school.sptech.vannbora.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoResponsavel {
    PADRAO("P", "Padrão"),
    FINANCEIRO("F", "Financeiro");

    private final String valor;
    private final String descricao;
}
