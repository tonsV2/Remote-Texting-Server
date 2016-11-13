package dk.fitfit.remotetexting.business.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@EnableOAuth2Sso
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()   // TODO: enable CSRF
									// Test that the below works...
									// See http://docs.spring.io/spring-security/site/docs/current/reference/html/csrf.html
//				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//				.and()
				.antMatcher("/**")
				.authorizeRequests()
				.antMatchers(HttpMethod.PUT, "/api/users/fcmToken").permitAll()
				.antMatchers("/api/messages").permitAll()
				// TODO: Hu? Why can't this pattern end with '*'?
				.antMatchers("/api/messages/*/msg").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/messages/*/sent/*").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/messages/*/delivered/*").permitAll()
				.antMatchers("/", "/login**", "/webjars/**").permitAll()
				.anyRequest()
				.authenticated();
	}
}
