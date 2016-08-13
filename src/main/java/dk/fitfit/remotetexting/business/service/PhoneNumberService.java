package dk.fitfit.remotetexting.business.service;

import dk.fitfit.remotetexting.business.domain.PhoneNumber;
import dk.fitfit.remotetexting.business.repository.PhoneNumberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PhoneNumberService implements PhoneNumberServiceInterface {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PhoneNumberRepository repository;

	@Override
	public Iterable<PhoneNumber> findAll() {
		return repository.findAll();
	}

	@Override
	public PhoneNumber save(final PhoneNumber number) {
		return repository.save(number);
	}

	@Override
	public PhoneNumber findByNumber(final String number) {
		return repository.findByNumber(number);
	}

	@Override
	public Iterable<PhoneNumber> findByUser(final long userId) {
		return repository.findByUserId(userId);
	}
}
