package dk.fitfit.remotetexting.api.resource;


import org.springframework.hateoas.ResourceSupport;

import java.util.Collection;

public class ContactResource extends ResourceSupport {
	private String name;
	private Collection<PhoneNumberResource> phoneNumbers;

	protected ContactResource(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Collection<PhoneNumberResource> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(final Collection<PhoneNumberResource> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
}
