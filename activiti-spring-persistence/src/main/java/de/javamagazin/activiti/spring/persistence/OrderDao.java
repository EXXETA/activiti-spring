package de.javamagazin.activiti.spring.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, Long> {
    
}