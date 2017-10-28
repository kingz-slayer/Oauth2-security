package osp.security.oauth2.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Long> {
	Roles findByRoleId(String roleId);
}
