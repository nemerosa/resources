Resources module
================

The *Resources* module declares a `Resource` and a `Resources` class, which can be used to represent resources. `ResourceDecorator` can also be used to decorate arbitrary objects, using an `URIBuilder`.

### `Resource` and `Resources`

Both objects allow to associate an object or a collection of objects with some named links.

> Since standard objects could benefit from the `Resource` capabilities, this class will be seldom used whereas the `Resources` class will allow to represent annotated collections of resources.

The `Resources` holds a collection of objects and allows to decorate it with named links.

```java
Collection<T> objects = ...
URI uri = ...
Resources<T> resources = Resources.of(
   objects,
   uri
)
.with("_linkA", anotherURI)
.with("_linkB", yetAnotherURI)
```

In this example, the `uri` variable is the URI which allows to get access to the list of objects, and two other links are associated with the list of objects.

> The URI can be built using an `URIBuilder`.

### `ResourceDecorator`

In your application, you want to focus on our REST end points and your model, and not pollute them with annotations or other methods to link the returned entities to the other end points.

You can declare `ResourceDecorator` implementations for the different classes you have to return, and bind them to your REST framework so that returned JSON representations are automatically annotated with links.

> See the [Spring Resources](../spring-resources) documentation for details about using this with the Spring Web framework.

To declare a decorator:

```java

@Data
public class Person {
   private final int id;
   private final String name;
}

public class PersonResourceDecorator
   extends AbstractResourceDecorator<Person> {
   public PersonResourceDecorator() {
      super(Person.class);
   }
   @Override
   public List<Link> links(Person person, ResourceContext resourceContext) {
      return resourceContext.links()
               .self(on(PersonController.class).getPerson(person.getId()))
                .link("_address", on(PersonController.class).getAddress(person.getId()))
                .link("_contacts", on(PersonController.class).getContacts(person.getId()))
                .link(
                        "_update",
                        on(PersonController.class).getPersonUpdateForm(person.getId()),
                        securityService.hasRole(SecurityRoles.GENERATOR)
                )
                .build();
   }
}
```

The URI are built in a type safe way by binding to the actual controller methods. The [Spring Resources](../spring-resources) provides an implementation of the `URIBuilder` for this purpose.

> Note the use of the `self` method and of a `link` method with a test based on a service call (not described here). Look at the `LinksBuilder` class for a list of all available methods.

Declare your decorators in a `ResourceModule`:

```java
public class MyResourceModule implements ResourceModule {
   @Override
   public Collection<ResourceDecorator<?>> decorators() {
      return Array.asList(
         new PersonResourceDecorator(),
         ...
      );
   }
}
```

You still need to bind those modules into the Jackson `ObjectMapper` you use for the serialisation:

```java
ObjectMapper mapper = ...
URIBuilder uriBuilder = ...
ResourceContext resourceContext = new DefaultResourceContext(uriBuilder);
mapper = new ResourceObjectMapperFactory(
   Arrays.asList(
      new MyResourceModule()
   )
).configure(mapper, resourceContext)
```

> See [Spring Resources](../spring-resources) to know how to automate the binding with Spring.

In the end, given the following object returned by your REST API:

```java
Person person = new Person(5, "Damien");
```

When the decorator is applied, the return JSON will be like:

```json
{
   "id": 5,
   "name": "Damien",
   "_self": "https://host/person/5",
   "_address": "https://host/person/5/address",
   "_contacts": "https://host/person/5/contacts",
   "_update": "https://host/person/5/update"
}
```

The binding of the links to the actual Web address is done b the `URIBuilder`.

### `URIBuilder`

The `URIBuilder` is the magic which allows to bind the creation of the URI with an actual framework. See [Spring Resources](../spring-resources) for more information.
