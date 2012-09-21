package de.javamagazin.activiti.spring.domain.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import de.javamagazin.activiti.spring.domain.BillingFacade;

@Profile(value = "integration")
@Service(value = "billingFacade")
public class BillingFacadeImpl implements BillingFacade {

    @Override
    public void createInvoice(Long contractId) {
        throw new UnsupportedOperationException("Integration environment not implemented.");
    }

}
