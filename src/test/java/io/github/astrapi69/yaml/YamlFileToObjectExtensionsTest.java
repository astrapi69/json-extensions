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
import io.github.astrapi69.test.object.Employee;
import io.github.astrapi69.test.object.Person;
import io.github.astrapi69.test.object.enumeration.Gender;
import io.github.astrapi69.yaml.factory.YAMLMapperFactory;

/**
 * The unit test class for the class {@link YamlFileToObjectExtensions}
 */
public class YamlFileToObjectExtensionsTest
{

	File yamlDir;
	File yamlFile;
	File yamlCollectionFile;

	File yamlMapFile;
	File yamlListFile;

	@BeforeMethod
	protected void setUp()
	{
		yamlDir = new File(PathFinder.getSrcTestResourcesDir(), "yaml");
		yamlFile = new File(yamlDir, "person.yaml");
		yamlListFile = new File(yamlDir, "employees.yaml");
		yamlMapFile = new File(yamlDir, "map.yaml");
		yamlCollectionFile = new File(yamlDir, "collection.yaml");
	}

	/**
	 * Test method for {@link YamlFileToObjectExtensions#toMapObject(File, TypeReference)}
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

		// new scenario: try to convert yaml to integer map
		typeReference = new TypeReference<>()
		{
		};
		actual = YamlFileToObjectExtensions.toMapObject(yamlMapFile, typeReference);
		expected = MapFactory.newCounterMap(ListFactory.newRangeList(1, 5));
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link YamlFileToObjectExtensions#toMapObject(File, TypeReference, ObjectMapper)}
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

		// new scenario: try to convert yaml to integer map
		typeReference = new TypeReference<>()
		{
		};
		actual = YamlFileToObjectExtensions.toMapObject(yamlMapFile, typeReference,
			YAMLMapperFactory.newYAMLMapper());
		expected = MapFactory.newCounterMap(ListFactory.newRangeList(1, 5));
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link YamlFileToObjectExtensions#toObjectCollection(File, Class, Class)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToObjectCollection() throws IOException
	{
		boolean actual;
		boolean expected;
		Set<Employee> yamlList;
		Set<Employee> objectList;
		Employee firstExpected;
		Employee secondExpected;
		Employee thirdExpected;

		yamlList = (Set<Employee>)YamlFileToObjectExtensions.toObjectCollection(yamlCollectionFile,
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

		actual = CollectionExtensions.isEqualCollection(yamlList, objectList);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link YamlFileToObjectExtensions#toObjectList(File, Class)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToObjectList() throws IOException
	{
		boolean actual;
		boolean expected;
		List<Employee> yamlList;
		List<Employee> objectList;
		Employee firstExpected;
		Employee secondExpected;
		Employee thirdExpected;

		yamlList = YamlFileToObjectExtensions.toObjectList(yamlCollectionFile, Employee.class);
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

		actual = CollectionExtensions.isEqualCollection(yamlList, objectList);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link YamlFileToObjectExtensions#toObject(File, Class, ObjectMapper)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToObjectFileClassOfT() throws IOException
	{
		Employee actual;
		Employee expected;

		actual = YamlFileToObjectExtensions.toObject(yamlFile, Employee.class);
		expected = Employee
			.builder().subOrdinates(new ArrayList<>()).person(Person.builder().gender(Gender.FEMALE)
				.name("Anna").nickname("beast").married(true).about("Ha ha ha...").build())
			.id("23").build();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link YamlFileToObjectExtensions#toObject(File, Class, ObjectMapper)}
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

		objectMapper = YAMLMapperFactory.newYAMLMapper();
		actual = YamlFileToObjectExtensions.toObject(yamlFile, Employee.class, objectMapper);
		expected = Employee.builder().person(Person.builder().gender(Gender.FEMALE).name("Anna")
			.nickname("beast").married(true).about("Ha ha ha...").build()).id("23").build();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link YamlFileToObjectExtensions#toObject(File, TypeReference, ObjectMapper)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToObjectFileTypeReferenceOfTObjectMapper() throws IOException
	{
		boolean actual;
		boolean expected;
		List<Employee> yamlList;
		List<Employee> objectList;
		Employee firstExpected;
		Employee secondExpected;
		Employee thirdExpected;
		ObjectMapper objectMapper;

		objectMapper = YAMLMapperFactory.newYAMLMapper();
		yamlList = YamlFileToObjectExtensions.toObject(yamlListFile, new TypeReference<>()
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

		actual = CollectionExtensions.isEqualCollection(yamlList, objectList);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link YamlFileToObjectExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(YamlFileToObjectExtensions.class);
	}

}
