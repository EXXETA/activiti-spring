package de.javamagazin.activiti.spring.domain;

import de.javamagazin.activiti.spring.domain.model.Contract;

public interface ContractFacade {
    
    Long createContract(Long customerId, Long productId);
    
    Contract loadContract(Long id);

}
