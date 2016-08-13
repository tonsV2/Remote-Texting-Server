package dk.fitfit.remotetexting.api.resource;


import dk.fitfit.remotetexting.business.domain.PhoneNumber;
import org.springframework.hateoas.ResourceSupport;

import java.util.HashSet;
import java.util.Set;

public class ContactResource extends ResourceSupport {
	private String name;
	private Set<PhoneNumber> phoneNumbers = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Set<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(final Set<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
}
