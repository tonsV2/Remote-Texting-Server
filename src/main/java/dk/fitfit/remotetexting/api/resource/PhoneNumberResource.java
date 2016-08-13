package dk.fitfit.remotetexting.api.resource;

import org.springframework.hateoas.ResourceSupport;

public class PhoneNumberResource extends ResourceSupport {
	private String number;
	private ContactResource contact;

	public String getNumber() {
		return number;
	}

	public void setNumber(final String number) {
		this.number = number;
	}

	public ContactResource getContact() {
		return contact;
	}

	public void setContact(final ContactResource contact) {
		this.contact = contact;
	}
}
