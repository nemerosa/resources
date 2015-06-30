package net.nemerosa.resources;

import java.util.Collection;

public interface ResourceModule {

    Collection<ResourceDecorator<?>> decorators();

}
