package dk.fitfit.remotetexting.api.controller;

import dk.fitfit.remotetexting.api.resource.UserResource;
import dk.fitfit.remotetexting.api.resource.assembler.UserResourceAssembler;
import dk.fitfit.remotetexting.business.domain.User;
import dk.fitfit.remotetexting.business.service.UserServiceInterface;
import dk.fitfit.remotetexting.util.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping("/api")
public class UserController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CurrentUser currentUser;

	@Autowired
	private UserServiceInterface userService;

	@Autowired
	private UserResourceAssembler userResourceAssembler;

	@RequestMapping(value = "/users", method = GET)
	public UserResource getUser() {
		String sub = currentUser.getSub();
		User user = userService.findByUserId(sub);
		if (user == null) {
			String email = currentUser.getEmail();
			userService.create(sub, email);
		}
		return userResourceAssembler.toResource(user);
	}

}
