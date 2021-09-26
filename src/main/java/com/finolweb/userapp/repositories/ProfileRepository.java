package com.finolweb.userapp.repositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.finolweb.userapp.entity.Profile;


@Repository
public interface ProfileRepository  extends JpaRepository <Profile, Integer>{


	@Query("SELECT p from Profile p where p.user.id=?1 and p.id=?2")
	Optional <Profile> findByUserAndProfileId(Integer userid, Integer profileid);

}
