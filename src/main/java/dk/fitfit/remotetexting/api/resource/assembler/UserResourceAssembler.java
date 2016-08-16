package dk.fitfit.remotetexting.api.resource.assembler;


import dk.fitfit.remotetexting.api.resource.PhoneNumberResource;
import dk.fitfit.remotetexting.api.resource.UserResource;
import dk.fitfit.remotetexting.api.resource.UserResourceBuilder;
import dk.fitfit.remotetexting.business.domain.PhoneNumber;
import dk.fitfit.remotetexting.business.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;


@Component
public class UserResourceAssembler implements ResourceAssembler<User, UserResource> {
	@Autowired
	private PhoneNumberResourceAssembler phoneNumberResourceAssembler;

	@Override
	public UserResource toResource(final User user) {
		if (user == null) {
			return null;
		}
		Set<PhoneNumber> phoneNumbers = user.getPhoneNumbers();
		Collection<PhoneNumberResource> phoneNumberResources = phoneNumberResourceAssembler.toResources(phoneNumbers);
		return new UserResourceBuilder()
				.withEmail(user.getEmail())
				.withPhoneNumbers(phoneNumberResources)
				.build();
	}

}
