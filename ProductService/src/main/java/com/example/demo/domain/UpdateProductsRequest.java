package com.example.demo.domain;
import jakarta.validation.constraints.NotNull;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductsRequest {
    @NotNull
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private long price;
    @NotNull
    private long quantity;
}
