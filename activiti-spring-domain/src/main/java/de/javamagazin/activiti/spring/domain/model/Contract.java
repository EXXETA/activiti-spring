package de.javamagazin.activiti.spring.domain.model;

import java.io.Serializable;
import java.util.Date;

public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long customerId;

    private Long productId;

    private Date validFrom;

    private Date validTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    @Override
    public String toString() {
        return "Contract [id=" + id + ", customerId=" + customerId + ", productId=" + productId + ", validFrom="
                + validFrom + ", validTo=" + validTo + "]";
    }

}
