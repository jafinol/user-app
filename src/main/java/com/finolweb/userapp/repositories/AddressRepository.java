package com.finolweb.userapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.finolweb.userapp.entity.Address;

@Repository
public interface AddressRepository extends CrudRepository <Address, Integer>  {

}
