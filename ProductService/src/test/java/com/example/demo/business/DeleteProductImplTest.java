package com.example.demo.business;
import com.example.demo.business.Impl.DeleteProductImpl;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteProductImplTest {
    @Mock
    private
    ProductRepository productRepository;

    @InjectMocks
    private DeleteProductImpl deleteProductImpl;

    @Test
    void deleteProduct_shouldInvokeRepositoryDeleteById() {
        // Arrange
        String id = "abc-123";

        // Act
        deleteProductImpl.deleteProduct(id);

        // Assert
        verify(productRepository, times(1)).deleteById("abc-123");
    }
}
