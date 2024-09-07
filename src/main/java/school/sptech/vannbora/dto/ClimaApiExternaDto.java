package school.sptech.vannbora.dto;

import java.util.List;

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

        @JsonProperty
        private List<Forecast> forecast;

        @AllArgsConstructor
        @NoArgsConstructor
        @Getter
        @Setter
        public static class Forecast {

            @JsonProperty("date")
            private String date;

            @JsonProperty("weekday")
            private String weekday;

            @JsonProperty("max")
            private int max;

            @JsonProperty("min")
            private int min;

            @JsonProperty("humidity")
            private int humidity;

            @JsonProperty("description")
            private String description;

        }
    }

}
