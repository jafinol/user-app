package com.finolweb.userapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.finolweb.userapp.entity.Profile;
import com.finolweb.userapp.entity.User;
import com.finolweb.userapp.repositories.ProfileRepository;
import com.finolweb.userapp.repositories.UserRepository;


@Service
public class ProfileService {
	
	

	@Autowired
    private ProfileRepository repository;
	
	@Autowired
    private UserRepository userRepository;
	
    public List<Profile> getProfiles() {
        return repository.findAll();
    }
    
   
    public Profile createProfile(Integer userid, Profile profile) {
    	Optional<User> result = userRepository.findById(userid);
    	if (result.isPresent()) {
    		profile.setUser(result.get());
    		 return repository.save(profile);
    	}
    	else {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User id %d doesnt exits", userid));
    	}
    	
       
    }
    
    
    
    public Profile updateProfile( Integer profileid, Profile profile) {
    	Optional<Profile> result = repository.findById(profileid);
    	if (result.isPresent()) {
    		return repository.save(profile);
    	}
    	else {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Profile id %d doesnt exits", profileid));
    	}
    }
    
    public void deleteProfile( Integer profileid) {
    	Optional<Profile> result = repository.findById(profileid);
    	if (result.isPresent()) {
    		 repository.delete(result.get());
    	}
    	else {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Profile id %d doesnt exits", profileid));
    	}
    }


	public Profile getByUserAndProfileId(Integer userid, Integer profileid) {
		// TODO Auto-generated method stub
		return repository.findByUserAndProfileId(userid,profileid )
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Profile not found for user %d and profile %d", userid, profileid)));
	}



}
