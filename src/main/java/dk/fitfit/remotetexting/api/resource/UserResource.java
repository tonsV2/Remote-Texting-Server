package dk.fitfit.remotetexting.api.resource;


import dk.fitfit.remotetexting.business.domain.PhoneNumber;

import java.util.Set;


public class UserResource {
	private String email;
	private Set<PhoneNumber> phoneNumbers;

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public Set<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(final Set<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
}
