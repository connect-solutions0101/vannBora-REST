package school.sptech.vannbora.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClimaApiExternaDto {
    
    @JsonProperty("results")
    private Results results;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Results {

        @JsonProperty("temp")
        private int temp;

        @JsonProperty("city_name")
        private String city;

        @JsonProperty("description")
        private String description;

        @JsonProperty("currently")
        private String currently;

        @JsonProperty("humidity")
        private int humidity;

    }

}
