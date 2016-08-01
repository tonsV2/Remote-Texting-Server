package dk.fitfit.remotetexting.business.repository;

import dk.fitfit.remotetexting.business.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
