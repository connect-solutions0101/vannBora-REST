package school.sptech.vannbora.dto.dependente;

import java.util.List;

public record DependenteResponsavelEnderecoFaturaRequestDto(
    Integer id,
    String nome,
    String dataNascimento,
    String turno,
    String condicao,
    String turma,
    Integer escolaId,
    List<ResponsavelDependente> responsaveis,
    Fatura fatura
) {
    public record ResponsavelDependente(
        Integer dependenteId,
        Integer responsavelId,
        String tipoResponsavel,
        Responsavel responsavel
    ){}
    public record Responsavel(
        Integer id, 
        String nome,
        String parentesco,
        String cpf,
        String telefone,
        Endereco endereco
    ) {
        public record Endereco(
            Integer id,
            String cep,
            String logradouro,
            String numero,
            String bairro,
            String cidade,
            String pontoReferencia
        ){}
    }

    public record Fatura(
        Integer id,
        Double valor,
        Integer diaPagamento,
        Integer quantidadeParcelas,
        String pago
    ) {}
}