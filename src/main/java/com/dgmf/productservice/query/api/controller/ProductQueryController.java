package com.dgmf.productservice.query.api.controller;

import com.dgmf.productservice.command.api.model.ProductRestModel;
import com.dgmf.productservice.query.api.queries.GetProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {
    private QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }


    @GetMapping
    public List<ProductRestModel> getAllProducts() {
        GetProductsQuery getProductsQuery = new GetProductsQuery();

        // Using QueryGateway to query "getProductsQuery"
        List<ProductRestModel> productRestModels = queryGateway.query(getProductsQuery,
                // Response type back (list of "ProductRestModel")
                ResponseTypes.multipleInstancesOf(ProductRestModel.class))
                .join();

        return productRestModels;
    }
}
