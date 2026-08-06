package com.sk.skala.shopapi.service;

import org.springframework.stereotype.Service;

import com.sk.skala.shopapi.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
}
