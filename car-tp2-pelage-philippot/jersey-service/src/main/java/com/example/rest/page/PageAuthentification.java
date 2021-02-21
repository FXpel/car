/**
 * Class PageAughtentification
 * Returns the tags allowing the user :
 * - to connect to the server
 * - to know if his connection was successful
 * 
 * @author philippot
 * @version 1
 */
package com.example.rest.page;

public class PageAuthentification extends Page{
	// ATTRIBUTS
	/* input_login : */
	protected final static String input_login = "<input name=\"login\" placeholder=\"login\"/>";
	/* input_password : */
	protected final static String input_password = "<input name=\"psw\" type=\"password\" placeholder=\"password\"/>";
	/* input_submit :*/
	protected final static String input_submit = "<input type=\"submit\" placeholder=\"connexion\"/>";
	/* form : */
	protected final static String form = "<form method=\"POST\" action=\"auth\">"+input_login+input_password+input_submit+"</form>";
	/* error_password : */
	private final static String error_password = "<p>Les identifiants ne sont pas corrects</p>";
	/* error : */
	private final static String error = "<p>Erreur de connection !</p>";
	/* connection_succes : */
	private final static String connection_success = "<p>Connection réussie !</p>";
	
	// CONSTRUCTOR	
	public PageAuthentification() {
		super("Authentification page");
	}

	// OVERRIDE
	@Override
	public String getPage() {
		return head+form+end;
	}
	
	// METHODS
	/**
	 * 
	 * @return : (type-String)
	 */
	public String getErrorPassword() {
		return head+error_password+end;
	}
	
	/**
	 * 
	 * @return : (type-String)
	 */	
	public String getErrorConnection() {
		return head+error+end;
	}

	/**
	 * 
	 * @return : (type-String)
	 */
	public String getConnectionSuccess() {
		return head+connection_success+end;
	}

}
