package de.javamagazin.activiti.spring.domain;

import de.javamagazin.activiti.spring.domain.model.Customer;

public interface CustomerFacade {
    
    Customer loadCustomer(Long id);
    
    void updateCustomer(Customer customer);

}
