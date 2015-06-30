package net.nemerosa.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DefaultResourceContext implements ResourceContext {

    private final URIBuilder uriBuilder;

    public DefaultResourceContext(URIBuilder uriBuilder) {
        this.uriBuilder = uriBuilder;
    }

    @Override
    public URI uri(Object methodInvocation) {
        return uriBuilder.build(methodInvocation);
    }

    @Override
    public LinksBuilder links() {
        return new DefaultLinksBuilder();
    }

    protected class DefaultLinksBuilder implements LinksBuilder {

        private final Map<String, Link> links = new LinkedHashMap<>();

        @Override
        public LinksBuilder link(Link link) {
            links.put(link.getName(), link);
            return this;
        }

        @Override
        public LinksBuilder link(String name, URI uri) {
            return link(Link.of(name, uri));
        }

        @Override
        public LinksBuilder self(Object methodInvocation) {
            return link(Link.SELF, methodInvocation);
        }

        @Override
        public LinksBuilder link(String name, Object methodInvocation) {
            return link(name, uri(methodInvocation));
        }

        @Override
        public LinksBuilder link(String name, Object methodInvocation, boolean test) {
            if (test) {
                return link(name, methodInvocation);
            } else {
                return this;
            }
        }

        @Override
        public LinksBuilder page(String name, String path, Object... arguments) {
            return link(name, uriBuilder.page(path, arguments));
        }

        @Override
        public LinksBuilder page(String name, boolean allowed, String path, Object... arguments) {
            if (allowed) {
                return page(name, path, arguments);
            } else {
                return this;
            }
        }

        @Override
        public List<Link> build() {
            return new ArrayList<>(links.values());
        }
    }
}
