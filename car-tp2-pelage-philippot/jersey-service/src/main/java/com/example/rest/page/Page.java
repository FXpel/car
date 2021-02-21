/**
 * Abstract Class Page :
 * Class to extend to create web pages
 * 
 * @author philippot
 * @version 1
 */
package com.example.rest.page;

abstract class Page {
	// ATTRIBUTS
	/* title : title of this page */
	private static String title = "";
	/* head : instantiate the HTML page */
	protected final static String head="<html><head><title>"+title+"</title></head><body>";
	/* end : finalize the HTML page */
	protected final static String end="</body></html>";
		
	// CONSTRUCTOR
	/**
	 * Create a Page Web
	 * @param t : (type-String) the new title of this page
	 */
	public Page(String t) {
		Page.title = t;
	}
	
	// METHODS
	/**
	 * Return 
	 * @return : (type-String) return the tags that will form the web page
	 */
	public abstract String getPage();
}
