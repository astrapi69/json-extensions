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

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;

import io.github.astrapi69.json.factory.CustomJsonNodeFactory;
import io.github.astrapi69.json.factory.CustomParserFactory;
import io.github.astrapi69.json.factory.ObjectMapperFactory;

/**
 * The class {@link JsonLineNumberResolver} provides algorithms for resolve the line number in a
 * json File with a json path
 */
public final class JsonLineNumberResolver
{
	private JsonLineNumberResolver()
	{
	}

	/**
	 * Gets the line number from the json file of the given json path.
	 * 
	 * @param jsonFile
	 *            the json file
	 * @param jsonPath
	 *            the json path
	 * @return the line number or -1 if not found or if the given json path matches more than once
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static int getLineNumber(File jsonFile, String jsonPath) throws IOException
	{
		int lineNumber = -1;
		CustomParserFactory customParserFactory = new CustomParserFactory();
		ObjectMapper objectMapper = ObjectMapperFactory.newObjectMapper(customParserFactory);
		CustomJsonNodeFactory factory = newCustomJsonNodeFactory(objectMapper, customParserFactory);
		Configuration configuration = newLineNumberConfiguration(objectMapper);
		DocumentContext parsedDocument = JsonPath.parse(jsonFile, configuration);
		ArrayNode findings = parsedDocument.read(jsonPath);
		if (findings.size() == 1)
		{
			for (JsonNode finding : findings)
			{
				JsonLocation location = factory.getLocationForNode(finding);
				lineNumber = location.getLineNr();
			}
		}
		return lineNumber;
	}

	private static CustomJsonNodeFactory newCustomJsonNodeFactory(ObjectMapper objectMapper,
		CustomParserFactory customParserFactory)
	{
		CustomJsonNodeFactory factory = new CustomJsonNodeFactory(
			objectMapper.getDeserializationConfig().getNodeFactory(), customParserFactory);
		objectMapper.setConfig(objectMapper.getDeserializationConfig().with(factory));
		return factory;
	}

	private static Configuration newLineNumberConfiguration(ObjectMapper objectMapper)
	{
		return Configuration.builder()
			.mappingProvider(new JacksonMappingProvider(objectMapper))
			.jsonProvider(new JacksonJsonNodeJsonProvider(objectMapper))
			.options(Option.ALWAYS_RETURN_LIST).build();
	}

}
