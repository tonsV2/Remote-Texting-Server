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
	public void sent(final Long id, final Long ts) {
		Message message = messageRepository.findOne(id);
		message.setTimestampSent(ts);
		messageRepository.save(message);
	}

	@Override
	public void delivered(final Long id, final Long ts) {
		Message message = messageRepository.findOne(id);
		message.setTimestampDelivered(ts);
		messageRepository.save(message);
	}

	@Override
	// TODO: This method should take a Message object as argument
	public void send(final User user, final Message message) throws IOException {
		String phoneNumber = message.getTo().getNumber();
		// TODO: Load phoneNumber from repository... this could probably be done smarter... prepopulated prototype?
		PhoneNumber to = phoneNumberService.findByNumber(phoneNumber);
		if (to == null) {
			phoneNumberService.save(message.getTo());
		} else {
			message.setTo(to);
		}
		messageRepository.save(message);

		Map<String, String> data = new HashMap<>();
		data.put("command", "sendMessage");
		data.put("messageId", String.valueOf(message.getId()));
		notificationService.sendFCMMessage(user.getFcmRegId(), data);
	}

	@Override
	public Message findOne(final User user, final long messageId) {
		return messageRepository.findOne(messageId);
	}

	@Override
	public Iterable<Message> findBy(final User user, final long phoneNumberId) {
		// TODO: Ensure messages belong to user!
		return messageRepository.findByFromIdOrToIdOrderById(phoneNumberId, phoneNumberId);
	}
}
