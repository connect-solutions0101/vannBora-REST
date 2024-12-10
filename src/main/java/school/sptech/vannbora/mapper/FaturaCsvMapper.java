package school.sptech.vannbora.mapper;

import school.sptech.vannbora.dto.fatura.FaturaCsvDto;
import school.sptech.vannbora.entidade.RegistroFatura;

public class FaturaCsvMapper {
    public static FaturaCsvDto toCsvDto(RegistroFatura registroFatura) {
        return new FaturaCsvDto(
                
                registroFatura.getFatura().getResponsavelDependente().getResponsavel().getNome(),
                registroFatura.getFatura().getResponsavelDependente().getDependente().getNome(),
                registroFatura.getFatura().getResponsavelDependente().getResponsavel().getParentesco(),
                registroFatura.getFatura().getDiaPagamento(),
                registroFatura.getFatura().getValor(),
                registroFatura.getPago()
        );
    }
}