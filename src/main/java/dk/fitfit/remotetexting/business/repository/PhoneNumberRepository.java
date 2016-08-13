package dk.fitfit.remotetexting.business.repository;

import dk.fitfit.remotetexting.business.domain.PhoneNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PhoneNumberRepository extends CrudRepository<PhoneNumber, Long> {
	PhoneNumber findByNumber(String number);
	Iterable<PhoneNumber> findByUserId(long userId);
}
