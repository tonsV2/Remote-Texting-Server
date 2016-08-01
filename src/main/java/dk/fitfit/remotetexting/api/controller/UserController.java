package dk.fitfit.remotetexting.api.controller;

import dk.fitfit.remotetexting.api.resource.UserResource;
import dk.fitfit.remotetexting.business.domain.User;
import dk.fitfit.remotetexting.business.service.UserServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping("/api")
public class UserController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserServiceInterface userServiceInterface;

//	@RequestMapping(value = "/pages/{id}", method = GET)
//	public Page getPage(@PathVariable Long id) {
//		log.info("getPage({})", id);
//		return userServiceInterface.findOne(id);
//	}

	@RequestMapping(value = "/users", method = GET)
	public Iterable<UserResource> getAll() {
		log.info("getAll()");
		Iterable<User> users = userServiceInterface.findAll();
		ArrayList<UserResource> userResources = new ArrayList<>();
		for (User user : users) {
			UserResource userResource = new UserResource();
			userResource.setId(user.getId());
			userResource.setEmail(user.getEmail());
			userResource.setContacts(user.getContacts());
			userResources.add(userResource);
		}
		return userResources;
	}
}
