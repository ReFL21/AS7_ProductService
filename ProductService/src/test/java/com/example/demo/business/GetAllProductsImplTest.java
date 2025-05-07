package com.example.demo.business;
import com.example.demo.business.Impl.GetAllProductsImpl;
import com.example.demo.domain.GetAllProductsResponse;
import com.example.demo.domain.Product;
import com.example.demo.repository.ProductEntity;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetAllProductsImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetAllProductsImpl getAllProductsImpl;

    @Test
    void getAllProducts_shouldReturnMappedProducts() {
        // Arrange
        ProductEntity e1 = ProductEntity.builder()
                .id("1").name("A").price(12).description("a").quantity(1).build();
        ProductEntity e2 = ProductEntity.builder()
                .id("2").name("B").price(12).description("b").quantity(2).build();
        when(productRepository.findAll()).thenReturn(List.of(e1, e2));

        // Act
        GetAllProductsResponse resp = getAllProductsImpl.getAllProducts();

        // Assert
        assertNotNull(resp);
        List<Product> products = resp.getProducts();
        assertEquals(2, products.size());
        // spot-check that conversion happened (assuming converter copies name)
        assertEquals("A", products.get(0).getName());
        assertEquals("B", products.get(1).getName());

        verify(productRepository).findAll();
    }
}
