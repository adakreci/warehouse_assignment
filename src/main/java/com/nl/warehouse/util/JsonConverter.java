package com.nl.warehouse.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

    public static final <T> T jsonToObject(String json, Class<T> beanClass) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        T object = null;
        if (beanClass != null && json != null) {
            object = mapper.readValue(json, beanClass);
        }
        return object;
    }

    public static final <T> T jsonToCollection(String json, TypeReference<T> typeReference)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        T object = null;
        if (typeReference != null && json != null) {
            object = mapper.readValue(json, typeReference);
        }
        return object;
    }

}
