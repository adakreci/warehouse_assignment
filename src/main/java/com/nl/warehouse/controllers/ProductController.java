package com.nl.warehouse.controllers;

import com.nl.warehouse.models.Product;
import com.nl.warehouse.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Product>> findAll() {
        return new ResponseEntity<>(productService.findAllAvailable(), HttpStatus.OK);
    }

}
