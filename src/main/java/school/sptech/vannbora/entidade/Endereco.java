package school.sptech.vannbora.entidade;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String cep;

    @Column
    private String logradouro;

    @Column
    private String bairro;

    @Column
    private String cidade;

    @Column
    private String pontoReferencia;

    @Column
    private String numero;

    public Endereco(String cep, String logradouro, String bairro, String cidade, String pontoReferencia, String numero) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.pontoReferencia = pontoReferencia;
        this.numero = numero;
    }
}
