package com.detectify.challenge.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Created by vinodjagwani on 11/27/18.
 */
@Slf4j
public class MockUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @SuppressWarnings("unchecked")
    public static <T> T getResource(String resource, Class<T> clazz) {
        InputStream inStream = MockUtils.class.getClassLoader().getResourceAsStream(resource);
        try {
            if (String.class.equals(clazz)) {
                return (T) new BufferedReader(new InputStreamReader(inStream)).lines()
                        .collect(Collectors.joining("\n"));
            }
            return MAPPER.readValue(inStream, clazz);
        } catch (IOException e) {
            log.error("Can't serialize: {}", e);
        }
        return null;
    }
}
