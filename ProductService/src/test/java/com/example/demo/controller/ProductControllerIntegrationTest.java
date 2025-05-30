package com.example.demo.controller;

import com.example.demo.business.*;
import com.example.demo.domain.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IGetAllProducts getAllProducts;

    @MockitoBean
    private IGetProductByID getProductByID;

    @MockitoBean
    private ICreateProduct createProduct;

    @MockitoBean
    private IDeleteProduct deleteProduct;

    @MockitoBean
    private IUpdateProduct updateProduct;


    @Test
    @DisplayName("GET /products/{id} returns product when found")
    void testGetProductFound() throws Exception {
        // Arrange
        Product sample = new Product("asdasd", "Found",19, "Found product", 99);
        given(getProductByID.getProductById("asdasd")).willReturn(Optional.of(sample));

        // Act & Assert
        mockMvc.perform(get("/products/asdasd").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("asdasd"))
                .andExpect(jsonPath("$.name").value("Found"));
    }

    @Test
    @DisplayName("GET /products/{id} returns 404 when not found")
    void testGetProductNotFound() throws Exception {
        // Arrange
        given(getProductByID.getProductById("99")).willReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/products/99").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /products/create creates a new product")
    void testCreateProduct() throws Exception {
        // Arrange
        CreateProductsRequest request = new CreateProductsRequest("New", 29, "New product", 99);
        CreateProductsResponse resp = CreateProductsResponse.builder().id("asdasd").build();
        given(createProduct.createProduct(Mockito.any(CreateProductsRequest.class))).willReturn(resp);

        // Act & Assert
        mockMvc.perform(post("/products/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("asdasd"));

    }

    @Test
    @DisplayName("DELETE /products/{id} deletes product and returns 204")
    void testDeleteProduct() throws Exception {
        // Arrange
        doNothing().when(deleteProduct).deleteProduct("5");

        // Act & Assert
        mockMvc.perform(delete("/products/5"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("PUT /products/update updates product and returns updated data")
    void testUpdateProduct() throws Exception {
        // Arrange
        UpdateProductsRequest request = new UpdateProductsRequest("asd", "Updated","Updated desc", 40, 99);
        UpdateProductsResponse resp = new UpdateProductsResponse("asd", "Updated", "Updated desc",40, 99);
        given(updateProduct.updateProduct(Mockito.any(UpdateProductsRequest.class))).willReturn(resp);

        // Act & Assert
        mockMvc.perform(put("/products/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("asd"))
                .andExpect(jsonPath("$.name").value("Updated"));
    }
}
