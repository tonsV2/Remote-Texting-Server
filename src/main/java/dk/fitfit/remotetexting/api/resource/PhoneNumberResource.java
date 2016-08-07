package dk.fitfit.remotetexting.api.resource;

import org.springframework.hateoas.ResourceSupport;

public class PhoneNumberResource extends ResourceSupport {
	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(final String number) {
		this.number = number;
	}
}
