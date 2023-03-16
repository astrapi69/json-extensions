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

import static org.testng.Assert.*;

import java.io.IOException;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;

import io.github.astrapi69.test.object.Employee;

/**
 * The unit test class for the class {@link ClassToJsonSchemaExtensions}
 */
public class ClassToJsonSchemaExtensionsTest
{

	/**
	 * Test method for {@link ClassToJsonSchemaExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ClassToJsonSchemaExtensions.class);
	}

	/**
	 * Test method for {@link ClassToJsonSchemaExtensions#toJsonSchemaAsString(Class)}
	 *
	 * @throws JsonProcessingException
	 *             If an error occurs when converting object to String
	 */
	@Test
	public void testToJsonSchemaAsString() throws JsonProcessingException
	{
		String expected;
		String actual;

		actual = ClassToJsonSchemaExtensions.toJsonSchemaAsString(Employee.class);
		assertNotNull(actual);
		expected = // language=json
			"{\n" + "  \"type\" : \"object\",\n" + "  \"properties\" : {\n" + "    \"id\" : {\n"
				+ "      \"type\" : \"string\"\n" + "    },\n" + "    \"person\" : {\n"
				+ "      \"type\" : \"object\",\n" + "      \"properties\" : {\n"
				+ "        \"about\" : {\n" + "          \"type\" : \"string\"\n" + "        },\n"
				+ "        \"gender\" : {\n" + "          \"type\" : \"string\",\n"
				+ "          \"enum\" : [ \"FEMALE\", \"MALE\", \"UNDEFINED\" ]\n" + "        },\n"
				+ "        \"married\" : {\n" + "          \"type\" : \"boolean\"\n"
				+ "        },\n" + "        \"name\" : {\n" + "          \"type\" : \"string\"\n"
				+ "        },\n" + "        \"nickname\" : {\n"
				+ "          \"type\" : \"string\"\n" + "        }\n" + "      }\n" + "    },\n"
				+ "    \"subOrdinates\" : {\n" + "      \"type\" : \"array\"\n" + "    }\n"
				+ "  }\n" + "}";
		assertEquals(actual, expected);
	}


	/**
	 * Test method for {@link ClassToJsonSchemaExtensions#toJsonSchema(Class)}
	 *
	 * @throws JsonProcessingException
	 *             If an error occurs when converting object to String
	 */
	@Test
	public void testToJsonSchema() throws JsonProcessingException
	{
		String expected;
		String actual;
		JsonSchema jsonSchema;

		jsonSchema = ClassToJsonSchemaExtensions.toJsonSchema(Employee.class);
		assertNotNull(jsonSchema);
		expected = "{\"type\":\"object\",\"properties\":{\"id\":{\"type\":\"string\"},\"person\":{\"type\":\"object\",\"properties\":{\"about\":{\"type\":\"string\"},\"gender\":{\"type\":\"string\",\"enum\":[\"FEMALE\",\"MALE\",\"UNDEFINED\"]},\"married\":{\"type\":\"boolean\"},\"name\":{\"type\":\"string\"},\"nickname\":{\"type\":\"string\"}}},\"subOrdinates\":{\"type\":\"array\"}}}";
		actual = jsonSchema.toString();
		assertEquals(actual, expected);
	}
}