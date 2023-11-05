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

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.astrapi69.collection.properties.SortedProperties;
import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.file.search.PathFinder;

public class JsonToPropertiesExtensionsTest
{

	File jsonDir;
	File jsonFile;
	File jsonLongFile;
	String jsonString;
	String jsonLongString;

	@BeforeMethod
	protected void setUp() throws IOException
	{
		jsonDir = new File(PathFinder.getSrcTestResourcesDir(), "json");
		jsonFile = new File(jsonDir, "en.json");
		jsonLongFile = new File(jsonDir, "en-long.json");
		jsonString = ReadFileExtensions.fromFile(jsonFile);
		jsonLongString = ReadFileExtensions.fromFile(jsonLongFile);
	}

	/**
	 * Test method for {@link JsonToPropertiesExtensionsTest#toProperties(File)}
	 *
	 * @throws FileNotFoundException
	 *             is thrown if the json file is not found
	 */
	@Test
	public void testToPropertiesFromJsonFile() throws FileNotFoundException
	{
		Properties actual;
		Properties expected;
		// new scenario...
		expected = new Properties();
		expected.put("myapp.menu.new", "Translation new");
		expected.put("myapp.title", "Translation app");
		expected.put("myapp.menu.edit", "Translation edit");
		expected.put("myapp.text", "Translation app for test with ngx-translate");
		actual = JsonToPropertiesExtensionsTest.toProperties(jsonFile);
		assertEquals(actual, expected);

		actual = JsonToPropertiesExtensionsTest.toProperties(jsonLongFile);

		expected = new Properties();
		expected.put("myapp.title", "Translation app");
		expected.put("myapp.text", "Translation app for test with ngx-translate");
		expected.put("myapp.menu.new", "Translation new");
		expected.put("myapp.menu.edit", "Translation edit");
		expected.put("myapp.menu.popup.copy", "Copy");
		expected.put("foo.title", "Translation foo");
		expected.put("foo.text", "Translation foo for test with ngx-translate");
		expected.put("foo.menu.new", "Translation new");
		expected.put("foo.menu.edit", "Translation edit");
		expected.put("foo.menu.popup.copy", "Copy");
		assertEquals(actual, expected);
	}

	@Test
	public void testToPropertiesFromJsonString()
	{
		Properties actual;
		Properties expected;
		// new scenario...
		expected = new SortedProperties();
		expected.put("myapp.menu.new", "Translation new");
		expected.put("myapp.title", "Translation app");
		expected.put("myapp.menu.edit", "Translation edit");
		expected.put("myapp.text", "Translation app for test with ngx-translate");
		actual = JsonToPropertiesExtensionsTest.toProperties(jsonString);
		assertEquals(actual, expected);
		// new scenario...
		expected = new SortedProperties();
		expected.put("myapp.title", "Translation app");
		expected.put("myapp.text", "Translation app for test with ngx-translate");
		expected.put("myapp.menu.new", "Translation new");
		expected.put("myapp.menu.edit", "Translation edit");
		expected.put("myapp.menu.popup.copy", "Copy");
		expected.put("foo.title", "Translation foo");
		expected.put("foo.text", "Translation foo for test with ngx-translate");
		expected.put("foo.menu.new", "Translation new");
		expected.put("foo.menu.edit", "Translation edit");
		expected.put("foo.menu.popup.copy", "Copy");
		actual = JsonToPropertiesExtensionsTest.toProperties(jsonLongString);
		assertEquals(actual, expected);
	}


	/**
	 * Transforms the given json file into a java properties object
	 *
	 * @param jsonFile
	 *            the json file
	 * @return the generated java properties object
	 * @throws FileNotFoundException
	 *             is thrown if the given file is not found
	 */
	public static Properties toProperties(final File jsonFile) throws FileNotFoundException
	{
		Objects.requireNonNull(jsonFile);
		Properties properties = prepare(jsonFile);
		Enumeration<Object> keys = properties.keys();
		Properties sortedProperties = new Properties();
		while (keys.hasMoreElements())
		{
			Object key = keys.nextElement();
			sortedProperties.put(key, properties.get(key));
		}
		return sortedProperties;
	}

	/**
	 * Prepare and return the sorted properties from the given json file
	 *
	 * @param jsonFile
	 *            the json file
	 * @return the generated java properties object
	 * @throws FileNotFoundException
	 *             is thrown if the given file is not found
	 */
	public static Properties prepare(final File jsonFile) throws FileNotFoundException
	{
		Objects.requireNonNull(jsonFile);
		Properties properties = new Properties();
		JsonReader jsonReader = Json.createReader(new FileReader(jsonFile));
		JsonObject root = jsonReader.readObject();
		addPropertiesFromJsonObject(root, null, properties);
		return properties;
	}

	/**
	 * Transforms the given json string into a java properties object
	 *
	 * @param jsonString
	 *            the json string
	 * @return the generated java properties object
	 */
	public static Properties toProperties(final String jsonString)
	{
		Objects.requireNonNull(jsonString);
		Properties properties = new Properties();
		JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
		JsonObject root = jsonReader.readObject();
		addPropertiesFromJsonObject(root, null, properties);
		return properties;
	}

	private static void addPropertiesFromJsonObject(JsonObject jsonObject, String parentKey,
		Properties properties)
	{
		Set<String> jsonKeys = jsonObject.keySet();
		for (String key : jsonKeys)
		{
			Object val = jsonObject.get(key);
			String fullKey = parentKey == null || parentKey.length() == 0
				? key
				: parentKey + "." + key;
			if (val instanceof JsonArray)
			{
				JsonArray array = (JsonArray)val;
				addPropertiesFromJsonArray(array, fullKey, properties);
			}
			else if (val instanceof JsonObject)
			{
				JsonObject jsonOb = (JsonObject)val;
				addPropertiesFromJsonObject(jsonOb, fullKey, properties);
			}
			else
			{
				JsonString js = (JsonString)val;
				properties.put(fullKey, js.getString());
			}
		}
	}

	private static void addPropertiesFromJsonArray(JsonArray array, String parentKey,
		Properties properties)
	{
		if (array.size() == 0)
		{
			properties.put(parentKey, "");
		}
		else
		{
			for (int i = 0; i < array.size(); i++)
			{
				Object jsonElement = array.get(i);
				if (jsonElement instanceof JsonObject)
				{
					JsonObject jsonObject = (JsonObject)jsonElement;
					addPropertiesFromJsonObject(jsonObject, parentKey, properties);
				}
			}
		}
	}
}
