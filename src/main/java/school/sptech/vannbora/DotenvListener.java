package school.sptech.vannbora;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Properties;

public class DotenvListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent>{
    
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        Dotenv dotenv = Dotenv.load(); // Carrega o arquivo .env
        Properties properties = new Properties();
        dotenv.entries().forEach(entry -> properties.put(entry.getKey(), entry.getValue()));
        
        ConfigurableEnvironment environment = event.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        propertySources.addFirst(new PropertiesPropertySource("dotenvProperties", properties));
    }
}
