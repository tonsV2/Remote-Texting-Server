package dk.fitfit.remotetexting.business.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableOAuth2Sso
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()   // TODO: enable CSRF
				.antMatcher("/**")
				.authorizeRequests()
				.antMatchers(HttpMethod.PUT, "/api/users/fcmToken").permitAll()
				.antMatchers("/api/messages").permitAll()
				// TODO: Hu? Why can't the pattern end with '*'?
				.antMatchers("/api/messages/*/msg").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/messages/*/sent").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/messages/*/received").permitAll()
				.antMatchers("/", "/login**", "/webjars/**").permitAll()
				.anyRequest()
				.authenticated();
	}
}
