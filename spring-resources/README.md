The *Spring Resources* module allows an integration of the [*Resources* module](../resources/README) with the Spring Web framework.

To declare a Spring-aware `URIBuilder` and a message converter which uses yout `ResourceDecorator` instances, configure your Web configuration:

```java
@Component
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private List<ResourceDecorator<?>> resourceDecorators;

    @Bean
    public ResourceModule resourceModule() {
      return new ResourceModule() {
         @Override
         public Collection<ResourceDecorator<?>> decorators() {
            return resourceDecorators;
         }
      }
   }

    @Bean
    public URIBuilder uriBuilder() {
        return new DefaultURIBuilder();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return ObjectMapperFactory.create().registerModule(new JSR310Module());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // Plain text
        converters.add(new StringHttpMessageConverter());
        // JSON
        converters.add(
                new ResourceHttpMessageConverter(
                        objectMapper(),
                        resourceModules,
                        uriBuilder()
                )
        );
    }

}

```

That's it. You can now create your controllers and return model objects, and those will be linked automatically with the registered decorators.
