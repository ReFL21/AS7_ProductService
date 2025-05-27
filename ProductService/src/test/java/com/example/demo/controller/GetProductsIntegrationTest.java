package com.example.demo.controller;

import com.example.demo.repository.ProductEntity;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class GetProductsIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        productRepository.deleteAll(); // Clean up before each test
        ProductEntity p1 = new ProductEntity();
        p1.setName("Laptop");
        p1.setPrice(999);
        p1.setQuantity(5);
        p1.setDescription("Test Laptop");

        ProductEntity p2 = new ProductEntity();
        p2.setName("Mouse");
        p2.setPrice(19);
        p2.setQuantity(100);
        p2.setDescription("Test Mouse");

        productRepository.saveAll(List.of(p1, p2));
    }

    @Test
    @Order(1)
    void shouldReturnAllProducts() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.products.length()").value(2))
                .andExpect(jsonPath("$.products[0].name").value("Laptop"))
                .andExpect(jsonPath("$.products[1].name").value("Mouse"));
    }
}
