package dk.fitfit.remotetexting.api.resource.assembler;

import com.google.common.collect.Lists;
import dk.fitfit.remotetexting.api.controller.MessageController;
import dk.fitfit.remotetexting.business.domain.PhoneNumber;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@Component
public class PhoneNumberResourceLinksAssembler implements ResourceLinksAssemblerInterface<PhoneNumber> {
	@Override
	public List<Link> getLinks(final PhoneNumber entity) {
		return Lists.newArrayList(new MessagesLink(entity));
	}

	static class MessagesLink extends Link {
		public MessagesLink(final PhoneNumber phoneNumber) {
			super("messages", linkTo(methodOn(MessageController.class).getByPhoneNumber(phoneNumber.getId())).toString());
		}
	}

}
