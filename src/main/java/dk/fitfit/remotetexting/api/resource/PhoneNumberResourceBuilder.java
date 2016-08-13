package dk.fitfit.remotetexting.api.resource;

public class PhoneNumberResourceBuilder {
	private String number;
	private ContactResource contact;

	public PhoneNumberResourceBuilder withNumber(final String number) {
		this.number = number;
		return this;
	}

	public PhoneNumberResourceBuilder withContact(final ContactResource contact) {
		this.contact = contact;
		return this;
	}

	public PhoneNumberResource build() {
		PhoneNumberResource phoneNumberResource = new PhoneNumberResource(number);
		if(contact != null) {
			phoneNumberResource.setContact(contact);
		}
		return phoneNumberResource;
	}
}
