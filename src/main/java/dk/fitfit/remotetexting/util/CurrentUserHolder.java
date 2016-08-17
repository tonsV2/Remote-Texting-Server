package dk.fitfit.remotetexting.util;

import dk.fitfit.remotetexting.business.domain.User;
import dk.fitfit.remotetexting.business.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class CurrentUserHolder {
	@Autowired
	private UserServiceInterface userService;

	public User getUser() {
		String sub = getSub();
		User user = userService.findByUserId(sub);
		if (user == null) {
			String email = getEmail();
			user = userService.create(sub, email);
		}
		return user;
	}

	private String getSub() {
		return (String) getUserDetails().get("sub");
	}

	private String getEmail() {
		return (String) getUserDetails().get("email");
	}

	private Map getUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
		Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
		return (Map) userAuthentication.getDetails();
	}

}
