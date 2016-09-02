package dk.fitfit.remotetexting.business.service;

import dk.fitfit.remotetexting.business.domain.Message;
import dk.fitfit.remotetexting.business.domain.User;

import java.io.IOException;


public interface MessageServiceInterface {
	Iterable<Message> findBy(final User userId, long phoneNumberId);
	void save(final Message message);
	void sent(final Long id);
	void send(final User user, final Message message) throws IOException;
	Message findOne(User user, long messageId);
}
