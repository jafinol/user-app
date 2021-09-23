package com.finolweb.userapp.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.finolweb.userapp.entity.Profile;


@Repository
public interface ProfileRepository  extends CrudRepository <Profile, Integer>{

}
