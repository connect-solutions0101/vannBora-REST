package school.sptech.vannbora.dto.escola;

public record EscolaEditRequestDto(
        String nome,
        String nomeResponsavel,
        String telefone,
        String telefoneResponsavel,
        Endereco endereco
) {
    public record Endereco(
        int id,
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String cep
    ) {}
} 
