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

import static org.testng.AssertJUnit.assertEquals;

import java.io.File;
import java.io.IOException;

import org.json.JSONException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.file.search.PathFinder;

/**
 * The unit test class for the class {@link JsonToXmlExtensions}
 */
public class JsonToXmlExtensionsTest
{


	File jsonDir;
	File jsonFile;
	File jsonCollectionFile;

	File jsonMapFile;
	File jsonListFile;

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
	 * Test method for {@link JsonToXmlExtensions#toXml(String)}
	 *
	 * @throws JSONException
	 *             if there is a syntax error in the source string or a duplicated key
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToXmlString() throws JSONException, IOException
	{
		String expected;
		String actual;
		String jsonString;

		jsonString = "{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"}";
		actual = JsonToXmlExtensions.toXml(jsonString);
		expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><person><gender>FEMALE</gender><name>Anna</name><nickname>beast</nickname><about>Ha ha ha...</about><married>true</married></person><id>23</id>";
		assertEquals(actual, expected);

		jsonString = ReadFileExtensions.fromFile(jsonFile);
		actual = JsonToXmlExtensions.toXml(jsonString);
		assertEquals(actual, expected);

		jsonString = ReadFileExtensions.fromFile(jsonListFile);
		actual = JsonToXmlExtensions.toXml(jsonString);
		expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><array><person><gender>FEMALE</gender><name>Anna</name><nickname>beast</nickname><about>Ha ha ha...</about><married>true</married></person><id>23</id></array><array><person><gender>MALE</gender><name>Andreas</name><nickname>cute</nickname><about>fine person</about><married>false</married></person><id>24</id></array><array><person><gender>FEMALE</gender><name>Tatjana</name><nickname>beautiful</nickname><about>Im hot</about><married>false</married></person><id>25</id></array>";
		assertEquals(actual, expected);

		jsonString = ReadFileExtensions.fromFile(jsonMapFile);
		actual = JsonToXmlExtensions.toXml(jsonString);
		expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><1>0</1><2>0</2><3>0</3><4>0</4><5>0</5>";
		assertEquals(actual, expected);

		jsonString = ReadFileExtensions.fromFile(jsonCollectionFile);
		actual = JsonToXmlExtensions.toXml(jsonString);
		expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><array><person><gender>FEMALE</gender><name>Anna</name><nickname>beast</nickname><about>Ha ha ha...</about><married>true</married></person><id>23</id></array><array><person><gender>MALE</gender><name>Andreas</name><nickname>cute</nickname><about>fine person</about><married>false</married></person><id>24</id></array><array><person><gender>FEMALE</gender><name>Tatjana</name><nickname>beautiful</nickname><about>Im hot</about><married>false</married></person><id>25</id></array>";
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link JsonToXmlExtensions#toXml(String, int)}
	 *
	 * @throws JSONException
	 *             if there is a syntax error in the source string or a duplicated key
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToXmlStringWithIndent() throws JSONException, IOException
	{
		String expected;
		String actual;
		String jsonString;

		jsonString = "{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"}";
		actual = JsonToXmlExtensions.toXml(jsonString, 4);
		expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + System.lineSeparator()
			+ "<person>\n" + "    <gender>FEMALE</gender>\n" + "    <name>Anna</name>\n"
			+ "    <nickname>beast</nickname>\n" + "    <about>Ha ha ha...</about>\n"
			+ "    <married>true</married>\n" + "</person>\n" + "<id>23</id>\n";
		assertEquals(actual, expected);

		jsonString = ReadFileExtensions.fromFile(jsonFile);
		actual = JsonToXmlExtensions.toXml(jsonString, 4);
		assertEquals(actual, expected);

		jsonString = ReadFileExtensions.fromFile(jsonListFile);
		actual = JsonToXmlExtensions.toXml(jsonString, 4);
		expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + System.lineSeparator()
			+ "<array>\n" + "    <person>\n" + "        <gender>FEMALE</gender>\n"
			+ "        <name>Anna</name>\n" + "        <nickname>beast</nickname>\n"
			+ "        <about>Ha ha ha...</about>\n" + "        <married>true</married>\n"
			+ "    </person>\n" + "    <id>23</id>\n" + "</array>\n" + "<array>\n"
			+ "    <person>\n" + "        <gender>MALE</gender>\n"
			+ "        <name>Andreas</name>\n" + "        <nickname>cute</nickname>\n"
			+ "        <about>fine person</about>\n" + "        <married>false</married>\n"
			+ "    </person>\n" + "    <id>24</id>\n" + "</array>\n" + "<array>\n"
			+ "    <person>\n" + "        <gender>FEMALE</gender>\n"
			+ "        <name>Tatjana</name>\n" + "        <nickname>beautiful</nickname>\n"
			+ "        <about>Im hot</about>\n" + "        <married>false</married>\n"
			+ "    </person>\n" + "    <id>25</id>\n" + "</array>\n";
		assertEquals(actual, expected);

		jsonString = ReadFileExtensions.fromFile(jsonMapFile);
		actual = JsonToXmlExtensions.toXml(jsonString, 4);
		expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + System.lineSeparator()
			+ "<1>0</1>\n" + "<2>0</2>\n" + "<3>0</3>\n" + "<4>0</4>\n" + "<5>0</5>\n";
		assertEquals(actual, expected);

		jsonString = ReadFileExtensions.fromFile(jsonCollectionFile);
		actual = JsonToXmlExtensions.toXml(jsonString, 4);
		expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + System.lineSeparator()
			+ "<array>\n" + "    <person>\n" + "        <gender>FEMALE</gender>\n"
			+ "        <name>Anna</name>\n" + "        <nickname>beast</nickname>\n"
			+ "        <about>Ha ha ha...</about>\n" + "        <married>true</married>\n"
			+ "    </person>\n" + "    <id>23</id>\n" + "</array>\n" + "<array>\n"
			+ "    <person>\n" + "        <gender>MALE</gender>\n"
			+ "        <name>Andreas</name>\n" + "        <nickname>cute</nickname>\n"
			+ "        <about>fine person</about>\n" + "        <married>false</married>\n"
			+ "    </person>\n" + "    <id>24</id>\n" + "</array>\n" + "<array>\n"
			+ "    <person>\n" + "        <gender>FEMALE</gender>\n"
			+ "        <name>Tatjana</name>\n" + "        <nickname>beautiful</nickname>\n"
			+ "        <about>Im hot</about>\n" + "        <married>false</married>\n"
			+ "    </person>\n" + "    <id>25</id>\n" + "</array>\n";
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
