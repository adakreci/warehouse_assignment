package com.nl.warehouse.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Inventory {
    @JsonProperty("inventory")
    private List<Article> articles;
    private List<Product> products;
}
