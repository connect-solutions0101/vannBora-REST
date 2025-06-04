package school.sptech.vannbora.entidade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrajetoDependente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "trajeto_id")
    private Trajeto trajeto;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "responsavel_dependente_responsavel_id", referencedColumnName = "responsavel_id"),
            @JoinColumn(name = "responsavel_dependente_dependente_id", referencedColumnName = "dependente_id")
    })
    private ResponsavelDependente responsavelDependente;

    @Column()
    private Integer ordem;
}
