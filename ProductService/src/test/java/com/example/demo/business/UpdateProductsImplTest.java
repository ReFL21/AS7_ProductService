package com.example.demo.business;

import com.example.demo.business.Impl.ResourceNotFoundException;
import com.example.demo.business.Impl.UpdateProductImpl;
import com.example.demo.domain.UpdateProductsRequest;
import com.example.demo.domain.UpdateProductsResponse;
import com.example.demo.repository.ProductEntity;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateProductsImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private UpdateProductImpl updateProductImpl;

    @Test
    void updateProduct_existingId_shouldReturnUpdatedResponse() {
        // Arrange
        UpdateProductsRequest req = new UpdateProductsRequest();
        req.setId("42");
        req.setName("NewName");
        req.setDescription("NewDesc");
        req.setPrice(19);
        req.setQuantity(7);

        ProductEntity existing = ProductEntity.builder()
                .id("42").name("Old").description("Old").price(10).quantity(1).build();
        when(productRepository.findById("42")).thenReturn(Optional.of(existing));
        when(productRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        UpdateProductsResponse resp = updateProductImpl.updateProduct(req);

        // Assert
        assertNotNull(resp);
        assertEquals("42", resp.getId());
        assertEquals("NewName", resp.getName());
        assertEquals("NewDesc", resp.getDescription());
        assertEquals(19, resp.getPrice());
        assertEquals(7, resp.getQuantity());

        ArgumentCaptor<ProductEntity> captor = ArgumentCaptor.forClass(ProductEntity.class);
        verify(productRepository).save(captor.capture());
        ProductEntity saved = captor.getValue();
        assertEquals(7, saved.getQuantity());
    }

    @Test
    void updateProduct_nonExistingId_shouldThrowResourceNotFoundException() {
        // Arrange
        when(productRepository.findById("99")).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> updateProductImpl.updateProduct(new UpdateProductsRequest("99", "", "", 0, 0))
        );
        assertTrue(ex.getMessage().contains("Product not found with id 99"));
    }

    @Test
    void decrementStock_existingProduct_shouldReduceQuantity() {
        // Arrange
        ProductEntity p = ProductEntity.builder()
                .id("X").quantity(10).build();
        when(productRepository.findById("X")).thenReturn(Optional.of(p));

        // Act
        updateProductImpl.decrementStock("X", 3);

        // Assert
        assertEquals(7, p.getQuantity());
        verify(productRepository).save(p);
    }

    @Test
    void decrementStock_missingProduct_shouldThrowRuntimeException() {
        // Arrange
        when(productRepository.findById("Y")).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                updateProductImpl.decrementStock("Y", 1)
        );
        assertTrue(ex.getMessage().contains("Product not found: Y"));
    }
}
