package com.example.demo.business;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductConverterTest {
    @Test
    void shoudlConvertProductEntityToProduct(){
        ProductEntity productEntity = ProductEntity.builder()
                .id("123asd")
                .name("NvidiaRTX3070ti")
                .price(1200)
                .description("Very solid product for it's price.")
                .quantity(15)
                .build();

        Product actualProduct = ProductConverter.convert(productEntity);

        Product expectedProduct = Product.builder()
                .id("123asd")
                .name("NvidiaRTX3070ti")
                .price(1200)
                .description("Very solid product for it's price.")
                .quantity(15)
                .build();

        assertEquals(expectedProduct, actualProduct);
    }

}
