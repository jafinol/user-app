package com.finolweb.userapp.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finolweb.userapp.entity.User;
import com.finolweb.userapp.services.UserService;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/users")
public class UserController {
	
	

		@Autowired
		private UserService service;
	
//	 	@GetMapping
//	    public ResponseEntity<List<User>> getUsers() {
//	        return new ResponseEntity<List<User>>(service.getUsers(), HttpStatus.OK);
//	    }
		
	 	@GetMapping
	 	@Timed("get.users")
	    public ResponseEntity<Page<User>> getUsers(@RequestParam(value= "page", defaultValue="0" , required=false ) int page,@RequestParam(value= "size", defaultValue="100000" , required=false) int size) {
	        return new ResponseEntity<Page<User>>(service.getUsers( page,  size), HttpStatus.OK);
	    }
	    
	 	
	 	@GetMapping("/usernames")
	    public ResponseEntity<Page<String>> getUsernames(@RequestParam(value= "page", defaultValue="0" , required=false ) int page,@RequestParam(value= "size", defaultValue="1000" , required=false) int size) {
	        return new ResponseEntity<Page<String>>(service.getUsernames(page,  size), HttpStatus.OK);
	    }
	    
		@GetMapping(value="/{userId}")
		@ApiOperation(value="Retsurn a user for a given user id", response=User.class)
		@ApiResponses(value= {
				@ApiResponse(code=200, message="The record was found"),
				@ApiResponse(code=404, message="The record was not found")
		}
				)
		public ResponseEntity<User> getUsersById(@PathVariable("userId") Integer userId){
			return new ResponseEntity<>(service.getUserById(userId), HttpStatus.OK);
		}
		
		@GetMapping(value="/username/{username}")
		public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
			return new ResponseEntity<>(service.getUserByUsername(username), HttpStatus.OK);
		}		
		
		@PostMapping
		public ResponseEntity<User> authenticate(@RequestBody User user){
			return new ResponseEntity<>(service.getUserByUsernameAndPassword(user.getUsername(), user.getPassword()), HttpStatus.OK);
		}	
		
		
//	    @PostMapping
//	    public ResponseEntity<User> createUser(@RequestBody User user) {
//	        return new ResponseEntity<User>(service.createUser(user), HttpStatus.CREATED);
//	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<User> updtaUser(@PathVariable("id") Integer id, @RequestBody  User user) {
	        return new ResponseEntity<User>(service.updateUser(id, user), HttpStatus.OK);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
	    	service.deleteUser(id);
	        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	    }
	

	    @DeleteMapping("/del/{username}")
	    public ResponseEntity<Void> deleteUserByUsername(@PathVariable("username") String username) {
	    	service.deleteUserByUsername(username);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
}
