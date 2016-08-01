package dk.fitfit.remotetexting.business.service;

import dk.fitfit.remotetexting.business.domain.User;
import dk.fitfit.remotetexting.business.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserServiceInterface {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findOne(final Long id) {
		log.info("findOne({})", id);
		return userRepository.findOne(id);
	}

	@Override
	public User save(final User user) {
		log.info("save()");
		userRepository.save(user);
		return user;
	}

	@Override
	public Iterable<User> findAll() {
		log.info("findAll()");
		return userRepository.findAll();
	}
}
