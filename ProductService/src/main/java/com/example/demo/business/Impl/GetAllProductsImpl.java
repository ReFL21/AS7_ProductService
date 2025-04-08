package com.example.demo.business.Impl;

import com.example.demo.business.IGetAllProducts;
import com.example.demo.business.ProductConverter;
import com.example.demo.domain.GetAllProductsResponse;
import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GetAllProductsImpl implements IGetAllProducts {
    private ProductRepository productRepository;

    @Override
    @Transactional
    public GetAllProductsResponse getAllProducts() {
        List<Product> result;
        result = productRepository.findAll()
                .stream()
                .map(ProductConverter::convert)
                .toList();

        return GetAllProductsResponse.builder()
                .products(result)
                .build();
    }
}
