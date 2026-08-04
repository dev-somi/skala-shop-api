package com.sk.skala.shopapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sk.skala.shopapi.data.table.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    // 사용자 정의 메서드
    // findByProductName : 상품 이름과 동일한 상품 데이터를 조회 존재 여부 확인 목적 -> Optional
    Optional<Product> findByProductName(String productName);
}