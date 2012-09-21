package de.javamagazin.activiti.spring.process.service;

public interface PurchaseService {
    
    Long execute(Long customerId, Long productId);
    
}
