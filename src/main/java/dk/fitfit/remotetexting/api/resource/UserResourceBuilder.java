package dk.fitfit.remotetexting.api.resource;

import org.apache.commons.lang3.builder.Builder;

import java.util.Collection;


public class UserResourceBuilder implements Builder<UserResource> {
	private String email;
	private Collection<PhoneNumberResource> phoneNumbers;

	public UserResourceBuilder withEmail(final String email) {
		this.email = email;
		return this;
	}

	public UserResourceBuilder withPhoneNumbers(final Collection<PhoneNumberResource> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
		return this;
	}

	@Override
	public UserResource build() {
		return new UserResource(email, phoneNumbers);
	}
}
