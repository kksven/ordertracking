package com.ordertracking.storage;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonFileMapper {

    private static ObjectMapper mapper = new ObjectMapper();
    public static <T> T getMappedObjectFromFile(String path, Class<T> clazz) throws IOException {
        return mapper.readValue(new File(path), clazz);
        }
}
