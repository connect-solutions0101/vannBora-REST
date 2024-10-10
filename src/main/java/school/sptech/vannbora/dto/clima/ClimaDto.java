package school.sptech.vannbora.dto.clima;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.sptech.vannbora.dto.clima.ClimaApiExternaDto.Results.Forecast;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClimaDto {  
    private int temp;
    private String city;
    private String description;
    private String currently;
    private int humidity;
    private List<Forecast> forecast;
}
