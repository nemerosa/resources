The *Resources* project provides utilities to deal with _resources_ in a REST Java application, adding means to decorate returned objects with links to other resources, without to have to annotate your object model.

## List of modules

[Resources](resources/README)

> Model for the resources, independent from any representation or framework.

[JSON Resources](resources-json/README)

> General JSON utilities - in particular support for `@Data` Lombok annotations.

[JSON JSR310 Resources](resources-json-jsr310/README)

> JSON support for JDK8 DateTime API.

[Spring Resources](spring-resources/README)

> Integration of the resources model and their JSON representation into the Spring Framework.

## Dependencies

Main dependencies are:

* FasterXML Jackson 2.5.4
* Spring Framework 4.1.5

## Developing

### Importing in Intellij

* The Lombok plugin must be installed
* Do not forget to enable annotation processing in _Preferences > Compiler > Annotation processors_

## History

This library has been copied from the nemerosa/ontrack project.
