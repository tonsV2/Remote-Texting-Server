package dk.fitfit.remotetexting.business.repository;

import dk.fitfit.remotetexting.business.domain.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
//	@Query("select m.from, m.content from Message m where m.from = :phoneNumberId")
//	Iterable<Message> findBy(long phoneNumberId);

	Iterable<Message> findByFromIdOrToIdOrderById(long phoneNumberId, long phoneNumberTo);
}
