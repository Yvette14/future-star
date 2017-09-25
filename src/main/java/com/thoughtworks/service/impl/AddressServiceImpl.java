package com.thoughtworks.service.impl;

import com.thoughtworks.entity.Address;
import com.thoughtworks.entity.User;
import com.thoughtworks.repository.AddressRepository;
import com.thoughtworks.repository.UserRepository;
import com.thoughtworks.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Address createAddress(String username, Address address) {
        User user = userRepository.findUserByUsername(username);
        address.setUser_id(user);
        addressRepository.save(address);
        return null;
    }
}
