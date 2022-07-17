package com.nl.warehouse.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Product implements Serializable {
    private String name;

    @JsonProperty("contain_articles")
    private List<ProductComposition> productCompositions;
}
