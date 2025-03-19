package com.example.demo.business.Impl;

import com.example.demo.business.ICreateProduct;
import com.example.demo.domain.CreateProductsRequest;
import com.example.demo.domain.CreateProductsResponse;
import com.example.demo.repository.ProductEntity;
import com.example.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CreateProductImpl implements ICreateProduct {
    private final ProductRepository productRepository;
    @Override
    @Transactional
    public CreateProductsResponse createProduct(CreateProductsRequest productsRequest) {
        ProductEntity entity = saveProduct(productsRequest);
        return CreateProductsResponse.builder().id(entity.getId()).build();
    }


    private ProductEntity saveProduct(CreateProductsRequest productsRequest){
        ProductEntity productEntity = ProductEntity.builder()
                .name(productsRequest.getName())
                .price(productsRequest.getPrice())
                .description(productsRequest.getDescription())
                .quantity(productsRequest.getQuantity())
                .build();
        return productRepository.save(productEntity);
    }
}
