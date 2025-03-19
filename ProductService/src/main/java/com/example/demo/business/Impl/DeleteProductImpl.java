package com.example.demo.business.Impl;

import com.example.demo.business.IDeleteProduct;
import com.example.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeleteProductImpl implements IDeleteProduct {
    private final ProductRepository repository;
    @Override
    @Transactional
    public void deleteProduct(Long id){this.repository.deleteById(id);}
}
