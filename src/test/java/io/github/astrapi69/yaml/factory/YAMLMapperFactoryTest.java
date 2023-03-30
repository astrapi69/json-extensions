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

import static org.testng.AssertJUnit.assertNotNull;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import io.github.astrapi69.json.factory.ObjectMapperFactory;
import io.github.astrapi69.yaml.factory.YAMLMapperFactory;


/**
 * The unit test class for the class {@link ObjectMapperFactory}
 */
public class YAMLMapperFactoryTest
{

	/**
	 * Test method for {@link YAMLMapperFactory#newYAMLMapper()}
	 */
	@Test
	public void testNewYAMLMapper()
	{
		YAMLMapper actual;

		actual = YAMLMapperFactory.newYAMLMapper();
		assertNotNull(actual);
	}

	/**
	 * Test method for {@link YAMLMapperFactory#newYAMLMapper(YAMLFactory)}
	 */
	@Test
	public void testNewYAMLMapperWithYAMLFactory()
	{
		YAMLMapper actual;

		actual = YAMLMapperFactory.newYAMLMapper(new YAMLFactory());
		assertNotNull(actual);
	}


	/**
	 * Test method for {@link YAMLMapperFactory#newYAMLMapper(YAMLMapper)}
	 */
	@Test
	public void testNewYAMLMapperWithYAMLMapper()
	{
		YAMLMapper actual;

		actual = YAMLMapperFactory.newYAMLMapper(new YAMLMapper());
		assertNotNull(actual);
	}

	/**
	 * Test method for {@link YAMLMapperFactory}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(YAMLMapperFactory.class);
	}

}
