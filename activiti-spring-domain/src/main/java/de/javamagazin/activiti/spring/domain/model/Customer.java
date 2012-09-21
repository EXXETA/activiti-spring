package de.javamagazin.activiti.spring.domain.model;

import java.io.Serializable;

public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String zipCode;

    private String city;

    private String country;

    private String bankCode;

    private String bankAccountNumber;

    private Boolean fraudFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public Boolean getFraudFlag() {
        return fraudFlag;
    }

    public void setFraudFlag(Boolean fraudFlag) {
        this.fraudFlag = fraudFlag;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", zipCode=" + zipCode + ", city=" + city + ", country="
                + country + ", bankCode=" + bankCode + ", bankAccountNumber=" + bankAccountNumber + ", fraudFlag="
                + fraudFlag + "]";
    }

}
