package com.thoughtworks.repository;

import com.thoughtworks.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String>{

}
