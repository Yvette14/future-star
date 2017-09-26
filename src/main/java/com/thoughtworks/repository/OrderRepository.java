package com.thoughtworks.repository;

import com.thoughtworks.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,String>{
}
