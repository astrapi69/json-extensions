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
import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.astrapi69.file.search.PathFinder;

public class JsonLineNumberResolverTest
{

	File jsonDir;
	File jsonFile;
	File jsonListFile;

	@BeforeMethod
	protected void setUp()
	{
		jsonDir = new File(PathFinder.getSrcTestResourcesDir(), "json");
		jsonFile = new File(jsonDir, "person.json");
		jsonListFile = new File(jsonDir, "employees.json");
	}

	@Test
	public void testGetLineNumber() throws IOException
	{
		int actual;
		int expected;
		String path;

		// new scenario
		path = "$.person.name";
		actual = JsonLineNumberResolver.getLineNumber(jsonFile, path);
		expected = 3;
		assertEquals(actual, expected);

		// new scenario
		path = "$[0].person.name";
		actual = JsonLineNumberResolver.getLineNumber(jsonListFile, path);
		expected = 4;
		assertEquals(actual, expected);

		// new scenario
		path = "$[1].person.name";
		actual = JsonLineNumberResolver.getLineNumber(jsonListFile, path);
		expected = 14;
		assertEquals(actual, expected);

		// new scenario
		path = "$[2].person.name";
		actual = JsonLineNumberResolver.getLineNumber(jsonListFile, path);
		expected = 24;
		assertEquals(actual, expected);
	}
}