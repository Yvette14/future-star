package com.thoughtworks.service.impl;

import com.thoughtworks.cache.SessionCache;
import com.thoughtworks.entity.Address;
import com.thoughtworks.entity.User;
import com.thoughtworks.repository.AddressRepository;
import com.thoughtworks.repository.UserRepository;
import com.thoughtworks.service.AddressService;
import com.thoughtworks.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionCache sessionCache;

    @Override
    @Transactional
    public Address createAddress(Address address) {
        User user = sessionCache.loadCurrentUser();
        address.setId(StringUtils.randomUUID());
        addressRepository.save(address);
        user.getAddresses().add(address);
        return address;
    }
}
