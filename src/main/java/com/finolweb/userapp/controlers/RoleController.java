package com.finolweb.userapp.controlers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.finolweb.userapp.entity.Role;
import com.finolweb.userapp.entity.User;
import com.finolweb.userapp.services.RoleService;



@RestController
@RequestMapping("/roles")
public class RoleController {

	private static Logger LOG = LoggerFactory.getLogger(RoleController.class); 
	
    @Autowired
    private RoleService service;
    
    @GetMapping
    public ResponseEntity<List<Role>> getRoles() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	LOG.info("User {}", authentication.getName()  );
    	LOG.info("Principal {}", authentication.getPrincipal() );
    	LOG.info("Credentials {}", authentication.getCredentials()  );
    	LOG.info("Role {}", authentication.getAuthorities().toString() );
        return new ResponseEntity<List<Role>>(service.getRoles(), HttpStatus.OK);
    }
    
    @GetMapping("/{roleName}/users")
    public ResponseEntity<List<User>> getusersByRol(@PathVariable("roleName") String roleName) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	LOG.info("User {}", authentication.getName()  );
    	LOG.info("Principal {}", authentication.getPrincipal() );
    	LOG.info("Credentials {}", authentication.getCredentials()  );
    	LOG.info("Role {}", authentication.getAuthorities().toString() );
        return new ResponseEntity<List<User>>(service.getByRolName(roleName), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Role> createRoles(@RequestBody Role role) {
        return new ResponseEntity<Role>(service.createRole(role), HttpStatus.CREATED);
    }

    @PutMapping("/{roleid}")
    public ResponseEntity<Role> updtaeRoles(@PathVariable("roleid") Integer roleid, @RequestBody  Role role) {
        return new ResponseEntity<Role>(service.updateRole(roleid, role), HttpStatus.OK);
    }

    @DeleteMapping("/{roleid}")
    public ResponseEntity<Void> deleteRoles(@PathVariable("roleid") Integer roleid) {
    	service.deleteRole(roleid);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
}
