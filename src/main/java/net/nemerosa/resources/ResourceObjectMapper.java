package net.nemerosa.resources;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Wrapper around an {@link ObjectMapper}, aware of {@link Resource}s and {@link LinkContainer}s.
 */
public interface ResourceObjectMapper {

    ObjectMapper getObjectMapper();

    ResourceContext getResourceContext();

    String write(Object o) throws JsonProcessingException;

    void write(JsonGenerator jgen, Object o) throws IOException;

    void write(OutputStream out, Object o) throws IOException;

    void write(OutputStream out, Object o, Class<?> view) throws IOException;

    String write(Object o, Class<?> view) throws JsonProcessingException;

    void write(JsonGenerator jgen, Object o, Class<?> view) throws IOException;
}
