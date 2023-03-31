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

import static org.testng.AssertJUnit.assertEquals;

import java.io.File;
import java.io.IOException;

import org.meanbean.test.BeanTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.file.search.PathFinder;

/**
 * The unit test class for the class {@link XmlToYamlExtensions}
 */
public class XmlToYamlExtensionsTest
{

	File xmlDir;
	File xmlFile;

	/**
	 * Test method for {@link XmlToYamlExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(XmlToYamlExtensions.class);
	}

	@BeforeMethod
	protected void setUp()
	{
		xmlDir = new File(PathFinder.getSrcTestResourcesDir(), "xml");
		xmlFile = new File(xmlDir, "new-employee.xml");
	}

	/**
	 * Test method for {@link XmlToYamlExtensions#toYaml(String)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToYaml() throws IOException
	{
		String expected;
		String actual;
		String xmlString;

		xmlString = ReadFileExtensions.fromFile(xmlFile);

		actual = XmlToYamlExtensions.toYaml(xmlString);
		expected = // language=yaml
			"---\n" + "Employee:\n" + "  person:\n" + "    gender: \"FEMALE\"\n"
				+ "    about: \"\"\n" + "    name: \"Anna\"\n" + "    nickname: \"\"\n"
				+ "    married: \"\"\n" + "  id: 23\n" + "  subOrdinates: \"\"\n";
		assertEquals(expected, actual);

		xmlString = "<person><gender>FEMALE</gender><name>Anna</name><nickname>beast</nickname><about>Ha ha ha...</about><married>true</married></person><id>23</id>";

		actual = XmlToYamlExtensions.toYaml(xmlString);
		expected = // language=yaml
			"---\n" + "person:\n" + "  gender: \"FEMALE\"\n" + "  name: \"Anna\"\n"
				+ "  nickname: \"beast\"\n" + "  about: \"Ha ha ha...\"\n" + "  married: true\n"
				+ "id: 23\n";
		assertEquals(expected, actual);

		actual = YamlToXmlExtensions.toXml(actual);
		expected = "<person><gender>FEMALE</gender><name>Anna</name><nickname>beast</nickname><about>Ha ha ha...</about><married>true</married></person><id>23</id>";
		assertEquals(expected, actual);

		xmlString = "<array><person><gender>FEMALE</gender><name>Anna</name><nickname>beast</nickname><about>Ha ha ha...</about><married>true</married></person><id>23</id></array><array><person><gender>MALE</gender><name>Andreas</name><nickname>cute</nickname><about>fine person</about><married>false</married></person><id>24</id></array><array><person><gender>FEMALE</gender><name>Tatjana</name><nickname>beautiful</nickname><about>Im hot</about><married>false</married></person><id>25</id></array>";
		actual = XmlToYamlExtensions.toYaml(xmlString);

		expected = // language=yaml
			"---\n" + "array:\n" + "- person:\n" + "    gender: \"FEMALE\"\n"
				+ "    name: \"Anna\"\n" + "    nickname: \"beast\"\n"
				+ "    about: \"Ha ha ha...\"\n" + "    married: true\n" + "  id: 23\n"
				+ "- person:\n" + "    gender: \"MALE\"\n" + "    name: \"Andreas\"\n"
				+ "    nickname: \"cute\"\n" + "    about: \"fine person\"\n"
				+ "    married: false\n" + "  id: 24\n" + "- person:\n" + "    gender: \"FEMALE\"\n"
				+ "    name: \"Tatjana\"\n" + "    nickname: \"beautiful\"\n"
				+ "    about: \"Im hot\"\n" + "    married: false\n" + "  id: 25\n";
		assertEquals(expected, actual);

		actual = YamlToXmlExtensions.toXml(actual);
		expected = "<array><person><gender>FEMALE</gender><name>Anna</name><nickname>beast</nickname><about>Ha ha ha...</about><married>true</married></person><id>23</id></array><array><person><gender>MALE</gender><name>Andreas</name><nickname>cute</nickname><about>fine person</about><married>false</married></person><id>24</id></array><array><person><gender>FEMALE</gender><name>Tatjana</name><nickname>beautiful</nickname><about>Im hot</about><married>false</married></person><id>25</id></array>";
		assertEquals(expected, actual);

		xmlString = "<1>0</1><2>0</2><3>0</3><4>0</4><5>0</5>";
		actual = XmlToYamlExtensions.toYaml(xmlString);
		expected = // language=yaml
			"---\n" + "\"1\": 0\n" + "\"2\": 0\n" + "\"3\": 0\n" + "\"4\": 0\n" + "\"5\": 0\n";
		assertEquals(expected, actual);

		actual = YamlToXmlExtensions.toXml(actual);
		expected = "<1>0</1><2>0</2><3>0</3><4>0</4><5>0</5>";
		assertEquals(expected, actual);


		xmlString = "<array><person><gender>FEMALE</gender><name>Anna</name><nickname>beast</nickname><about>Ha ha ha...</about><married>true</married></person><id>23</id></array><array><person><gender>MALE</gender><name>Andreas</name><nickname>cute</nickname><about>fine person</about><married>false</married></person><id>24</id></array><array><person><gender>FEMALE</gender><name>Tatjana</name><nickname>beautiful</nickname><about>Im hot</about><married>false</married></person><id>25</id></array>";
		actual = XmlToYamlExtensions.toYaml(xmlString);
		expected = // language=yaml
			"---\n" + "array:\n" + "- person:\n" + "    gender: \"FEMALE\"\n"
				+ "    name: \"Anna\"\n" + "    nickname: \"beast\"\n"
				+ "    about: \"Ha ha ha...\"\n" + "    married: true\n" + "  id: 23\n"
				+ "- person:\n" + "    gender: \"MALE\"\n" + "    name: \"Andreas\"\n"
				+ "    nickname: \"cute\"\n" + "    about: \"fine person\"\n"
				+ "    married: false\n" + "  id: 24\n" + "- person:\n" + "    gender: \"FEMALE\"\n"
				+ "    name: \"Tatjana\"\n" + "    nickname: \"beautiful\"\n"
				+ "    about: \"Im hot\"\n" + "    married: false\n" + "  id: 25\n";
		assertEquals(expected, actual);

		actual = YamlToXmlExtensions.toXml(actual);
		expected = "<array><person><gender>FEMALE</gender><name>Anna</name><nickname>beast</nickname><about>Ha ha ha...</about><married>true</married></person><id>23</id></array><array><person><gender>MALE</gender><name>Andreas</name><nickname>cute</nickname><about>fine person</about><married>false</married></person><id>24</id></array><array><person><gender>FEMALE</gender><name>Tatjana</name><nickname>beautiful</nickname><about>Im hot</about><married>false</married></person><id>25</id></array>";
		assertEquals(expected, actual);
	}
}
