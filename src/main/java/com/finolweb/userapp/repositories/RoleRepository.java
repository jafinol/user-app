package com.finolweb.userapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.finolweb.userapp.entity.Role;


@Repository
public interface RoleRepository  extends JpaRepository <Role, Integer> {

}
