> This library is still in a very early stage.

## Developing

### Importing in Intellij

* The Lombok plugin must be installed
* Do not forget to enable annotation processing in _Preferences > Compiler > Annotation processors_

### Publishing

The `net:nemerosa:resources` library must be published into the Maven Central repository.

#### Signature configuration

In order for the artifacts to be published in the [Maven Central](http://central.sonatype.org/pages/gradle.html) repository, they must be signed using a GPG key. See the [documentation](http://central.sonatype.org/pages/requirements.html) on how to create a GPG key. Once you have this key, you can configure it in the `~/.gradle/gradle.properties` file.

```
signing.keyId=<short ID of your key>
signing.password=<passphrase for your key>
signing.secretKeyRingFile=<path to your key ring>
```

> :warning: Do not ever put such information in Git! This must stay local to your build environment and protected.

#### Publication configuration

#### Promotion configuration

## History

This library has been copied from the nemerosa/ontrack project.
