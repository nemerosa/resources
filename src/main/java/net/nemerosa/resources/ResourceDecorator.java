package net.nemerosa.resources;

import java.util.Collections;
import java.util.List;

public interface ResourceDecorator<T> {

    default List<Link> links(T resource, ResourceContext resourceContext) {
        return Collections.emptyList();
    }

    boolean appliesFor(Class<?> beanClass);

    /**
     * This method is called to give this decorator an opportunity to change the content of the model object
     * before it is serialized. A typical use is the obfuscation of sensitive data before it is sent to the client.
     * <p>
     * By default, this method returns the same bean.
     *
     * @param bean Model object
     * @return Decorated object
     */
    default T decorateBeforeSerialization(T bean) {
        return bean;
    }
}
