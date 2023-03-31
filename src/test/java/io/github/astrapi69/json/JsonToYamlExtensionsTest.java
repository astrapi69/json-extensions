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
package io.github.astrapi69.json;

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
 * The unit test class for the class {@link JsonToYamlExtensions}
 */
public class JsonToYamlExtensionsTest
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
	 * Test method for {@link JsonToYamlExtensions#toYaml(File, File)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToYamlWithJsonFileToYamlFile() throws IOException
	{
		String expected;
		String actual;

		JsonToYamlExtensions.toYaml(jsonFile, yamlFile);
		expected = "---\n" + "id: \"23\"\n" + "person:\n" + "  name: \"Anna\"\n"
			+ "  nickname: \"beast\"\n" + "  gender: \"FEMALE\"\n" + "  about: \"Ha ha ha...\"\n"
			+ "  married: true\n" + "subOrdinates: []\n" + "\n";
		actual = ReadFileExtensions.fromFile(yamlFile);
		expected = expected.replace("\n", "").replace("\r", "").replace(" ", "");
		actual = actual.replace("\n", "").replace("\r", "").replace(" ", "");
		assertEquals(expected, actual);

		JsonToYamlExtensions.toYaml(jsonSigninFile, yamlSigninFile);
		expected = "---\n" + "username: \"foo\"\n" + "password: \"bar\"\n" + "\n";
		actual = ReadFileExtensions.fromFile(yamlSigninFile);
		expected = expected.replace("\n", "").replace("\r", "").replace(" ", "");
		actual = actual.replace("\n", "").replace("\r", "").replace(" ", "");
		assertEquals(expected, actual);

		JsonToYamlExtensions.toYaml(jsonListFile, yamlListFile);
		expected = "---\n" + "- id: \"23\"\n" + "  person:\n" + "    name: \"Anna\"\n"
			+ "    nickname: \"beast\"\n" + "    gender: \"FEMALE\"\n"
			+ "    about: \"Ha ha ha...\"\n" + "    married: true\n" + "  subOrdinates: []\n"
			+ "- id: \"24\"\n" + "  person:\n" + "    name: \"Andreas\"\n"
			+ "    nickname: \"cute\"\n" + "    gender: \"MALE\"\n" + "    about: \"fine person\"\n"
			+ "    married: false\n" + "  subOrdinates: []\n" + "- id: \"25\"\n" + "  person:\n"
			+ "    name: \"Tatjana\"\n" + "    nickname: \"beautiful\"\n"
			+ "    gender: \"FEMALE\"\n" + "    about: \"Im hot\"\n" + "    married: false\n"
			+ "  subOrdinates: []\n";
		actual = ReadFileExtensions.fromFile(yamlListFile);
		expected = expected.replace("\n", "").replace("\r", "").replace(" ", "");
		actual = actual.replace("\n", "").replace("\r", "").replace(" ", "");
		assertEquals(expected, actual);

		JsonToYamlExtensions.toYaml(jsonMapFile, yamlMapFile);
		expected = "---\n" + "\"1\": 0\n" + "\"2\": 0\n" + "\"3\": 0\n" + "\"4\": 0\n"
			+ "\"5\": 0\n";
		actual = ReadFileExtensions.fromFile(yamlMapFile);
		expected = expected.replace("\n", "").replace("\r", "").replace(" ", "");
		actual = actual.replace("\n", "").replace("\r", "").replace(" ", "");
		assertEquals(expected, actual);

		JsonToYamlExtensions.toYaml(jsonCollectionFile, yamlCollectionFile);
		expected = "---\n" + "- person:\n" + "    name: \"Anna\"\n" + "    nickname: \"beast\"\n"
			+ "    gender: \"FEMALE\"\n" + "    about: \"Ha ha ha...\"\n" + "    married: true\n"
			+ "  id: \"23\"\n" + "- person:\n" + "    name: \"Andreas\"\n"
			+ "    nickname: \"cute\"\n" + "    gender: \"MALE\"\n" + "    about: \"fine person\"\n"
			+ "    married: false\n" + "  id: \"24\"\n" + "- person:\n" + "    name: \"Tatjana\"\n"
			+ "    nickname: \"beautiful\"\n" + "    gender: \"FEMALE\"\n"
			+ "    about: \"Im hot\"\n" + "    married: false\n" + "  id: \"25\"\n";
		actual = ReadFileExtensions.fromFile(yamlCollectionFile);
		expected = expected.replace("\n", "").replace("\r", "").replace(" ", "");
		actual = actual.replace("\n", "").replace("\r", "").replace(" ", "");
		assertEquals(expected, actual);
		File newFile = new File(yamlDir, "new-collection-file.yaml");
		JsonToYamlExtensions.toYaml(jsonCollectionFile, newFile);
		expected = "---\n" + "- person:\n" + "    name: \"Anna\"\n" + "    nickname: \"beast\"\n"
			+ "    gender: \"FEMALE\"\n" + "    about: \"Ha ha ha...\"\n" + "    married: true\n"
			+ "  id: \"23\"\n" + "- person:\n" + "    name: \"Andreas\"\n"
			+ "    nickname: \"cute\"\n" + "    gender: \"MALE\"\n" + "    about: \"fine person\"\n"
			+ "    married: false\n" + "  id: \"24\"\n" + "- person:\n" + "    name: \"Tatjana\"\n"
			+ "    nickname: \"beautiful\"\n" + "    gender: \"FEMALE\"\n"
			+ "    about: \"Im hot\"\n" + "    married: false\n" + "  id: \"25\"\n";
		actual = ReadFileExtensions.fromFile(yamlCollectionFile);
		expected = expected.replace("\n", "").replace("\r", "").replace(" ", "");
		actual = actual.replace("\n", "").replace("\r", "").replace(" ", "");
		assertEquals(expected, actual);
		DeleteFileExtensions.delete(newFile);
	}

	/**
	 * Test method for {@link JsonToYamlExtensions#toYaml(File)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToYamlWithJsonFile() throws IOException
	{
		String expected;
		String actual;

		actual = JsonToYamlExtensions.toYaml(jsonFile);
		expected = "---\n" + "id: \"23\"\n" + "person:\n" + "  name: \"Anna\"\n"
			+ "  nickname: \"beast\"\n" + "  gender: \"FEMALE\"\n" + "  about: \"Ha ha ha...\"\n"
			+ "  married: true\n" + "subOrdinates: []\n";
		assertEquals(expected, actual);

		actual = JsonToYamlExtensions.toYaml(jsonSigninFile);
		expected = "---\n" + "username: \"foo\"\n" + "password: \"bar\"\n";
		assertEquals(expected, actual);

		actual = JsonToYamlExtensions.toYaml(jsonListFile);
		expected = "---\n" + "- id: \"23\"\n" + "  person:\n" + "    name: \"Anna\"\n"
			+ "    nickname: \"beast\"\n" + "    gender: \"FEMALE\"\n"
			+ "    about: \"Ha ha ha...\"\n" + "    married: true\n" + "  subOrdinates: []\n"
			+ "- id: \"24\"\n" + "  person:\n" + "    name: \"Andreas\"\n"
			+ "    nickname: \"cute\"\n" + "    gender: \"MALE\"\n" + "    about: \"fine person\"\n"
			+ "    married: false\n" + "  subOrdinates: []\n" + "- id: \"25\"\n" + "  person:\n"
			+ "    name: \"Tatjana\"\n" + "    nickname: \"beautiful\"\n"
			+ "    gender: \"FEMALE\"\n" + "    about: \"Im hot\"\n" + "    married: false\n"
			+ "  subOrdinates: []\n";
		assertEquals(expected, actual);

		actual = JsonToYamlExtensions.toYaml(jsonMapFile);
		expected = "---\n" + "\"1\": 0\n" + "\"2\": 0\n" + "\"3\": 0\n" + "\"4\": 0\n"
			+ "\"5\": 0\n";
		assertEquals(expected, actual);

		actual = JsonToYamlExtensions.toYaml(jsonCollectionFile);
		expected = "---\n" + "- person:\n" + "    name: \"Anna\"\n" + "    nickname: \"beast\"\n"
			+ "    gender: \"FEMALE\"\n" + "    about: \"Ha ha ha...\"\n" + "    married: true\n"
			+ "  id: \"23\"\n" + "- person:\n" + "    name: \"Andreas\"\n"
			+ "    nickname: \"cute\"\n" + "    gender: \"MALE\"\n" + "    about: \"fine person\"\n"
			+ "    married: false\n" + "  id: \"24\"\n" + "- person:\n" + "    name: \"Tatjana\"\n"
			+ "    nickname: \"beautiful\"\n" + "    gender: \"FEMALE\"\n"
			+ "    about: \"Im hot\"\n" + "    married: false\n" + "  id: \"25\"\n";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link JsonToYamlExtensions#toYaml(String)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToYaml() throws IOException
	{

		String expected;
		String actual;

		actual = JsonToYamlExtensions.toYaml(ReadFileExtensions.fromFile(jsonFile));
		expected = "---\n" + "id: \"23\"\n" + "person:\n" + "  name: \"Anna\"\n"
			+ "  nickname: \"beast\"\n" + "  gender: \"FEMALE\"\n" + "  about: \"Ha ha ha...\"\n"
			+ "  married: true\n" + "subOrdinates: []\n";
		assertEquals(expected, actual);

		actual = JsonToYamlExtensions.toYaml(ReadFileExtensions.fromFile(jsonSigninFile));
		expected = "---\n" + "username: \"foo\"\n" + "password: \"bar\"\n";
		assertEquals(expected, actual);

		actual = JsonToYamlExtensions.toYaml(ReadFileExtensions.fromFile(jsonListFile));
		expected = "---\n" + "- id: \"23\"\n" + "  person:\n" + "    name: \"Anna\"\n"
			+ "    nickname: \"beast\"\n" + "    gender: \"FEMALE\"\n"
			+ "    about: \"Ha ha ha...\"\n" + "    married: true\n" + "  subOrdinates: []\n"
			+ "- id: \"24\"\n" + "  person:\n" + "    name: \"Andreas\"\n"
			+ "    nickname: \"cute\"\n" + "    gender: \"MALE\"\n" + "    about: \"fine person\"\n"
			+ "    married: false\n" + "  subOrdinates: []\n" + "- id: \"25\"\n" + "  person:\n"
			+ "    name: \"Tatjana\"\n" + "    nickname: \"beautiful\"\n"
			+ "    gender: \"FEMALE\"\n" + "    about: \"Im hot\"\n" + "    married: false\n"
			+ "  subOrdinates: []\n";
		assertEquals(expected, actual);

		actual = JsonToYamlExtensions.toYaml(ReadFileExtensions.fromFile(jsonMapFile));
		expected = "---\n" + "\"1\": 0\n" + "\"2\": 0\n" + "\"3\": 0\n" + "\"4\": 0\n"
			+ "\"5\": 0\n";
		assertEquals(expected, actual);

		actual = JsonToYamlExtensions.toYaml(ReadFileExtensions.fromFile(jsonCollectionFile));
		expected = "---\n" + "- person:\n" + "    name: \"Anna\"\n" + "    nickname: \"beast\"\n"
			+ "    gender: \"FEMALE\"\n" + "    about: \"Ha ha ha...\"\n" + "    married: true\n"
			+ "  id: \"23\"\n" + "- person:\n" + "    name: \"Andreas\"\n"
			+ "    nickname: \"cute\"\n" + "    gender: \"MALE\"\n" + "    about: \"fine person\"\n"
			+ "    married: false\n" + "  id: \"24\"\n" + "- person:\n" + "    name: \"Tatjana\"\n"
			+ "    nickname: \"beautiful\"\n" + "    gender: \"FEMALE\"\n"
			+ "    about: \"Im hot\"\n" + "    married: false\n" + "  id: \"25\"\n";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link JsonToYamlExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(JsonToYamlExtensions.class);
	}
}
