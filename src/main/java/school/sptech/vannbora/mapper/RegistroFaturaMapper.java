package school.sptech.vannbora.mapper;

import school.sptech.vannbora.dto.registroFatura.RegistroFaturaRequestDto;
import school.sptech.vannbora.dto.registroFatura.RegistroFaturaResponseDto;
import school.sptech.vannbora.entidade.RegistroFatura;
import school.sptech.vannbora.enums.Pago;

public class RegistroFaturaMapper {
    
        public static RegistroFaturaResponseDto toRegistroFaturaResponseDto(RegistroFatura registroFatura){
            if(registroFatura == null){
                return null;
            }
    
            return RegistroFaturaResponseDto.builder()
                    .id(registroFatura.getId())
                    .pago(registroFatura.getPago() == Pago.PAGO)
                    .dataPagamento(registroFatura.getDataPagamento())
                    .build();
        }
    
        public static RegistroFatura toRegistroFatura(RegistroFaturaRequestDto dto){
            if(dto == null){
                return null;
            }
    
            return RegistroFatura.builder()
                    .pago(dto.pago() ? Pago.PAGO : Pago.NAO_PAGO)
                    .dataPagamento(dto.dataPagamento())
                    .build();
        }
}
