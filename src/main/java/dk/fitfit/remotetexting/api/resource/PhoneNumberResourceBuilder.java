package dk.fitfit.remotetexting.api.resource;

import org.apache.commons.lang3.builder.Builder;
import org.springframework.hateoas.Link;

import java.util.List;


public class PhoneNumberResourceBuilder implements Builder<PhoneNumberResource> {
	private String number;
	private ContactResource contact;
	private List<Link> links;

	public PhoneNumberResourceBuilder withNumber(final String number) {
		this.number = number;
		return this;
	}

	public PhoneNumberResourceBuilder withContact(final ContactResource contact) {
		this.contact = contact;
		return this;
	}

	public PhoneNumberResourceBuilder withLinks(final List<Link> links) {
		this.links = links;
		return this;
	}

	@Override
	public PhoneNumberResource build() {
		PhoneNumberResource phoneNumberResource = new PhoneNumberResource(number);
		if(contact != null) {
			phoneNumberResource.setContact(contact);
		}
		if(links != null) {
			phoneNumberResource.add(links);
		}
		return phoneNumberResource;
	}
}
