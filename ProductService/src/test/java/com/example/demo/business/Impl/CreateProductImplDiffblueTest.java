package com.example.demo.business.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.domain.CreateProductsRequest;
import com.example.demo.domain.CreateProductsResponse;
import com.example.demo.repository.ProductEntity;
import com.example.demo.repository.ProductRepository;
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

@ContextConfiguration(classes = {CreateProductImpl.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class CreateProductImplDiffblueTest {
    @Autowired
    private CreateProductImpl createProductImpl;

    @MockitoBean
    private ProductRepository productRepository;

    /**
     * Test {@link CreateProductImpl#createProduct(CreateProductsRequest)}.
     * <p>
     * Method under test: {@link CreateProductImpl#createProduct(CreateProductsRequest)}
     */
    @Test
    @DisplayName("Test createProduct(CreateProductsRequest)")
    @Tag("MaintainedByDiffblue")
    void testCreateProduct() {
        // Arrange
        ProductEntity productEntity = new ProductEntity();
        productEntity.setDescription("The characteristics of someone or something");
        productEntity.setId("42");
        productEntity.setName("Name");
        productEntity.setPrice(1L);
        productEntity.setQuantity(1L);
        when(productRepository.save(Mockito.<ProductEntity>any())).thenReturn(productEntity);

        // Act
        CreateProductsResponse actualCreateProductResult = createProductImpl.createProduct(new CreateProductsRequest());

        // Assert
        verify(productRepository).save(isA(ProductEntity.class));
        assertEquals("42", actualCreateProductResult.getId());
    }

    /**
     * Test {@link CreateProductImpl#createProduct(CreateProductsRequest)}.
     * <ul>
     *   <li>Given {@link ProductEntity} {@link ProductEntity#getId()} return {@code 42}.</li>
     *   <li>Then calls {@link ProductEntity#getId()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link CreateProductImpl#createProduct(CreateProductsRequest)}
     */
    @Test
    @DisplayName("Test createProduct(CreateProductsRequest); given ProductEntity getId() return '42'; then calls getId()")
    @Tag("MaintainedByDiffblue")
    void testCreateProduct_givenProductEntityGetIdReturn42_thenCallsGetId() {
        // Arrange
        ProductEntity productEntity = mock(ProductEntity.class);
        when(productEntity.getId()).thenReturn("42");
        doNothing().when(productEntity).setDescription(Mockito.<String>any());
        doNothing().when(productEntity).setId(Mockito.<String>any());
        doNothing().when(productEntity).setName(Mockito.<String>any());
        doNothing().when(productEntity).setPrice(anyLong());
        doNothing().when(productEntity).setQuantity(anyLong());
        productEntity.setDescription("The characteristics of someone or something");
        productEntity.setId("42");
        productEntity.setName("Name");
        productEntity.setPrice(1L);
        productEntity.setQuantity(1L);
        when(productRepository.save(Mockito.<ProductEntity>any())).thenReturn(productEntity);

        // Act
        CreateProductsResponse actualCreateProductResult = createProductImpl.createProduct(new CreateProductsRequest());

        // Assert
        verify(productEntity).getId();
        verify(productEntity).setDescription(eq("The characteristics of someone or something"));
        verify(productEntity).setId(eq("42"));
        verify(productEntity).setName(eq("Name"));
        verify(productEntity).setPrice(eq(1L));
        verify(productEntity).setQuantity(eq(1L));
        verify(productRepository).save(isA(ProductEntity.class));
        assertEquals("42", actualCreateProductResult.getId());
    }
}
