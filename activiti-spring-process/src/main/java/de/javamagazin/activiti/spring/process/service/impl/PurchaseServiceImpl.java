package de.javamagazin.activiti.spring.process.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.javamagazin.activiti.spring.domain.CustomerFacade;
import de.javamagazin.activiti.spring.domain.model.Customer;
import de.javamagazin.activiti.spring.engine.ActivitiSpringEngine;
import de.javamagazin.activiti.spring.persistence.Order;
import de.javamagazin.activiti.spring.persistence.OrderAttribute;
import de.javamagazin.activiti.spring.persistence.OrderDao;
import de.javamagazin.activiti.spring.persistence.OrderType;
import de.javamagazin.activiti.spring.process.service.PurchaseService;

@Service(value = "purchaseService")
public class PurchaseServiceImpl implements PurchaseService {
    
    @Autowired
    private ActivitiSpringEngine engine;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CustomerFacade customerFacade;

    @Transactional
    @Override
    public Long execute(Long customerId, Long productId) {
        Customer customer = customerFacade.loadCustomer(1L);
        
        Map<OrderAttribute, Object> requestAttributes = new HashMap<OrderAttribute, Object>();
        requestAttributes.put(OrderAttribute.PRODUCT_ID, 1L);
        requestAttributes.put(OrderAttribute.CUSTOMER, customer);
        
        Order order = new Order(OrderType.PURCHASE, requestAttributes);
        order = orderDao.save(order);
       
        engine.startProcess(order);
        
        return order.getId();
    }
}
