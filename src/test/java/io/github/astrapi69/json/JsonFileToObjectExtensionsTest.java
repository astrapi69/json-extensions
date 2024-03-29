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
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.meanbean.test.BeanTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.astrapi69.collection.CollectionExtensions;
import io.github.astrapi69.collection.list.ListFactory;
import io.github.astrapi69.collection.map.MapFactory;
import io.github.astrapi69.collection.set.SetFactory;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.json.factory.ObjectMapperFactory;
import io.github.astrapi69.test.object.Employee;
import io.github.astrapi69.test.object.Person;
import io.github.astrapi69.test.object.enumeration.Gender;

/**
 * The unit test class for the class {@link JsonFileToObjectExtensions}
 */
public class JsonFileToObjectExtensionsTest
{

	File jsonDir;
	File jsonFile;
	File jsonCollectionFile;

	File jsonMapFile;
	File jsonListFile;

	@BeforeMethod
	protected void setUp()
	{
		jsonDir = new File(PathFinder.getSrcTestResourcesDir(), "json");
		jsonFile = new File(jsonDir, "person.json");
		jsonListFile = new File(jsonDir, "employees.json");
		jsonMapFile = new File(jsonDir, "map.json");
		jsonCollectionFile = new File(jsonDir, "collection.json");
	}

	/**
	 * Test method for {@link JsonFileToObjectExtensions#toMapObject(File, TypeReference)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToMapObjectStringTypeReference() throws IOException
	{
		Map<Integer, Integer> actual;
		Map<Integer, Integer> expected;
		TypeReference<Map<Integer, Integer>> typeReference;

		// new scenario: try to convert json to integer map
		typeReference = new TypeReference<Map<Integer, Integer>>()
		{
		};
		actual = JsonFileToObjectExtensions.toMapObject(jsonMapFile, typeReference);
		expected = MapFactory.newCounterMap(ListFactory.newRangeList(1, 5));
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link JsonFileToObjectExtensions#toMapObject(File, TypeReference, ObjectMapper)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToMapObjectStringTypeReferenceObjectMapper() throws IOException
	{
		Map<Integer, Integer> actual;
		Map<Integer, Integer> expected;
		TypeReference<Map<Integer, Integer>> typeReference;

		// new scenario: try to convert json to integer map
		typeReference = new TypeReference<Map<Integer, Integer>>()
		{
		};
		actual = JsonFileToObjectExtensions.toMapObject(jsonMapFile, typeReference,
			ObjectMapperFactory.newObjectMapper());
		expected = MapFactory.newCounterMap(ListFactory.newRangeList(1, 5));
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link JsonFileToObjectExtensions#toObjectCollection(File, Class, Class)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToObjectCollection() throws IOException
	{
		boolean actual;
		boolean expected;
		Set<Employee> jsonList;
		Set<Employee> objectList;
		Employee firstExpected;
		Employee secondExpected;
		Employee thirdExpected;

		jsonList = (Set<Employee>)JsonFileToObjectExtensions.toObjectCollection(jsonCollectionFile,
			LinkedHashSet.class, Employee.class);
		firstExpected = Employee.builder().person(Person.builder().gender(Gender.FEMALE)
			.name("Anna").nickname("beast").married(true).about("Ha ha ha...").build()).id("23")
			.build();
		secondExpected = Employee.builder().person(Person.builder().gender(Gender.MALE)
			.name("Andreas").nickname("cute").married(false).about("fine person").build()).id("24")
			.build();
		thirdExpected = Employee.builder().person(Person.builder().gender(Gender.FEMALE)
			.name("Tatjana").nickname("beautiful").married(false).about("Im hot").build()).id("25")
			.build();
		objectList = SetFactory.newLinkedHashSet(firstExpected, secondExpected, thirdExpected);

		actual = CollectionExtensions.isEqualCollection(jsonList, objectList);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link JsonFileToObjectExtensions#toObjectList(File, Class)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToObjectList() throws IOException
	{
		boolean actual;
		boolean expected;
		List<Employee> jsonList;
		List<Employee> objectList;
		Employee firstExpected;
		Employee secondExpected;
		Employee thirdExpected;

		jsonList = JsonFileToObjectExtensions.toObjectList(jsonCollectionFile, Employee.class);
		firstExpected = Employee.builder().person(Person.builder().gender(Gender.FEMALE)
			.name("Anna").nickname("beast").married(true).about("Ha ha ha...").build()).id("23")
			.build();
		secondExpected = Employee.builder().person(Person.builder().gender(Gender.MALE)
			.name("Andreas").nickname("cute").married(false).about("fine person").build()).id("24")
			.build();
		thirdExpected = Employee.builder().person(Person.builder().gender(Gender.FEMALE)
			.name("Tatjana").nickname("beautiful").married(false).about("Im hot").build()).id("25")
			.build();
		objectList = ListFactory.newArrayList(firstExpected, secondExpected, thirdExpected);

		actual = CollectionExtensions.isEqualCollection(jsonList, objectList);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link JsonFileToObjectExtensions#toObject(File, Class, ObjectMapper)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToObjectFileClassOfT() throws IOException
	{
		Employee actual;
		Employee expected;

		actual = JsonFileToObjectExtensions.toObject(jsonFile, Employee.class);
		expected = Employee
			.builder().subOrdinates(new ArrayList<>()).person(Person.builder().gender(Gender.FEMALE)
				.name("Anna").nickname("beast").married(true).about("Ha ha ha...").build())
			.id("23").build();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link JsonFileToObjectExtensions#toObject(File, Class, ObjectMapper)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToObjectFileClassOfTObjectMapper() throws IOException
	{
		Employee actual;
		Employee expected;
		ObjectMapper objectMapper;

		objectMapper = ObjectMapperFactory.newObjectMapper();
		actual = JsonFileToObjectExtensions.toObject(jsonFile, Employee.class, objectMapper);
		expected = Employee.builder().person(Person.builder().gender(Gender.FEMALE).name("Anna")
			.nickname("beast").married(true).about("Ha ha ha...").build()).id("23").build();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link JsonFileToObjectExtensions#toObject(File, TypeReference, ObjectMapper)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToObjectFileTypeReferenceOfTObjectMapper() throws IOException
	{
		boolean actual;
		boolean expected;
		List<Employee> jsonList;
		List<Employee> objectList;
		Employee firstExpected;
		Employee secondExpected;
		Employee thirdExpected;
		ObjectMapper objectMapper;

		objectMapper = ObjectMapperFactory.newObjectMapper();
		jsonList = JsonFileToObjectExtensions.toObject(jsonListFile,
			new TypeReference<List<Employee>>()
			{
			}, objectMapper);
		firstExpected = Employee.builder().person(Person.builder().gender(Gender.FEMALE)
			.name("Anna").nickname("beast").married(true).about("Ha ha ha...").build()).id("23")
			.build();
		secondExpected = Employee.builder().person(Person.builder().gender(Gender.MALE)
			.name("Andreas").nickname("cute").married(false).about("fine person").build()).id("24")
			.build();
		thirdExpected = Employee.builder().person(Person.builder().gender(Gender.FEMALE)
			.name("Tatjana").nickname("beautiful").married(false).about("Im hot").build()).id("25")
			.build();
		objectList = ListFactory.newArrayList(firstExpected, secondExpected, thirdExpected);

		actual = CollectionExtensions.isEqualCollection(jsonList, objectList);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link JsonFileToObjectExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(JsonFileToObjectExtensions.class);
	}

}
