package com.example.demo.MessageBrocker;

import java.util.Map;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductUpdateEvent {
    private Map<String, Integer> quantities;
}
