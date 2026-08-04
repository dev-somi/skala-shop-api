package com.sk.skala.shopapi.data.table;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    // 속성
    @Id
    String customerId;
    String customerPassword;
    Double customerPoint;
    
    // 생성자
    public Customer(String customerId, Double customerPoint){
        this.customerId = customerId;
        this.customerPoint = customerPoint;
    }
}
