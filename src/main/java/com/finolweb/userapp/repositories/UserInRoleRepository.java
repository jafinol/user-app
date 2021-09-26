package com.finolweb.userapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finolweb.userapp.entity.User;
import com.finolweb.userapp.entity.UserInRole;


@Repository
public interface UserInRoleRepository extends JpaRepository <UserInRole, Integer> {
	
	
	@Query("SELECT u.user from UserInRole u where u.role.name=?1")
	List<User> findByUserByRoleName(String roleName);


	List<UserInRole> findByUser(User user);
}
