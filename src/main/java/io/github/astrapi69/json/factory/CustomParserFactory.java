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

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;

/**
 * The factory class {@link CustomParserFactory} for creating {@link JsonParser} object with a
 * reference on the parser. <br>
 * Note: this class is not thread-save
 */
public class CustomParserFactory extends JsonFactory
{

	/**
	 * the {@link JsonParser} object
	 */
	private JsonParser parser;

	/**
	 * Gets the parser of this factory
	 *
	 * @return the parser
	 */
	public JsonParser getParser()
	{
		return this.parser;
	}

	/**
	 * {@inheritDoc}
	 **/
	@Override
	public JsonParser createParser(Reader r) throws IOException
	{
		parser = super.createParser(r);
		return parser;
	}

	/**
	 * {@inheritDoc}
	 **/
	@Override
	public JsonParser createParser(File f) throws IOException
	{
		parser = super.createParser(f);
		return parser;
	}

	/**
	 * {@inheritDoc}
	 **/
	@Override
	public JsonParser createParser(String content) throws IOException
	{
		parser = super.createParser(content);
		return parser;
	}
}
