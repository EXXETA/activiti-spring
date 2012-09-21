package de.javamagazin.activiti.spring.domain.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import de.javamagazin.activiti.spring.domain.ProductFacade;
import de.javamagazin.activiti.spring.domain.model.Product;

@Profile(value = "integration")
@Service(value = "productFacade")
public class ProductFacadeImpl implements ProductFacade {

    @Override
    public Product loadProduct(Long id) {
        throw new UnsupportedOperationException("Integration environment not implemented.");
    }
}
