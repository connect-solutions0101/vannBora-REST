package school.sptech.vannbora.entidade;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private Double valor;
    
    @Column
    private Integer diaPagamento;

    @Column
    private Integer quantidadeParcelas;

    @OneToMany(mappedBy = "fatura")
    private List<RegistroFatura> registroFatura;

    @ManyToOne
    private ResponsavelDependente responsavelDependente;    
}
