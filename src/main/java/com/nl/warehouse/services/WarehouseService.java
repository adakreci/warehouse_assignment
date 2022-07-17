package com.nl.warehouse.services;

import com.nl.warehouse.models.Inventory;
import com.nl.warehouse.util.JsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public abstract class WarehouseService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //loading the data from the json files specified in the yaml file
    protected Inventory loadFile(String filename) throws IOException {
        File file = null;
        file = ResourceUtils.getFile(filename);
        if (!file.exists()) {
            file = ResourceUtils.getFile("classpath:" + filename);
        }
        String json = new String(Files.readAllBytes(file.toPath()));
        return JsonConverter.jsonToObject(json, Inventory.class);
    }

}
