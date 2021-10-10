package in.nit.raghu.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.raghu.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

	Optional<User> findByUsername(String username);
}
