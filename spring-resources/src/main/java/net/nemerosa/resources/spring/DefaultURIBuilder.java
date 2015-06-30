package net.nemerosa.resources.spring;

import net.nemerosa.resources.URIBuilder;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static java.lang.String.format;

public class DefaultURIBuilder implements URIBuilder {

    @Override
    public URI build(Object methodInvocation) {
        // Default builder
        return MvcUriComponentsBuilder.fromMethodCall(methodInvocation).build().toUri();
    }

    @Override
    public URI page(String path, Object... arguments) {
        String pagePath = format(
                "/#/%s",
                format(path, arguments)
        );
        return URI.create(
                ServletUriComponentsBuilder.fromCurrentServletMapping().build().toUriString() +
                        pagePath
        );
    }
}
