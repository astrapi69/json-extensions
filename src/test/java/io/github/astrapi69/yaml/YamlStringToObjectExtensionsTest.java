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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.meanbean.test.BeanTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;

import io.github.astrapi69.collection.CollectionExtensions;
import io.github.astrapi69.collection.list.ListFactory;
import io.github.astrapi69.collection.map.MapFactory;
import io.github.astrapi69.collection.set.SetFactory;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.json.Signin;
import io.github.astrapi69.test.object.Employee;
import io.github.astrapi69.test.object.Person;
import io.github.astrapi69.test.object.enumtype.Gender;
import io.github.astrapi69.yaml.factory.YAMLMapperFactory;

/**
 * The unit test class for the class {@link YamlStringToObjectExtensions}
 */
public class YamlStringToObjectExtensionsTest
{

	File yamlDir;

	File yamlFile;

	@BeforeMethod
	protected void setUp()
	{
		yamlDir = new File(PathFinder.getSrcTestResourcesDir(), "yaml");
		yamlFile = new File(yamlDir, "signin.yaml");
	}

	/**
	 * Test method for {@link YamlStringToObjectExtensions#toMapObject(String, TypeReference)}
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
		String yamlString;

		yamlString = "{\"1\":0,\"2\":0,\"3\":0,\"4\":0,\"5\":0}";

		// new scenario: try to convert yaml to integer map
		typeReference = new TypeReference<Map<Integer, Integer>>()
		{
		};
		actual = YamlStringToObjectExtensions.toMapObject(yamlString, typeReference);
		expected = MapFactory.newCounterMap(ListFactory.newRangeList(1, 5));
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link YamlStringToObjectExtensions#toMapObject(String, TypeReference, ObjectMapper)}
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
		String yamlString;

		yamlString = "{\"1\":0,\"2\":0,\"3\":0,\"4\":0,\"5\":0}";

		// new scenario: try to convert yaml to integer map
		typeReference = new TypeReference<Map<Integer, Integer>>()
		{
		};
		actual = YamlStringToObjectExtensions.toMapObject(yamlString, typeReference,
			YAMLMapperFactory.newYAMLMapper());
		expected = MapFactory.newCounterMap(ListFactory.newRangeList(1, 5));
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link YamlStringToObjectExtensions#toObject(String, Class)}
	 *
	 * @throws JsonParseException
	 *             If an error occurs when parsing the string into Object
	 * @throws JsonMappingException
	 *             the If an error occurs when mapping the string into Object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToObject() throws JsonParseException, JsonMappingException, IOException
	{
		Employee actual;
		Employee expected;
		String yamlString;

		expected = Employee.builder().person(Person.builder().gender(Gender.FEMALE).name("Anna")
			.married(true).about("Ha ha ha...").nickname("beast").build()).id("23").build();
		yamlString = "{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\",\"subOrdinates\": []}";
		actual = YamlStringToObjectExtensions.toObject(yamlString, Employee.class);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link YamlStringToObjectExtensions#toObjectCollection(String, Class, Class)}
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
		String yamlString;

		yamlString = "[{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"},"
			+ "{\"person\":{\"name\":\"Andreas\",\"nickname\":\"cute\",\"gender\":\"MALE\",\"about\":\"fine person\",\"married\":false},\"id\":\"24\"},"
			+ "{\"person\":{\"name\":\"Tatjana\",\"nickname\":\"beautiful\",\"gender\":\"FEMALE\",\"about\":\"Im hot\",\"married\":false},\"id\":\"25\"}]";

		yamlList = (Set<Employee>)YamlStringToObjectExtensions.toObjectCollection(yamlString,
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
	 * Test method for {@link YamlStringToObjectExtensions#toObjectList(String, Class)}
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
		String yamlString;

		yamlString = "[{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"},"
			+ "{\"person\":{\"name\":\"Andreas\",\"nickname\":\"cute\",\"gender\":\"MALE\",\"about\":\"fine person\",\"married\":false},\"id\":\"24\"},"
			+ "{\"person\":{\"name\":\"Tatjana\",\"nickname\":\"beautiful\",\"gender\":\"FEMALE\",\"about\":\"Im hot\",\"married\":false},\"id\":\"25\"}]";

		yamlList = YamlStringToObjectExtensions.toObjectList(yamlString, Employee.class);
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
	 * Test method for
	 * {@link YamlStringToObjectExtensions#toObject(String, Class, com.fasterxml.jackson.databind.Module...)}
	 * This method shows also how to map a yaml string created from the org.yaml library. This is
	 * provided by a Module, the {@link JsonOrgModule}.
	 *
	 * @throws JsonParseException
	 *             If an error occurs when parsing the string into Object
	 * @throws JsonMappingException
	 *             the If an error occurs when mapping the string into Object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToObjectWithModules()
		throws JsonParseException, JsonMappingException, IOException
	{
		final Employee expected = Employee.builder().person(Person.builder().gender(Gender.FEMALE)
			.name("Anna").married(true).about("Ha ha ha...").nickname("beast").build()).id("23")
			.build();
		final String yamlString = "{\"id\":\"23\",\"subOrdinates\": [],\"person\":{\"married\":true,\"nickname\":\"beast\",\"name\":\"Anna\",\"about\":\"Ha ha ha...\",\"gender\":\"FEMALE\"}}";
		final Employee actual = YamlStringToObjectExtensions.toObject(yamlString, Employee.class,
			new JsonOrgModule());
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link YamlStringToObjectExtensions#toObject(String, TypeReference, ObjectMapper)}
	 *
	 * @throws JsonParseException
	 *             If an error occurs when parsing the string into Object
	 * @throws JsonMappingException
	 *             the If an error occurs when mapping the string into Object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToObjectWithSignin()
		throws JsonParseException, JsonMappingException, IOException
	{
		Signin actual;
		Signin expected;
		String yamlString;

		yamlString = "{\"username\":\"foo\",\"password\":\"bar\"}";
		actual = YamlStringToObjectExtensions.toObject(yamlString, Signin.class);
		expected = Signin.builder().username("foo").password("bar").build();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link YamlStringToObjectExtensions#toObject(String, Class)}
	 *
	 * @throws JsonParseException
	 *             If an error occurs when parsing the string into Object
	 * @throws JsonMappingException
	 *             the If an error occurs when mapping the string into Object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToObjectWithTypeReference()
		throws JsonParseException, JsonMappingException, IOException
	{
		Signin actual;
		Signin expected;
		TypeReference<Signin> typeReference;
		String yamlString;

		yamlString = "{\"username\":\"foo\",\"password\":\"bar\"}";
		typeReference = new TypeReference<Signin>()
		{
		};
		actual = YamlStringToObjectExtensions.toObject(yamlString, typeReference,
			YAMLMapperFactory.newYAMLMapper());
		expected = Signin.builder().username("foo").password("bar").build();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link YamlStringToObjectExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(YamlStringToObjectExtensions.class);
	}

}
