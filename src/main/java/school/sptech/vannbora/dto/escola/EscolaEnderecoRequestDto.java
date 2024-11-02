package school.sptech.vannbora.dto.escola;

public record EscolaEnderecoRequestDto(
    String nome,
    String telefone,
    String nomeResponsavel,
    String telefoneResponsavel,
    Endereco endereco
) {
    public record Endereco(
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String cep
    ) {}
}
