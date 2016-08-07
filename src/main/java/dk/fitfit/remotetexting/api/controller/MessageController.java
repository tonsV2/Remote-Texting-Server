package dk.fitfit.remotetexting.api.controller;

import dk.fitfit.remotetexting.api.resource.MessageResource;
import dk.fitfit.remotetexting.api.resource.PhoneNumberResource;
import dk.fitfit.remotetexting.business.domain.Message;
import dk.fitfit.remotetexting.business.domain.PhoneNumber;
import dk.fitfit.remotetexting.business.service.MessageServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping("/api")
public class MessageController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MessageServiceInterface messageService;

	@RequestMapping(value = "/messages", method = POST)
	public ResponseEntity<Void> postMessage(@RequestBody MessageResource resource, @RequestParam String idToken) {
		log.info("postMessage()");
		Message message = new Message();
		PhoneNumberResource fromResource = resource.getFrom();
		PhoneNumber from = new PhoneNumber();
		from.setNumber(fromResource.getNumber());
		message.setFrom(from);
		message.setContent(resource.getContent());
		message.setTimestampProvider(resource.getTimestampProvider());
		message.setTimestampReceived(resource.getTimestampReceived());
		messageService.save(message);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/messages", method = GET)
	public List<MessageResource> getAll() {
		log.info("getAll()");
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

}
