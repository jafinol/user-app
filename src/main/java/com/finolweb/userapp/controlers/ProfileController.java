package com.finolweb.userapp.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finolweb.userapp.entity.Profile;
import com.finolweb.userapp.services.ProfileService;


@RestController
@RequestMapping("/users/{userid}/profiles")
public class ProfileController {
	
	

    @Autowired
    private ProfileService service;
    
    @GetMapping
    public ResponseEntity<List<Profile>> getProfiles() {
        return new ResponseEntity<List<Profile>>(service.getProfiles(), HttpStatus.OK);
    }
    
    @GetMapping("/{profileid}")
    public ResponseEntity<Profile> getProfileById(@PathVariable("userid") Integer userid,@PathVariable("profileid") Integer profileid) {
        return new ResponseEntity<Profile>(service.getByUserAndProfileId(userid,profileid ), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Profile> createProfiles(@PathVariable("userid") Integer userid, @RequestBody Profile profile) {
        return new ResponseEntity<Profile>(service.createProfile(userid, profile), HttpStatus.CREATED);
    }

    @PutMapping("/{profileid}")
    public ResponseEntity<Profile> updateProfiles(@PathVariable("profileid") Integer profileid, @RequestBody  Profile profile) {
        return new ResponseEntity<Profile>(service.updateProfile(profileid, profile), HttpStatus.OK);
    }

    @DeleteMapping("/{profileid}")
    public ResponseEntity<Void> deleteProfiles(@PathVariable("profileid") Integer profileid) {
    	service.deleteProfile(profileid);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
