package school.sptech.vannbora.entidade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.vannbora.enums.Periodo;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trajeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Enumerated(EnumType.STRING)
    private Periodo periodo;

    @OneToMany(mappedBy = "trajeto")
    private List<TrajetoDependente> trajetoDependentes;

    @ManyToOne
    private ProprietarioServico proprietarioServico;
}