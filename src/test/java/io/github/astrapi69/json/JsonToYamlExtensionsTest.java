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

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.file.search.PathFinder;

public class JsonToYamlExtensionsTest
{

	File jsonFile;
	File jsonCollectionFile;

	File jsonMapFile;
	File jsonListFile;

	@BeforeMethod
	protected void setUp()
	{
		File jsonDir;
		jsonDir = new File(PathFinder.getSrcTestResourcesDir(), "json");
		jsonFile = new File(jsonDir, "person.json");
		jsonListFile = new File(jsonDir, "employees.json");
		jsonMapFile = new File(jsonDir, "map.json");
		jsonCollectionFile = new File(jsonDir, "collection.json");
	}

	@Test
	public void testToYaml() throws IOException
	{

		String expected;
		String actual;

		actual = JsonToYamlExtensions.toYaml(ReadFileExtensions.fromFile(jsonFile));
		expected = "---\n" + "id: \"23\"\n" + "person:\n" + "  name: \"Anna\"\n"
			+ "  nickname: \"beast\"\n" + "  gender: \"FEMALE\"\n" + "  about: \"Ha ha ha...\"\n"
			+ "  married: true\n" + "subOrdinates: []\n";
		assertEquals(expected, actual);

		actual = JsonToYamlExtensions.toYaml(ReadFileExtensions.fromFile(jsonListFile));
		expected = "---\n" + "- id: \"23\"\n" + "  person:\n" + "    name: \"Anna\"\n"
			+ "    nickname: \"beast\"\n" + "    gender: \"FEMALE\"\n"
			+ "    about: \"Ha ha ha...\"\n" + "    married: true\n" + "  subOrdinates: []\n"
			+ "- id: \"24\"\n" + "  person:\n" + "    name: \"Andreas\"\n"
			+ "    nickname: \"cute\"\n" + "    gender: \"MALE\"\n" + "    about: \"fine person\"\n"
			+ "    married: false\n" + "  subOrdinates: []\n" + "- id: \"25\"\n" + "  person:\n"
			+ "    name: \"Tatjana\"\n" + "    nickname: \"beautiful\"\n"
			+ "    gender: \"FEMALE\"\n" + "    about: \"Im hot\"\n" + "    married: false\n"
			+ "  subOrdinates: []\n";
		assertEquals(expected, actual);

		actual = JsonToYamlExtensions.toYaml(ReadFileExtensions.fromFile(jsonMapFile));
		expected = "---\n" + "\"1\": 0\n" + "\"2\": 0\n" + "\"3\": 0\n" + "\"4\": 0\n"
			+ "\"5\": 0\n";
		assertEquals(expected, actual);

		actual = JsonToYamlExtensions.toYaml(ReadFileExtensions.fromFile(jsonCollectionFile));
		expected = "---\n" + "- person:\n" + "    name: \"Anna\"\n" + "    nickname: \"beast\"\n"
			+ "    gender: \"FEMALE\"\n" + "    about: \"Ha ha ha...\"\n" + "    married: true\n"
			+ "  id: \"23\"\n" + "- person:\n" + "    name: \"Andreas\"\n"
			+ "    nickname: \"cute\"\n" + "    gender: \"MALE\"\n" + "    about: \"fine person\"\n"
			+ "    married: false\n" + "  id: \"24\"\n" + "- person:\n" + "    name: \"Tatjana\"\n"
			+ "    nickname: \"beautiful\"\n" + "    gender: \"FEMALE\"\n"
			+ "    about: \"Im hot\"\n" + "    married: false\n" + "  id: \"25\"\n";
		assertEquals(expected, actual);

	}
}
