package school.sptech.vannbora.mapper;

import school.sptech.vannbora.dto.fatura.FaturaRequestDto;
import school.sptech.vannbora.dto.fatura.FaturaResponseDto;
import school.sptech.vannbora.entidade.Fatura;
import school.sptech.vannbora.enums.Pago;

public class FaturaMapper {
    
    public static FaturaResponseDto toFaturaResponseDto(Fatura fatura){
        if(fatura == null){
            return null;
        }

        return FaturaResponseDto.builder()
                .id(fatura.getId())
                .valor(fatura.getValor())
                .pago(fatura.getPago())
                .dataVencimento(fatura.getDataVencimento())
                .dataPagamento(fatura.getDataPagamento())
                .responsavelDependenteId(
                        ResponsavelDependenteMapper.toResponseDto(fatura.getResponsavelDependente())
                )
                .build();
    }

    public static Fatura toFatura(FaturaRequestDto dto){
        if(dto == null){
            return null;
        }

        Pago pago = dto.pago() ? Pago.PAGO : Pago.NAO_PAGO;

        return Fatura.builder()
                .valor(dto.valor())
                .pago(pago)
                .dataVencimento(dto.dataVencimento())
                .build();
    }
}
