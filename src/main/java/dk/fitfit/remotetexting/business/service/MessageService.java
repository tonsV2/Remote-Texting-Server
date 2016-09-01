package dk.fitfit.remotetexting.business.service;

import dk.fitfit.remotetexting.business.domain.Message;
import dk.fitfit.remotetexting.business.domain.PhoneNumber;
import dk.fitfit.remotetexting.business.domain.User;
import dk.fitfit.remotetexting.business.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service
public class MessageService implements MessageServiceInterface {
	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private PhoneNumberServiceInterface phoneNumberService;

	@Autowired
	private NotificationService notificationService;

	@Override
	public void save(final Message message) {
		PhoneNumber from = phoneNumberService.findByNumber(message.getFrom().getNumber());
		if (from == null) {
			from = message.getFrom();
			phoneNumberService.save(from);
		}
		message.setFrom(from);
		messageRepository.save(message);
	}

	@Override
	public void sent(final Long id) {
		Message message = messageRepository.findOne(id);
// TODO:
//		message.sent(true);
//		store timestamp... which should probably be passed form the client... since the client might send the message and
// then later (due to lack of connect or server down time) post to the server about it
		messageRepository.save(message);
	}

	@Override
	// TODO: This method should take a Message object as argument
	public void send(final User user, final String phoneNumber, final String message) throws IOException {
		Map<String, String> data = new HashMap<>();
		data.put("command", "sendMessage");
		// TODO: Store message and send id to client
		data.put("to", phoneNumber);
		data.put("message", message);
		notificationService.sendFCMMessage(user.getFcmRegId(), data);
	}

	@Override
	public Iterable<Message> findBy(final User user, final long phoneNumberId) {
		// TODO: Ensure messages belong to user!
		return messageRepository.findByFromId(phoneNumberId);
	}
}
