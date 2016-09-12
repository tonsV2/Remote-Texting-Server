package dk.fitfit.remotetexting.api.resource;

import org.apache.commons.lang3.builder.Builder;
import org.springframework.hateoas.Link;

import java.util.Iterator;
import java.util.List;


public class MessageResourceContainerBuilder implements Builder<MessageResourceContainer> {
	private Iterator<MessageResource> messages;
	private List<Link> links;

	public MessageResourceContainerBuilder withMessages(final Iterator<MessageResource> messages) {
		this.messages = messages;
		return this;
	}

	public MessageResourceContainerBuilder withLinks(final List<Link> links) {
		this.links = links;
		return this;
	}

	@Override
	public MessageResourceContainer build() {
		MessageResourceContainer messageResourceContainer = new MessageResourceContainer(messages);
		if(links != null) {
			messageResourceContainer.add(links);
		}
		return messageResourceContainer;
	}
}
