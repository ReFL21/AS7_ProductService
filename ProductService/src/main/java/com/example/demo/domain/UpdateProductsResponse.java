package com.example.demo.domain;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductsResponse {
    private String id;
    private String name;
    private String description;
    private long price;
    private long quantity;
}
