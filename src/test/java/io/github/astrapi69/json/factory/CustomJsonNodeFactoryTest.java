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
package io.github.astrapi69.json.factory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

/**
 * The unit test class for the class {@link CustomJsonNodeFactory}. A document is parsed through an
 * {@link ObjectMapper} whose parser comes from a {@link CustomParserFactory} and whose nodes come
 * from the {@link CustomJsonNodeFactory}, as the class is meant to be used; each node then has to
 * be found on the line of the document it was parsed from.
 */
public class CustomJsonNodeFactoryTest
{

	/**
	 * The document every test parses, each node on a line of its own
	 */
	private static final String DOCUMENT = """
		{
		  "name": "Alice",
		  "active": true,
		  "tags": [
		    "first"
		  ]
		}
		""";

	/**
	 * The mapper that parses through {@link #nodeFactory}
	 */
	private ObjectMapper objectMapper;

	/**
	 * The factory under test, fresh for every test
	 */
	private CustomJsonNodeFactory nodeFactory;

	/**
	 * Builds the mapper as the class is meant to be used: its parser from a
	 * {@link CustomParserFactory}, its nodes from a {@link CustomJsonNodeFactory} that knows that
	 * parser factory
	 */
	@BeforeMethod
	protected void setUp()
	{
		CustomParserFactory parserFactory = new CustomParserFactory();
		objectMapper = new ObjectMapper(parserFactory);
		nodeFactory = new CustomJsonNodeFactory(objectMapper.getNodeFactory(), parserFactory);
		objectMapper.setNodeFactory(nodeFactory);
	}

	/**
	 * Reads a tree with the given {@link ObjectMapper}
	 */
	@FunctionalInterface
	interface TreeReader
	{
		JsonNode read(ObjectMapper objectMapper) throws IOException;
	}

	/**
	 * The three inputs {@link CustomParserFactory} keeps the parser of
	 *
	 * @return the name of the input and the reader that parses {@link #DOCUMENT} from it
	 */
	@DataProvider
	public static Object[][] inputs()
	{
		return new Object[][] {
				{ "a String", (TreeReader)objectMapper -> objectMapper.readTree(DOCUMENT) },
				{ "a Reader",
						(TreeReader)objectMapper -> objectMapper
							.readTree(new StringReader(DOCUMENT)) },
				{ "a File", (TreeReader)objectMapper -> {
					Path file = Files.createTempFile("custom-json-node-factory", ".json");
					try
					{
						Files.writeString(file, DOCUMENT);
						return objectMapper.readTree(file.toFile());
					}
					finally
					{
						Files.delete(file);
					}
				} } };
	}

	/**
	 * Test method for {@link CustomJsonNodeFactory#getLocationForNode(JsonNode)}: every node of the
	 * parsed document has the location of the line it stands on
	 *
	 * @param input
	 *            the name of the input the document is parsed from
	 * @param treeReader
	 *            parses the document from that input
	 * @throws IOException
	 *             if the document cannot be read
	 */
	@Test(dataProvider = "inputs")
	public void testGetLocationForNodeGivesTheLineOfEachParsedNode(String input,
		TreeReader treeReader) throws IOException
	{
		JsonNode root = treeReader.read(objectMapper);

		assertEquals(lineOf(root), 1, input);
		assertEquals(lineOf(root.get("name")), 2, input);
		assertEquals(lineOf(root.get("active")), 3, input);
		assertEquals(lineOf(root.get("tags")), 4, input);
		assertEquals(lineOf(root.get("tags").get(0)), 5, input);
	}

	/**
	 * Test method for {@link CustomJsonNodeFactory#getLocationForNode(JsonNode)}: a node that was
	 * not parsed through the factory has no location
	 *
	 * @throws IOException
	 *             if the document cannot be read
	 */
	@Test
	public void testGetLocationForNodeGivesNullForANodeNotParsed() throws IOException
	{
		assertNull(nodeFactory.getLocationForNode(JsonNodeFactory.instance.textNode("Alice")));

		objectMapper.readTree(DOCUMENT);

		assertNull(nodeFactory.getLocationForNode(JsonNodeFactory.instance.textNode("Bob")));
	}

	private int lineOf(JsonNode node)
	{
		JsonLocation location = nodeFactory.getLocationForNode(node);
		assertNotNull(location, "no location for " + node);
		return location.getLineNr();
	}

}
