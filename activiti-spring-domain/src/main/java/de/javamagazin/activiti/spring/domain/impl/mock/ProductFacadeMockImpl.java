package de.javamagazin.activiti.spring.domain.impl.mock;

import java.util.Calendar;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import de.javamagazin.activiti.spring.domain.ProductFacade;
import de.javamagazin.activiti.spring.domain.model.Product;

@Profile(value = "standalone")
@Service(value = "productFacade")
public class ProductFacadeMockImpl implements ProductFacade {

    @Override
    public Product loadProduct(Long id) {
        Product product = new Product();
        product.setId(1L);
        product.setName("Java Magazin");
        product.setPrice(9.8);
        
        Calendar validFrom = Calendar.getInstance();
        validFrom.set(Calendar.YEAR, 2012);
        validFrom.set(Calendar.MONTH, 0);
        validFrom.set(Calendar.DAY_OF_MONTH, 1);
        product.setValidFrom(validFrom.getTime());
        
        Calendar validTo = Calendar.getInstance();
        validTo.set(Calendar.YEAR, 2012);
        validTo.set(Calendar.MONTH, 11);
        validTo.set(Calendar.DAY_OF_MONTH, 31);
        product.setValidTo(validTo.getTime());
        
        return product;
    }

}
