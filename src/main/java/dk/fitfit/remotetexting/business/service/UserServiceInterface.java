package dk.fitfit.remotetexting.business.service;


import dk.fitfit.remotetexting.business.domain.User;

public interface UserServiceInterface {
	Iterable<User> findAll();
	User findByUserId(String userId);
	User findOne(long id);
	User create(String userId, String email);
}
