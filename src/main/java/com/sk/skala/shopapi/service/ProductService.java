package com.sk.skala.shopapi.service;

import org.springframework.stereotype.Service;

import com.sk.skala.shopapi.common.Response;
import com.sk.skala.shopapi.data.table.Product;
import com.sk.skala.shopapi.exception.ParameterException;
import com.sk.skala.shopapi.exception.ResponseException;
import com.sk.skala.shopapi.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    // 전체 상품 목록 조회
public Response getAllProducts(int offset, int count)
    // - Pageable 객체 생성 (페이징 및 정렬)
    // - productRepository.findAll(pageable)로 페이지 단위 데이터 조회
    // - 결과를 PagedList 객체로 가공
    // - Response 객체에 담아 반환


    // // 개별 상품 상세 조회
    // public Response getProductById(Long id)
    // - ID로 상품(Product) 조회, Optional로 존재 여부 확인 : 오류시 ResponseException(Error.DATA_NOT_FOUND)
    // - Response 객체에 담아 반환


    // // 상품 등록 (생성)
    public Response createProduct(Product product){
    // 입력값 검증 productName 비어있음 or price 0 : 오류시 ParameterException productName, productPrice
        if (product.getProductName().isEmpty() || product.getProductPrice() <= 0) {
            throw new ParameterException();
        }
    
    // 이름 중복 체크 findByProductName : 중복시 ResponseException Error.DATADUPLICATED
    // 신규 Product의 ID는 0L로 세팅JPA가 자동 생성
    // 저장 후 Response 반환
    }



    // // 상품 정보 수정
    // public Response updateProductProduct product
    // 입력값 검증 productName, productPrice : 오류시 ParameterException productName, productPrice
    // 해당 ID의 Product이 존재하는지 확인 : 오류시 ResponseException Error.DATANOTFOUND
    // 있으면 수정 저장 후 Response 반환


    // // 상품 삭제
    // public Response deleteProductProduct product
    // ID로 조회해서 존재하면 삭제, 없으면 예외 처리 : ResponseException Error.DATANOTFOUND
    // 저장 후 Response 반환

}
