## Change log
----------------------

Version 3.1-SNAPSHOT
-------------

ADDED:

- new test dependency lombok in version 1.18.34
- new test dependency jsoup in version 1.18.1
- new unit tests for extract large map from html and transform to json

CHANGED:

- rename of module to new name 'io.github.astrapisixtynine.json.extensions'
- update gradle to new version 8.10.2
- update of gradle plugin dependency com.github.ben-manes.versions.gradle.plugin to new version 0.51.0
- update of gradle-plugin dependency 'org.ajoberstar.grgit:grgit-gradle' to new version 5.3.0
- update of gradle-plugin dependency with id 'com.diffplug.spotless' to new beta version 7.0.0.BETA2
- update of gradle plugin dependency io.freefair.gradle:lombok-plugin to new version 8.10.2
- update of main dependency json to new version 20240303
- update of main dependency jackson-* to new version 2.18.0
- update of test dependency throwable to new version 3
- update of test dependency silly-bean to new version 3.0
- update of test dependency silly-collection to new version 28.1
- update of test dependency file-worker to new version 18.0
- update of test dependency test-object to new version 9
- update of test dependency hamcrest-core to new version 3.0
- update of test dependency mockito-core to new version 5.14.1
- update of test dependency testng to new version 7.10.2
- dependencies are managed now over bundles

Version 3
-------------

ADDED:

- new factory class JavaTypeFactory

CHANGED:

- update of jdk to version 17
- update gradle to new version 8.4
- update of gradle plugin dependency com.github.ben-manes.versions.gradle.plugin to new version 0.49.0
- update of gradle-plugin dependency 'org.ajoberstar.grgit:grgit-gradle' to new version 5.2.1
- update of gradle plugin dependency io.freefair.gradle:lombok-plugin to new version 6.22.0
- update of main dependency json to new version 20231013
- update of main dependency jackson-* to new version 2.16.0-rc1
- update of test dependency silly-collection to new version 27
- update of test dependency xml-jackson-extensions to new version 2.1
- update of test dependency file-worker to new version 17.1
- update of test dependency test-object to new version 8.2
- update of test dependency testng to new version 7.8.0

Version 2.4
-------------

ADDED:

- new test dependency xml-jackson-extensions in version 1.1
- new method that takes a flag that indicates if a xml header should be added
- new method that takes a flag that indicates if a xml root tag with name should be added
- new extension class YamlFileToObjectExtensions
- new extension class YamlStringToObjectExtensions
- new extension class YamlToJsonExtensions
- new extension class ObjectToYamlExtensions
- new extension class ObjectToYamlFileExtensions
- new extension class XmlToYamlExtensions
- new extension class YamlToXmlExtensions

CHANGED:

- update of main dependency json-path to new version 2.8.0

Version 2.3
-------------

ADDED:

- new dependency com.fasterxml.jackson.module:jackson-module-jsonSchema in same version like the main jackson version
  2.14.2
- new test dependency throwable in version 2.3
- new test dependency xml-extensions in version 8.2

CHANGED:

- update gradle to new version 8.0.2
- update of gradle-plugin dependency of com.diffplug.spotless:spotless-plugin-gradle in version 6.17.0
- removed dependency com.fasterxml.jackson.module:jackson-module-jaxb-annotations

Version 2.2
-------------

ADDED:

- new factory method that created an object mapper with a jaxb annotation introspector
- new converter class ClassToJsonSchemaExtensions that can convert a class object to a json schema
- new dependency jackson-module-jaxb-annotations for processing jaxb annotations
- new converter class XmlToJsonExtensions that converts xml string to json string

CHANGED:

- update gradle to new version 8.0.1
- update of com.github.ben-manes.versions.gradle.plugin to new version 0.46.0
- update of gradle-plugin dependency of com.diffplug.spotless:spotless-plugin-gradle in version 6.16.0
- update of main dependency json to new version 20230227
- update of main dependency jackson-* to new version 2.14.2
- update of test dependency silly-collection to new version 21

Version 2.1
-------------

ADDED:

- new converter class JsonToYamlExtensions that can convert a json string to yaml string
- new converter class JsonToYamlExtensions that can convert a json file to yaml string
- new converter class JsonToYamlExtensions that can write a json file to a new or existing yaml file
- new factory class YAMLMapperFactory for create YAMLMapper objects
- new main dependency com.fasterxml.jackson.dataformat:jackson-dataformat-yaml for convert json to yaml

CHANGED:

- update gradle to new version 7.6
- update of gradle-plugin dependency of 'com.diffplug.spotless:spotless-plugin-gradle' in version 6.13.0
- update of com.github.ben-manes.versions.gradle.plugin to new version 0.44.0
- update of main dependency jackson-* to new version 2.14.1
- update of main dependency json to new version 20220924
- update of test dependency file-worker to new version 11.6
- update of test dependency test-object to new version 7.2
- update of test dependency silly-collection to new version 20.4
- update of test dependency 'com.github.meanbeanlib:meanbean' to new version 3.0.0-M9
- update of test dependency testng to new version 7.7.1
- prepare of project for migration to new gradle version 8

Version 2
-------------

ADDED:

- new method for create java map object from json file
- new method for create java collection object from json file
- new method for create java list object from json file
- new gradle-plugin dependency of 'org.ajoberstar.grgit:grgit-gradle' in version 5.0.0 for create
  git release tags
- new gradle-plugin dependency of 'com.diffplug.spotless:spotless-plugin-gradle' in version 6.9.0
  for format the source files

CHANGED:

- update to jdk version 11
- update gradle to new version 7.5
- update of com.github.ben-manes.versions.gradle.plugin to new version 0.42.0
- update of main dependency jackson-* to new version 2.13.3
- update of main dependency json to new version 20220320
- update of main dependency json-path to new version 2.7.0
- update of test dependency file-worker to new version 8.2
- update of test dependency test-objects to new version 6.1
- update of test dependency silly-collection to new version 18.2

Version 1.2
-------------

ADDED:

- new method for create java object from file
- new class CustomParserFactory that derives from JsonFactory
- new class CustomJsonNodeFactory that derives from JsonFactory
- new dependency com.jayway.jsonpath:json-path in the version 2.6.0
- new class JsonLineNumberResolver that resolves the line number in a json File with a json path

CHANGED:

- update gradle to new version 7.2
- update of main dependency jackson-* to new version 2.13.0
- update of test dependency file-worker to new version 5.10
- update of test dependency test-objects to new version 5.5
- update of test dependency silly-collection to new version 18

Version 1.1
-------------

ADDED:

- new class ObjectToJsonFileExtensions for save directly an object to a json file

CHANGED:

- update gradle to new version 7.1
- changed all dependencies from groupid de.alpharogroup to new groupid io.github.astrapi69
- update gradle-plugin dependency of gradle.plugin.com.hierynomus.gradle.plugins:license-gradle-plugin to new version
  0.16.1
- changed to new package io.github.astrapi69

Version 1
-------------

ADDED:

- new CHANGELOG.md file created
- moved relevant classes from xml-extensions project

Notable links:
[keep a changelog](http://keepachangelog.com/en/1.0.0/) Don’t let your friends dump git logs into changelogs
