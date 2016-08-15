package dk.fitfit.remotetexting.api.resource.assembler;

import com.google.common.collect.Lists;
import dk.fitfit.remotetexting.api.controller.MessageController;
import dk.fitfit.remotetexting.business.domain.Message;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@Component
public class MessageResourceContainerLinksAssembler implements ResourceLinksAssemblerInterface<Iterable<Message>> {

	@Override
	public List<Link> getLinks(final Iterable<Message> entity) {
		Message message = entity.iterator().next();
		try {
			return Lists.newArrayList(new SelfLink(message), new PrototypeLink(), new SendMessageLink());
		} catch (GeneralSecurityException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	static class SelfLink extends Link {
		public SelfLink(final Message messages) {
			super(REL_SELF, linkTo(methodOn(MessageController.class).getByPhoneNumber(messages.getFrom().getId())).toString());
		}
	}

	static class PrototypeLink extends Link {
		public PrototypeLink() throws GeneralSecurityException, IOException {
			super("prototype", linkTo(methodOn(MessageController.class).getPrototype()).toString());
		}
	}

	static class SendMessageLink extends Link {
		public SendMessageLink() throws GeneralSecurityException, IOException {
			super("send", linkTo(methodOn(MessageController.class).sendMessage(null)).toString());
		}
	}

}
