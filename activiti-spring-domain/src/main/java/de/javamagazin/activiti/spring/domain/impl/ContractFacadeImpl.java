package de.javamagazin.activiti.spring.domain.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import de.javamagazin.activiti.spring.domain.ContractFacade;
import de.javamagazin.activiti.spring.domain.model.Contract;

@Profile(value = "integration")
@Service(value = "contractFacade")
public class ContractFacadeImpl implements ContractFacade {

    @Override
    public Long createContract(Long customerId, Long productId) {
        throw new UnsupportedOperationException("Integration environment not implemented.");
    }

    @Override
    public Contract loadContract(Long id) {
        throw new UnsupportedOperationException("Integration environment not implemented.");
    }

}
