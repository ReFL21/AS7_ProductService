package com.example.demo.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class ProductEntity {
    @Id
    private String id;

    private long price;
    private long quantity;

    @NotNull
    private String name;

    @NotNull
    private String description;
}
