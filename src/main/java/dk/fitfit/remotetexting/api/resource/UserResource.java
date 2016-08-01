package dk.fitfit.remotetexting.api.resource;


import dk.fitfit.remotetexting.business.domain.Contact;

import java.util.Set;

public class UserResource {
	private Long id;
	private String email;
	private Set<Contact> contacts;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(final Set<Contact> contacts) {
		this.contacts = contacts;
	}
}
