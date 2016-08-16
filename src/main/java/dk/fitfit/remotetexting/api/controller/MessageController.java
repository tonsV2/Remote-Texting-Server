package dk.fitfit.remotetexting.api.controller;

import dk.fitfit.remotetexting.api.resource.MessageResource;
import dk.fitfit.remotetexting.api.resource.MessageResourceBuilder;
import dk.fitfit.remotetexting.api.resource.MessageResourceContainer;
import dk.fitfit.remotetexting.api.resource.assembler.MessageResourceAssembler;
import dk.fitfit.remotetexting.api.resource.assembler.MessageResourceContainerAssembler;
import dk.fitfit.remotetexting.business.domain.Message;
import dk.fitfit.remotetexting.business.domain.User;
import dk.fitfit.remotetexting.business.service.MessageServiceInterface;
import dk.fitfit.remotetexting.business.service.UserServiceInterface;
import dk.fitfit.remotetexting.util.GoogleAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@RequestMapping("/api")
public class MessageController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GoogleAuth googleAuth;

	@Autowired
	private UserServiceInterface userService;

	@Autowired
	private MessageServiceInterface messageService;

	@Autowired
	private MessageResourceAssembler messageResourceAssembler;

	@Autowired
	private MessageResourceContainerAssembler messageResourceContainerAssembler;

	// TODO: Ensure that a user only can get back it's own messages!!!
	@RequestMapping(value = "/phoneNumbers/{phoneNumberId}/messages", method = GET)
	public MessageResourceContainer getByPhoneNumber(@PathVariable long phoneNumberId) {
		Iterable<Message> messages = messageService.findBy(phoneNumberId);
		return messageResourceContainerAssembler.toResource(messages);
	}

	@RequestMapping(value = "/messages/prototype", method = GET)
	public MessageResource getPrototype() {
		return new MessageResourceBuilder().build();
	}

	@RequestMapping(value = "/messages/send", method = POST)
	public ResponseEntity<Void> sendMessage(@RequestBody MessageResource resource)  {
		return ResponseEntity.status(NOT_IMPLEMENTED).build();
	}

	@RequestMapping(value = "/messages", method = POST)
	public ResponseEntity<Void> postMessage(@RequestBody MessageResource resource, @RequestParam String idToken) throws GeneralSecurityException, IOException {
		if (googleAuth.getUserId(idToken) == null) {
			return ResponseEntity.status(UNAUTHORIZED).build();
		}
		User user = getUser(idToken);
		Message message = messageResourceAssembler.toEntity(resource);
		message.getFrom().setUser(user);
		messageService.save(message);
		return ResponseEntity.ok().build();
	}

	private User getUser(final String idToken) throws GeneralSecurityException, IOException {
		String userId = googleAuth.getUserId(idToken);
		User user = userService.findByUserId(userId);
		if (user == null) {
			String email = googleAuth.getEmail(idToken);
			user = userService.create(userId, email);
		}
		return user;
	}

	@RequestMapping(value = "/messages/{id}/sent", method = PUT)
	public ResponseEntity<Void> sent(@PathVariable long id, @RequestParam String idToken) {
		messageService.sent(id);
		return ResponseEntity.ok().build();
	}

// TODO: https://stackoverflow.com/questions/15609105/how-to-save-the-delivery-status-of-sms-in-default-sms-table-in-android
	@RequestMapping(value = "/messages/{id}/received", method = PUT)
	public ResponseEntity<Void> received(@PathVariable long id, @RequestParam String idToken) {
//		messageService.received(id);
		return ResponseEntity.ok().build();
	}

}
