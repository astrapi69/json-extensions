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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

/**
 * The class {@link JsonToXmlExtensions} helps to transform a given json string to an xml string.
 */
public final class JsonToXmlExtensions
{
	private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

	private JsonToXmlExtensions()
	{
	}

	/**
	 * Transform the given json as {@link String} object to a xml as {@link String} object
	 *
	 * @param jsonString
	 *            the json as {@link String} object
	 * @return the transformed xml as {@link String} object
	 * @throws JSONException
	 *             if there is a syntax error in the source string or a duplicated key
	 */
	public static String toXml(final String jsonString) throws JSONException
	{
		if (jsonString.startsWith("["))
		{
			JSONArray jsonArray = new JSONArray(jsonString);
			return XML.toString(jsonArray);
		}
		else
		{
			final JSONObject jsonObject = new JSONObject(jsonString);
			return XML.toString(jsonObject);
		}
	}

	/**
	 * Transform the given json as {@link String} object to a xml as {@link String} object
	 *
	 * @param jsonString
	 *            the json as {@link String} object
	 * @param indent
	 *            the indent is the number of spaces to add to each level of indentation
	 * @return the transformed xml as {@link String} object
	 * @throws JSONException
	 *             if there is a syntax error in the source string or a duplicated key
	 */
	public static String toXml(final String jsonString, int indent) throws JSONException
	{
		return toXml(jsonString, indent, false);
	}

	/**
	 * Transform the given json as {@link String} object to a xml as {@link String} object
	 *
	 * @param jsonString
	 *            the json as {@link String} object
	 * @param indent
	 *            the indent is the number of spaces to add to each level of indentation
	 * @param withHeader
	 *            the flag that indicates if an xml header should be added
	 * @return the transformed xml as {@link String} object
	 * @throws JSONException
	 *             if there is a syntax error in the source string or a duplicated key
	 */
	public static String toXml(final String jsonString, int indent, boolean withHeader)
		throws JSONException
	{
		if (jsonString.startsWith("["))
		{
			JSONArray objects = new JSONArray(jsonString);
			return withHeader
				? XML_HEADER + System.lineSeparator() + XML.toString(objects, indent)
				: XML.toString(objects, indent);
		}
		else
		{
			final JSONObject json = new JSONObject(jsonString);
			return withHeader
				? XML_HEADER + System.lineSeparator() + XML.toString(json, indent)
				: XML.toString(json, indent);
		}
	}

	/**
	 * Transform the given json as {@link String} object to a xml as {@link String} object
	 *
	 * @param jsonString
	 *            the json as {@link String} object
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
	 */
	public static String toXml(final String jsonString, int indent, boolean withHeader,
		boolean withRootTag, String rootTagName) throws JSONException
	{
		if (jsonString.startsWith("["))
		{
			JSONArray jsonArray = new JSONArray(jsonString);
			return getXmlString(withHeader, withRootTag, rootTagName,
				XML.toString(jsonArray, indent));
		}
		else
		{
			final JSONObject jsonObject = new JSONObject(jsonString);
			return getXmlString(withHeader, withRootTag, rootTagName,
				XML.toString(jsonObject, indent));
		}
	}

	private static String getXmlString(boolean withHeader, boolean withRootTag, String rootTagName,
		String xmlString)
	{
		StringBuilder sb = new StringBuilder();
		if (withHeader)
		{
			sb.append(XML_HEADER).append(System.lineSeparator());
		}
		if (withRootTag && rootTagName != null && !rootTagName.isEmpty())
		{
			sb.append("<").append(rootTagName).append(">").append(System.lineSeparator());
		}
		sb.append(xmlString);
		if (withRootTag && rootTagName != null && !rootTagName.isEmpty())
		{
			sb.append("</").append(rootTagName).append(">");
		}
		return sb.toString();
	}

}
