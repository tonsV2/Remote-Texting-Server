package dk.fitfit.remotetexting.api.resource;

import org.apache.commons.lang3.builder.Builder;

import java.util.Collection;


public class ContactResourceBuilder implements Builder<ContactResource> {
	private String name;
	private Collection<PhoneNumberResource> phoneNumbers;

	public ContactResourceBuilder withName(final String name) {
		this.name = name;
		return this;
	}

	public ContactResourceBuilder withPhoneNumbers(final Collection<PhoneNumberResource> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
		return this;
	}

	@Override
	public ContactResource build() {
		ContactResource contactResource = new ContactResource(name);
		if(phoneNumbers != null) {
			contactResource.setPhoneNumbers(phoneNumbers);
		}
		return contactResource;
	}
}
