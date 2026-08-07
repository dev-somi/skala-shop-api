package com.sk.skala.shopapi.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sk.skala.shopapi.common.PagedList;
import com.sk.skala.shopapi.common.Response;
import com.sk.skala.shopapi.data.table.Product;
import com.sk.skala.shopapi.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list")
    @Operation(summary = "전체 상품 목록 조회")
    public Response<PagedList<Product>> getAllProducts(@RequestParam(defaultValue = "0") Integer offset,
                                                    @RequestParam(defaultValue = "10") Integer count){
        return productService.getAllProducts(offset, count);
    }

    @GetMapping("/{id}")
    @Operation(summary = "개별 상품 상세 조회")
    public Response<Product> getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping
    @Operation(summary = "상품 등록")
    public Response<Product> createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping
    @Operation(summary = "상품 정보 수정")
    public Response<Product> updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @DeleteMapping
    @Operation(summary = "상품 삭제")
    public Response<Long> deleteProduct(@RequestBody Product product){
        return productService.deleteProduct(product);
    }

}
