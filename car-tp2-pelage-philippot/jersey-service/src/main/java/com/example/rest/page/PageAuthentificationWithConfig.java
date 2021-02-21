package com.example.rest.page;

public class PageAuthentificationWithConfig extends PageAuthentification{
	/* input_port : */
	private final static String input_port = "<input name=\"port\" type=\"port\" placeholder=\"port\" required/>";
	/* input_address : */
	private final static String input_address = "<input name=\"address\" type=\"address\" placeholder=\"address\" required/>";
	/* form : */
	private final static String form = "<form method=\"POST\" action=\"auth\">"+input_login+input_password+input_port+input_address+input_submit+"</form>";
	
	// OVERRIDE
	@Override
	public String getPage() {
		return head+form+end;
	}
}
