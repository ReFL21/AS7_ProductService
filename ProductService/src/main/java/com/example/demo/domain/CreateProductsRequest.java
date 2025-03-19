package com.example.demo.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductsRequest {
    @NotBlank
    private String name;
    @NotNull
    private long price;
    @NotBlank
    private String description;
    @NotNull
    private long quantity;
}
