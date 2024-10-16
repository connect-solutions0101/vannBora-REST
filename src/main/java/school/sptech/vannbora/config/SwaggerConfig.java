package school.sptech.vannbora.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customApi() {
        return new OpenAPI().info(new Info().title("Swagger Api").version("1.0.0")
                .license(new License().name("Licença do Sistema").url("www.connectsolutions.com.br"))
        );
    }
}
