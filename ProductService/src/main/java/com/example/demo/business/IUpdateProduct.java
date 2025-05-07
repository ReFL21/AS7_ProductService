package com.example.demo.business;

import com.example.demo.domain.UpdateProductsRequest;
import com.example.demo.domain.UpdateProductsResponse;

public interface IUpdateProduct {
UpdateProductsResponse updateProduct(UpdateProductsRequest request);

    void decrementStock(String s, Integer integer);
}
