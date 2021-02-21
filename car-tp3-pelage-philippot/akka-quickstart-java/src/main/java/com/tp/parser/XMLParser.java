/**
 * Class XMLParser
 * @author phigr
 */
package com.tp.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {
	// ATTRIBUTS
	/* xml to parse */
	private Document xml = null;
	
	// CONSTRUCTOR
	/**
	 * 
	 * @param f the file 
	 */
	public XMLParser(String f) {
		this.initiateXMLParser(f);
	}
	
	// METHODS
	/**
	 * Initialize the xml document to parse
	 * @param f the file
	 */
	public void initiateXMLParser(String f) {
		try {
			File file = new File(f);
			DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuilder;
			dbBuilder = dbFact.newDocumentBuilder();
			Document doc = dbBuilder.parse(file);
			doc.getDocumentElement().normalize();
			this.xml = doc;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Generate a node list from the <code>xml</code> file
	 * @return return
	 */
	public List<String> generatorNodeListFromXML(){
		List<String> nodes = new ArrayList<String>();
		NodeList nL = this.xml.getElementsByTagName("node");
		for(int idx = 0; idx < nL.getLength(); idx++) {
			Node node = nL.item(idx);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) node;
				String nN = e.getAttribute("name");
				nodes.add(nN);
			}
		}
		return nodes;
	}
	
	/**
	 * Generate the childs of a node from the <code>xml</code> file
	 * @param n the node
	 * @return return
	 */
	public List<String> getChildsFromNode(String n){
		List<String> nodes = new ArrayList<String>();
		NodeList nL = this.xml.getElementsByTagName("nodelink");
		for(int idx = 0; idx < nL.getLength(); idx++) {
			Node node = nL.item(idx);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) node;
				String nN = e.getAttribute("name");
				if(n.equals(nN)){
					NodeList link = e.getElementsByTagName("link");
					for(int jdx = 0;  jdx < link.getLength(); jdx++) {
						Element l = (Element) link.item(jdx);
						nodes.add(l.getAttribute("to"));
					}
				}
			}
		}
		return nodes;
	}
}
