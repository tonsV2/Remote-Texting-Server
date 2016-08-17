package dk.fitfit.remotetexting.api.controller;

import dk.fitfit.remotetexting.api.resource.UserResource;
import dk.fitfit.remotetexting.api.resource.assembler.UserResourceAssembler;
import dk.fitfit.remotetexting.business.domain.User;
import dk.fitfit.remotetexting.util.CurrentUserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private CurrentUserHolder currentUserHolder;

	@Autowired
	private UserResourceAssembler userResourceAssembler;

	@RequestMapping(value = "/users", method = GET)
	public UserResource getUser() {
		User user = currentUserHolder.getUser();
		return userResourceAssembler.toResource(user);
	}
}
