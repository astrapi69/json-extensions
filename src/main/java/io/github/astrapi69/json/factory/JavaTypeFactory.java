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

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The factory class {@link JavaTypeFactory} for creating {@link JavaType} objects for serializing
 * java collection beans to xml string and deserialize xml string to java collection beans
 */
public class JavaTypeFactory
{
	private JavaTypeFactory()
	{
	}

	/**
	 * Factory method for create a new {@link JavaType} from the given xml mapper, the collection
	 * class and the element class
	 *
	 * @param parametrized
	 *            the class type-erased type to parameterize
	 * @param parameterClasses
	 *            the array with the type parameters to apply
	 * @return the new {@link JavaType}
	 */
	public static JavaType newParametricType(final Class<?> parametrized,
		final Class<?>... parameterClasses)
	{
		return newParametricType(ObjectMapperFactory.newObjectMapper(), parametrized,
			parameterClasses);
	}

	/**
	 * Factory method for create a new {@link JavaType} from the given xml mapper, the collection
	 * class and the element class
	 *
	 * @param objectMapper
	 *            the xml mapper
	 * @param parametrized
	 *            the class type-erased type to parameterize
	 * @param parameterClasses
	 *            the array with the type parameters to apply
	 * @return the new {@link JavaType}
	 */
	public static JavaType newParametricType(final ObjectMapper objectMapper,
		final Class<?> parametrized, final Class<?>... parameterClasses)
	{
		return objectMapper.getTypeFactory().constructParametricType(parametrized,
			parameterClasses);
	}

	/**
	 * Factory method for create a new {@link JavaType} from the given xml mapper, the collection
	 * class and the element class
	 *
	 * @param parametrized
	 *            the class type-erased type to parameterize
	 * @param parameterTypes
	 *            the array with the {@link JavaType} to apply
	 * @return the new {@link JavaType}
	 */
	public static JavaType newParametricType(final Class<?> parametrized,
		final JavaType... parameterTypes)
	{
		return newParametricType(ObjectMapperFactory.newObjectMapper(), parametrized,
			parameterTypes);
	}

	/**
	 * Factory method for create a new {@link JavaType} from the given xml mapper, the collection
	 * class and the element class
	 *
	 * @param objectMapper
	 *            the object mapper
	 * @param parametrized
	 *            the class type-erased type to parameterize
	 * @param parameterTypes
	 *            the array with the {@link JavaType} to apply
	 * @return the new {@link JavaType}
	 */
	public static JavaType newParametricType(final ObjectMapper objectMapper,
		final Class<?> parametrized, final JavaType... parameterTypes)
	{
		return objectMapper.getTypeFactory().constructParametricType(parametrized, parameterTypes);
	}

	/**
	 * Factory method for create a new {@link JavaType} from the given collection class and the
	 * element class
	 *
	 * @param collectionClass
	 *            the collection class
	 * @param elementClass
	 *            the element class
	 * @return the new {@link JavaType}
	 */
	public static JavaType newCollectionType(final Class<? extends Collection> collectionClass,
		final Class<?> elementClass)
	{
		return newCollectionType(ObjectMapperFactory.newObjectMapper(), collectionClass,
			elementClass);
	}

	/**
	 * Factory method for create a new {@link JavaType} from the given xml mapper, the collection
	 * class and the element class
	 *
	 * @param objectMapper
	 *            the object mapper
	 * @param collectionClass
	 *            the collection class
	 * @param elementClass
	 *            the element class
	 * @return the new {@link JavaType}
	 */
	public static JavaType newCollectionType(final ObjectMapper objectMapper,
		final Class<? extends Collection> collectionClass, final Class<?> elementClass)
	{
		return objectMapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
	}

	/**
	 * Factory method for create a new {@link JavaType} from the given xml mapper, the collection
	 * class and the element class
	 *
	 * @param collectionClass
	 *            the collection class
	 * @param elementJavaType
	 *            the element type
	 * @return the new {@link JavaType}
	 */
	public static JavaType newCollectionType(final Class<? extends Collection> collectionClass,
		final JavaType elementJavaType)
	{
		return newCollectionType(ObjectMapperFactory.newObjectMapper(), collectionClass,
			elementJavaType);
	}

