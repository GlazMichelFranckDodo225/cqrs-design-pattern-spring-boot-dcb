package com.dgmf.productservice.command.api.aggregate;

import com.dgmf.productservice.command.api.commands.CreateProductCommand;
import com.dgmf.productservice.command.api.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/* Command API Flow ==> Step 3 :
ProductAggregate ==> Command Handler
Whatever the Command send to the CommandGateway, this command
will be handled using the ProductAggregate. */
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
    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        // Performing all validations
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        // Copies des propriétés de la source vers la cible
        BeanUtils.copyProperties(createProductCommand, productCreatedEvent);

        /* Command API Flow ==> Step 4 :
        Event publishing (productCreatedEvent)
        Whatever the operations or the business logic, an event is published.
        That particular event, "productCreatedEvent", is being published
        and stored in the Event Store */
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
