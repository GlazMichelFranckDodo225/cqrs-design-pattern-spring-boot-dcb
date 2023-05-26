package com.dgmf.productservice.command.api.events;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class ProductEventHandler {
    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {

    }
}
