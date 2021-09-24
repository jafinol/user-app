package com.finolweb.userapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.finolweb.userapp.entity.User;
import com.finolweb.userapp.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
    private UserRepository UserRepository;
	
	
    public List<User> getUsers() {
        return UserRepository.findAll();
    }
    
   
    public User createUser(User user) {
        return UserRepository.save(user);
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
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User id %d doesnt exits", id));
    	}
    }

	public User getUserById(Integer userId) {
		return UserRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User id %d doesnt exits", userId)) );
		
	}
	
	public User getUserByUsername(String username) {
		return UserRepository.findByUsername(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Username %d doesnt exits", username)) );
	}
	
	public User getUserByUsernameAndPassword(String username, String password) {
		return UserRepository.findByUsernameAndPassword(username,password ).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Username %s doesnt exits", username)) );
	}

}
