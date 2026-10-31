# Overview

<div style="text-align: center">

[![Java CI with Gradle](https://github.com/astrapi69/json-extensions/actions/workflows/gradle.yml/badge.svg)](https://github.com/astrapi69/json-extensions/actions/workflows/gradle.yml)
[![Coverage Status](https://codecov.io/gh/astrapi69/json-extensions/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/json-extensions)
[![Open Issues](https://img.shields.io/github/issues/astrapi69/json-extensions.svg?style=flat)](https://github.com/astrapi69/json-extensions/issues)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.astrapi69/json-extensions)](https://central.sonatype.com/artifact/io.github.astrapi69/json-extensions)
[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/json-extensions.svg)](http://www.javadoc.io/doc/io.github.astrapi69/json-extensions)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)
[![Donate](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)
</div>

Useful extensions methods and utilities for transform json string to java object and back to json string

If you like this project put a ⭐ and donate

## Note

No animals were harmed in the making of this library.

## License

The source code comes under the liberal MIT License, making json-extensions great for all types of applications.

## Import dependencies to your project

<details>
  <summary>gradle (click to expand)</summary>

## gradle dependency

Replace the variable ${latestVersion} with the current latest
version: [![Maven Central](https://img.shields.io/maven-central/v/io.github.astrapi69/json-extensions)](https://central.sonatype.com/artifact/io.github.astrapi69/json-extensions)

You can first define the version in the ext section and add than the following gradle dependency to
your project `build.gradle` if you want to import the core functionality of json-extensions:

define version in file gradle.properties

```
jsonExtensionsVersion=${latestVersion}
```

or in build.gradle ext area

```
    jsonExtensionsVersion = "${latestVersion}"
```

then add the dependency to the dependencies area

```
    implementation("io.github.astrapi69:json-extensions:$jsonExtensionsVersion")
```

# with new libs.versions.toml file

If you use the new libs.versions.toml file for new automatic catalog versions update

```
[versions]
```
```
json-extensions-version= "${latestVersion}"
```
```
[libraries]
```
```
json-extensions = { module = "io.github.astrapi69:json-extensions", version.ref = "json-extensions-version" }
```

then add the dependency to the dependencies area

```
    implementation libs.json.extensions
```

</details>

<details>
  <summary>Maven (click to expand)</summary>

## Maven dependency

The library is on Maven Central. Check out [Maven Central](https://central.sonatype.com/artifact/io.github.astrapi69/json-extensions) for the latest release.

Add the following maven dependency to your project `pom.xml` if you want to import the core
functionality of json-extensions:

Then you can add the dependency to your dependencies:

    <properties>
        ...

```xml
        <!-- json-extensions version -->
        <json-extensions.version>${latestVersion}</json-extensions.version>
```

        ...
    </properties>
        ...
        <dependencies>
        ...

```xml
            <!-- json-extensions DEPENDENCY -->
            <dependency>
                <groupId>io.github.astrapi69</groupId>
                <artifactId>json-extensions</artifactId>
                <version>${json-extensions.version}</version>
            </dependency>
```

        ...
        </dependencies>

</details>


<details>
  <summary>Snapshots (click to expand)</summary>

## 📸 Snapshots

[![Snapshot](https://img.shields.io/badge/dynamic/xml?url=https://central.sonatype.com/repository/maven-snapshots/io/github/astrapi69/json-extensions/maven-metadata.xml&label=snapshot&color=red&query=.//versioning/latest)](https://central.sonatype.com/repository/maven-snapshots/io/github/astrapi69/json-extensions/maven-metadata.xml)

This section describes how to import snapshot versions into your project.
Add the following code snippet to your gradle file in the repositories section:

```
repositories {
   //...
```

```groovy
    maven {
        name "Central Portal Snapshots"
        url "https://central.sonatype.com/repository/maven-snapshots/"
        mavenContent {
            snapshotsOnly()
        }
    }
```

```
}
```

</details>

## Support this project

If json-extensions is useful to you, a star on [GitHub](https://github.com/astrapi69/json-extensions) helps others find it.
Sharing it or reporting a bug helps just as much.

If you would like to contribute financially, the channels are on the repository's Sponsor button, which
reads [.github/FUNDING.yml](.github/FUNDING.yml): GitHub Sponsors, Liberapay, Ko-fi and PayPal.

## Semantic Versioning

The versions of json-extensions are maintained with the Semantic Versioning guidelines.

Release version numbers will be incremented in the following format:

`<major>.<minor>.<patch>`

For detailed information on versioning you can visit
the [wiki page](https://github.com/lightblueseas/mvn-parent-projects/wiki/Semantic-Versioning).

## Want to Help and improve it? ###

The source code for json-extensions are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [astrapi69/json-extensions/fork](https://github.com/astrapi69/json-extensions/fork)

To share your changes, [submit a pull request](https://github.com/astrapi69/json-extensions/pull/new/develop).

Don't forget to add new units tests on your changes.

## Contacting the Developers

Do not hesitate to contact the json-extensions developers with your questions, concerns, comments, bug reports, or
feature requests.

- Feature requests, questions and bug reports can be reported at
  the [issues page](https://github.com/astrapi69/json-extensions/issues).

## Credits

|                                                                                                     **Maven Central**                                                                                                      |
|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| [![Maven Central](https://img.shields.io/maven-central/v/io.github.astrapi69/json-extensions?style=for-the-badge)](https://central.sonatype.com/artifact/io.github.astrapi69/json-extensions) |
|                                                 Special thanks to [sonatype repository](https://www.sonatype.com) for providing a free maven repository service for open source projects                                                 |
|                                                                                                            <img width=1000/>                                                                                                             |

|                                                                    **codecov.io**                                                                     |
|:-----------------------------------------------------------------------------------------------------------------------------------------------------:|
| [![Coverage Status](https://codecov.io/gh/astrapi69/json-extensions/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/json-extensions) |
|                    Special thanks to [codecov.io](https://codecov.io) for providing a free code coverage for open source projects                     |
|                                                                   <img width=1000/>                                                                   |

|                                                                  **javadoc.io**                                                                   |
|:-------------------------------------------------------------------------------------------------------------------------------------------------:|
| [![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/json-extensions.svg)](http://www.javadoc.io/doc/io.github.astrapi69/json-extensions) |
|             Special thanks to [javadoc.io](http://www.javadoc.io) for providing a free javadoc documentation for open source projects             |
|                                                                 <img width=1000/>                                                                 |
