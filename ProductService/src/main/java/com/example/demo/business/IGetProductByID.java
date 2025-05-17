package com.example.demo.business;


import com.example.demo.domain.Product;

import java.util.Optional;

public interface IGetProductByID {
    public Optional<Product> getProductById(String id);
}
