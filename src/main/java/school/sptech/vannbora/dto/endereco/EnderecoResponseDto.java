package school.sptech.vannbora.dto.endereco;

public record EnderecoResponseDto(
        int id,
        String cep,
        String logradouro,
        String bairro,
        String cidade,
        String pontoReferencia,
        String numero
) {
}
