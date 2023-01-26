/**
 * The MIT License
 * <p>
 * Copyright (C) 2015 Asterios Raptis
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * <p>
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
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.astrapi69.json.factory.ObjectMapperFactory;

/**
 * The class {@link JsonFileToObjectExtensions} converts json strings to java object and java
 * collections.
 */
public final class JsonFileToObjectExtensions
{
	private JsonFileToObjectExtensions()
	{
	}

	/**
	 * Transforms the given json file into a java map object
	 *
	 * @param <K>
	 *            the generic type of keys
	 * @param <V>
	 *            the generic type of values
	 * @param jsonFile
	 *            the json file
	 * @param typeReference
	 *            the type reference
	 * @return the t
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static <K, V> Map<K, V> toMapObject(final File jsonFile,
		final TypeReference<Map<K, V>> typeReference) throws IOException
	{
		Objects.requireNonNull(jsonFile);
		Objects.requireNonNull(typeReference);
		return toMapObject(jsonFile, typeReference, ObjectMapperFactory.newObjectMapper(true));
	}

	/**
	 * Transforms the given json file into a java map object
	 *
	 * @param <K>
	 *            the generic type of keys
	 * @param <V>
	 *            the generic type of values
	 * @param jsonFile
	 *            the json file
	 * @param typeReference
	 *            the type reference
	 * @param mapper
	 *            the object mapper
	 * @return the t
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static <K, V> Map<K, V> toMapObject(final File jsonFile,
		final TypeReference<Map<K, V>> typeReference, final ObjectMapper mapper) throws IOException
	{
		Objects.requireNonNull(jsonFile);
		Objects.requireNonNull(typeReference);
		Objects.requireNonNull(mapper);
		return mapper.readValue(jsonFile, typeReference);
	}

	/**
	 * Transforms the given json file into a java object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param jsonFile
	 *            the json file
	 * @param clazz
	 *            the clazz
	 * @return the t
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static <T> T toObject(final File jsonFile, final Class<T> clazz) throws IOException
	{
		return toObject(jsonFile, clazz, ObjectMapperFactory.newObjectMapper());
	}

	/**
	 * Transforms the given json file into a java object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param jsonFile
	 *            the json file
	 * @param clazz
	 *            the clazz
	 * @param mapper
	 *            the mapper
	 * @return the t
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static <T> T toObject(final File jsonFile, final Class<T> clazz,
		final ObjectMapper mapper) throws IOException
	{
		Objects.requireNonNull(jsonFile);
		Objects.requireNonNull(clazz);
		Objects.requireNonNull(mapper);
		return mapper.readValue(jsonFile, clazz);
	}

	/**
	 * Transforms the given json file into a java object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param jsonFile
	 *            the json file
	 * @param typeReference
	 *            the type reference
	 * @param mapper
	 *            the mapper
	 * @return the t
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static <T> T toObject(final File jsonFile, final TypeReference<T> typeReference,
		final ObjectMapper mapper) throws IOException
	{
		Objects.requireNonNull(jsonFile);
		Objects.requireNonNull(typeReference);
		Objects.requireNonNull(mapper);
		return mapper.readValue(jsonFile, typeReference);
	}

	/**
	 * Transforms the given json file into a java object {@link Collection}
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param jsonFile
	 *            the json file
	 * @param collectionClass
	 *            the collection class
	 * @param elementClass
	 *            the element class
	 * @return the list with the java objects.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static <T> Collection<T> toObjectCollection(final File jsonFile,
		@SuppressWarnings("rawtypes") final Class<? extends Collection> collectionClass,
		final Class<T> elementClass) throws IOException
	{
		Objects.requireNonNull(jsonFile);
		Objects.requireNonNull(collectionClass);
		Objects.requireNonNull(elementClass);
		final ObjectMapper mapper = ObjectMapperFactory.newObjectMapper(true);
		return mapper.readValue(jsonFile,
			mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass));
	}

	/**
	 * Transforms the given json string into a java object {@link List}
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param jsonFile
	 *            the json file
	 * @param elementClass
	 *            the element class of the generic type
	 * @return the list with the java objects
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> List<T> toObjectList(final File jsonFile, final Class<T> elementClass)
		throws IOException
	{
		Objects.requireNonNull(jsonFile);
		Objects.requireNonNull(elementClass);
		return (List<T>)toObjectCollection(jsonFile, List.class, elementClass);
	}

}
