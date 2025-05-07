package com.example.demo.business;

import com.example.demo.business.Impl.CreateProductImpl;
import com.example.demo.domain.CreateProductsRequest;
import com.example.demo.domain.CreateProductsResponse;
import com.example.demo.repository.ProductEntity;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CreateProductImplTests {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateProductImpl createProductImpl;

    @Test
    void createProduct_shouldSaveEntityAndReturnResponse() {
        // Arrange
        CreateProductsRequest request = new CreateProductsRequest();
        request.setName("Widget");
        request.setPrice(99);
        request.setDescription("Test widget");
        request.setQuantity(42);

        ProductEntity savedEntity = ProductEntity.builder()
                .id("123")
                .name("Widget")
                .price(99)
                .description("Test widget")
                .quantity(42)
                .build();

        when(productRepository.save(any(ProductEntity.class))).thenReturn(savedEntity);

        // Act
        CreateProductsResponse response = createProductImpl.createProduct(request);

        // Assert
        assertNotNull(response);
       assertEquals("123", response.getId());

      }


}
