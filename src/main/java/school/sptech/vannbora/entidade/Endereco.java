package school.sptech.vannbora.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotBlank
    private String cep;

    @Column
    @NotBlank
    private String logradouro;

    @Column
    @NotBlank
    private String numero;

    @Column
    private String complemento;

    @Column
    @NotBlank
    private String bairro;

    @Column
    @NotBlank
    private String localidade;

    @Column
    @NotBlank
    private String uf;
}
