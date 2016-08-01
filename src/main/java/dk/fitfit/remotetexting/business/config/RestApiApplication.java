package dk.fitfit.remotetexting.business.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;


@SpringBootApplication(scanBasePackages = "dk.fitfit.remotetexting")
@EntityScan(basePackages = "dk.fitfit.remotetexting.business.domain")
public class RestApiApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RestApiApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}
}
