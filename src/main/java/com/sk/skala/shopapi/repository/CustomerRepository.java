package com.sk.skala.shopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sk.skala.shopapi.data.table.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String>{
    // 사용자 메서드 없음
}