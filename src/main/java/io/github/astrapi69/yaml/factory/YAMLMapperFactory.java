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
package io.github.astrapi69.yaml.factory;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

/**
 * The factory class {@link YAMLMapperFactory} for creating {@link YAMLMapper} objects
 */
public final class YAMLMapperFactory
{
	private YAMLMapperFactory()
	{
	}

	/**
	 * Factory method for create a new {@link YAMLMapper}
	 *
	 * @return the new {@link YAMLMapper}
	 */
	public static YAMLMapper newYAMLMapper()
	{
		return newYAMLMapper(true);
	}

	/** The constant mapper. */
	private final static YAMLMapper YAML_MAPPER = newYAMLMapper(true);

	/**
	 * Factory method for create a new {@link YAMLMapper}. If the given flag is true a new
	 * {@link YAMLMapper} will be created otherwise the default {@link YAMLMapper} will be taken.
	 *
	 * @param newMapper
	 *            flag that indicates if a new {@link YAMLMapper} should be created, if true a new
	 *            {@link YAMLMapper} will be created otherwise the default {@link YAMLMapper} from
	 *            this class will be returned.
	 * @return the new {@link YAMLMapper}
	 */
	public static YAMLMapper newYAMLMapper(final boolean newMapper)
	{
		if (newMapper)
		{
			return new YAMLMapper();
		}
		return YAML_MAPPER;
	}

	/**
	 * Factory method for create a new {@link YAMLMapper}
	 *
	 * @param yamlFactory
	 *            the {@link YAMLFactory} object
	 *
	 * @return the new {@link YAMLMapper}
	 */
	public static YAMLMapper newYAMLMapper(YAMLFactory yamlFactory)
	{
		return new YAMLMapper(yamlFactory);
	}

	/**
	 * Factory method for create a new {@link YAMLMapper}
	 *
	 * @param yamlMapper
	 *            the {@link YAMLMapper} object
	 *
	 * @return the new {@link YAMLMapper}
	 */
	public static YAMLMapper newYAMLMapper(YAMLMapper yamlMapper)
	{
		return new YAMLMapper(yamlMapper);
	}


}
