package de.javamagazin.activiti.spring.engine;

import de.javamagazin.activiti.spring.persistence.Order;

public interface ActivitiSpringEngine {
    
    String startProcess(Order order);

}
