package net.nemerosa.resources;

import org.junit.Test;

import java.net.URI;

import static org.junit.Assert.assertEquals;

public class LinkTest {

    @Test
    public void of() {
        Link link = Link.of("name", URI.create("urn:test"));
        assertEquals("name", link.getName());
        assertEquals("urn:test", link.getHref().toString());
    }

}
