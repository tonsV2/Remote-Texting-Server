package dk.fitfit.remotetexting.business.repository;

import dk.fitfit.remotetexting.business.domain.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
}