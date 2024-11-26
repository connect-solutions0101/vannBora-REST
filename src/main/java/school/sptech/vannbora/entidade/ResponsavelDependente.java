package school.sptech.vannbora.entidade;

import java.util.List;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.vannbora.enums.TipoResponsavel;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponsavelDependente {
    
    @EmbeddedId
    private ResponsavelDependenteId id;

    @ManyToOne
    @MapsId("responsavelId")
    @JoinColumn(name = "responsavel_id")
    private Responsavel responsavel;

    @ManyToOne
    @MapsId("dependenteId")
    @JoinColumn(name = "dependente_id")
    private Dependente dependente;

    @Enumerated(EnumType.STRING)
    private TipoResponsavel tipoResponsavel;

    @OneToMany(mappedBy = "responsavelDependente")
    private List<Fatura> fatura;

    @Embeddable
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ResponsavelDependenteId {
        private int responsavelId;
        private int dependenteId;
    }
}
