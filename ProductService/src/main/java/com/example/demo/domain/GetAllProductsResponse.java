package com.example.demo.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class GetAllProductsResponse {
    List<Product> products;
}
