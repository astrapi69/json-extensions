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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;

import io.github.astrapi69.json.factory.ObjectMapperFactory;

/**
 * The class {@link ClassToJsonSchemaExtensions} converts java class objects to json schema object
 */
public class ClassToJsonSchemaExtensions
{


	/**
	 * Transforms the given java class object to json schema as string
	 *
	 * @param clazz
	 *            the clazz
	 * @return the json schema as string
	 * @throws JsonProcessingException
	 *             If an error occurs when converting object to String
	 */
	public static String toJsonSchemaAsString(Class clazz) throws JsonProcessingException
	{
		ObjectMapper objectMapper = ObjectMapperFactory.newJaxbObjectMapper();
		final JsonSchema jsonSchema = objectMapper.generateJsonSchema(clazz);
		return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonSchema);
	}


	/**
	 * Transforms the given java class object to json schema
	 *
	 * @param clazz
	 *            the clazz
	 * @return the json schema
	 * @throws JsonProcessingException
	 *             If an error occurs when converting object to String
	 */
	public static JsonSchema toJsonSchema(Class clazz) throws JsonProcessingException
	{
		ObjectMapper objectMapper = ObjectMapperFactory.newJaxbObjectMapper();
		return objectMapper.generateJsonSchema(clazz);
	}
}
