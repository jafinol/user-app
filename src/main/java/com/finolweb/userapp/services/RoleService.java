package com.finolweb.userapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    	if 
    	
    	
        return repository.save(role);
    }
    

}
