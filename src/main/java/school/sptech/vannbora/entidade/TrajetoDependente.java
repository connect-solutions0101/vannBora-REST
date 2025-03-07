package school.sptech.vannbora.entidade;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.vannbora.entidade.ResponsavelDependente.ResponsavelDependenteId;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrajetoDependente {

    @EmbeddedId
    private TrajetoDependenteId id;

    @ManyToOne
    @MapsId("trajetoId")
    @JoinColumn(name = "trajeto_id")
    private Trajeto trajeto;

    @ManyToOne
    @MapsId("responsavelDependenteId")
    @JoinColumns({
            @JoinColumn(name = "responsavel_dependente_responsavel_id"),
            @JoinColumn(name = "responsavel_dependente_dependente_id")
    })
    private ResponsavelDependente responsavelDependente;

    @Embeddable
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TrajetoDependenteId {
        private Integer trajetoId;
        private ResponsavelDependenteId responsavelDependenteId;
    }
}
