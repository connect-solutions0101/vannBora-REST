package school.sptech.vannbora;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VannboraApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(VannboraApplication.class);
        app.addListeners(new DotenvListener());
        app.run(args);
	}
}
