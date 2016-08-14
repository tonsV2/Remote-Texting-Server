package dk.fitfit.remotetexting.business.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan({"dk.fitfit.remotetexting.api"})
public class ResourceWebConfig extends WebMvcConfigurerAdapter {
}
