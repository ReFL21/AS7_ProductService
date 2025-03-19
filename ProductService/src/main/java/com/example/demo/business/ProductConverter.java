package com.example.demo.business;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductEntity;

public class ProductConverter {
    private ProductConverter(){}

    public static Product convert(ProductEntity entity){
        return Product.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .description(entity.getDescription())
                .build();
    }
}
