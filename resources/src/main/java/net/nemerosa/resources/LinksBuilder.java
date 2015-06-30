package net.nemerosa.resources;

import java.net.URI;
import java.util.List;

public interface LinksBuilder {

    LinksBuilder self(Object methodInvocation);

    LinksBuilder link(Link link);

    LinksBuilder link(String name, URI uri);

    LinksBuilder link(String name, Object methodInvocation);

    LinksBuilder link(String name, Object methodInvocation, boolean test);

    LinksBuilder page(String name, String path, Object... arguments);

    LinksBuilder page(String name, boolean allowed, String path, Object... arguments);

    List<Link> build();

}
