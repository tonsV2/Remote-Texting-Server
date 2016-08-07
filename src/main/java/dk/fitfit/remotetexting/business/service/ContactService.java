package dk.fitfit.remotetexting.business.service;

import dk.fitfit.remotetexting.business.domain.Contact;
import dk.fitfit.remotetexting.business.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ContactService implements ContactServiceInterface {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ContactRepository contactRepository;

	@Override
	public Iterable<Contact> findAll() {
		return contactRepository.findAll();
	}
}
