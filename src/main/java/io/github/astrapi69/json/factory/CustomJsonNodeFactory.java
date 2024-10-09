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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BinaryNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.fasterxml.jackson.databind.util.RawValue;

/**
 * Custom implementation of {@link JsonNodeFactory} used to store references between nodes and
 * {@link JsonLocation}. This class delegates most operations to another {@link JsonNodeFactory} and
 * tracks the location of each node created. This helps to associate JSON nodes with their original
 * location in a parsed document, allowing retrieval of line numbers or positions.
 *
 * <p>
 * See: <a href=
 * "https://stackoverflow.com/questions/63585750/get-the-line-number-of-a-json-file-given-a-json-path-json-pointer-in-java">
 * Get the line number of a JSON file given a JSON path</a>
 * </p>
 */
public class CustomJsonNodeFactory extends JsonNodeFactory
{

	/**
	 * the serialVersionUID
	 */
	private static final long serialVersionUID = 8807395553661461181L;

	/**
	 * the delegation {@link JsonNodeFactory} object
	 */
	private final JsonNodeFactory delegate;

	/**
	 * the {@link CustomParserFactory} object
	 */
	private final CustomParserFactory parserFactory;

	/**
	 * Why isn't this a map? Well, when the nodes are created, they're all empty and a node's
	 * hashCode is based on its children. So if you use a map and put the node in, the node's
	 * hashCode is based on no children. When you look up your node later, it has children, so the
	 * hashCodes are different. Instead, you have to iterate through a list and find matches once
	 * the document has been completely parsed
	 */
	private final List<Entry<JsonNode, JsonLocation>> locationMapping;

	/**
	 * Constructor that initializes the factory with a delegate {@link JsonNodeFactory} and a
	 * {@link CustomParserFactory}.
	 *
	 * @param nodeFactory
	 *            the delegate factory for node creation
	 * @param parserFactory
	 *            the factory for retrieving the current parser location
	 */
	public CustomJsonNodeFactory(JsonNodeFactory nodeFactory, CustomParserFactory parserFactory)
	{
		delegate = nodeFactory;
		this.parserFactory = parserFactory;
		locationMapping = new ArrayList<>();
	}

	/**
	 * Returns the location of the given {@link JsonNode}, or null if the node's location wasn't
	 * found in the mapping.
	 *
	 * @param jsonNode
	 *            the node whose location is to be found
	 * @return the location of the node or null if not found
	 */
	public JsonLocation getLocationForNode(JsonNode jsonNode)
	{
		return this.locationMapping.stream().filter(e -> e.getKey().equals(jsonNode))
			.map(Entry::getValue).findAny().orElse(null);
	}

	/**
	 * Intercepts node creation, marks its location, and returns the node.
	 *
	 * @param <T>
	 *            the type of {@link JsonNode}
	 * @param node
	 *            the node to mark and return
	 * @return the same node with its location marked
	 */
	private <T extends JsonNode> T markNode(T node)
	{
		JsonLocation loc = parserFactory.getParser().currentLocation();
		locationMapping.add(new SimpleEntry<>(node, loc));
		return node;
	}

	/** {@inheritDoc} */
	@Override
	public BooleanNode booleanNode(boolean v)
	{
		return markNode(delegate.booleanNode(v));
	}

	/** {@inheritDoc} */
	@Override
	public NullNode nullNode()
	{
		return markNode(delegate.nullNode());
	}

	/** {@inheritDoc} */
	@Override
	public NumericNode numberNode(byte v)
	{
		return markNode(delegate.numberNode(v));
	}

	/** {@inheritDoc} */
	@Override
	public ValueNode numberNode(Byte value)
	{
		return markNode(delegate.numberNode(value));
	}

	/** {@inheritDoc} */
	@Override
	public NumericNode numberNode(short v)
	{
		return markNode(delegate.numberNode(v));
	}

	/** {@inheritDoc} */
	@Override
	public ValueNode numberNode(Short value)
	{
		return markNode(delegate.numberNode(value));
	}

	/** {@inheritDoc} */
	@Override
	public NumericNode numberNode(int v)
	{
		return markNode(delegate.numberNode(v));
	}

	/** {@inheritDoc} */
	@Override
	public ValueNode numberNode(Integer value)
	{
		return markNode(delegate.numberNode(value));
	}

	/** {@inheritDoc} */
	@Override
	public NumericNode numberNode(long v)
	{
		return markNode(delegate.numberNode(v));
	}

	/** {@inheritDoc} */
	@Override
	public ValueNode numberNode(Long value)
	{
		return markNode(delegate.numberNode(value));
	}

	/** {@inheritDoc} */
	@Override
	public ValueNode numberNode(BigInteger v)
	{
		return markNode(delegate.numberNode(v));
	}

	/** {@inheritDoc} */
	@Override
	public NumericNode numberNode(float v)
	{
		return markNode(delegate.numberNode(v));
	}

	/** {@inheritDoc} */
	@Override
	public ValueNode numberNode(Float value)
	{
		return markNode(delegate.numberNode(value));
	}

	/** {@inheritDoc} */
	@Override
	public NumericNode numberNode(double v)
	{
		return markNode(delegate.numberNode(v));
	}

	/** {@inheritDoc} */
	@Override
	public ValueNode numberNode(Double value)
	{
		return markNode(delegate.numberNode(value));
	}

	/** {@inheritDoc} */
	@Override
	public ValueNode numberNode(BigDecimal v)
	{
		return markNode(delegate.numberNode(v));
	}

	/** {@inheritDoc} */
	@Override
	public TextNode textNode(String text)
	{
		return markNode(delegate.textNode(text));
	}

	/** {@inheritDoc} */
	@Override
	public BinaryNode binaryNode(byte[] data)
	{
		return markNode(delegate.binaryNode(data));
	}

	/** {@inheritDoc} */
	@Override
	public BinaryNode binaryNode(byte[] data, int offset, int length)
	{
		return markNode(delegate.binaryNode(data, offset, length));
	}

	/** {@inheritDoc} */
	@Override
	public ValueNode pojoNode(Object pojo)
	{
		return markNode(delegate.pojoNode(pojo));
	}

	/** {@inheritDoc} */
	@Override
	public ValueNode rawValueNode(RawValue value)
	{
		return markNode(delegate.rawValueNode(value));
	}

	/** {@inheritDoc} */
	@Override
	public ArrayNode arrayNode()
	{
		return markNode(delegate.arrayNode());
	}

	/** {@inheritDoc} */
	@Override
	public ArrayNode arrayNode(int capacity)
	{
		return markNode(delegate.arrayNode(capacity));
	}

	/** {@inheritDoc} */
	@Override
	public ObjectNode objectNode()
	{
		return markNode(delegate.objectNode());
	}

}
