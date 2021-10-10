package in.nit.raghu.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.raghu.entity.User;
import in.nit.raghu.repo.UserRepository;
import in.nit.raghu.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repo;

	@Override
	public Long saveUser(User user) {
		return repo.save(user).getId();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return repo.findByUsername(username);
	}

}
