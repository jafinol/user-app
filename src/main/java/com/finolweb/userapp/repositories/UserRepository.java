package com.finolweb.userapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.finolweb.userapp.entity.User;


public interface UserRepository  extends CrudRepository <User, Integer> {

}
