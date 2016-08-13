package dk.fitfit.remotetexting.api.resource.assembler;

import com.google.common.collect.Collections2;
import dk.fitfit.remotetexting.api.resource.ContactResource;
import dk.fitfit.remotetexting.api.resource.PhoneNumberResource;
import dk.fitfit.remotetexting.api.resource.PhoneNumberResourceBuilder;
import dk.fitfit.remotetexting.business.domain.Contact;
import dk.fitfit.remotetexting.business.domain.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;


@Component
public class PhoneNumberResourceAssembler implements ResourceAssembler<PhoneNumber, PhoneNumberResource> {
	@Autowired
	private ContactResourceAssembler contactResourceAssembler;

	@Autowired
	PhoneNumberResourceLinksAssembler linksAssembler;

	@Override
	public PhoneNumberResource toResource(final PhoneNumber phoneNumber) {
		Contact contact = phoneNumber.getContact();
		ContactResource contactResource = contactResourceAssembler.toResource(contact);
		return new PhoneNumberResourceBuilder()
				.withNumber(phoneNumber.getNumber())
				.withContact(contactResource)
				.withLinks(linksAssembler.getLinks(phoneNumber))
				.build();
	}

	public Collection<PhoneNumberResource> toResources(final Set<PhoneNumber> phoneNumbers) {
		return Collections2.transform(phoneNumbers, this::toResource);
	}
}
