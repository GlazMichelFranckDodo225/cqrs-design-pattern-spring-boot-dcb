package com.dgmf.productservice.command.api.events;

import com.dgmf.productservice.command.api.data.Product;
import com.dgmf.productservice.command.api.repository.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/* Command API Flow ==> Step 5 :
This Event Handler listens the "productCreatedEvent".
Whenever a "productCreatedEvent" occurs, ProductEventHandler uses
the Repository to store the data in the Database. */
@Component
public class ProductEventHandler {
    private ProductRepository productRepository;

    public ProductEventHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        Product product = new Product();
        BeanUtils.copyProperties(productCreatedEvent, product);
        productRepository.save(product);
    }
}
