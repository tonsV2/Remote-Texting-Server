package dk.fitfit.remotetexting.api.controller;

import dk.fitfit.remotetexting.api.resource.ContactResource;
import dk.fitfit.remotetexting.business.domain.Contact;
import dk.fitfit.remotetexting.business.service.ContactServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping("/api")
public class ContactController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ContactServiceInterface contactService;

	@RequestMapping(value = "/contacts", method = GET)
	public List<ContactResource> getAll() {
		Iterable<Contact> contacts = contactService.findAll();
		ArrayList<ContactResource> resources = new ArrayList<>();
		for (Contact contact : contacts) {
			ContactResource resource = new ContactResource();
			resource.setName(contact.getName());
			resource.setUser(contact.getUser());
			resource.setPhoneNumbers(contact.getPhoneNumbers());
			resources.add(resource);
		}
		return resources;
	}
}
