package dk.fitfit.remotetexting.api.controller;

import dk.fitfit.remotetexting.api.resource.UserResource;
import dk.fitfit.remotetexting.api.resource.assembler.UserResourceAssembler;
import dk.fitfit.remotetexting.business.domain.User;
import dk.fitfit.remotetexting.business.service.UserServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping("/api")
public class UserController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserServiceInterface userService;

	@Autowired
	private UserResourceAssembler userResourceAssembler;

	@RequestMapping(value = "/users/{id}", method = GET)
	public UserResource getOne(Principal principal, @PathVariable long id) {
		User user = userService.findOne(id);
		return userResourceAssembler.toResource(user);
	}

	@RequestMapping("/user")
	public Map user(Principal principal) {
		Map userDetails = getUserDetails(principal);
		String sub = (String) userDetails.get("sub");
		User user = userService.findByUserId(sub);
		if (user == null) {
			String email = (String) userDetails.get("email");
			userService.create(sub, email);
		}
		return userDetails;
	}

	private Map getUserDetails(final Principal principal) {
		OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
		Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
		return (HashMap) userAuthentication.getDetails();
	}

}
