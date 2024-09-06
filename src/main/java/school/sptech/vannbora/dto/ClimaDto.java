package school.sptech.vannbora.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClimaDto {  
    private int temp;
    private String city;
    private String description;
    private String currently;
    private int humidity;
}
