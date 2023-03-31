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

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.github.astrapi69.json.JsonToXmlExtensions;

/**
 * The class {@link YamlToXmlExtensions} helps to transform a given yaml string to an xml string.
 */
public final class YamlToXmlExtensions
{

	private YamlToXmlExtensions()
	{
	}

	/**
	 * Transform the given yaml as {@link String} object to a xml as {@link String} object
	 *
	 * @param yamlString
	 *            the yaml as {@link String} object
	 * @return the transformed xml as {@link String} object
	 * @throws JSONException
	 *             if there is a syntax error in the source string or a duplicated key
	 * @throws JsonProcessingException
	 *             If an error occurs when converting object to String
	 */
	public static String toXml(final String yamlString)
		throws JSONException, JsonProcessingException
	{
		final String jsonString = YamlToJsonExtensions.toJson(yamlString);
		return JsonToXmlExtensions.toXml(jsonString);
	}

	/**
	 * Transform the given yaml as {@link String} object to a xml as {@link String} object
	 *
	 * @param yamlString
	 *            the yaml as {@link String} object
	 * @param indent
	 *            the indent is the number of spaces to add to each level of indentation
	 * @return the transformed xml as {@link String} object
	 * @throws JSONException
	 *             if there is a syntax error in the source string or a duplicated key
	 * @throws JsonProcessingException
	 *             If an error occurs when converting object to String
	 */
	public static String toXml(final String yamlString, int indent)
		throws JSONException, JsonProcessingException
	{
		final String jsonString = YamlToJsonExtensions.toJson(yamlString);
		return JsonToXmlExtensions.toXml(jsonString, indent);
	}

	/**
	 * Transform the given yaml as {@link String} object to a xml as {@link String} object
	 *
	 * @param yamlString
	 *            the yaml as {@link String} object
	 * @param indent
	 *            the indent is the number of spaces to add to each level of indentation
	 * @param withHeader
	 *            the flag that indicates if an xml header should be added
	 * @return the transformed xml as {@link String} object
	 * @throws JSONException
	 *             if there is a syntax error in the source string or a duplicated key
	 * @throws JsonProcessingException
	 *             If an error occurs when converting object to String
	 */
	public static String toXml(final String yamlString, int indent, boolean withHeader)
		throws JSONException, JsonProcessingException
	{
		final String jsonString = YamlToJsonExtensions.toJson(yamlString);
		return JsonToXmlExtensions.toXml(jsonString, indent, withHeader);
	}

	/**
	 * Transform the given yaml as {@link String} object to a xml as {@link String} object
	 *
	 * @param yamlString
	 *            the yaml as {@link String} object
	 * @param indent
	 *            the indent is the number of spaces to add to each level of indentation
	 * @param withHeader
	 *            the flag that indicates if a xml header should be added
	 * @param withRootTag
	 *            the flag that indicates if a root tag will be added
	 * @param rootTagName
	 *            the name of the root tag
	 * @return the transformed xml as {@link String} object
	 * @throws JSONException
	 *             if there is a syntax error in the source string or a duplicated key
	 * @throws JsonProcessingException
	 *             If an error occurs when converting object to String
	 */
	public static String toXml(final String yamlString, int indent, boolean withHeader,
		boolean withRootTag, String rootTagName) throws JSONException, JsonProcessingException
	{
		final String jsonString = YamlToJsonExtensions.toJson(yamlString);
		return JsonToXmlExtensions.toXml(jsonString, indent, withHeader, withRootTag, rootTagName);
	}

}
