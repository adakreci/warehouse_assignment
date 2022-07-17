package com.nl.warehouse.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Article implements Serializable {
    @JsonProperty("art_id")
    private Long id;
    private String name;
    private Long stock;
}
