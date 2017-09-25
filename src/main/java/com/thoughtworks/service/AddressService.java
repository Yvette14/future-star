package com.thoughtworks.service;

import com.thoughtworks.entity.Address;

public interface AddressService {

    Address createAddress(String username, Address address);
}
