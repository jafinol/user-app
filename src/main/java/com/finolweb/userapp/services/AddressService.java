package com.finolweb.userapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.finolweb.userapp.entity.Address;
import com.finolweb.userapp.entity.Profile;
import com.finolweb.userapp.repositories.AddressRepository;
import com.finolweb.userapp.repositories.ProfileRepository;

@Service
public class AddressService {

	
	
	@Autowired
    private AddressRepository addressRepository;
	
	@Autowired
    private ProfileRepository profileRepository;


	public List<Address> findAddressbyProfileAndUserId(Integer userid, Integer profileid) {
		return  addressRepository.findByUserAndProfileId(userid,profileid );
	}
	
	
    public Address createAddress(Integer userid,Integer profileid, Address address) {
    	Optional<Profile> result = profileRepository.findByUserAndProfileId(userid, profileid);
    	
    	if (result.isPresent()) {
    		address.setProfile(result.get());
    		 return addressRepository.save(address);
    	}
    	else {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Perfil not found for user %d and profile %d", userid, profileid));
    	}
    	
       
    }

}
