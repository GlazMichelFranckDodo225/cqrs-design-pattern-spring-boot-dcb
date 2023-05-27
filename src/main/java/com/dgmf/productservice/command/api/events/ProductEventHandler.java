package com.dgmf.productservice.command.api.events;

import com.dgmf.productservice.command.api.data.Product;
import com.dgmf.productservice.command.api.repository.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/* Command API Flow ==> Step 5 :
This Event Handler listens the "productCreatedEvent".
Whenever a "productCreatedEvent" occurs, ProductEventHandler uses
the Repository to store the data in the Database. */
@Component
@ProcessingGroup("product")
public class ProductEventHandler {
    private ProductRepository productRepository;

    public ProductEventHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) throws Exception {
        Product product = new Product();
        BeanUtils.copyProperties(productCreatedEvent, product);
        productRepository.save(product);

        // In the case where there is an exception after saving data
        throw new Exception("Exception Occured");
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }
}
