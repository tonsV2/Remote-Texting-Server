package dk.fitfit.remotetexting.api.controller;

import dk.fitfit.remotetexting.api.resource.ContactResource;
import dk.fitfit.remotetexting.api.resource.PhoneNumberResource;
import dk.fitfit.remotetexting.business.domain.Contact;
import dk.fitfit.remotetexting.business.domain.PhoneNumber;
import dk.fitfit.remotetexting.business.service.PhoneNumberServiceInterface;
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
public class PhoneNumberController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PhoneNumberServiceInterface phoneNumberService;

	@RequestMapping(value = "/users/{userId}/phoneNumbers", method = GET)
	public Iterable<PhoneNumberResource> getPhoneNumbers(@PathVariable long userId) {
		Iterable<PhoneNumber> phoneNumbers = phoneNumberService.findByUser(userId);
		List<PhoneNumberResource> phoneNumberResources = new ArrayList<>();
		for (PhoneNumber phoneNumber : phoneNumbers) {
			PhoneNumberResource phoneNumberResource = new PhoneNumberResource();
			phoneNumberResource.setNumber(phoneNumber.getNumber());
			Contact contact = phoneNumber.getContact();
			if(contact != null) {
				ContactResource contactResource = new ContactResource();
				contactResource.setName(contact.getName());
				phoneNumberResource.setContact(contactResource);
			}
			phoneNumberResources.add(phoneNumberResource);
		}
		return phoneNumberResources;
	}
}