	/**
	 * Factory method for create a new {@link JavaType} from the given xml mapper, the collection
	 * class and the element class
	 *
	 * @param objectMapper
	 *            the object mapper
	 * @param collectionClass
	 *            the collection class
	 * @param elementJavaType
	 *            the element type
	 * @return the new {@link JavaType}
	 */
	public static JavaType newCollectionType(final ObjectMapper objectMapper,
		final Class<? extends Collection> collectionClass, final JavaType elementJavaType)
	{
		return objectMapper.getTypeFactory().constructCollectionType(collectionClass,
			elementJavaType);
	}

	/**
	 * Factory method for create a new {@link JavaType} from the given xml mapper, the map class and
	 * the key java type and value java type
	 *
	 * @param mapClass
	 *            the map class
	 * @param keyType
	 *            the key type
	 * @param valueType
	 *            the value type
	 * @return the new {@link JavaType}
	 */
	public static JavaType newMapType(final Class<? extends Map> mapClass, final JavaType keyType,
		final JavaType valueType)
	{
		return newMapType(ObjectMapperFactory.newObjectMapper(), mapClass, keyType, valueType);
	}

	/**
	 * Factory method for create a new {@link JavaType} from the given xml mapper, the map class and
	 * the key java type and value java type
	 *
	 * @param objectMapper
	 *            the object mapper
	 * @param mapClass
	 *            the map class
	 * @param keyType
	 *            the key type
	 * @param valueType
	 *            the value type
	 * @return the new {@link JavaType}
	 */
	public static JavaType newMapType(final ObjectMapper objectMapper,
		final Class<? extends Map> mapClass, final JavaType keyType, final JavaType valueType)
	{
		return objectMapper.getTypeFactory().constructMapType(mapClass, keyType, valueType);
	}

	/**
	 * Factory method for create a new {@link JavaType} from the given map class and the key java
	 * type and value java type
	 *
	 * @param mapClass
	 *            the map class
	 * @param keyClass
	 *            the key class
	 * @param valueClass
	 *            the value class
	 * @return the new {@link JavaType}
	 */
	public static JavaType newMapType(final Class<? extends Map> mapClass, final Class<?> keyClass,
		final Class<?> valueClass)
	{
		return newMapType(ObjectMapperFactory.newObjectMapper(), mapClass, keyClass, valueClass);
	}

	/**
	 * Factory method for create a new {@link JavaType} from the given xml mapper, the map class and
	 * the key java type and value java type
	 *
	 * @param objectMapper
	 *            the object mapper
	 * @param mapClass
	 *            the map class
	 * @param keyClass
	 *            the key class
	 * @param valueClass
	 *            the value class
	 * @return the new {@link JavaType}
	 */
	public static JavaType newMapType(final ObjectMapper objectMapper,
		final Class<? extends Map> mapClass, final Class<?> keyClass, final Class<?> valueClass)
	{
		return objectMapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
	}

	/**
	 * Factory method for create a new {@link JavaType} from the given xml mapper with the given
	 * type reference
	 *
	 * @param <T>
	 *            the generic type
	 * @param typeReference
	 *            the type reference
	 * @return the new {@link JavaType}
	 */
	public static <T> JavaType newJavaType(final TypeReference<T> typeReference)
	{
		return newJavaType(ObjectMapperFactory.newObjectMapper(), typeReference);
	}

	/**
	 * Factory method for create a new {@link JavaType} from the given xml mapper with the given
	 * type reference
	 *
	 * @param <T>
	 *            the generic type
	 * @param objectMapper
	 *            the object mapper
	 * @param typeReference
	 *            the type reference
	 * @return the new {@link JavaType}
	 */
	public static <T> JavaType newJavaType(final ObjectMapper objectMapper,
		final TypeReference<T> typeReference)
	{
		return objectMapper.getTypeFactory().constructType(typeReference);
	}

	/**
	 * Factory method for create a new {@link TypeReference} from the given type class
	 *
	 * @param <T>
	 *            the generic type
	 * @param typeClass
	 *            the type class
	 * @return the new {@link TypeReference}
	 */
	public static <T> TypeReference<T> newTypeReference(final Class<T> typeClass)
	{
		return new TypeReference<T>()
		{
			@Override
			public Type getType()
			{
				return typeClass;
			}
		};
	}


	/**
	 * Factory method for create a new {@link JavaType} from the given xml mapper with the given
	 * type
	 *
	 * @param <T>
	 *            the generic type
	 * @param typeClass
	 *            the type class
	 * @return the new {@link JavaType}
	 */
	public static <T> JavaType newJavaType(final Class<T> typeClass)
	{
		return newJavaType(ObjectMapperFactory.newObjectMapper(), newTypeReference(typeClass));
	}

}
