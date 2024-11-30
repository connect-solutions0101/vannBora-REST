package school.sptech.vannbora.mapper;

import school.sptech.vannbora.dto.dependente.DependenteResponsavelEnderecoFaturaRequestDto;
import school.sptech.vannbora.dto.fatura.FaturaRequestDto;
import school.sptech.vannbora.dto.fatura.FaturaResponseDto;
import school.sptech.vannbora.entidade.Fatura;

public class FaturaMapper {

    public static FaturaResponseDto toFaturaResponseDto(Fatura fatura){
        if(fatura == null){
            return null;
        }

        return FaturaResponseDto.builder()
                .id(fatura.getId())
                .valor(fatura.getValor())
                .diaPagamento(fatura.getDiaPagamento())
                .quantidadeParcelas(fatura.getQuantidadeParcelas())
                .responsavelDependenteId(
                        ResponsavelDependenteMapper.toResponseDto(fatura.getResponsavelDependente())
                )
                .build();
    }

    public static Fatura toFatura(FaturaRequestDto dto){
        if(dto == null){
            return null;
        }

        // Pago pago = dto.pago() ? Pago.PAGO : Pago.NAO_PAGO;

        return Fatura.builder()
                .valor(dto.valor())
                .diaPagamento(dto.diaPagamento())
                .quantidadeParcelas(dto.quantidadeParcelas())
                .build();
    }

    public static Fatura toFatura(DependenteResponsavelEnderecoFaturaRequestDto.Fatura fatura) {
        if(fatura == null){
            return null;
        }

        return Fatura.builder()
                .valor(fatura.valor())
                .diaPagamento(fatura.diaPagamento())
                .quantidadeParcelas(fatura.quantidadeParcelas())
                .build();
    }
}