package dk.fitfit.remotetexting.api.resource.assembler;

import com.google.common.collect.Lists;
import dk.fitfit.remotetexting.api.controller.MessageController;
import dk.fitfit.remotetexting.business.domain.Message;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@Component
public class MessageResourceContainerLinksAssembler implements ResourceLinksAssemblerInterface<Iterable<Message>> {

	@Override
	public List<Link> getLinks(final Iterable<Message> entity) {
		return Lists.newArrayList(new SelfLink(entity));
	}

	static class SelfLink extends Link {
		public SelfLink(final Iterable<Message> messages) {
			super(REL_SELF, linkTo(methodOn(MessageController.class).getByPhoneNumber(messages.iterator().next().getFrom().getId())).toString());
		}
	}

}
