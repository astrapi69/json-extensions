package de.alpharogroup.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.collections.map.MapExtensions;
import de.alpharogroup.collections.map.MapFactory;
import de.alpharogroup.file.create.FileFactory;
import de.alpharogroup.file.delete.DeleteFileExtensions;
import de.alpharogroup.file.read.ReadFileExtensions;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.json.factory.ObjectMapperFactory;
import de.alpharogroup.test.objects.Employee;
import de.alpharogroup.test.objects.Person;
import de.alpharogroup.test.objects.enums.Gender;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.testng.Assert.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * The unit test class for the class {@link ObjectToJsonFileExtensions}
 */
public class ObjectToJsonFileExtensionsTest
{

	@Test public void testToJsonFile() throws IOException
	{
		String expected;
		String actual;
		File jsonFile;
		Employee employee;
		Map<Integer, Integer> numberCounterMap;

		employee = Employee.builder().person(Person.builder().gender(Gender.FEMALE)
			.name("Anna").married(true).about("Ha ha ha...").nickname("beast").build()).id("23")
			.build();
		// new scenario: try to convert a Employee object to json
		expected = "{\"id\":\"23\",\"person\":{\"about\":\"Ha ha ha...\",\"gender\":\"FEMALE\",\"married\":true,\"name\":\"Anna\",\"nickname\":\"beast\"}}";
		jsonFile = new File(PathFinder.getSrcTestResourcesDir(), "tmp.json");
		ObjectToJsonFileExtensions.toJsonFile(employee, jsonFile);
		actual = ReadFileExtensions.readFromFile(jsonFile);
		assertEquals(expected, actual);
		// new scenario: try to convert a integer map to json
		numberCounterMap = MapFactory
			.newCounterMap(ListFactory.newRangeList(1, 5));
		expected = "{\"1\":0,\"2\":0,\"3\":0,\"4\":0,\"5\":0}";
		ObjectToJsonFileExtensions.toJsonFile(numberCounterMap, jsonFile);
		actual = ReadFileExtensions.readFromFile(jsonFile);
		assertEquals(expected, actual);
		// clean up
		DeleteFileExtensions.delete(jsonFile);
	}

	@Test public void testTestToJsonFile() throws IOException
	{
		String expected;
		String actual;
		File jsonFile;
		Employee employee;
		Map<Integer, Integer> numberCounterMap;

		employee = Employee.builder().person(Person.builder().gender(Gender.FEMALE)
			.name("Anna").married(true).about("Ha ha ha...").nickname("beast").build()).id("23")
			.build();
		// new scenario: try to convert a Employee object to json
		expected = "{\"id\":\"23\",\"person\":{\"about\":\"Ha ha ha...\",\"gender\":\"FEMALE\",\"married\":true,\"name\":\"Anna\",\"nickname\":\"beast\"}}";
		jsonFile = new File(PathFinder.getSrcTestResourcesDir(), "tmp.json");
		ObjectToJsonFileExtensions.toJsonFile(employee, jsonFile, false);
		actual = ReadFileExtensions.readFromFile(jsonFile);
		assertEquals(expected, actual);
		// new scenario: try to convert a integer map to json
		numberCounterMap = MapFactory
			.newCounterMap(ListFactory.newRangeList(1, 5));
		expected = "{\"1\":0,\"2\":0,\"3\":0,\"4\":0,\"5\":0}";
		ObjectToJsonFileExtensions.toJsonFile(numberCounterMap, jsonFile, false);
		actual = ReadFileExtensions.readFromFile(jsonFile);
		assertEquals(expected, actual);
		// clean up
		DeleteFileExtensions.delete(jsonFile);
	}

	@Test public void testTestToJsonFile1() throws IOException
	{
		String expected;
		String actual;
		File jsonFile;
		Employee employee;
		Map<Integer, Integer> numberCounterMap;
		ObjectMapper mapper;
		mapper = ObjectMapperFactory.newObjectMapper();

		employee = Employee.builder().person(Person.builder().gender(Gender.FEMALE)
			.name("Anna").married(true).about("Ha ha ha...").nickname("beast").build()).id("23")
			.build();
		// new scenario: try to convert a Employee object to json
		expected = "{\"id\":\"23\",\"person\":{\"about\":\"Ha ha ha...\",\"gender\":\"FEMALE\",\"married\":true,\"name\":\"Anna\",\"nickname\":\"beast\"}}";
		jsonFile = new File(PathFinder.getSrcTestResourcesDir(), "tmp.json");
		ObjectToJsonFileExtensions.toJsonFile(employee, jsonFile, mapper);
		actual = ReadFileExtensions.readFromFile(jsonFile);
		assertEquals(expected, actual);
		// new scenario: try to convert a integer map to json
		numberCounterMap = MapFactory
			.newCounterMap(ListFactory.newRangeList(1, 5));
		expected = "{\"1\":0,\"2\":0,\"3\":0,\"4\":0,\"5\":0}";
		ObjectToJsonFileExtensions.toJsonFile(numberCounterMap, jsonFile, mapper);
		actual = ReadFileExtensions.readFromFile(jsonFile);
		assertEquals(expected, actual);
		// clean up
		DeleteFileExtensions.delete(jsonFile);
	}
}
