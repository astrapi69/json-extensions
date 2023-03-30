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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.github.astrapi69.collection.list.ListFactory;
import io.github.astrapi69.collection.map.MapFactory;
import io.github.astrapi69.test.object.Employee;
import io.github.astrapi69.test.object.Person;
import io.github.astrapi69.test.object.enumtype.Gender;

/**
 * The unit test class for the class {@link ObjectToYamlExtensions}
 */
public class ObjectToYamlExtensionsTest
{

	public static <K> Map<K, Integer> newCounterMap(final Collection<K> elements)
	{
		Objects.requireNonNull(elements);
		Map<K, Integer> elementsCount = MapFactory.newHashMap();
		for (K element : elements)
		{
			if (elementsCount.containsKey(element))
			{
				elementsCount.merge(element, 1, Integer::sum);
				continue;
			}
			elementsCount.put(element, 0);
		}
		return elementsCount;
	}

	/**
	 * Test method for {@link ObjectToYamlExtensions#toYaml(Object)}
	 *
	 * @throws JsonProcessingException
	 *             if an error occurs when converting object to String
	 */
	@Test
	public void testToJson() throws JsonProcessingException
	{
		String expected;
		String actual;
		final Employee employee = Employee.builder().person(Person.builder().gender(Gender.FEMALE)
			.name("Anna").married(true).about("Ha ha ha...").nickname("beast").build()).id("23")
			.build();
		// new scenario: try to convert a Employee object to json
		expected = "---\n" + "id: \"23\"\n" + "person:\n" + "  about: \"Ha ha ha...\"\n"
			+ "  gender: \"FEMALE\"\n" + "  married: true\n" + "  name: \"Anna\"\n"
			+ "  nickname: \"beast\"\n" + "subOrdinates: []\n";
		actual = ObjectToYamlExtensions.toYaml(employee);
		assertEquals(expected, actual);
		// new scenario: try to convert a integer map to json
		Map<Integer, Integer> numberCounterMap = newCounterMap(ListFactory.newRangeList(1, 5));
		expected = "---\n" + "1: 0\n" + "2: 0\n" + "3: 0\n" + "4: 0\n" + "5: 0\n";
		actual = ObjectToYamlExtensions.toYaml(numberCounterMap);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ObjectToYamlExtensions#toYaml(Object)} with {@link Map}
	 *
	 * @throws JsonProcessingException
	 *             if an error occurs when converting object to String
	 */
	@Test
	public void testToJsonFromMap() throws JsonProcessingException
	{
		String expected;
		String actual;
		final Map<String, String> stringMap = new HashMap<>();
		stringMap.put("a", "ss");
		stringMap.put("b", "qq");

		expected = "---\n" + "a: \"ss\"\n" + "b: \"qq\"\n";
		actual = ObjectToYamlExtensions.toYaml(stringMap);
		assertEquals(actual, expected);

		final Employee employee1 = Employee.builder().person(Person.builder().gender(Gender.FEMALE)
			.name("Anna").married(true).about("Ha ha ha...").nickname("beast").build()).id("23")
			.build();

		final Map<Integer, Employee> integerEmployeeMap = new HashMap<>();
		integerEmployeeMap.put(1, employee1);

		expected = "---\n" + "1:\n" + "  id: \"23\"\n" + "  person:\n"
			+ "    about: \"Ha ha ha...\"\n" + "    gender: \"FEMALE\"\n" + "    married: true\n"
			+ "    name: \"Anna\"\n" + "    nickname: \"beast\"\n" + "  subOrdinates: []\n";
		actual = ObjectToYamlExtensions.toYaml(integerEmployeeMap);
		assertEquals(actual, expected);

	}

	/**
	 * Test method for {@link ObjectToYamlExtensions#toYaml(List)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToJsonList() throws IOException
	{
		String expected;
		String actual;
		List<Employee> employees;

		employees = new ArrayList<>();
		employees
			.add(
				Employee
					.builder().person(Person.builder().gender(Gender.FEMALE).name("Anna")
						.married(true).about("Ha ha ha...").nickname("beast").build())
					.id("23").build());
		employees
			.add(Employee
				.builder().person(Person.builder().gender(Gender.MALE).name("Andreas")
					.married(false).about("fine person").nickname("cute").build())
				.id("24").build());
		employees
			.add(Employee
				.builder().person(Person.builder().gender(Gender.FEMALE).name("Tatjana")
					.married(false).about("Im hot").nickname("beautiful").build())
				.id("25").build());
		actual = ObjectToYamlExtensions.toYaml(employees);

		expected = "---\n" + "- id: \"23\"\n" + "  person:\n" + "    about: \"Ha ha ha...\"\n"
			+ "    gender: \"FEMALE\"\n" + "    married: true\n" + "    name: \"Anna\"\n"
			+ "    nickname: \"beast\"\n" + "  subOrdinates: []\n" + "- id: \"24\"\n"
			+ "  person:\n" + "    about: \"fine person\"\n" + "    gender: \"MALE\"\n"
			+ "    married: false\n" + "    name: \"Andreas\"\n" + "    nickname: \"cute\"\n"
			+ "  subOrdinates: []\n" + "- id: \"25\"\n" + "  person:\n" + "    about: \"Im hot\"\n"
			+ "    gender: \"FEMALE\"\n" + "    married: false\n" + "    name: \"Tatjana\"\n"
			+ "    nickname: \"beautiful\"\n" + "  subOrdinates: []\n";
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link ObjectToYamlExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ObjectToYamlExtensions.class);
	}

}
