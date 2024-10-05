package school.sptech.vannbora.mapper;

import org.springframework.stereotype.Component;
import school.sptech.vannbora.dto.endereco.EnderecoRequestDto;
import school.sptech.vannbora.dto.endereco.EnderecoResponseDto;
import school.sptech.vannbora.entidade.Endereco;

@Component
public class EnderecoMapper {
    public final EnderecoResponseDto toEnderecoResponseDto(Endereco endereco){
        if(endereco == null){
            return null;
        }

        return new EnderecoResponseDto(
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getUf()
        );
    }

    public final Endereco toEndereco(EnderecoRequestDto dto){
        if(dto == null){
            return null;
        }

        return new Endereco(
                dto.cep(),
                dto.logradouro(),
                dto.numero(),
                dto.cidade(),
                dto.uf()
        );
    }

    public final Endereco toEnderecoAtualizar(int id, EnderecoRequestDto dto){
        if(dto == null){
            return null;
        }

        return new Endereco(
                id,
                dto.cep(),
                dto.logradouro(),
                dto.numero(),
                dto.cidade(),
                dto.uf()
        );
    }
}
