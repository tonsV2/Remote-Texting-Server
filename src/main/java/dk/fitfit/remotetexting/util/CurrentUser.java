package dk.fitfit.remotetexting.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class CurrentUser {

	public String getSub() {
		return (String) getUserDetails().get("sub");
	}

	public String getEmail() {
		return (String) getUserDetails().get("email");
	}

	// TODO: Use @PostConstruct rather than calling getUserDetails in each method
	private Map getUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
		Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
		return (Map) userAuthentication.getDetails();
	}

}
