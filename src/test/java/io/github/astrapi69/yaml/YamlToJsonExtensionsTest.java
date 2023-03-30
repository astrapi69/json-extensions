/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.yaml;

import static org.testng.AssertJUnit.assertEquals;

import java.io.File;
import java.io.IOException;

import org.meanbean.test.BeanTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.astrapi69.file.delete.DeleteFileExtensions;
import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.file.search.PathFinder;

/**
 * The unit test class for the class {@link YamlToJsonExtensions}
 */
public class YamlToJsonExtensionsTest
{

	File jsonDir;
	File yamlDir;
	File jsonFile;
	File jsonSigninFile;
	File jsonCollectionFile;

	File jsonMapFile;
	File jsonListFile;
	File yamlFile;
	File yamlSigninFile;
	File yamlCollectionFile;

	File yamlMapFile;
	File yamlListFile;

	@BeforeMethod
	protected void setUp()
	{
		jsonDir = new File(PathFinder.getSrcTestResourcesDir(), "json");
		yamlDir = new File(PathFinder.getSrcTestResourcesDir(), "yaml");
		jsonFile = new File(jsonDir, "person.json");
		jsonSigninFile = new File(jsonDir, "signin.json");
		jsonListFile = new File(jsonDir, "employees.json");
		jsonMapFile = new File(jsonDir, "map.json");
		jsonCollectionFile = new File(jsonDir, "collection.json");

		yamlFile = new File(yamlDir, "person.yaml");
		yamlSigninFile = new File(yamlDir, "signin.yaml");
		yamlListFile = new File(yamlDir, "employees.yaml");
		yamlMapFile = new File(yamlDir, "map.yaml");
		yamlCollectionFile = new File(yamlDir, "collection.yaml");
	}

