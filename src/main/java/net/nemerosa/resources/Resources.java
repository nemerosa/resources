package net.nemerosa.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.net.URI;
import java.util.Collection;

@EqualsAndHashCode(callSuper = false)
@Data
public class Resources<T> extends LinkContainer<Resources<T>> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Pagination pagination;
    private final Collection<T> resources;

    protected Resources(Collection<T> resources, URI self, Pagination pagination) {
        super(self);
        this.pagination = pagination;
        this.resources = resources;
    }

    public static <R> Resources<R> of(Collection<R> resources, URI href) {
        return new Resources<>(resources, href, Pagination.NONE);
    }

    public Resources<T> withPagination(Pagination pagination) {
        return this.pagination == pagination ? this : new Resources<>(this.resources, get_self(), pagination);
    }
}
