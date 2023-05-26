package com.dgmf.productservice.command.api.controller;

import com.dgmf.productservice.command.api.commands.CreateProductCommand;
import com.dgmf.productservice.command.api.model.ProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/* Command API Flow ==> Step 1 :
Creating the Controller that handle the "/products" EndPoint
for the Post Method */
@RestController
@RequestMapping("/products")
public class ProductCommandController {
    private CommandGateway commandGateway;

    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addProduct(@RequestBody ProductRestModel productRestModel) {
        CreateProductCommand createProductCommand =
                CreateProductCommand.builder()
                        .productId(UUID.randomUUID().toString())
                        .name(productRestModel.getName())
                        .price(productRestModel.getPrice())
                        .quantity(productRestModel.getQuantity())
                        .build();

        /* Command API Flow ==> Step 2 :
        From this particular Controller, sending the "createProductCommand"
        to the CommandGateway.
        This "createProductCommand" will be handled by the ProductAggregate*/
        String result = commandGateway.sendAndWait(createProductCommand);

        return result;
    }
}
