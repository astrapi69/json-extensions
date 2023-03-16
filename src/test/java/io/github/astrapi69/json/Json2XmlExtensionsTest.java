/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.json;

import static org.testng.AssertJUnit.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import javax.xml.stream.XMLStreamException;

import org.json.JSONException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.json.to.xml.JsonStreamXMLWriter;

/**
 * The unit test class for the class {@link JsonToXmlExtensions}
 */
public class Json2XmlExtensionsTest
{


	File jsonDir;
	File jsonFile;
	File jsonCollectionFile;

	File jsonMapFile;
	File jsonListFile;

	/**
	 * Transform the given json as {@link String} object to a xml as {@link String} object
	 *
	 * @param jsonString
	 *            the json as {@link String} object
	 * @return the transformed xml as {@link String} object
	 * @throws JSONException
	 *             if there is a syntax error in the source string or a duplicated key.
	 */
	public static String toXml(final String jsonString) throws XMLStreamException, IOException
	{
		InputStream json = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
		ByteArrayOutputStream xml = new ByteArrayOutputStream();
		try (
			Reader reader = new BufferedReader(new InputStreamReader(json, StandardCharsets.UTF_8)))
		{
			new JsonStreamXMLWriter(reader,
				new BufferedWriter(new OutputStreamWriter(xml, StandardCharsets.UTF_8)))
					.convert(StandardCharsets.UTF_8.name(), "1.0");
			return xml.toString(StandardCharsets.UTF_8);
		}
	}

	@BeforeMethod
	protected void setUp()
	{
		jsonDir = new File(PathFinder.getSrcTestResourcesDir(), "json");
		jsonFile = new File(jsonDir, "person.json");
		jsonListFile = new File(jsonDir, "employees.json");
		jsonMapFile = new File(jsonDir, "map.json");
		jsonCollectionFile = new File(jsonDir, "collection.json");
	}

	/**
	 * Test method for {@link Json2XmlExtensionsTest#toXml(String)}
	 *
	 * @throws JSONException
	 *             if there is a syntax error in the source string or a duplicated key
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToXmlStringWithJson2Xml() throws XMLStreamException, IOException
	{
		String expected;
		String actual;
		String jsonString;

		jsonString = "{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"}";
		actual = toXml(jsonString);
		expected = "<?xml version='1.0' encoding='UTF-8'?><map xmlns=\"http://www.w3.org/2005/xpath-functions\"><map key=\"person\"><string key=\"name\">Anna</string><string key=\"nickname\">beast</string><string key=\"gender\">FEMALE</string><string key=\"about\">Ha ha ha...</string><boolean key=\"married\">true</boolean></map><string key=\"id\">23</string></map>";
		assertEquals(actual, expected);

		jsonString = ReadFileExtensions.fromFile(jsonFile);
		actual = toXml(jsonString);
		expected = "<?xml version='1.0' encoding='UTF-8'?><map xmlns=\"http://www.w3.org/2005/xpath-functions\"><string key=\"id\">23</string><map key=\"person\"><string key=\"name\">Anna</string><string key=\"nickname\">beast</string><string key=\"gender\">FEMALE</string><string key=\"about\">Ha ha ha...</string><boolean key=\"married\">true</boolean></map><array key=\"subOrdinates\"/></map>";
		assertEquals(actual, expected);

		jsonString = ReadFileExtensions.fromFile(jsonListFile);
		actual = toXml(jsonString);
		expected = "<?xml version='1.0' encoding='UTF-8'?><array xmlns=\"http://www.w3.org/2005/xpath-functions\"><map><string key=\"id\">23</string><map key=\"person\"><string key=\"name\">Anna</string><string key=\"nickname\">beast</string><string key=\"gender\">FEMALE</string><string key=\"about\">Ha ha ha...</string><boolean key=\"married\">true</boolean></map><array key=\"subOrdinates\"/></map><map><string key=\"id\">24</string><map key=\"person\"><string key=\"name\">Andreas</string><string key=\"nickname\">cute</string><string key=\"gender\">MALE</string><string key=\"about\">fine person</string><boolean key=\"married\">false</boolean></map><array key=\"subOrdinates\"/></map><map><string key=\"id\">25</string><map key=\"person\"><string key=\"name\">Tatjana</string><string key=\"nickname\">beautiful</string><string key=\"gender\">FEMALE</string><string key=\"about\">Im hot</string><boolean key=\"married\">false</boolean></map><array key=\"subOrdinates\"/></map></array>";
		assertEquals(actual, expected);

		jsonString = ReadFileExtensions.fromFile(jsonMapFile);
		actual = toXml(jsonString);
		expected = "<?xml version='1.0' encoding='UTF-8'?><map xmlns=\"http://www.w3.org/2005/xpath-functions\"><number key=\"1\">0</number><number key=\"2\">0</number><number key=\"3\">0</number><number key=\"4\">0</number><number key=\"5\">0</number></map>";
		assertEquals(actual, expected);

		jsonString = ReadFileExtensions.fromFile(jsonCollectionFile);
		actual = toXml(jsonString);
		expected = "<?xml version='1.0' encoding='UTF-8'?><array xmlns=\"http://www.w3.org/2005/xpath-functions\"><map><map key=\"person\"><string key=\"name\">Anna</string><string key=\"nickname\">beast</string><string key=\"gender\">FEMALE</string><string key=\"about\">Ha ha ha...</string><boolean key=\"married\">true</boolean></map><string key=\"id\">23</string></map><map><map key=\"person\"><string key=\"name\">Andreas</string><string key=\"nickname\">cute</string><string key=\"gender\">MALE</string><string key=\"about\">fine person</string><boolean key=\"married\">false</boolean></map><string key=\"id\">24</string></map><map><map key=\"person\"><string key=\"name\">Tatjana</string><string key=\"nickname\">beautiful</string><string key=\"gender\">FEMALE</string><string key=\"about\">Im hot</string><boolean key=\"married\">false</boolean></map><string key=\"id\">25</string></map></array>";
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link JsonToXmlExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(JsonToXmlExtensions.class);
	}

}
