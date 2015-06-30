package net.nemerosa.resources;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Configures a {@link ObjectMapper} from an existing {@link ObjectMapper}, and registers
 * several {@link ResourceModule}s in a given {@link ResourceContext}.
 */
public class ResourceObjectMapperFactory {

    private final List<ResourceModule> resourceModules;

    public ResourceObjectMapperFactory(List<ResourceModule> resourceModules) {
        this.resourceModules = resourceModules;
    }

    public ObjectMapper configure(ObjectMapper origMapper, ResourceContext resourceContext) {
        ObjectMapper mapper = origMapper.copy();
        // Registers as JSON modules
        for (ResourceModule resourceModule : resourceModules) {
            mapper = mapper.registerModule(new JSONResourceModule(resourceModule, resourceContext));
        }
        // OK
        return mapper;
    }
}
