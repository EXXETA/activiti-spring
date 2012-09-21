package de.javamagazin.activiti.spring.domain.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import de.javamagazin.activiti.spring.domain.CustomerFacade;
import de.javamagazin.activiti.spring.domain.model.Customer;

@Profile(value = "integration")
@Service(value = "customerFacade")
public class CustomerFacadeImpl implements CustomerFacade {

    @Override
    public Customer loadCustomer(Long id) {
        throw new UnsupportedOperationException("Integration environment not implemented.");
    }

    @Override
    public void updateCustomer(Customer customer) {
        throw new UnsupportedOperationException("Integration environment not implemented.");
    }

}
