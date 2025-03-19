package com.example.demo.business;

import com.example.demo.domain.CreateProductsRequest;
import com.example.demo.domain.CreateProductsResponse;

public interface ICreateProduct {
    CreateProductsResponse createProduct(CreateProductsRequest productsRequest);
}
