package de.javamagazin.activiti.spring.persistence;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_REGISTRY")
public class Order implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public Order() {
    }

    @SuppressWarnings("unchecked")
    public Order(OrderType type, Map<OrderAttribute, Object> requestAttributes) {
        this.type = type;
        Map<OrderAttribute, String> serializedAttributes = new EnumMap<OrderAttribute, String>(OrderAttribute.class);
        for (Map.Entry<OrderAttribute, Object> orderAttributeObjectEntry : requestAttributes.entrySet()) {
            OrderAttribute key = orderAttributeObjectEntry.getKey();
            Object value = orderAttributeObjectEntry.getValue();
            String serialized = key.getConverter().serialize(value);
            serializedAttributes.put(key, serialized);
        }
        this.requestAttributes = serializedAttributes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID", nullable = false)
    private Long id;

    @Column(name = "PROCESS_INSTANCE_ID", unique = true)
    private String processInstanceId;

    @Column(name = "ORDERTYPE")
    @Enumerated(EnumType.STRING)
    private OrderType type;

    /**
     * Process Instance Attributes which may be modified during the process instance lifecycle.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "ATTRIBUTE_KEY", updatable = false, length = 64)
    @MapKeyEnumerated(EnumType.STRING)
    @CollectionTable(name = "PERSIST_ORDER_ATTRIB", joinColumns = @JoinColumn(name = "ORDER_ID"))
    @Column(name = "ATTRIBUTE_VALUE", nullable = false, length = 65534, updatable = true)
    private Map<OrderAttribute, String> processInstanceAttributes = new EnumMap<OrderAttribute, String>(OrderAttribute.class);

    /**
     * Request Attributes which must remain untouched during the process instance lifecycle.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "ATTRIBUTE_KEY", updatable = false, length = 64)
    @MapKeyEnumerated(EnumType.STRING)
    @CollectionTable(name = "REQUEST_ORDER_ATTRIB", joinColumns = @JoinColumn(name = "ORDER_ID"))
    @Column(name = "ATTRIBUTE_VALUE", nullable = false, length = 65534, updatable = false)
    private Map<OrderAttribute, String> requestAttributes = new EnumMap<OrderAttribute, String>(OrderAttribute.class);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    @SuppressWarnings("unchecked")
    public void setAttribute(OrderAttribute key, Object value) {
        processInstanceAttributes.put(key, key.getConverter().serialize(value));
    }

    public Object getAttribute(OrderAttribute key) {
        String serialized = processInstanceAttributes.get(key);
        if (serialized == null) {
            serialized = requestAttributes.get(key);
        }
        return key.getConverter().deserialize(serialized);
    }

    public Map<OrderAttribute, Object> getAttributes() {
        Map<OrderAttribute, Object> attributes = new EnumMap<OrderAttribute, Object>(OrderAttribute.class);
        for (OrderAttribute requestAttribute : requestAttributes.keySet()) {
            attributes.put(requestAttribute, getAttribute(requestAttribute));
        }
        for (OrderAttribute requestAttribute : processInstanceAttributes.keySet()) {
            attributes.put(requestAttribute, getAttribute(requestAttribute));
        }
        return Collections.unmodifiableMap(attributes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
