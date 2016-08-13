package dk.fitfit.remotetexting.business.service;

import dk.fitfit.remotetexting.business.domain.PhoneNumber;


public interface PhoneNumberServiceInterface {
	Iterable<PhoneNumber> findAll();
	PhoneNumber save(PhoneNumber number);
	PhoneNumber findByNumber(String number);
	Iterable<PhoneNumber> findByUser(long userId);
}
