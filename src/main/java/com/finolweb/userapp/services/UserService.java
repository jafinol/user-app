package com.finolweb.userapp.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.finolweb.userapp.entity.User;
import com.finolweb.userapp.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
    private UserRepository UserRepository;
	
	private static Logger LOG = LoggerFactory.getLogger(UserService.class);
	
//    public List<User> getUsers(int page, int size) {
//    	UserRepository.findAll(PageRequest.of(page, size));       
//    	return UserRepository.findAll();
//    }
    
    public Page<User> getUsers(int page, int size) {
    	    
    	return UserRepository.findAll(PageRequest.of(page, size));   
    }
    
   
    public User createUser(User user) {
        return UserRepository.save(user);
    }
    
  public Page<String> getUsernames(int page, int size) {
	  return UserRepository.findUsernames(PageRequest.of(page, size));
	
}
    
    public User updateUser( Integer id, User user) {
    	Optional<User> result = UserRepository.findById(id);
    	if (result.isPresent()) {
    		return UserRepository.save(user);
    	}
    	else {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User id %d doesnt exits", id));
    	}
    }
    
    public void deleteUser( Integer id) {
    	Optional<User> result = UserRepository.findById(id);
    	if (result.isPresent()) {
    		UserRepository.delete(result.get());
    	}
    	else {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User id %s doesnt exits", id));
    	}
    }

	public User getUserById(Integer userId) {
		return UserRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User id %s doesnt exits", userId)) );
		
	}
	
	@CacheEvict("users")
	public void deleteUserByUsername(String username){
		User user = getUserByUsername(username);
		UserRepository.delete(user);
	}

	
	
	@Cacheable("users")
	public User getUserByUsername(String username) {
		LOG.info("Getting user by username {}", username);
		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		return UserRepository.findByUsername(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Username %s doesnt exits", username)) );
	}
	
	public User getUserByUsernameAndPassword(String username, String password) {
		return UserRepository.findByUsernameAndPassword(username,password ).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Username %s doesnt exits", username)) );
	}

}
