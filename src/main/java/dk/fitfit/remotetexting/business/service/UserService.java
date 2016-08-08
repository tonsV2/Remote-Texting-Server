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
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	// TODO: Should this method be called findOrCreateUserById?
	@Override
	public User findByUserId(String userId) {
		return userRepository.findByUserId(userId);
	}

	@Override
	public User create(final String userId, final String email) {
		User user = new User();
		user.setUserId(userId);
		user.setEmail(email);
		userRepository.save(user);
		return user;
	}
}
