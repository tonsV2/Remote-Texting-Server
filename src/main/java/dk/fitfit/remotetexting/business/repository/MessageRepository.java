package dk.fitfit.remotetexting.business.repository;

import dk.fitfit.remotetexting.business.domain.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
}
