package school.sptech.vannbora.entidade;

import jakarta.persistence.*;
import lombok.*;
import school.sptech.vannbora.enums.UfEnum;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String cep;

    @Column
    private String logradouro;

    @Column
    private String numero;

    @Column
    private String cidade;

    @Column
    private UfEnum uf;

    public Endereco(String cep, String logradouro, String numero, String cidade, UfEnum uf) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.uf = uf;
    }
}
