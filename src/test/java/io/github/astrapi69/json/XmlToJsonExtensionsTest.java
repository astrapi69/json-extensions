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

import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.file.search.PathFinder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.testng.AssertJUnit.assertEquals;

/**
 * The unit test class for the class {@link XmlToJsonExtensions}
 */
public class XmlToJsonExtensionsTest
{

	File xmlDir;
	File xmlFile;

	@BeforeMethod
	protected void setUp()
	{
		xmlDir = new File(PathFinder.getSrcTestResourcesDir(), "xml");
		xmlFile = new File(xmlDir, "new-employee.xml");
	}

	/**
	 * Test method for {@link XmlToJsonExtensions#toJson(String)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToJson() throws IOException
	{
		String expected;
		String actual;
		String xmlString;

		xmlString = ReadFileExtensions.fromFile(xmlFile);

		actual = XmlToJsonExtensions.toJson(xmlString);
		expected = // language=json
			"{\"Employee\":{\"person\":{\"gender\":\"FEMALE\",\"about\":\"\",\"name\":\"Anna\",\"nickname\":\"\",\"married\":\"\"},\"id\":23,\"subOrdinates\":\"\"}}";
		assertEquals(expected, actual);

		xmlString = "<person><gender>FEMALE</gender><name>Anna</name><nickname>beast</nickname><about>Ha ha ha...</about><married>true</married></person><id>23</id>";

		actual = XmlToJsonExtensions.toJson(xmlString);
		expected = // language=json
			"{\"person\":{\"gender\":\"FEMALE\",\"name\":\"Anna\",\"nickname\":\"beast\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":23}";
		assertEquals(expected, actual);

		actual = JsonToXmlExtensions.toXml(actual);
		expected = xmlString;
		assertEquals(expected, actual);

		xmlString = "<array><person><gender>FEMALE</gender><name>Anna</name><nickname>beast</nickname><about>Ha ha ha...</about><married>true</married></person><id>23</id></array><array><person><gender>MALE</gender><name>Andreas</name><nickname>cute</nickname><about>fine person</about><married>false</married></person><id>24</id></array><array><person><gender>FEMALE</gender><name>Tatjana</name><nickname>beautiful</nickname><about>Im hot</about><married>false</married></person><id>25</id></array>";
		actual = XmlToJsonExtensions.toJson(xmlString);

		expected = // language=json
			"{\"array\":[{\"person\":{\"gender\":\"FEMALE\",\"name\":\"Anna\",\"nickname\":\"beast\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":23},{\"person\":{\"gender\":\"MALE\",\"name\":\"Andreas\",\"nickname\":\"cute\",\"about\":\"fine person\",\"married\":false},\"id\":24},{\"person\":{\"gender\":\"FEMALE\",\"name\":\"Tatjana\",\"nickname\":\"beautiful\",\"about\":\"Im hot\",\"married\":false},\"id\":25}]}";
		assertEquals(expected, actual);

		actual = JsonToXmlExtensions.toXml(actual);
		expected = xmlString;
		assertEquals(expected, actual);

		xmlString = "<1>0</1><2>0</2><3>0</3><4>0</4><5>0</5>";
		actual = XmlToJsonExtensions.toJson(xmlString);
		expected = // language=json
			"{\"1\":0,\"2\":0,\"3\":0,\"4\":0,\"5\":0}";
		assertEquals(expected, actual);

		actual = JsonToXmlExtensions.toXml(actual);
		expected = xmlString;
		assertEquals(expected, actual);


		xmlString = "<array><person><gender>FEMALE</gender><name>Anna</name><nickname>beast</nickname><about>Ha ha ha...</about><married>true</married></person><id>23</id></array><array><person><gender>MALE</gender><name>Andreas</name><nickname>cute</nickname><about>fine person</about><married>false</married></person><id>24</id></array><array><person><gender>FEMALE</gender><name>Tatjana</name><nickname>beautiful</nickname><about>Im hot</about><married>false</married></person><id>25</id></array>";
		actual = XmlToJsonExtensions.toJson(xmlString);
		expected = // language=json
			"{\"array\":[{\"person\":{\"gender\":\"FEMALE\",\"name\":\"Anna\",\"nickname\":\"beast\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":23},{\"person\":{\"gender\":\"MALE\",\"name\":\"Andreas\",\"nickname\":\"cute\",\"about\":\"fine person\",\"married\":false},\"id\":24},{\"person\":{\"gender\":\"FEMALE\",\"name\":\"Tatjana\",\"nickname\":\"beautiful\",\"about\":\"Im hot\",\"married\":false},\"id\":25}]}";
		assertEquals(expected, actual);

		actual = JsonToXmlExtensions.toXml(actual);
		expected = xmlString;
		assertEquals(expected, actual);
	}
}