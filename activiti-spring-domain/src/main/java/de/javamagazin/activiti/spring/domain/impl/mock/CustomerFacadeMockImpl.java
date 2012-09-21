package de.javamagazin.activiti.spring.domain.impl.mock;

import java.util.Locale;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import de.javamagazin.activiti.spring.domain.CustomerFacade;
import de.javamagazin.activiti.spring.domain.model.Customer;

@Profile(value = "standalone")
@Service(value = "customerFacade")
public class CustomerFacadeMockImpl implements CustomerFacade {
    
    @Override
    public Customer loadCustomer(Long id) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName("Max Mustermann");
        customer.setZipCode("76131");
        customer.setCity("Karlsruhe");
        customer.setCountry(Locale.GERMANY.getCountry());
        customer.setBankCode("1234567");
        customer.setBankAccountNumber("12345678");
        customer.setFraudFlag(Boolean.FALSE);
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) {
        // Mock implementation... nothing to do
    }

}
