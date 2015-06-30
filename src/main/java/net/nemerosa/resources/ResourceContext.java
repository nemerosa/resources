package net.nemerosa.resources;

import java.net.URI;

public interface ResourceContext {

    URI uri(Object methodInvocation);

    /**
     * Gets a builder for links
     */
    LinksBuilder links();

}
