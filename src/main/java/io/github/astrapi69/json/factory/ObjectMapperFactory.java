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

import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.DatatypeFeature;

/**
 * The factory class {@link ObjectMapperFactory} for creating {@link ObjectMapper} objects
 */
public final class ObjectMapperFactory
{
	private ObjectMapperFactory()
	{
	}

	/**
	 * Factory method for create a new {@link ObjectMapper}
	 *
	 * @return the new {@link ObjectMapper}
	 */
	public static ObjectMapper newObjectMapper()
	{
		return newObjectMapper(false);
	}

	/** The constant mapper. */
	private final static ObjectMapper OBJECT_MAPPER = newObjectMapper(true);

	/**
	 * Factory method for create a new {@link ObjectMapper}. If the given flag is true a new
	 * {@link ObjectMapper} will be created otherwise the default {@link ObjectMapper} will be
	 * taken.
	 *
	 * @param newMapper
	 *            flag that indicates if a new {@link ObjectMapper} should be created, if true a new
	 *            {@link ObjectMapper} will be created otherwise the default {@link ObjectMapper}
	 *            from this class will be returned.
	 * @return the new {@link ObjectMapper}
	 */
	public static ObjectMapper newObjectMapper(final boolean newMapper)
	{
		if (newMapper)
		{
			return new ObjectMapper();
		}
		return OBJECT_MAPPER;
	}

	/**
	 * Factory method for create a new {@link ObjectMapper} with the given map of json parser
	 * features
	 *
	 * @param jsonParserFeatures
	 *            the map with the json parser features for the new {@link ObjectMapper}
	 * @return the new {@link ObjectMapper}
	 */
	public static ObjectMapper newObjectMapper(Map<JsonParser.Feature, Boolean> jsonParserFeatures)
	{
		final ObjectMapper objectMapper = newObjectMapper(true);
		jsonParserFeatures.forEach(objectMapper::configure);
		return newObjectMapper(jsonParserFeatures, null, null, null, null);
	}

	/**
	 * Factory method for create a new {@link ObjectMapper} with the given features
	 *
	 * @param jsonParserFeatures
	 *            the map with the json parser features for the new {@link ObjectMapper}
	 * @param jsonGeneratorFeatures
	 *            the map with the json generator features for the new {@link ObjectMapper}
	 * @param deserializationFeatures
	 *            the map with the json deserialization features for the new {@link ObjectMapper}
	 * @param serializationFeatures
	 *            the map with the json serialization features for the new {@link ObjectMapper}
	 * @param datatypeFeatures
	 *            the map with the datatype features for the new {@link ObjectMapper}
	 * @return the new {@link ObjectMapper}
	 */
	public static ObjectMapper newObjectMapper(Map<JsonParser.Feature, Boolean> jsonParserFeatures,
		Map<JsonGenerator.Feature, Boolean> jsonGeneratorFeatures,
		Map<DeserializationFeature, Boolean> deserializationFeatures,
		Map<SerializationFeature, Boolean> serializationFeatures,
		Map<DatatypeFeature, Boolean> datatypeFeatures)
	{
		final ObjectMapper objectMapper = newObjectMapper(true);
		if (jsonParserFeatures != null && !jsonParserFeatures.isEmpty())
		{
			jsonParserFeatures.forEach(objectMapper::configure);
		}
		if (jsonGeneratorFeatures != null && !jsonGeneratorFeatures.isEmpty())
		{
			jsonGeneratorFeatures.forEach(objectMapper::configure);
		}
		if (deserializationFeatures != null && !deserializationFeatures.isEmpty())
		{
			deserializationFeatures.forEach(objectMapper::configure);
		}
		if (serializationFeatures != null && !serializationFeatures.isEmpty())
		{
			serializationFeatures.forEach(objectMapper::configure);
		}
		if (datatypeFeatures != null && !datatypeFeatures.isEmpty())
		{
			datatypeFeatures.forEach(objectMapper::configure);
		}
		return objectMapper;
	}

	/**
	 * Factory method for create a new {@link ObjectMapper} with the given {@link JsonFactory}
	 *
	 * @param jsonFactory
	 *            the {@link JsonFactory} object
	 * @return the new {@link ObjectMapper}
	 */
	public static ObjectMapper newObjectMapper(JsonFactory jsonFactory)
	{
		return new ObjectMapper(jsonFactory);
	}

}
