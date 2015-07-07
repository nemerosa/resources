package net.nemerosa.resources.json.jsr310;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.nemerosa.resources.json.ObjectMapperFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class JDKLocalTimeSerializerTest {

    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        objectMapper = ObjectMapperFactory.create();
        objectMapper.registerModule(new JSR310Module());
    }

    @Test
    public void toJson() throws JsonProcessingException {
        assertEquals(
                "\"20:44\"",
                objectMapper.writeValueAsString(LocalTime.of(20, 44))
        );
    }

    @Test
    public void toJson_null() throws JsonProcessingException {
        assertEquals(
                "{\"time\":null}",
                objectMapper.writeValueAsString(new LTContainer(null))
        );
    }

    @Test
    public void fromJson() throws IOException {
        assertEquals(
                LocalTime.of(7, 30),
                objectMapper.readValue("\"07:30\"", LocalTime.class)
        );
    }

    @Test
    public void fromJson_null() throws IOException {
        assertNull(
                objectMapper.readValue("{\"time\":null}", LTContainer.class).getTime()
        );
    }

    @Test
    public void fromJson_blank() throws IOException {
        assertNull(
                objectMapper.readValue("{\"time\":\"\"}", LTContainer.class).getTime()
        );
    }

    @Test
    public void fromJson_none() throws IOException {
        assertNull(
                objectMapper.readValue("{}", LTContainer.class).getTime()
        );
    }

}
