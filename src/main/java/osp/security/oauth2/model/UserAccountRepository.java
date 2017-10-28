package osp.security.oauth2.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long>{
	
	UserAccount findByUserName(String userName);
	
}
