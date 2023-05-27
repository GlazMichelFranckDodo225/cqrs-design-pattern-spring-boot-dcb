package com.dgmf.productservice.query.api.controller;

import com.dgmf.productservice.command.api.model.ProductRestModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {
    @GetMapping
    public List<ProductRestModel> getAllProducts() {
        return null;
    }
}
