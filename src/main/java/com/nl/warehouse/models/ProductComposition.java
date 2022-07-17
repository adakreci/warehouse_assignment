package com.nl.warehouse.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductComposition implements Serializable {
    @JsonProperty("art_id")
    private Long id;
    @JsonProperty("amount_of")
    private Long amount;
}
