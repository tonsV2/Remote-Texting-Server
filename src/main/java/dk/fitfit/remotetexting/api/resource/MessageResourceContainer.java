package dk.fitfit.remotetexting.api.resource;

import org.springframework.hateoas.ResourceSupport;

import java.util.Iterator;


public class MessageResourceContainer extends ResourceSupport {
	private Iterator<MessageResource> messages;

	protected MessageResourceContainer(final Iterator<MessageResource> messages) {
		this.messages = messages;
	}

	public Iterator<MessageResource> getMessages() {
		return messages;
	}

	public void setMessages(final Iterator<MessageResource> messages) {
		this.messages = messages;
	}
}
