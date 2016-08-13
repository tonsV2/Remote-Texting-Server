package dk.fitfit.remotetexting.api.resource.assembler;

import com.google.common.collect.Iterables;
import dk.fitfit.remotetexting.api.resource.MessageResource;
import dk.fitfit.remotetexting.api.resource.MessageResourceBuilder;
import dk.fitfit.remotetexting.api.resource.PhoneNumberResource;
import dk.fitfit.remotetexting.business.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.Iterator;


@Component
public class MessageResourceAssembler  implements ResourceAssembler<Message, MessageResource> {
	@Autowired
	private PhoneNumberResourceAssembler phoneNumberResourceAssembler;

	@Override
	public MessageResource toResource(final Message message) {
		PhoneNumberResource from = phoneNumberResourceAssembler.toResource(message.getFrom());
		return new MessageResourceBuilder()
				.withFrom(from)
				.withContent(message.getContent())
				.withTimestampProvider(message.getTimestampProvider())
				.withTimestampReceived(message.getTimestampReceived())
				.build();
	}

	public Iterator<MessageResource> toResources(final Iterable<Message> messages) {
		return Iterables.transform(messages, this::toResource).iterator();
	}
}
