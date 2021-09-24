package com.finolweb.userapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.finolweb.userapp.entity.Role;
import com.finolweb.userapp.repositories.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
    private RoleRepository repository;
	
	
    public List<Role> getRoles() {
        return repository.findAll();
    }
    
   
    public Role createRole(Role role) {
        return repository.save(role);
    }
    
    
    
    public Role updateRole( Integer roleid, Role role) {
    	Optional<Role> result = repository.findById(roleid);
    	if (result.isPresent()) {
    		return repository.save(role);
    	}
    	else {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d doesnt exits", roleid));
    	}
    }
    
    public void deleteRole( Integer roleid) {
    	Optional<Role> result = repository.findById(roleid);
    	if (result.isPresent()) {
    		 repository.delete(result.get());
    	}
    	else {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d doesnt exits", roleid));
    	}
    }
    
    
}
