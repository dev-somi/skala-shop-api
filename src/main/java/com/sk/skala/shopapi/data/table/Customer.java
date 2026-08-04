package com.sk.skala.shopapi.data.table;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
    // 속성
    String customerId;
    String customerPassword;
    Double customerPoint;
    
    // 생성자
    public Customer(){}

    public Customer(String customerId, Double customerPoint){
        this.customerId = customerId;
        this.customerPoint = customerPoint;
    }

    // 메서드
    // Getter / Setter
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public Double getCustomerPoint() {
        return customerPoint;
    }

    public void setCustomerPoint(Double customerPoint) {
        this.customerPoint = customerPoint;
    }
}
