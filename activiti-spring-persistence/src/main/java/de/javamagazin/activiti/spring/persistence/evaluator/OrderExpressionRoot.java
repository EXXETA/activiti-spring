package de.javamagazin.activiti.spring.persistence.evaluator;

import de.javamagazin.activiti.spring.persistence.Order;

public class OrderExpressionRoot {

    private Order order;

    public OrderExpressionRoot(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

}