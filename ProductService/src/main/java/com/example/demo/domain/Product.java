package com.example.demo.domain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
private long id;
private String name;
private long price;
private String description;
private long quantity;

}
