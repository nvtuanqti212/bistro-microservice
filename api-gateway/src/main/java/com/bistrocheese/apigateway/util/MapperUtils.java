package com.bistrocheese.apigateway.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MapperUtils {
    ObjectMapper objectMapper = new ObjectMapper();

    public <T> T objectMapper(Object object, Class<T> contentClassType) {
        return objectMapper.convertValue(object, contentClassType);
    }
}
