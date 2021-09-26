package com.finolweb.userapp.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finolweb.userapp.entity.Address;
import com.finolweb.userapp.services.AddressService;


@RestController
@RequestMapping("/users/{userid}/profiles/{profileid}/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;
    
    @GetMapping
    public ResponseEntity<List<Address>> findAddressbyProfileAndUserId(@PathVariable("userid") Integer userid,@PathVariable("profileid") Integer profileid) {
        return new ResponseEntity<List<Address>>(addressService.findAddressbyProfileAndUserId(userid,profileid ), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Address> createAddress(@PathVariable("userid") Integer userid,@PathVariable("profileid") Integer profileid, @RequestBody Address address) {
        return new ResponseEntity<Address>(addressService.createAddress(userid, profileid, address), HttpStatus.CREATED);
    }
}
