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
import java.util.Map;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import io.github.astrapi69.collection.list.ListFactory;
import io.github.astrapi69.collection.map.MapFactory;
import io.github.astrapi69.file.delete.DeleteFileExtensions;
import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.test.object.Employee;
import io.github.astrapi69.test.object.Person;
import io.github.astrapi69.test.object.enumeration.Gender;
import io.github.astrapi69.yaml.factory.YAMLMapperFactory;

/**
 * The unit test class for the class {@link ObjectToYamlFileExtensions}
 */
public class ObjectToYamlFileExtensionsTest
{

	/**
	 * Test method for {@link ObjectToYamlFileExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ObjectToYamlFileExtensions.class);
	}

	@Test
	public void testToJsonFile() throws IOException
	{
		String expected;
		String actual;
		File jsonFile;
		Employee employee;
		Map<Integer, Integer> numberCounterMap;

		employee = Employee.builder().person(Person.builder().gender(Gender.FEMALE).name("Anna")
			.married(true).about("Ha ha ha...").nickname("beast").build()).id("23").build();
		// new scenario: try to convert a Employee object to json
		expected = "---\n" + "id: \"23\"\n" + "person:\n" + "  about: \"Ha ha ha...\"\n"
			+ "  gender: \"FEMALE\"\n" + "  married: true\n" + "  name: \"Anna\"\n"
			+ "  nickname: \"beast\"\n" + "subOrdinates: []\n";
		jsonFile = new File(PathFinder.getSrcTestResourcesDir(), "tmp.json");
		ObjectToYamlFileExtensions.toYamlFile(employee, jsonFile);
		actual = ReadFileExtensions.fromFile(jsonFile);
		assertEquals(expected, actual);
		// new scenario: try to convert a integer map to json
		numberCounterMap = MapFactory.newCounterMap(ListFactory.newRangeList(1, 5));
		expected = "---\n" + "1: 0\n" + "2: 0\n" + "3: 0\n" + "4: 0\n" + "5: 0\n";
		ObjectToYamlFileExtensions.toYamlFile(numberCounterMap, jsonFile);
		actual = ReadFileExtensions.fromFile(jsonFile);
		assertEquals(expected, actual);
		// clean up
		DeleteFileExtensions.delete(jsonFile);
	}

	@Test
	public void testTestToJsonFile() throws IOException
	{
		String expected;
		String actual;
		File jsonFile;
		Employee employee;
		Map<Integer, Integer> numberCounterMap;

		employee = Employee.builder().person(Person.builder().gender(Gender.FEMALE).name("Anna")
			.married(true).about("Ha ha ha...").nickname("beast").build()).id("23").build();
		// new scenario: try to convert a Employee object to json
		expected = "---\n" + "id: \"23\"\n" + "person:\n" + "  about: \"Ha ha ha...\"\n"
			+ "  gender: \"FEMALE\"\n" + "  married: true\n" + "  name: \"Anna\"\n"
			+ "  nickname: \"beast\"\n" + "subOrdinates: []\n";
		jsonFile = new File(PathFinder.getSrcTestResourcesDir(), "tmp.json");
		ObjectToYamlFileExtensions.toYamlFile(employee, jsonFile, false);
		actual = ReadFileExtensions.fromFile(jsonFile);
		assertEquals(expected, actual);
		// new scenario: try to convert a integer map to json
		numberCounterMap = MapFactory.newCounterMap(ListFactory.newRangeList(1, 5));
		expected = "---\n" + "1: 0\n" + "2: 0\n" + "3: 0\n" + "4: 0\n" + "5: 0\n";
		ObjectToYamlFileExtensions.toYamlFile(numberCounterMap, jsonFile, false);
		actual = ReadFileExtensions.fromFile(jsonFile);
		assertEquals(expected, actual);
		// clean up
		DeleteFileExtensions.delete(jsonFile);
	}

	@Test
	public void testTestToJsonFile1() throws IOException
	{
		String expected;
		String actual;
		File jsonFile;
		Employee employee;
		Map<Integer, Integer> numberCounterMap;
		YAMLMapper mapper;
		mapper = YAMLMapperFactory.newYAMLMapper();

		employee = Employee.builder().person(Person.builder().gender(Gender.FEMALE).name("Anna")
			.married(true).about("Ha ha ha...").nickname("beast").build()).id("23").build();
		// new scenario: try to convert a Employee object to json
		expected = "---\n" + "id: \"23\"\n" + "person:\n" + "  about: \"Ha ha ha...\"\n"
			+ "  gender: \"FEMALE\"\n" + "  married: true\n" + "  name: \"Anna\"\n"
			+ "  nickname: \"beast\"\n" + "subOrdinates: []\n";
		jsonFile = new File(PathFinder.getSrcTestResourcesDir(), "tmp.json");
		ObjectToYamlFileExtensions.toYamlFile(employee, jsonFile, mapper);
		actual = ReadFileExtensions.fromFile(jsonFile);
		assertEquals(expected, actual);
		// new scenario: try to convert a integer map to json
		numberCounterMap = MapFactory.newCounterMap(ListFactory.newRangeList(1, 5));
		expected = "---\n" + "1: 0\n" + "2: 0\n" + "3: 0\n" + "4: 0\n" + "5: 0\n";
		ObjectToYamlFileExtensions.toYamlFile(numberCounterMap, jsonFile, mapper);
		actual = ReadFileExtensions.fromFile(jsonFile);
		assertEquals(expected, actual);
		// clean up
		DeleteFileExtensions.delete(jsonFile);
	}
}
