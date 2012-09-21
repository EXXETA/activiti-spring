package de.javamagazin.activiti.spring.persistence;

public enum OrderType {
    
    PURCHASE("purchaseProcess");
    
    private String processName;
    
    OrderType(String processName) {
        this.processName = processName;
    }
    
    public String getProcessName() {
        return processName;
    }

}
