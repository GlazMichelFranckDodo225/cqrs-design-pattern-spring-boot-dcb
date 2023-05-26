package com.dgmf.productservice.command.api.aggregate;

import com.dgmf.productservice.command.api.commands.CreateProductCommand;
import com.dgmf.productservice.command.api.events.ProductCreatedEvent;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    public ProductAggregate() {
    }

    // Handling of the command
    public ProductAggregate(CreateProductCommand createProductCommand) {
        // Performing all validations
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        // Copies des propriétés de la source vers la cible
        BeanUtils.copyProperties(createProductCommand, productCreatedEvent);
        // Publishing the event
        AggregateLifecycle.apply(productCreatedEvent);
    }

    // Updating of all the fields (State of the command)
    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        this.productId = productCreatedEvent.getProductId();
        this.name = productCreatedEvent.getName();
        this.price = productCreatedEvent.getPrice();
        this.quantity = productCreatedEvent.getQuantity();
    }

}
