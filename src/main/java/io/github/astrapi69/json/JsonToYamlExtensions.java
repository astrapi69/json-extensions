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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import io.github.astrapi69.json.factory.ObjectMapperFactory;
import io.github.astrapi69.json.factory.YAMLMapperFactory;

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
		final String yamlString = YAMLMapperFactory.newYAMLMapper().writeValueAsString(jsonNode);
		return yamlString;
	}


	/**
	 * Transforms the given json file into a yaml as {@link String} object
	 *
	 * @param jsonFile
	 *            the json file
	 * @return the transformed yaml as {@link String} object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static String toYaml(final File jsonFile) throws IOException
	{
		JsonNode jsonNode = YAMLMapperFactory.newYAMLMapper().readTree(jsonFile);
		final String yamlString = YAMLMapperFactory.newYAMLMapper().writeValueAsString(jsonNode);
		return yamlString;
	}

	/**
	 * Transforms the given json file into a yaml as {@link File} object
	 *
	 * @param jsonFile
	 *            the json file
	 * @param resultYamlFile
	 *            the result file in yaml format
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static void toYaml(final File jsonFile, File resultYamlFile) throws IOException
	{
		if (!resultYamlFile.exists())
		{
			resultYamlFile.createNewFile();
		}
		if (resultYamlFile.exists())
		{
			final String yaml = toYaml(jsonFile);
			try (PrintWriter out = new PrintWriter(resultYamlFile))
			{
				out.println(yaml);
			}
		}
	}

}
