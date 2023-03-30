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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import io.github.astrapi69.json.factory.ObjectMapperFactory;
import io.github.astrapi69.yaml.factory.YAMLMapperFactory;

/**
 * The class {@link YamlToJsonExtensions} helps to transform a given yaml string to a json string
 */
public final class YamlToJsonExtensions
{

	private YamlToJsonExtensions()
	{
	}

	/**
	 * Transform the given yaml as {@link String} object to a json as {@link String} object
	 *
	 * @param yamlString
	 *            the yaml as {@link String} object
	 * @return the transformed json as {@link String} object
	 * @throws JsonProcessingException
	 *             If an error occurs when converting object to String
	 */
	public static String toJson(final String yamlString) throws JsonProcessingException
	{
		final YAMLMapper yamlMapper = YAMLMapperFactory.newYAMLMapper();
		Object value = yamlMapper.readValue(yamlString, Object.class);
		final ObjectMapper objectMapper = ObjectMapperFactory.newObjectMapper();
		return toJson(yamlString, false);
	}

	/**
	 * Transform the given yaml as {@link String} object to a json as {@link String} object
	 *
	 * @param yamlString
	 *            the yaml as {@link String} object
	 * @param prettyPrint
	 *            the flag that indicates if the output json string should be pretty formatted
	 * @return the transformed json as {@link String} object
	 * @throws JsonProcessingException
	 *             If an error occurs when converting object to String
	 */
	public static String toJson(final String yamlString, boolean prettyPrint)
		throws JsonProcessingException
	{
		final YAMLMapper yamlMapper = YAMLMapperFactory.newYAMLMapper();
		Object value = yamlMapper.readValue(yamlString, Object.class);
		final ObjectMapper objectMapper = ObjectMapperFactory.newObjectMapper();
		if (prettyPrint)
		{
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		}
		return objectMapper.writeValueAsString(value);
	}


	/**
	 * Transforms the given yaml file into a json as {@link String} object
	 *
	 * @param yamlFile
	 *            the yaml file
	 * @return the transformed json as {@link String} object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static String toJson(final File yamlFile) throws IOException
	{
		final YAMLMapper yamlMapper = YAMLMapperFactory.newYAMLMapper();
		Object value = yamlMapper.readValue(yamlFile, Object.class);
		final ObjectMapper objectMapper = ObjectMapperFactory.newObjectMapper();
		return objectMapper.writeValueAsString(value);
	}

	/**
	 * Transforms the given yaml file into a json as {@link String} object
	 *
	 * @param yamlFile
	 *            the yaml file
	 * @param prettyPrint
	 *            the flag that indicates if the output json string should be pretty formatted
	 * @return the transformed json as {@link String} object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static String toJson(final File yamlFile, boolean prettyPrint) throws IOException
	{
		final YAMLMapper yamlMapper = YAMLMapperFactory.newYAMLMapper();
		Object value = yamlMapper.readValue(yamlFile, Object.class);
		final ObjectMapper objectMapper = ObjectMapperFactory.newObjectMapper();
		if (prettyPrint)
		{
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		}
		return objectMapper.writeValueAsString(value);
	}

	/**
	 * Transforms the given yaml file into a json as {@link File} object
	 *
	 * @param yamlFile
	 *            the yaml file
	 * @param resultJsonFile
	 *            the result file in json format
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static void toJson(final File yamlFile, File resultJsonFile) throws IOException
	{
		if (!resultJsonFile.exists())
		{
			resultJsonFile.createNewFile();
		}
		if (resultJsonFile.exists())
		{
			final String yaml = toJson(yamlFile);
			try (PrintWriter out = new PrintWriter(resultJsonFile))
			{
				out.println(yaml);
			}
		}
	}

}
