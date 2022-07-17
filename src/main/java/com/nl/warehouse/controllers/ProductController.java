package com.nl.warehouse.controllers;

import com.nl.warehouse.exceptions.ProductNotFoundException;
import com.nl.warehouse.models.Product;
import com.nl.warehouse.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable String name) {
        log.info("Getting product {}", name);
        Product product = productService.getProduct(name);
        if (product == null) {
            throw new ProductNotFoundException("Product " + name + " not found");
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sellProduct(@PathVariable String name) {
        log.info("Selling product {}", name);
        productService.sellProduct(name);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
