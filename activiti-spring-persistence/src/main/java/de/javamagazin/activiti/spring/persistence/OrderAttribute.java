package de.javamagazin.activiti.spring.persistence;

import de.javamagazin.activiti.spring.persistence.converter.BeanConverter;
import de.javamagazin.activiti.spring.persistence.converter.Converter;
import de.javamagazin.activiti.spring.persistence.converter.LongConverter;

public enum OrderAttribute {

    CONTRACT_ID(LongConverter.getInstance()), 
    CUSTOMER(BeanConverter.getInstance()), 
    PRODUCT_ID(LongConverter.getInstance());

    @SuppressWarnings("rawtypes")
    private Converter converter;

    @SuppressWarnings("rawtypes")
    OrderAttribute(Converter converter) {
        this.converter = converter;
    }

    @SuppressWarnings("rawtypes")
    public Converter getConverter() {
        return converter;
    }
}
