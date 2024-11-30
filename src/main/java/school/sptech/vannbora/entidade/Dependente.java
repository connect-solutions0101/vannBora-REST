package school.sptech.vannbora.entidade;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.vannbora.enums.Turno;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dependente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private LocalDate dataNascimento;

    @Column
    private String turma;

    @Column
    @Enumerated(EnumType.STRING)
    private Turno turno;

    @Column
    private String condicao;

    @Column
    private LocalDate dataCadastro;

    @ManyToOne
    private Escola escola;

    @ManyToOne
    private ProprietarioServico proprietarioServico;

    @OneToMany(mappedBy = "dependente")
    private List<ResponsavelDependente> responsaveis;
    
}
