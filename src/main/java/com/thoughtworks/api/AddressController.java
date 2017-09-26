package com.thoughtworks.api;

import com.thoughtworks.entity.Address;
import com.thoughtworks.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Address createAddress(@RequestBody Address address) {
        addressService.createAddress(address);
        return address;
    }
}
