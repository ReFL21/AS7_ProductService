package com.example.demo.MessageBrocker;

import com.example.demo.business.IUpdateProduct;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductUpdateListener {

    private final IUpdateProduct productService;

    @RabbitListener(queues = RabbitMQConfig.PRODUCT_QUEUE)
    public void handle(ProductUpdateEvent event) {
        event.getQuantities().forEach(
                productService::decrementStock
        );
    }
}
