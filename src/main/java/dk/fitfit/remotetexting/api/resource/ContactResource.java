package dk.fitfit.remotetexting.api.resource;


import dk.fitfit.remotetexting.business.domain.Message;
import dk.fitfit.remotetexting.business.domain.PhoneNumber;
import dk.fitfit.remotetexting.business.domain.User;
import org.springframework.hateoas.ResourceSupport;

import java.util.HashSet;
import java.util.Set;

public class ContactResource extends ResourceSupport {
	private String name;
	private User user;
	private Set<PhoneNumber> phoneNumbers = new HashSet<>();
	private Set<Message> messages = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public Set<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(final Set<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(final Set<Message> messages) {
		this.messages = messages;
	}
}
