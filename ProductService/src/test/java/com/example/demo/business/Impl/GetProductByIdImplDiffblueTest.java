package com.example.demo.business.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductEntity;
import com.example.demo.repository.ProductRepository;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {GetProductByIdImpl.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class GetProductByIdImplDiffblueTest {
    @Autowired
    private GetProductByIdImpl getProductByIdImpl;

    @MockitoBean
    private ProductRepository productRepository;

    /**
     * Test {@link GetProductByIdImpl#getProductById(String)}.
     * <p>
     * Method under test: {@link GetProductByIdImpl#getProductById(String)}
     */
    @Test
    @DisplayName("Test getProductById(String)")
    @Tag("MaintainedByDiffblue")
    void testGetProductById() {
        // Arrange
        ProductEntity productEntity = new ProductEntity();
        productEntity.setDescription("The characteristics of someone or something");
        productEntity.setId("42");
        productEntity.setName("Name");
        productEntity.setPrice(1L);
        productEntity.setQuantity(1L);
        Optional<ProductEntity> ofResult = Optional.of(productEntity);
        when(productRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Optional<Product> actualProductById = getProductByIdImpl.getProductById("42");

        // Assert
        verify(productRepository).findById(eq("42"));
        Product getResult = actualProductById.get();
        assertEquals("42", getResult.getId());
        assertEquals("Name", getResult.getName());
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        assertEquals(1L, getResult.getPrice());
        assertEquals(1L, getResult.getQuantity());
        assertTrue(actualProductById.isPresent());
    }
}
