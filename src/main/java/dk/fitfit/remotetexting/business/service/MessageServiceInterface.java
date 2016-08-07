package dk.fitfit.remotetexting.business.service;

import dk.fitfit.remotetexting.business.domain.Message;


public interface MessageServiceInterface {
	Iterable<Message> findAll();
	Message save(Message message);

}
