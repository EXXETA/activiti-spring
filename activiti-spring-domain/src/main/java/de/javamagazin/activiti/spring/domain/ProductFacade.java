package de.javamagazin.activiti.spring.domain;

import de.javamagazin.activiti.spring.domain.model.Product;

public interface ProductFacade {
    
    Product loadProduct(Long id);

}
