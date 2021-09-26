package com.finolweb.userapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finolweb.userapp.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository <Address, Integer>  {
	
	
	@Query("SELECT a from Address a where a.profile.user.id=?1 and a.profile.id=?2")
	List<Address> findByUserAndProfileId(Integer userid, Integer profileid);

}
