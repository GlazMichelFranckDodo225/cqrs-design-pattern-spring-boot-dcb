package com.dgmf.productservice.query.api.projection;

import com.dgmf.productservice.command.api.data.Product;
import com.dgmf.productservice.command.api.model.ProductRestModel;
import com.dgmf.productservice.command.api.repository.ProductRepository;
import com.dgmf.productservice.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

// To handle "getProductsQuery" from the QueryGateway
@Component
public class ProductProjection {
    // To get Data back from the DB
    private ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Handling of the "getProductsQuery" from the QueryGateway
    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery getProductsQuery) {
        // Retrieving of all the Products
        List<Product> products = productRepository.findAll();

        // Mapping "products" to "productRestModels"
        List<ProductRestModel> productRestModels = products.stream()
                .map(product -> ProductRestModel.builder()
                        .name(product.getName())
                        .price(product.getPrice())
                        .quantity(product.getQuantity())
                        .build())
                .collect(Collectors.toList());

        return productRestModels;
    }
}
