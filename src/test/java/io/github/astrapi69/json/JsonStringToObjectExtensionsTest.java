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
import io.github.astrapi69.json.factory.ObjectMapperFactory;
import io.github.astrapi69.test.object.Employee;
import io.github.astrapi69.test.object.Person;
import io.github.astrapi69.test.object.enumeration.Gender;

/**
 * The unit test class for the class {@link JsonStringToObjectExtensions}
 */
public class JsonStringToObjectExtensionsTest
{

	File jsonDir;

	File jsonFile;

	@BeforeMethod
	protected void setUp()
	{
		jsonDir = new File(PathFinder.getSrcTestResourcesDir(), "json");
		jsonFile = new File(jsonDir, "signin.json");
	}

	/**
	 * Test method for {@link JsonStringToObjectExtensions#toMapObject(String, TypeReference)}
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
		String jsonString;

		jsonString = "{\"1\":0,\"2\":0,\"3\":0,\"4\":0,\"5\":0}";

		// new scenario: try to convert json to integer map
		typeReference = new TypeReference<Map<Integer, Integer>>()
		{
		};
		actual = JsonStringToObjectExtensions.toMapObject(jsonString, typeReference);
		expected = MapFactory.newCounterMap(ListFactory.newRangeList(1, 5));
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link JsonStringToObjectExtensions#toMapObject(String, TypeReference, ObjectMapper)}
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
		String jsonString;

		jsonString = "{\"1\":0,\"2\":0,\"3\":0,\"4\":0,\"5\":0}";

		// new scenario: try to convert json to integer map
		typeReference = new TypeReference<Map<Integer, Integer>>()
		{
		};
		actual = JsonStringToObjectExtensions.toMapObject(jsonString, typeReference,
			ObjectMapperFactory.newObjectMapper());
		expected = MapFactory.newCounterMap(ListFactory.newRangeList(1, 5));
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link JsonStringToObjectExtensions#toObject(String, Class)}
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
		String jsonString;

		expected = Employee.builder().person(Person.builder().gender(Gender.FEMALE).name("Anna")
			.married(true).about("Ha ha ha...").nickname("beast").build()).id("23").build();
		jsonString = "{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\",\"subOrdinates\": []}";
		actual = JsonStringToObjectExtensions.toObject(jsonString, Employee.class);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link JsonStringToObjectExtensions#toObjectCollection(String, Class, Class)}
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
		String jsonString;

		jsonString = "[{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"},"
			+ "{\"person\":{\"name\":\"Andreas\",\"nickname\":\"cute\",\"gender\":\"MALE\",\"about\":\"fine person\",\"married\":false},\"id\":\"24\"},"
			+ "{\"person\":{\"name\":\"Tatjana\",\"nickname\":\"beautiful\",\"gender\":\"FEMALE\",\"about\":\"Im hot\",\"married\":false},\"id\":\"25\"}]";

		jsonList = (Set<Employee>)JsonStringToObjectExtensions.toObjectCollection(jsonString,
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
	 * Test method for {@link JsonStringToObjectExtensions#toObjectList(String, Class)}
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
		String jsonString;

		jsonString = "[{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"},"
			+ "{\"person\":{\"name\":\"Andreas\",\"nickname\":\"cute\",\"gender\":\"MALE\",\"about\":\"fine person\",\"married\":false},\"id\":\"24\"},"
			+ "{\"person\":{\"name\":\"Tatjana\",\"nickname\":\"beautiful\",\"gender\":\"FEMALE\",\"about\":\"Im hot\",\"married\":false},\"id\":\"25\"}]";

		jsonList = JsonStringToObjectExtensions.toObjectList(jsonString, Employee.class);
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
	 * Test method for
	 * {@link JsonStringToObjectExtensions#toObject(String, Class, com.fasterxml.jackson.databind.Module...)}
	 * This method shows also how to map a json string created from the org.json library. This is
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
		final String jsonString = "{\"id\":\"23\",\"subOrdinates\": [],\"person\":{\"married\":true,\"nickname\":\"beast\",\"name\":\"Anna\",\"about\":\"Ha ha ha...\",\"gender\":\"FEMALE\"}}";
		final Employee actual = JsonStringToObjectExtensions.toObject(jsonString, Employee.class,
			new JsonOrgModule());
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link JsonStringToObjectExtensions#toObject(String, TypeReference, ObjectMapper)}
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
		String jsonString;

		jsonString = "{\"username\":\"foo\",\"password\":\"bar\"}";
		actual = JsonStringToObjectExtensions.toObject(jsonString, Signin.class);
		expected = Signin.builder().username("foo").password("bar").build();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link JsonStringToObjectExtensions#toObject(String, Class)}
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
		String jsonString;

		jsonString = "{\"username\":\"foo\",\"password\":\"bar\"}";
		typeReference = new TypeReference<Signin>()
		{
		};
		actual = JsonStringToObjectExtensions.toObject(jsonString, typeReference,
			ObjectMapperFactory.newObjectMapper());
		expected = Signin.builder().username("foo").password("bar").build();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link JsonStringToObjectExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(JsonStringToObjectExtensions.class);
	}

}
