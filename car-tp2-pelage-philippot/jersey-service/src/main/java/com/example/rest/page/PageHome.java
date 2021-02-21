/**
 * Class PageHome : 
 * Returns to the user a connection link to the FTP server
 * 
 * @author philippot
 * @version 1
 */
package com.example.rest.page;

public class PageHome extends Page{
	// ATTRIBUTS
	/**/
	private static String url_authentification;
	/* link : connection link to the FTP server */
	private final static String link ="<p><a href="+url_authentification+">Authentification</a></p>";
	
	// CONSTRUCTOR
	public PageHome(String u_a) {
		super("Home page");
		url_authentification = u_a;
	}

	// METHODS
	@Override
	public String getPage() {
		return head+link+end;
	}

	// GET
	/**
	 * @return the link
	 */
	public static String getLink() {
		return link;
	}

	/**
	 * @return the url_authentification
	 */
	public String getUrl_authentification() {
		return url_authentification;
	}

	/**
	 * @param url_authentification the url_authentification to set
	 */
	public void setUrl_authentification(String url_authentification) {
		PageHome.url_authentification = url_authentification;
	}

}
