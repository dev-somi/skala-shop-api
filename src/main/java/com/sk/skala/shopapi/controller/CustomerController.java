package com.sk.skala.shopapi.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sk.skala.shopapi.common.PagedList;
import com.sk.skala.shopapi.common.Response;
import com.sk.skala.shopapi.data.dto.OrderListDto;
import com.sk.skala.shopapi.data.table.Customer;
import com.sk.skala.shopapi.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController{
    private final CustomerService customerService;

    @GetMapping("/list")
    @Operation(summary = "전체 고객 목록 조회")
    public Response<PagedList<Customer>> getAllCustomers(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                        @RequestParam(value = "count", defaultValue = "10") int count){
        return customerService.getAllCustomers(offset, count);
    }

    @GetMapping("/{customerId}")
    @Operation(summary = "단일 고객 상세 조회")
    public Response<OrderListDto> getCustomerById(@PathVariable String customerId){
        return customerService.getCustomerById(customerId);
    }

    @PostMapping
    @Operation(summary = "고객 등록")
    public Response<Customer> createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @PutMapping
    @Operation(summary = "고객 정보 수정")
    public Response<Customer> updateCustomer(@RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping
    @Operation(summary = "고객 삭제")
    public Response<String> deleteCustomer(@RequestBody Customer customer){
        return customerService.deleteCustomer(customer);
    }
}