package net.nemerosa.resources.json;

import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;

import java.beans.ConstructorProperties;
import java.lang.reflect.Constructor;

/**
 * This introspector is plugged into a Jackson {@link com.fasterxml.jackson.databind.ObjectMapper} in order
 * to setup artificially a {@link com.fasterxml.jackson.annotation.JsonCreator} on constructors generated
 * by <a href="http://projectlombok.org/">Lombok</a> using the {@link lombok.Data} annotation.
 * <p>
 * It uses the fact that Lombok adds the {@link java.beans.ConstructorProperties} annotations on the generated constructors.
 */
public class ConstructorPropertiesAnnotationIntrospector extends NopAnnotationIntrospector {

    @Override
    public PropertyName findNameForDeserialization(Annotated a) {
        if (a instanceof AnnotatedParameter) {
            AnnotatedParameter ap = (AnnotatedParameter) a;
            if (ap.getOwner() instanceof AnnotatedConstructor) {
                AnnotatedConstructor ac = (AnnotatedConstructor) ap.getOwner();
                Constructor<?> c = ac.getAnnotated();
                ConstructorProperties properties = c.getAnnotation(ConstructorProperties.class);
                if (properties != null) {
                    String[] names = properties.value();
                    int index = ap.getIndex();
                    if (index < names.length) {
                        return new PropertyName(
                                names[index]
                        );
                    }
                }
            }
        }
        // Default
        return super.findNameForDeserialization(a);
    }
}
