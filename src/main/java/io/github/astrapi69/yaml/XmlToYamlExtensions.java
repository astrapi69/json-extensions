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

import com.fasterxml.jackson.core.JsonProcessingException;

import io.github.astrapi69.json.JsonToYamlExtensions;
import io.github.astrapi69.json.XmlToJsonExtensions;

/**
 * The class {@link XmlToYamlExtensions} provides methods for convert xml string objects to yaml
 * string
 */
public class XmlToYamlExtensions
{

	private XmlToYamlExtensions()
	{
	}

	/**
	 * Creates from the given xml string a yaml string
	 *
	 * @param xmlString
	 *            the xml string
	 * @return the yaml string
	 * @throws JsonProcessingException
	 *             If an error occurs when converting object to String
	 */
	public static String toYaml(final String xmlString) throws JsonProcessingException
	{
		return JsonToYamlExtensions.toYaml(XmlToJsonExtensions.toJson(xmlString));
	}
}
