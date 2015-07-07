package net.nemerosa.resources.json.jsr310;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.nemerosa.resources.json.ObjectMapperFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class JDKLocalDateTimeSerializerTest {

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = ObjectMapperFactory.create();
        objectMapper.registerModule(new JSR310Module());
    }

    @Test
    public void toJson() throws JsonProcessingException {
        assertEquals(
                "\"2014-03-20T20:44:00Z\"",
                objectMapper.writeValueAsString(LocalDateTime.of(2014, 3, 20, 20, 44))
        );
    }

    @Test
    public void toJson_null() throws JsonProcessingException {
        assertEquals(
                "{\"time\":null}",
                objectMapper.writeValueAsString(new LDTContainer(null))
        );
    }

    @Test
    public void fromJson_null() throws IOException {
        assertNull(
                objectMapper.readValue("{}", LDTContainer.class).getTime()
        );
    }

    @Test
    public void fromJson_blank() throws IOException {
        assertNull(
                objectMapper.readValue("\"\"", LocalDateTime.class)
        );
    }

    @Test
    public void fromJson_with_zone() throws IOException {
        assertEquals(
                LocalDateTime.of(2014, 3, 20, 7, 30),
                objectMapper.readValue("\"2014-03-20T07:30:00.000Z\"", LocalDateTime.class)
        );
    }

    @Test
    public void fromJson_without_zone() throws IOException {
        assertEquals(
                LocalDateTime.of(2014, 3, 20, 7, 30),
                objectMapper.readValue("\"2014-03-20T07:30:00.000\"", LocalDateTime.class)
        );
    }

    @Test
    public void fromJson_without_millis() throws IOException {
        assertEquals(
                LocalDateTime.of(2014, 3, 20, 7, 30),
                objectMapper.readValue("\"2014-03-20T07:30:00\"", LocalDateTime.class)
        );
    }

    @Test
    public void fromJson_without_seconds() throws IOException {
        assertEquals(
                LocalDateTime.of(2014, 3, 20, 7, 30),
                objectMapper.readValue("\"2014-03-20T07:30\"", LocalDateTime.class)
        );
    }

}
