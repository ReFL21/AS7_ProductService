package com.example.demo.business.Impl;

import com.example.demo.business.IGetProductByID;
import com.example.demo.business.ProductConverter;
import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetProductByIdImpl implements IGetProductByID {
    private final ProductRepository repository;
    @Override
    @Transactional
public Optional<Product> getProductById(String id){return repository.findById(id).map(ProductConverter::convert);}

}
