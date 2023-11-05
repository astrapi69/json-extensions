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
package io.github.astrapi69.json.factory;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.util.List;
import java.util.Map;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.astrapi69.test.object.Person;
import io.github.astrapi69.test.object.generic.GenericDao;

/**
 * The unit test class for the class {@link JavaTypeFactory}
 */
public class JavaTypeFactoryTest
{

	/**
	 * Test method for {@link JavaTypeFactory}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(JavaTypeFactory.class);
	}

	/**
	 * Test method for {@link JavaTypeFactory#newParametricType(Class, Class...)}
	 */
	@Test
	public void testNewParametricTypeWithClass()
	{
		JavaType actual;
		actual = JavaTypeFactory.newParametricType(GenericDao.class, Integer.class, Person.class);
		assertNotNull(actual);
	}

	/**
	 * Test method for {@link JavaTypeFactory#newParametricType(Class, JavaType...)}
	 */
	@Test
	public void testNewParametricType()
	{
		JavaType actual;
		JavaType integerType;
		JavaType personType;

		integerType = JavaTypeFactory.newJavaType(Integer.TYPE);
		personType = JavaTypeFactory.newJavaType(Person.class);
		actual = JavaTypeFactory.newParametricType(GenericDao.class, integerType, personType);
		assertNotNull(actual);
	}

	/**
	 * Test method for {@link JavaTypeFactory#newCollectionType(Class, Class)}
	 */
	@Test
	public void testNewCollectionType()
	{
		JavaType actual;
		actual = JavaTypeFactory.newCollectionType(List.class, Person.class);
		assertNotNull(actual);
	}

	/**
	 * Test method for {@link JavaTypeFactory#newCollectionType(Class, Class)}
	 */
	@Test
	public void testNewCollectionTypeWithJavaType()
	{
		JavaType actual;
		JavaType personType;

		personType = JavaTypeFactory.newJavaType(Person.class);
		actual = JavaTypeFactory.newCollectionType(List.class, personType);
		assertNotNull(actual);
	}

	/**
	 * Test method for {@link JavaTypeFactory#newCollectionType(ObjectMapper, Class, Class)}
	 */
	@Test
	public void testNewCollectionTypeWithXmlMapper()
	{
		JavaType actual;
		actual = JavaTypeFactory.newCollectionType(ObjectMapperFactory.newObjectMapper(),
			List.class, Person.class);
		assertNotNull(actual);
	}

	/**
	 * Test method for {@link JavaTypeFactory#newMapType(Class, Class, Class)}
	 */
	@Test
	public void testNewMapType()
	{
		JavaType actual;
		actual = JavaTypeFactory.newMapType(Map.class, Integer.class, Person.class);
		assertNotNull(actual);
	}

	/**
	 * Test method for {@link JavaTypeFactory#newMapType(Class, Class, Class)}
	 */
	@Test
	public void testNewMapTypeWithJavaType()
	{
		JavaType actual;
		JavaType integerType;
		JavaType personType;

		integerType = JavaTypeFactory.newJavaType(Integer.TYPE);
		personType = JavaTypeFactory.newJavaType(Person.class);
		actual = JavaTypeFactory.newMapType(Map.class, integerType, personType);
		assertNotNull(actual);
	}

	/**
	 * Test method for {@link JavaTypeFactory#newJavaType(Class)}
	 */
	@Test
	public void testNewJavaType()
	{
		JavaType actual;
		actual = JavaTypeFactory.newJavaType(Integer.class);
		assertNotNull(actual);
	}

	/**
	 * Test method for {@link JavaTypeFactory#newJavaType(TypeReference)}
	 */
	@Test
	public void testNewJavaTypeWithTypeReference()
	{
		JavaType actual;
		JavaType expected;
		TypeReference<Integer> integerTypeReference = JavaTypeFactory
			.newTypeReference(Integer.class);
		actual = JavaTypeFactory.newJavaType(integerTypeReference);
		assertNotNull(actual);
		expected = JavaTypeFactory.newJavaType(Integer.class);
		assertEquals(actual, expected);
	}

}
