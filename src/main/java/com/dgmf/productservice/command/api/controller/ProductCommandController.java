package com.dgmf.productservice.command.api.controller;

import com.dgmf.productservice.command.api.model.ProductRestModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductCommandController {
    @PostMapping
    public String addProduct(@RequestBody ProductRestModel productRestModel) {
        return "Product Added";
    }
}
