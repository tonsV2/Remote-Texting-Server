package dk.fitfit.remotetexting.api.resource;

import java.util.Collection;

public class UserResourceBuilder {
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

	public UserResource build() {
		return new UserResource(email, phoneNumbers);
	}
}
