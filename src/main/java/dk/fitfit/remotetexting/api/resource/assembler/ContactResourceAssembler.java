package dk.fitfit.remotetexting.api.resource.assembler;

import dk.fitfit.remotetexting.api.resource.ContactResource;
import dk.fitfit.remotetexting.api.resource.ContactResourceBuilder;
import dk.fitfit.remotetexting.business.domain.Contact;
import dk.fitfit.remotetexting.business.domain.PhoneNumber;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class ContactResourceAssembler implements ResourceAssembler<Contact, ContactResource> {
//	@Autowired
//	private PhoneNumberResourceAssembler phoneNumberResourceAssembler;

	@Override
	public ContactResource toResource(final Contact contact) {
		if(contact == null) {
			return null;
		}
		Set<PhoneNumber> phoneNumbers = contact.getPhoneNumbers();
//		Collection<PhoneNumberResource> phoneNumberResources = phoneNumberResourceAssembler.toResources(phoneNumbers);
		return new ContactResourceBuilder()
				.withName(contact.getName())
//				.withPhoneNumbers(phoneNumberResources)
				.build();
	}
}
