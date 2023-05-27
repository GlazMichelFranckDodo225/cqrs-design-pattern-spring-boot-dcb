package com.dgmf.productservice.query.api.projection;

import com.dgmf.productservice.command.api.repository.ProductRepository;
import org.springframework.stereotype.Component;

// To handle "getProductsQuery" from the QueryGateway
@Component
public class ProductProjection {
    // To get Data back from the DB
    private ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
