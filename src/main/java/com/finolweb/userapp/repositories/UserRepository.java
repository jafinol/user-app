package com.finolweb.userapp.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finolweb.userapp.entity.User;


@Repository
public interface UserRepository  extends JpaRepository <User, Integer> {
	
	public Optional <User> findByUsername(String username);
	public Optional <User> findByUsernameAndPassword(String username, String password);
	
	@Query("SELECT u.username FROM User u WHERE u.username like '%s' ")
	public Page<String> findUsernames(PageRequest pageRequest);


}
