package school.sptech.vannbora.entidade;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.vannbora.enums.Pago;

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
    private LocalDate dataVencimento;
    
    @Column
    private LocalDate dataPagamento;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Pago pago;

    @ManyToOne
    private ResponsavelDependente responsavelDependente;    
}
