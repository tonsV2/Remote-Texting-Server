package dk.fitfit.remotetexting.api.controller;

import dk.fitfit.remotetexting.api.resource.*;
import dk.fitfit.remotetexting.api.resource.assembler.MessageResourceAssembler;
import dk.fitfit.remotetexting.api.resource.assembler.MessageResourceContainerAssembler;
import dk.fitfit.remotetexting.business.domain.Message;
import dk.fitfit.remotetexting.business.domain.PhoneNumber;
import dk.fitfit.remotetexting.business.domain.User;
import dk.fitfit.remotetexting.business.service.MessageServiceInterface;
import dk.fitfit.remotetexting.business.service.UserServiceInterface;
import dk.fitfit.remotetexting.util.CurrentUserHolder;
import dk.fitfit.remotetexting.util.GoogleAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@RequestMapping("/api")
public class MessageController {
	@Autowired
	private CurrentUserHolder currentUserHolder;

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
//	@RequestMapping(value = "/messages/{phoneNumberId}", method = GET)
// The above request mapping clashes with the mapping of getById
	public MessageResourceContainer getByPhoneNumber(@PathVariable long phoneNumberId) {
		User user = currentUserHolder.getUser();
		Iterable<Message> messages = messageService.findBy(user, phoneNumberId);
		return messageResourceContainerAssembler.toResource(messages);
	}

	// TODO: Ensure that a user only can get back it's own messages!!!
	@RequestMapping(value = "/messages/{id}/msg", method = GET)
	public ResponseEntity<?> getById(@PathVariable long id, @RequestParam String idToken) throws GeneralSecurityException, IOException {
		if (googleAuth.getUserId(idToken) == null) {
			return ResponseEntity.status(UNAUTHORIZED).build();
		}

		User user = getUser(idToken);
		Message message = messageService.findOne(user, id);
		MessageResource messageResource = messageResourceAssembler.toResource(message);
		return new ResponseEntity<>(messageResource, HttpStatus.OK);
	}

	@RequestMapping(value = "/messages/prototype", method = GET)
	public MessageResource getPrototype() {
		PhoneNumberResource phoneNumberResource = new PhoneNumberResourceBuilder().build();
		return new MessageResourceBuilder().withTo(phoneNumberResource).build();
	}

	@RequestMapping(value = "/messages/send", method = POST)
	public ResponseEntity<Void> sendMessage(@RequestBody MessageResource resource) throws Exception {
		// TODO: Use assembler
		String phoneNumber = resource.getTo().getNumber();
		String content = resource.getContent();

		Message message = new Message();
		message.setTo(new PhoneNumber(phoneNumber));
		message.setContent(content);

		messageService.send(currentUserHolder.getUser(), message);
		return ResponseEntity.ok().build();
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

	// TODO: Move this into CurrentUserHolder
	private User getUser(final String idToken) throws GeneralSecurityException, IOException {
		String userId = googleAuth.getUserId(idToken);
		User user = userService.findByUserId(userId);
		if (user == null) {
			String email = googleAuth.getEmail(idToken);
			user = userService.create(userId, email);
		}
		return user;
	}

	// TODO: Move this to the UserController
	@RequestMapping(value = "/users/fcmToken", method = PUT)
	public ResponseEntity<Void> putFcmToken(@RequestParam String fcmToken, @RequestParam String idToken) throws GeneralSecurityException, IOException {
		if (googleAuth.getUserId(idToken) == null) {
			return ResponseEntity.status(UNAUTHORIZED).build();
		}
		User user = getUser(idToken);
		user.setFcmRegId(fcmToken);
		userService.save(user);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/messages/{id}/sent/{ts}", method = PUT)
	public ResponseEntity<Void> sent(@PathVariable long id, @PathVariable long ts, @RequestParam String idToken) {
		messageService.sent(id, ts);
		return ResponseEntity.ok().build();
	}

// TODO: https://stackoverflow.com/questions/15609105/how-to-save-the-delivery-status-of-sms-in-default-sms-table-in-android
	@RequestMapping(value = "/messages/{id}/delivered/{ts}", method = PUT)
	public ResponseEntity<Void> delivered(@PathVariable long id, @PathVariable long ts, @RequestParam String idToken) {
		messageService.delivered(id, ts);
		return ResponseEntity.ok().build();
	}
}
