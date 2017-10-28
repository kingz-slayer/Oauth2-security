package osp.security.oauth2.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmailAddress(String emailAddress);
}
