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

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import io.github.astrapi69.yaml.factory.YAMLMapperFactory;

/**
 * The class {@link ObjectToYamlFileExtensions} converts java objects to yaml file objects.
 */
public final class ObjectToYamlFileExtensions
{

	private ObjectToYamlFileExtensions()
	{
	}

	/**
	 * Creates a yaml {@link String} from the given argument object
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
	public static <T> void toYamlFile(final T object, final File resultFile) throws IOException
	{
		Objects.requireNonNull(object);
		Objects.requireNonNull(resultFile);
		toYamlFile(object, resultFile, false);
	}

	/**
	 * Creates a yaml {@link String} from the given Object
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
	public static <T> void toYamlFile(final T object, final File resultFile,
		final boolean newMapper) throws IOException
	{
		Objects.requireNonNull(object);
		Objects.requireNonNull(resultFile);
		final YAMLMapper mapper = YAMLMapperFactory.newYAMLMapper(newMapper);
		toYamlFile(object, resultFile, mapper);
	}

	/**
	 * Creates a yaml {@link File} from the given Object and the given object mapper
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
	public static <T> void toYamlFile(final T object, final File resultFile,
		final YAMLMapper mapper) throws IOException
	{
		Objects.requireNonNull(object);
		Objects.requireNonNull(resultFile);
		Objects.requireNonNull(mapper);
		mapper.writeValue(resultFile, object);
	}

}
