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
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.astrapi69.json.factory.ObjectMapperFactory;

/**
 * The class {@link ObjectToJsonFileExtensions} converts java objects to json file objects.
 */
public final class ObjectToJsonFileExtensions
{

	private ObjectToJsonFileExtensions()
	{
	}

	/**
	 * Creates a json {@link String} from the given argument object
	 *
	 * @param <T>
	 *            the generic type of the given argument object
	 * @param object
	 *            the object.
	 * @param resultFile
	 *            the result file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> void toJsonFile(final T object, final File resultFile) throws IOException
	{
		Objects.requireNonNull(object);
		Objects.requireNonNull(resultFile);
		toJsonFile(object, resultFile, false);
	}

	/**
	 * Creates a json {@link String} from the given Object
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @param resultFile
	 *            the result file
	 * @param newMapper
	 *            flag that indicates if a new ObjectMapper should be created. if true a new
	 *            ObjectMapper will be created otherwise the ObjectMapper from this class will be
	 *            returned.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> void toJsonFile(final T object, final File resultFile,
		final boolean newMapper) throws IOException
	{
		Objects.requireNonNull(object);
		Objects.requireNonNull(resultFile);
		final ObjectMapper mapper = ObjectMapperFactory.newObjectMapper(newMapper);
		toJsonFile(object, resultFile, mapper);
	}

	/**
	 * Creates a json {@link File} from the given Object and the given object mapper
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @param resultFile
	 *            the result file
	 * @param mapper
	 *            the object mapper
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> void toJsonFile(final T object, final File resultFile,
		final ObjectMapper mapper) throws IOException
	{
		Objects.requireNonNull(object);
		Objects.requireNonNull(resultFile);
		Objects.requireNonNull(mapper);
		mapper.writeValue(resultFile, object);
	}

}
