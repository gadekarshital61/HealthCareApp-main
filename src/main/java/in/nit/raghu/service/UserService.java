package in.nit.raghu.service;

import java.util.Optional;

import in.nit.raghu.entity.User;

public interface UserService {
	
	Long saveUser(User user);
	Optional<User> findByUsername(String username);
	
	// update password 
	void updateUserPwd(String pwd, Long userId);
}
