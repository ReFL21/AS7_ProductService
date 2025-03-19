package com.example.demo.controller;

import com.example.demo.business.ICreateProduct;
import com.example.demo.business.IDeleteProduct;
import com.example.demo.business.IGetAllProducts;
import com.example.demo.domain.CreateProductsRequest;
import com.example.demo.domain.CreateProductsResponse;
import com.example.demo.domain.GetAllProductsResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private final ICreateProduct createProduct;

    @Autowired
    private final IGetAllProducts getAllProducts;

    @Autowired
    private final IDeleteProduct deleteProduct;

    @GetMapping
    public ResponseEntity<GetAllProductsResponse>getAllProducts(){
        GetAllProductsResponse response = getAllProducts.getAllProducts();
        return ResponseEntity.ok(response);
    }
    @PostMapping("/create")
    public ResponseEntity<CreateProductsResponse>createProduct(@RequestBody @Valid CreateProductsRequest productsRequest){
        CreateProductsResponse productsResponse = createProduct.createProduct(productsRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productsResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void>deleteProduct(@PathVariable Long id){
       deleteProduct.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }


}
