package net.nemerosa.resources.spring;

import net.nemerosa.resources.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;

public abstract class AbstractResourceController {

    @Autowired
    private URIBuilder uriBuilder;

    /**
     * @see org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder#fromMethodCall(Object)
     */
    protected URI uri(Object methodInvocation) {
        return uriBuilder.build(methodInvocation);
    }


}
