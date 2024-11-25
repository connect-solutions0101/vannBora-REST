package school.sptech.vannbora;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VannboraApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(VannboraApplication.class);
        app.addListeners(new DotenvListener());
        app.run(args);
	}
}
