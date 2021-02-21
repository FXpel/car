package com.example.rest;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyResourceTest {

    private HttpServer server;
    private WebTarget target;
    
    private String auth;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
        auth = target.getUri()+"/auth";
    }

    @SuppressWarnings("deprecation")
	@After
    public void tearDown() throws Exception {
        server.stop();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        String responseMsg = target.path("myresource").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }
    
    /**
     * Test the connection with good logs
     */
    @Test
    public void testConnectionWhenGoodLogs() {
    	Form form = new Form();
    	form.param("login", "user");
    	form.param("psw", "12345");
    	
    	Response connect = target.path("auth").request().post(Entity.form(form));
    	assertEquals(230,connect.getStatus());
    }
    
    /**
     * Test the close connection
     */
    @Test
    public void testCloseConnection() {
        String responseMsg = target.path("myresource/quit").request().get(String.class);
        assertEquals("<h1>connection is closed</h1>", responseMsg);
    }
    
    /**
     * Test the connection when we change the server port
     */
    @Test
    public void testChangePort() {
    	Form form = new Form();
    	form.param("newPort", "2121");
    	
    	Response connect = target.path("myressource/port").request().post(Entity.form(form));
    	assertEquals(230,connect.getStatus());
    }
    
    /**
     * Test the connection with bad user
     */
    @Test
    public void testConnectionWhenBadUser() {
    	Form form = new Form();
    	form.param("login", "test");
    	form.param("psw", "12345");
    	
    	Response connect = target.path(auth).request().post(Entity.form(form));
    	assertEquals(530,connect.getStatus());
    }
    
    /**
     * Test the connection with bad password
     */
    @Test
    public void testConnectionWhenBadPass() {
    	Form form = new Form();
    	form.param("login", "user");
    	form.param("psw", "00000");
    	
    	Response connect = target.path(auth).request().post(Entity.form(form));
    	assertEquals(530,connect.getStatus());
    }
}
