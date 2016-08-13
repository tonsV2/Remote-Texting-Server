package dk.fitfit.remotetexting.business.service;

import dk.fitfit.remotetexting.business.domain.Message;


public interface MessageServiceInterface {
	Iterable<Message> findAll();
	Iterable<Message> findBy(long phoneNumberId);
	Message save(Message message);
	void sent(Long id);
}
