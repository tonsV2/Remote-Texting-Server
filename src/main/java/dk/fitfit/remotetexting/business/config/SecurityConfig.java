package dk.fitfit.remotetexting.business.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableOAuth2Sso
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()   // TODO: enable CSRF
				.antMatcher("/**")
				.authorizeRequests()
				.antMatchers("/api/messages").permitAll()
				.antMatchers("/api/messages/*/sent").permitAll()
				.antMatchers("/api/messages/*/received").permitAll()
				.antMatchers("/", "/login**", "/webjars/**").permitAll()
				.anyRequest()
				.authenticated();
	}
}