	/**
	 * Test method for {@link YamlToJsonExtensions#toJson(File, File)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToYamlWithJsonFileToYamlFile() throws IOException
	{
		String expected;
		String actual;

		YamlToJsonExtensions.toJson(yamlFile, jsonFile);
		expected = "{\"id\":\"23\",\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"subOrdinates\":[]}\n";
		actual = ReadFileExtensions.fromFile(jsonFile);
		expected = expected.replace("\n", "").replace("\r", "").replace(" ", "");
		actual = actual.replace("\n", "").replace("\r", "").replace(" ", "");
		assertEquals(expected, actual);

		YamlToJsonExtensions.toJson(yamlSigninFile, jsonSigninFile);
		expected = "{\"username\":\"foo\",\"password\":\"bar\"}\n";
		actual = ReadFileExtensions.fromFile(jsonSigninFile);
		expected = expected.replace("\n", "").replace("\r", "").replace(" ", "");
		actual = actual.replace("\n", "").replace("\r", "").replace(" ", "");
		assertEquals(expected, actual);

		YamlToJsonExtensions.toJson(yamlListFile, jsonListFile);
		expected = "[{\"id\":\"23\",\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"subOrdinates\":[]},{\"id\":\"24\",\"person\":{\"name\":\"Andreas\",\"nickname\":\"cute\",\"gender\":\"MALE\",\"about\":\"fine person\",\"married\":false},\"subOrdinates\":[]},{\"id\":\"25\",\"person\":{\"name\":\"Tatjana\",\"nickname\":\"beautiful\",\"gender\":\"FEMALE\",\"about\":\"Im hot\",\"married\":false},\"subOrdinates\":[]}]\n";
		actual = ReadFileExtensions.fromFile(jsonListFile);
		expected = expected.replace("\n", "").replace("\r", "").replace(" ", "");
		actual = actual.replace("\n", "").replace("\r", "").replace(" ", "");
		assertEquals(expected, actual);

		YamlToJsonExtensions.toJson(yamlMapFile, jsonMapFile);
		expected = "{\"1\":0,\"2\":0,\"3\":0,\"4\":0,\"5\":0}\n";
		actual = ReadFileExtensions.fromFile(jsonMapFile);
		expected = expected.replace("\n", "").replace("\r", "").replace(" ", "");
		actual = actual.replace("\n", "").replace("\r", "").replace(" ", "");
		assertEquals(expected, actual);

		YamlToJsonExtensions.toJson(yamlCollectionFile, jsonCollectionFile);
		expected = "[{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"},{\"person\":{\"name\":\"Andreas\",\"nickname\":\"cute\",\"gender\":\"MALE\",\"about\":\"fine person\",\"married\":false},\"id\":\"24\"},{\"person\":{\"name\":\"Tatjana\",\"nickname\":\"beautiful\",\"gender\":\"FEMALE\",\"about\":\"Im hot\",\"married\":false},\"id\":\"25\"}]\n";
		actual = ReadFileExtensions.fromFile(jsonCollectionFile);
		expected = expected.replace("\n", "").replace("\r", "").replace(" ", "");
		actual = actual.replace("\n", "").replace("\r", "").replace(" ", "");
		assertEquals(expected, actual);
		File newFile = new File(jsonDir, "new-collection-file.json");
		YamlToJsonExtensions.toJson(yamlCollectionFile, newFile);
		expected = "[{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"},{\"person\":{\"name\":\"Andreas\",\"nickname\":\"cute\",\"gender\":\"MALE\",\"about\":\"fine person\",\"married\":false},\"id\":\"24\"},{\"person\":{\"name\":\"Tatjana\",\"nickname\":\"beautiful\",\"gender\":\"FEMALE\",\"about\":\"Im hot\",\"married\":false},\"id\":\"25\"}]\n";
		actual = ReadFileExtensions.fromFile(jsonCollectionFile);
		expected = expected.replace("\n", "").replace("\r", "").replace(" ", "");
		actual = actual.replace("\n", "").replace("\r", "").replace(" ", "");
		assertEquals(expected, actual);
		DeleteFileExtensions.delete(newFile);
	}

	/**
	 * Test method for {@link YamlToJsonExtensions#toJson(File)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToYamlWithJsonFile() throws IOException
	{
		String expected;
		String actual;

		actual = YamlToJsonExtensions.toJson(yamlFile);
		expected = "{\"id\":\"23\",\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"subOrdinates\":[]}";
		assertEquals(expected, actual);

		actual = YamlToJsonExtensions.toJson(yamlSigninFile);
		expected = "{\"username\":\"foo\",\"password\":\"bar\"}";
		assertEquals(expected, actual);

		actual = YamlToJsonExtensions.toJson(yamlListFile);
		expected = "[{\"id\":\"23\",\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"subOrdinates\":[]},{\"id\":\"24\",\"person\":{\"name\":\"Andreas\",\"nickname\":\"cute\",\"gender\":\"MALE\",\"about\":\"fine person\",\"married\":false},\"subOrdinates\":[]},{\"id\":\"25\",\"person\":{\"name\":\"Tatjana\",\"nickname\":\"beautiful\",\"gender\":\"FEMALE\",\"about\":\"Im hot\",\"married\":false},\"subOrdinates\":[]}]";
		assertEquals(expected, actual);

		actual = YamlToJsonExtensions.toJson(yamlMapFile);
		expected = "{\"1\":0,\"2\":0,\"3\":0,\"4\":0,\"5\":0}";
		assertEquals(expected, actual);

		actual = YamlToJsonExtensions.toJson(yamlCollectionFile);
		expected = "[{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"},{\"person\":{\"name\":\"Andreas\",\"nickname\":\"cute\",\"gender\":\"MALE\",\"about\":\"fine person\",\"married\":false},\"id\":\"24\"},{\"person\":{\"name\":\"Tatjana\",\"nickname\":\"beautiful\",\"gender\":\"FEMALE\",\"about\":\"Im hot\",\"married\":false},\"id\":\"25\"}]";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link YamlToJsonExtensions#toJson(String)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToYaml() throws IOException
	{

		String expected;
		String actual;

		actual = YamlToJsonExtensions.toJson(ReadFileExtensions.fromFile(yamlFile));
		expected = "{\"id\":\"23\",\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"subOrdinates\":[]}";
		assertEquals(expected, actual);

		actual = YamlToJsonExtensions.toJson(ReadFileExtensions.fromFile(yamlSigninFile));
		expected = "{\"username\":\"foo\",\"password\":\"bar\"}";
		assertEquals(expected, actual);

		actual = YamlToJsonExtensions.toJson(ReadFileExtensions.fromFile(yamlListFile));
		expected = "[{\"id\":\"23\",\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"subOrdinates\":[]},{\"id\":\"24\",\"person\":{\"name\":\"Andreas\",\"nickname\":\"cute\",\"gender\":\"MALE\",\"about\":\"fine person\",\"married\":false},\"subOrdinates\":[]},{\"id\":\"25\",\"person\":{\"name\":\"Tatjana\",\"nickname\":\"beautiful\",\"gender\":\"FEMALE\",\"about\":\"Im hot\",\"married\":false},\"subOrdinates\":[]}]";
		assertEquals(expected, actual);

		actual = YamlToJsonExtensions.toJson(ReadFileExtensions.fromFile(yamlMapFile));
		expected = "{\"1\":0,\"2\":0,\"3\":0,\"4\":0,\"5\":0}";
		assertEquals(expected, actual);

		actual = YamlToJsonExtensions.toJson(ReadFileExtensions.fromFile(yamlCollectionFile));
		expected = "[{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"},{\"person\":{\"name\":\"Andreas\",\"nickname\":\"cute\",\"gender\":\"MALE\",\"about\":\"fine person\",\"married\":false},\"id\":\"24\"},{\"person\":{\"name\":\"Tatjana\",\"nickname\":\"beautiful\",\"gender\":\"FEMALE\",\"about\":\"Im hot\",\"married\":false},\"id\":\"25\"}]";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link YamlToJsonExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(YamlToJsonExtensions.class);
	}
}
