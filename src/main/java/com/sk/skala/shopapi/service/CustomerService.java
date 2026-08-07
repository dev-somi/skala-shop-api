package com.sk.skala.shopapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import com.sk.skala.shopapi.common.PagedList;
import com.sk.skala.shopapi.common.Response;
import com.sk.skala.shopapi.data.dto.OrderItemDto;
import com.sk.skala.shopapi.data.dto.OrderListDto;
import com.sk.skala.shopapi.data.table.Customer;
import com.sk.skala.shopapi.data.table.OrderItem;
import com.sk.skala.shopapi.exception.ResponseException;
import com.sk.skala.shopapi.exception.Error;
import com.sk.skala.shopapi.exception.ParameterException;
import com.sk.skala.shopapi.repository.CustomerRepository;
import com.sk.skala.shopapi.repository.OrderItemRepository;
import com.sk.skala.shopapi.repository.ProductRepository;
import com.sk.skala.shopapi.tools.StringUtil;

import org.springframework.transaction.annotation.Transactional;

public class CustomerService {
    public CustomerRepository customerRepository;
    public ProductRepository productRepository;
    public OrderItemRepository orderItemRepository;

    // 전체 고객 목록 조회
    public Response<PagedList<Customer>> getAllCustomers(int offset, int count){
        Pageable pageable = PageRequest.of(offset / count, count);
            Page<Customer> page = customerRepository.findAll(pageable);

            PagedList<Customer> pagedList = new PagedList<>(
                page.getContent(),        // 이번 페이지 데이터 리스트
                page.getTotalElements(),  // 전체 개수
                offset,
                count
            );
    
        return new Response<>("전체 고객 목록 조회 성공", pagedList);
    }

    // 단일 고객 및 상품 목록 조회
    @Transactional(readOnly = true)
    public Response<OrderListDto> getCustomerById(String customerId){
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new ResponseException(Error.DATA_NOT_FOUND));
        
        List<OrderItem> orderItems = orderItemRepository.findByCustomerId(customerId);

        List<OrderItemDto> orderItemDtos = orderItems.stream()
            .map(item -> OrderItemDto.builder()
                    .productId(item.getProduct().getId())
                    .productName(item.getProduct().getProductName())
                    .productPrice(item.getProduct().getProductPrice())
                    .quantity(item.getQuantity())
                    .build())
            .collect(Collectors.toList());

        OrderListDto orderListDto = OrderListDto.builder()
        .customerId(customer.getCustomerId())
        .customerPoint(customer.getCustomerPoint())
        .products(orderItemDtos)
        .build();

        return new Response<>("단일 고객 및 상품 목록 조회 성공", orderListDto);      
    }

    // 고객 생성
    @Transactional 
    public Response<Customer> createCustomer(Customer customerSession){
        if (StringUtil.isAnyEmpty(customerSession.getCustomerId(), 
                                    customerSession.getCustomerPassword())){
            throw new ParameterException("customerId", "customerPassword");
        }

        customerRepository.findById(customerSession.getCustomerId())
        .ifPresent(p -> { throw new ResponseException(Error.DATA_DUPLICATED); });

        Customer customer = new Customer(customerSession.getCustomerId(), 1000000.0);  // 초기 포인트는 서버가 고정 지급
        customer.setCustomerPassword(customerSession.getCustomerPassword());
        customerRepository.save(customer);

        return new Response<>("새로운 고객 생성 성공", customer);
    }

    // 고객 정보 업데이트
    @Transactional
    public Response<Customer> updateCustomer(Customer customer){
        Customer updataedCustomer = customerRepository.findById(customer.getCustomerId())
            .orElseThrow(() -> new ResponseException(Error.DATA_NOT_FOUND));

        updataedCustomer.setCustomerPoint(customer.getCustomerPoint());
        customerRepository.save(updataedCustomer);

        return new Response<>("고객 정보 업데이트 성공", updataedCustomer);
    }

    // 고객 삭제
    @Transactional
    public Response<String> deleteCustomer(Customer customer){
        Customer deletedCustomer = customerRepository.findById(customer.getCustomerId())
            .orElseThrow(() -> new ResponseException(Error.DATA_NOT_FOUND));  

        customerRepository.delete(deletedCustomer);

        return new Response<>("고객 삭제 성공", deletedCustomer.getCustomerId());
    }

}
