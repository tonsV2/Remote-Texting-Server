package dk.fitfit.remotetexting.api.controller;

import dk.fitfit.remotetexting.api.resource.UserResource;
import dk.fitfit.remotetexting.business.domain.User;
import dk.fitfit.remotetexting.business.service.UserServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping("/api")
public class UserController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserServiceInterface userService;

	@RequestMapping(value = "/users/{id}", method = GET)
	public UserResource getOne(@PathVariable long id) {
		User user = userService.findOne(id);
		UserResource userResource = new UserResource();
		userResource.setEmail(user.getEmail());
		userResource.setPhoneNumbers(user.getPhoneNumbers());
		return userResource;
	}

	@RequestMapping(value = "/users", method = GET)
	public Iterable<UserResource> getAll() {
		Iterable<User> users = userService.findAll();
		List<UserResource> userResources = new ArrayList<>();
		for (User user : users) {
			UserResource userResource = new UserResource();
			userResource.setEmail(user.getEmail());
			userResource.setPhoneNumbers(user.getPhoneNumbers());
			userResources.add(userResource);
		}
		return userResources;
	}
}
