package com.nl.warehouse.controllers;

import com.nl.warehouse.models.Article;
import com.nl.warehouse.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Long, Article>> findAll() {
        return new ResponseEntity<>(inventoryService.findAll(), HttpStatus.OK);
    }


}
