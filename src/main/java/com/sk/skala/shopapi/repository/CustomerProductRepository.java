package com.sk.skala.shopapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sk.skala.shopapi.data.table.Customer;
import com.sk.skala.shopapi.data.table.OrderItem;
import com.sk.skala.shopapi.data.table.Product;

public interface CustomerProductRepository extends JpaRepository<OrderItem, Long>{
    // findByCustomerCustomerId : OrderItem 엔티티의 customer 필드가 참조하는 Customer 엔티티의 
    // customerId 필드 값과 일치하는 OrderItem 목록 조회
    List<OrderItem> findByCustomerCustomerId(String customerId);

    // findByCustomerAndProduct : 특정 고객Customer가 특정 상품Product을 보유하고 있는지 검색
    Optional<OrderItem> findByCustomerAndProduct(Customer customer, Product product);
}
