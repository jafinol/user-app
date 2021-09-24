package com.finolweb.userapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.finolweb.userapp.entity.User;
import com.finolweb.userapp.repositories.UserRepository;
import com.github.javafaker.Faker;

@SpringBootApplication
public class UserAppApplication implements ApplicationRunner {

	@Autowired
	private Faker faker;
	
	@Autowired
	private UserRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(UserAppApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		for (int i=0 ; i<100 ; i++) {
			User user=new User();
			user.setUsername(faker.name().username());
			user.setPassword(faker.dragonBall().character());
			user.setProfile(null);
			repository.save(user);
		
		}
	}

}
