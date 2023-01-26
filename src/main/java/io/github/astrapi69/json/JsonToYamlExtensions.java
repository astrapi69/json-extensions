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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import io.github.astrapi69.json.factory.ObjectMapperFactory;

/**
 * The class {@link JsonToYamlExtensions} helps to transform a given json string to a yaml string
 */
public final class JsonToYamlExtensions
{

	private JsonToYamlExtensions()
	{
	}

	/**
	 * Transform the given json as {@link String} object to a yaml as {@link String} object
	 *
	 * @param jsonString
	 *            the json as {@link String} object
	 * @return the transformed yaml as {@link String} object
	 * @throws JsonProcessingException
	 *             If an error occurs when converting object to String
	 */
	public static String toYaml(final String jsonString) throws JsonProcessingException
	{
		JsonNode jsonNode = ObjectMapperFactory.newObjectMapper().readTree(jsonString);
		final String yamlString = new YAMLMapper().writeValueAsString(jsonNode);
		return yamlString;
	}

}
