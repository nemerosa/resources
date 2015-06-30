package net.nemerosa.resources;

import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Jackson module which registers all {@link ResourceDecorator} for a given {@link ResourceModule} in a
 * {@link ResourceContext}.
 */
public class JSONResourceModule extends SimpleModule {

    private final ResourceModule resourceModule;
    private final ResourceContext resourceContext;

    public JSONResourceModule(ResourceModule resourceModule, ResourceContext resourceContext) {
        super(resourceModule.getClass().getSimpleName());
        this.resourceModule = resourceModule;
        this.resourceContext = resourceContext;
    }

    @Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);
        // Registration
        for (ResourceDecorator<?> resourceDecorator : resourceModule.decorators()) {
            context.addBeanSerializerModifier(
                    new ResourceSerializerModifier<>(
                            resourceContext,
                            resourceDecorator
                    )
            );
        }
    }

}
