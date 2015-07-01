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

In order to get this information, run:

```bash
gpg -K
```

The key ring file path is the first line. The key ID is the string after the / in the `sec` line.

#### Publication configuration

The OSSRH credentials must be available in the `~/.gradle/gradle.properties` file:

```
# OSS publication
ossrhUser = <OSSRH user>
ossrhPassword = <OSSRH password>
```

#### Promotion configuration

#### Release

Run the following command to publish in a staging repository at https://oss.sonatype.org:

```bash
./gradlew clean build publish -Prelease
```

The `release` property enables the signature and the upload.

## History

This library has been copied from the nemerosa/ontrack project.
