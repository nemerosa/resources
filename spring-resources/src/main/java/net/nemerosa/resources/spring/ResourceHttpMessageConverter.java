package net.nemerosa.resources.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.nemerosa.resources.*;
import net.nemerosa.resources.json.ObjectMapperFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

public class ResourceHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    public ResourceHttpMessageConverter(
            List<ResourceModule> resourceModules,
            URIBuilder uriBuilder) {
        this(
                ObjectMapperFactory.create(),
                resourceModules,
                uriBuilder
        );
    }

    public ResourceHttpMessageConverter(
            ObjectMapper objectMapper,
            List<ResourceModule> resourceModules,
            URIBuilder uriBuilder) {
        this(
                objectMapper,
                resourceModules,
                new DefaultResourceContext(uriBuilder)
        );
    }

    public ResourceHttpMessageConverter(
            ObjectMapper objectMapper,
            List<ResourceModule> resourceModules,
            ResourceContext resourceContext) {
        super(
                new ResourceObjectMapperFactory(resourceModules).configure(
                        objectMapper,
                        resourceContext
                )
        );
    }
}
