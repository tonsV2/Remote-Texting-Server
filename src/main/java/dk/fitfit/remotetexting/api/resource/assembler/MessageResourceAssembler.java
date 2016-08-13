package dk.fitfit.remotetexting.api.resource.assembler;

import com.google.common.collect.Iterables;
import dk.fitfit.remotetexting.api.resource.MessageResource;
import dk.fitfit.remotetexting.api.resource.MessageResourceBuilder;
import dk.fitfit.remotetexting.api.resource.PhoneNumberResource;
import dk.fitfit.remotetexting.business.domain.Message;
import dk.fitfit.remotetexting.business.domain.PhoneNumber;
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

	public Message toEntity(final MessageResource resource) {
		Message message = new Message();
		PhoneNumberResource from = resource.getFrom();
		PhoneNumber phoneNumber = phoneNumberResourceAssembler.toEntity(from);
		message.setFrom(phoneNumber);
		message.setContent(resource.getContent());
		message.setTimestampProvider(resource.getTimestampProvider());
		message.setTimestampReceived(resource.getTimestampReceived());
		return message;
	}

	public Iterator<MessageResource> toResources(final Iterable<Message> messages) {
		return Iterables.transform(messages, this::toResource).iterator();
	}
}
