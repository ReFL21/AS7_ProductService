package com.example.demo.business.Impl;

import com.example.demo.business.IUpdateProduct;
import com.example.demo.domain.Product;
import com.example.demo.domain.UpdateProductsRequest;
import com.example.demo.domain.UpdateProductsResponse;
import com.example.demo.repository.ProductEntity;
import com.example.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UpdateProductImpl implements IUpdateProduct {

    private final ProductRepository productRepository;
    @Override
    @Transactional
    public UpdateProductsResponse updateProduct(UpdateProductsRequest request) {
        ProductEntity entity = productRepository.findById(request.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id " + request.getId())
                );

        // 2) apply changes
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setPrice(request.getPrice());
        entity.setQuantity(request.getQuantity());

        // 3) save & map to response
        ProductEntity saved = productRepository.save(entity);
        return UpdateProductsResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .description(saved.getDescription())
                .price(saved.getPrice())
                .quantity(saved.getQuantity())
                .build();
    }

    @Override
    public void decrementStock(String productId, Integer qty) {
        ProductEntity p = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));
        p.setQuantity(p.getQuantity() - qty);
        productRepository.save(p);
    }
}
