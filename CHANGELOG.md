## Change log
----------------------

Version 2-SNAPSHOT
-------------

ADDED:

- new method for create java map object from json file
- new method for create java collection object from json file
- new method for create java list object from json file

CHANGED:

- update to jdk version 11
- update gradle to new version 7.5

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
- update gradle-plugin dependency of gradle.plugin.com.hierynomus.gradle.plugins:license-gradle-plugin to new version 0.16.1
- changed to new package io.github.astrapi69

Version 1
-------------

ADDED: 

- new CHANGELOG.md file created
- moved relevant classes from xml-extensions project

Notable links:
[keep a changelog](http://keepachangelog.com/en/1.0.0/) Don’t let your friends dump git logs into changelogs
