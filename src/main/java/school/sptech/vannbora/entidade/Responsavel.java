package school.sptech.vannbora.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Responsavel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;
    
    @Column
    private String telefone;

    @Column
    private String cpf;

    @Column
    private String parentesco;

    @Column
    private String telegram;

    @ManyToOne
    private Endereco endereco;
    
    @ManyToOne
    private ProprietarioServico proprietarioServico;
}
