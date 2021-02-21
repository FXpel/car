package com.example.tp.parser;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.tp.parser.XMLParser;

public class TestXMLParser {
	@Test
	public void testGeneratorNodeListFromXML() {
		XMLParser parser = new XMLParser("./src/test/java/com/example/tp/Testnode.xml");
		List<String> nodesExpected = new ArrayList<>();
		nodesExpected.add("1");
		nodesExpected.add("2");
		List<String> nodes = parser.generatorNodeListFromXML();
		assertEquals(nodesExpected,nodes);
	}
	
	@Test
	public void testGetChildsFromNode() {
		XMLParser parser = new XMLParser("./src/test/java/com/example/tp/Testnode.xml");
		List<String> nodesExpected = new ArrayList<>();
		nodesExpected.add("2");
		List<String> nodes = parser.getChildsFromNode("1");
		assertEquals(nodesExpected,nodes);
	}
}
