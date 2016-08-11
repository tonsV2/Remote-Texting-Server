package dk.fitfit.remotetexting.api.controller;

import dk.fitfit.remotetexting.api.resource.MessageResource;
import dk.fitfit.remotetexting.api.resource.PhoneNumberResource;
import dk.fitfit.remotetexting.business.domain.Message;
import dk.fitfit.remotetexting.business.domain.PhoneNumber;
import dk.fitfit.remotetexting.business.domain.User;
import dk.fitfit.remotetexting.business.service.MessageServiceInterface;
import dk.fitfit.remotetexting.business.service.UserServiceInterface;
import dk.fitfit.remotetexting.util.GoogleAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@RequestMapping("/api")
public class MessageController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserServiceInterface userService;

	@Autowired
	private MessageServiceInterface messageService;

	@RequestMapping(value = "/messages", method = POST)
	public ResponseEntity<Void> postMessage(@RequestBody MessageResource resource, @RequestParam String idToken) throws GeneralSecurityException, IOException {
		if (GoogleAuth.getUserId(idToken) == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		User user = getUser(idToken);
		Message message = new Message();
		PhoneNumberResource phoneNumberResource = resource.getFrom();
		PhoneNumber phoneNumber = new PhoneNumber();
		phoneNumber.setNumber(phoneNumberResource.getNumber());
		phoneNumber.setUser(user);
		message.setFrom(phoneNumber);
		message.setContent(resource.getContent());
		message.setTimestampProvider(resource.getTimestampProvider());
		message.setTimestampReceived(resource.getTimestampReceived());
		messageService.save(message);
		return ResponseEntity.ok().build();
	}

	private User getUser(final String idToken) throws GeneralSecurityException, IOException {
		String userId = GoogleAuth.getUserId(idToken);
		User user = userService.findByUserId(userId);
		if (user == null) {
			String email = GoogleAuth.getEmail(idToken);
			user = userService.create(userId, email);
		}
		return user;
	}

	@RequestMapping(value = "/messages", method = GET)
	public List<MessageResource> getAll() {
		Iterable<Message> messages = messageService.findAll();
		List<MessageResource> resources = new ArrayList<>();
		for (Message message : messages) {
			MessageResource resource = new MessageResource();
			PhoneNumber from = message.getFrom();
			PhoneNumberResource fromResource = new PhoneNumberResource();
			fromResource.setNumber(from.getNumber());
			resource.setFrom(fromResource);
			resource.setContent(message.getContent());
			resource.setTimestampProvider(message.getTimestampProvider());
			resource.setTimestampReceived(message.getTimestampReceived());
			resources.add(resource);
		}
		return resources;
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
