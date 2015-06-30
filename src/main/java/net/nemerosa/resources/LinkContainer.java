package net.nemerosa.resources;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Data;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public abstract class LinkContainer<L extends LinkContainer<L>> {

    private final URI _self;
    private final Map<String, URI> links = new LinkedHashMap<>();

    public L with(String name, URI uri, boolean authorized) {
        if (authorized) {
            return with(name, uri);
        } else {
            //noinspection unchecked
            return (L) this;
        }
    }

    private L with(Link link) {
        return with(link.getName(), link.getHref());
    }

    public L with(String name, URI uri) {
        links.put(name, uri);
        //noinspection unchecked
        return (L) this;
    }

    @JsonAnyGetter
    public Map<String, URI> getLinks() {
        return links;
    }

}
