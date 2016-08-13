package dk.fitfit.remotetexting.api.resource;


import org.springframework.hateoas.ResourceSupport;

import java.util.Set;


public class UserResource extends ResourceSupport {
	private String email;
	private Iterable<PhoneNumberResource> phoneNumbers;

	protected UserResource(final String email, final Iterable<PhoneNumberResource> phoneNumbers) {
		this.email = email;
		this.phoneNumbers = phoneNumbers;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public Iterable<PhoneNumberResource> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(final Set<PhoneNumberResource> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
}
