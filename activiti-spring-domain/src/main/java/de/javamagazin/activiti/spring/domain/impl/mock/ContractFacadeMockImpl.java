package de.javamagazin.activiti.spring.domain.impl.mock;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import de.javamagazin.activiti.spring.domain.ContractFacade;
import de.javamagazin.activiti.spring.domain.ProductFacade;
import de.javamagazin.activiti.spring.domain.model.Contract;
import de.javamagazin.activiti.spring.domain.model.Product;

@Profile(value = "standalone")
@Service(value = "contractFacade")
public class ContractFacadeMockImpl implements ContractFacade {

    private Map<Long, Contract> simpleContractStore = new HashMap<Long, Contract>();
    
    @Autowired
    private ProductFacade productFacade;

    @Override
    public Long createContract(Long customerId, Long productId) {
        Product product = productFacade.loadProduct(productId);
        
        Contract contract = new Contract();
        contract.setId(1L);
        contract.setCustomerId(customerId);
        contract.setProductId(product.getId());
        contract.setValidFrom(new Date());
        contract.setValidTo(product.getValidTo());
        
        simpleContractStore.put(contract.getId(), contract);
        
        return contract.getId();
    }

    @Override
    public Contract loadContract(Long id) {
        return simpleContractStore.get(id);
    }

}
