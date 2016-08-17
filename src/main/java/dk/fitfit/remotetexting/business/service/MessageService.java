package dk.fitfit.remotetexting.business.service;

import dk.fitfit.remotetexting.business.domain.Message;
import dk.fitfit.remotetexting.business.domain.PhoneNumber;
import dk.fitfit.remotetexting.business.domain.User;
import dk.fitfit.remotetexting.business.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageService implements MessageServiceInterface {
	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private PhoneNumberServiceInterface phoneNumberService;

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
//		store timestamp
		messageRepository.save(message);
	}

	@Override
	public Iterable<Message> findBy(final User user, final long phoneNumberId) {
		// TODO: Ensure messages belong to user!
		return messageRepository.findByFromId(phoneNumberId);
	}
}
