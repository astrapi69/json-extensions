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
import java.util.Map;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.astrapi69.collections.list.ListFactory;
import io.github.astrapi69.collections.map.MapFactory;
import io.github.astrapi69.file.delete.DeleteFileExtensions;
import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.json.factory.ObjectMapperFactory;
import io.github.astrapi69.test.objects.Employee;
import io.github.astrapi69.test.objects.Person;
import io.github.astrapi69.test.objects.enums.Gender;

/**
 * The unit test class for the class {@link ObjectToJsonFileExtensions}
 */
public class ObjectToJsonFileExtensionsTest
{

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
		expected = "{\"id\":\"23\",\"person\":{\"about\":\"Ha ha ha...\",\"gender\":\"FEMALE\",\"married\":true,\"name\":\"Anna\",\"nickname\":\"beast\"}}";
		jsonFile = new File(PathFinder.getSrcTestResourcesDir(), "tmp.json");
		ObjectToJsonFileExtensions.toJsonFile(employee, jsonFile);
		actual = ReadFileExtensions.readFromFile(jsonFile);
		assertEquals(expected, actual);
		// new scenario: try to convert a integer map to json
		numberCounterMap = MapFactory.newCounterMap(ListFactory.newRangeList(1, 5));
		expected = "{\"1\":0,\"2\":0,\"3\":0,\"4\":0,\"5\":0}";
		ObjectToJsonFileExtensions.toJsonFile(numberCounterMap, jsonFile);
		actual = ReadFileExtensions.readFromFile(jsonFile);
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
		expected = "{\"id\":\"23\",\"person\":{\"about\":\"Ha ha ha...\",\"gender\":\"FEMALE\",\"married\":true,\"name\":\"Anna\",\"nickname\":\"beast\"}}";
		jsonFile = new File(PathFinder.getSrcTestResourcesDir(), "tmp.json");
		ObjectToJsonFileExtensions.toJsonFile(employee, jsonFile, false);
		actual = ReadFileExtensions.readFromFile(jsonFile);
		assertEquals(expected, actual);
		// new scenario: try to convert a integer map to json
		numberCounterMap = MapFactory.newCounterMap(ListFactory.newRangeList(1, 5));
		expected = "{\"1\":0,\"2\":0,\"3\":0,\"4\":0,\"5\":0}";
		ObjectToJsonFileExtensions.toJsonFile(numberCounterMap, jsonFile, false);
		actual = ReadFileExtensions.readFromFile(jsonFile);
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
		ObjectMapper mapper;
		mapper = ObjectMapperFactory.newObjectMapper();

		employee = Employee.builder().person(Person.builder().gender(Gender.FEMALE).name("Anna")
			.married(true).about("Ha ha ha...").nickname("beast").build()).id("23").build();
		// new scenario: try to convert a Employee object to json
		expected = "{\"id\":\"23\",\"person\":{\"about\":\"Ha ha ha...\",\"gender\":\"FEMALE\",\"married\":true,\"name\":\"Anna\",\"nickname\":\"beast\"}}";
		jsonFile = new File(PathFinder.getSrcTestResourcesDir(), "tmp.json");
		ObjectToJsonFileExtensions.toJsonFile(employee, jsonFile, mapper);
		actual = ReadFileExtensions.readFromFile(jsonFile);
		assertEquals(expected, actual);
		// new scenario: try to convert a integer map to json
		numberCounterMap = MapFactory.newCounterMap(ListFactory.newRangeList(1, 5));
		expected = "{\"1\":0,\"2\":0,\"3\":0,\"4\":0,\"5\":0}";
		ObjectToJsonFileExtensions.toJsonFile(numberCounterMap, jsonFile, mapper);
		actual = ReadFileExtensions.readFromFile(jsonFile);
		assertEquals(expected, actual);
		// clean up
		DeleteFileExtensions.delete(jsonFile);
	}
}
