package com.sk.skala.shopapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sk.skala.shopapi.common.PagedList;
import com.sk.skala.shopapi.common.Response;
import com.sk.skala.shopapi.data.table.Product;
import com.sk.skala.shopapi.exception.ParameterException;
import com.sk.skala.shopapi.exception.ResponseException;
import com.sk.skala.shopapi.exception.Error;
import com.sk.skala.shopapi.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

        // 전체 상품 목록 조회
        public Response<PagedList<Product>> getAllProducts(int offset, int count) {
            Pageable pageable = PageRequest.of(offset / count, count);
            Page<Product> page = productRepository.findAll(pageable);

            PagedList<Product> pagedList = new PagedList<>(
                page.getContent(),        // 이번 페이지 데이터 리스트
                page.getTotalElements(),  // 전체 개수
                offset,
                count
            );

            return new Response<>("상품 목록 조회 성공", pagedList);
        }

        // 개별 상품 상세 조회
        public Response<Product> getProductById(Long id){
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ResponseException(Error.DATA_NOT_FOUND));

            return new Response<>("개별 상품 조회 성공", product);
        }

        @Transactional
        // 상품 등록 (생성)
        public Response<Product> createProduct(Product product){
            if (product.getProductName() == null || product.getProductName().isEmpty()
                    || product.getProductPrice() == null || product.getProductPrice() <= 0) {
                throw new ParameterException("productName", "productPrice");
            }

            productRepository.findByProductName(product.getProductName())
                    .ifPresent(p -> {throw new ResponseException(Error.DATA_DUPLICATED);});

            product.setId(0L);
            productRepository.save(product);
            return new Response<>("상품 등록 성공", product);
        }
    
        @Transactional
        // 상품 정보 수정
        public Response<Product> updateProduct(Product product){
            if (product.getProductName() == null || product.getProductName().isEmpty()
                    || product.getProductPrice() == null || product.getProductPrice() <= 0) {
                throw new ParameterException("productName", "productPrice");
            }

            Product savedProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new ResponseException(Error.DATA_NOT_FOUND));

            savedProduct.setProductName(product.getProductName());
            savedProduct.setProductPrice(product.getProductPrice());
            productRepository.save(savedProduct);

            return new Response<>("상품 정보 수정 성공", savedProduct);
        }

        @Transactional
        // 상품 삭제
        public Response<Long> deleteProduct(Product product){
            Product deletedProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new ResponseException(Error.DATA_NOT_FOUND));

            productRepository.delete(deletedProduct);

            return new Response<>("상품 삭제 성공", deletedProduct.getId());
        }
}
