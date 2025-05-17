package com.example.demo.controller;

import com.example.demo.business.*;
import com.example.demo.domain.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173"})
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private final IGetProductByID getProductByID;

    @Autowired
    private final ICreateProduct createProduct;

    @Autowired
    private final IGetAllProducts getAllProducts;

    @Autowired
    private final IDeleteProduct deleteProduct;
    @Autowired
    private final IUpdateProduct updateProduct;

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
    public ResponseEntity<Void>deleteProduct(@PathVariable String id){
       deleteProduct.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateProductsResponse> updateProduct(
            @Valid @RequestBody UpdateProductsRequest request
    ) {
        UpdateProductsResponse resp = updateProduct.updateProduct(request);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(value = "id") String id){
        final Optional<Product> product =  getProductByID.getProductById(id);
        if (!product.isEmpty()) return ResponseEntity.ok(product.get());
        return ResponseEntity.notFound().build();
    }

}
