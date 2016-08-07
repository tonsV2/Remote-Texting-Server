package dk.fitfit.remotetexting.business.service;


import dk.fitfit.remotetexting.business.domain.Contact;

public interface ContactServiceInterface {
	Iterable<Contact> findAll();
}
