package com.example.demo.repository;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table()
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private long price;

    @Column(name = "quantity")
    private long quantity;
    @NotNull
    private String name;
    @NotNull
    private String description;
}
