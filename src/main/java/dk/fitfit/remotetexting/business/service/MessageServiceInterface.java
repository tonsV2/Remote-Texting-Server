package dk.fitfit.remotetexting.business.service;

import dk.fitfit.remotetexting.business.domain.Message;
import dk.fitfit.remotetexting.business.domain.User;

import java.io.IOException;


public interface MessageServiceInterface {
	Iterable<Message> findBy(final User userId, long phoneNumberId);
	void save(Message message);
	void sent(Long id);
	void send(User user, String phoneNumber, String content) throws IOException;
}
