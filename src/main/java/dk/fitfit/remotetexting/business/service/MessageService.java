package dk.fitfit.remotetexting.business.service;

import dk.fitfit.remotetexting.business.domain.Message;
import dk.fitfit.remotetexting.business.domain.PhoneNumber;
import dk.fitfit.remotetexting.business.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageService implements MessageServiceInterface {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

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
//		message.sent(true);
//		store timestamp
		messageRepository.save(message);
	}

	@Override
	public Iterable<Message> findBy(final long phoneNumberId) {
		return messageRepository.findByFromId(phoneNumberId);
	}
}
