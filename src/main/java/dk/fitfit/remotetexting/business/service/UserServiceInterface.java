package dk.fitfit.remotetexting.business.service;


import dk.fitfit.remotetexting.business.domain.User;

public interface UserServiceInterface {
	User findOne(Long id);
	User save(User page);
	Iterable<User> findAll();
}
