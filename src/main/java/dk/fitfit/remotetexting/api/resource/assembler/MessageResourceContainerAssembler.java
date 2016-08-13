package dk.fitfit.remotetexting.api.resource.assembler;

import dk.fitfit.remotetexting.api.resource.MessageResourceContainer;
import dk.fitfit.remotetexting.api.resource.MessageResourceContainerBuilder;
import dk.fitfit.remotetexting.business.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;


@Component
public class MessageResourceContainerAssembler implements ResourceAssembler<Iterable<Message>, MessageResourceContainer> {
	@Autowired
	private MessageResourceAssembler messageResourceAssembler;

	@Autowired
	private MessageResourceContainerLinksAssembler messageResourceContainerLinksAssembler;

	@Override
	public MessageResourceContainer toResource(final Iterable<Message> messages) {
		return new MessageResourceContainerBuilder()
				.withMessages(messageResourceAssembler.toResources(messages))
				.withLinks(messageResourceContainerLinksAssembler.getLinks(messages))
				.build();
	}
}
