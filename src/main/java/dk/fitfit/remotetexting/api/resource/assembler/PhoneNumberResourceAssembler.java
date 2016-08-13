package dk.fitfit.remotetexting.api.resource.assembler;

import com.google.common.collect.Collections2;
import dk.fitfit.remotetexting.api.resource.PhoneNumberResource;
import dk.fitfit.remotetexting.api.resource.PhoneNumberResourceBuilder;
import dk.fitfit.remotetexting.business.domain.PhoneNumber;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;


@Component
public class PhoneNumberResourceAssembler implements ResourceAssembler<PhoneNumber, PhoneNumberResource> {
	@Override
	public PhoneNumberResource toResource(final PhoneNumber phoneNumber) {
		return new PhoneNumberResourceBuilder()
				.withNumber(phoneNumber.getNumber())
// TODO: Add contact assembler
//				.withContact(phoneNumber.getContact())
				.build();
	}

	public Collection<PhoneNumberResource> toResources(final Set<PhoneNumber> phoneNumbers) {
		return Collections2.transform(phoneNumbers, phoneNumber -> toResource(phoneNumber));
	}
}
