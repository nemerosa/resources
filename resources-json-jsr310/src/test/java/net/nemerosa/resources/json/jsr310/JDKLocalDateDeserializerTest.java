package net.nemerosa.resources.json.jsr310;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class JDKLocalDateDeserializerTest {

    @Test
    public void null_string() {
        assertNull(JDKLocalDateDeserializer.parse(null));
    }

    @Test
    public void blank_string() {
        assertNull(JDKLocalDateDeserializer.parse(""));
    }

    @Test
    public void date_only() {
        assertEquals(
                LocalDate.of(2014, 7, 14),
                JDKLocalDateDeserializer.parse("2014-07-14")
        );
    }

    @Test
    public void date_time_zone() {
        assertEquals(
                LocalDate.of(2014, 7, 14),
                JDKLocalDateDeserializer.parse("2014-07-14T22:00:00.000Z")
        );
    }

    @Test
    public void date_time() {
        assertEquals(
                LocalDate.of(2014, 7, 14),
                JDKLocalDateDeserializer.parse("2014-07-14T22:00:00.000")
        );
    }

}