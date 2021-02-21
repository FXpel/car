package com.example.rest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTPClient;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Main class.
 *
 */
public class Main {
	// ATTRIBUTS	
    // Base URI the Grizzly HTTP server will listen on
    public static String BASE_URI = "http://localhost:8080/myapp/";
    public static final FTPClient ftp = new FTPClient();
    public static String root = "/";
    public static int server_port = 2121;
    public static int rest_port = 8080;
    public static InetAddress host;
    public static String hostName="localhost";
    public static String current_dir = root;

    // METHODS
    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example.rest package
        final ResourceConfig rc = new ResourceConfig().packages("com.example.rest");
        //rc.register(MultiPartFeature.class);
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * The new port 
     * If the user enters the value 0, the port will be 8080 (by default)
     * User must enter a value between 1024 and 65535
     * @throws IOException
     */
    public static void setStartRestPort() throws IOException{
        System.out.println("please enter the port number you want to use");
        Scanner myObj;
        int p=0;
        boolean port_ok = false;
        while (!port_ok){
            myObj =new Scanner(System.in);
            p += myObj.nextInt();
            if(p==0){
                setRest_port(8080);
                System.out.println("8080 is the port number that you'll use");
                return;
            }
            if(p >= 1024 && p <= 65535 && p != server_port){
                port_ok = true;
                setRest_port(p);
                System.out.println(p + " is the port number that you'll use");
                return;
            }
            System.out.println("port is incorrect please choose a correct port number between 1024 and 65353 wich isn't used");
            p=0;
        }
    }
    
    /**
     * The new url
     * If the user enter no value, the url will be http://localhost:<code>rest_port</code>/myapp (by default)
     * @throws IOException
     */
    public static void setUrl() throws IOException {
        String serv = "http://localhost:"+rest_port+"/";
        boolean url_ok = false;
        Scanner myObj;
        String tmp;
        while (!url_ok){
            System.out.println("Enter the url you want which start with: http://localhost:"+rest_port+"/");
            myObj = new Scanner(System.in);
            tmp = myObj.nextLine();
            if(tmp.equals("")){
                serv += server_port+"/myapp/";
                return;
            }
            String[] tab = tmp.split("/");
            if(tmp.charAt(tmp.length()-1) == '/' && tab.length > 0 ){
                url_ok = true;
                serv += tmp;
                System.out.println("Username is: " + serv);
                Main.setBaseUri(serv);
                return;
            }
            else{
                System.out.println("not a valide url, please rewrite your URL following this example : http://localhost:8080/myapp/");
            }
        }
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    @SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
        setStartRestPort();
        setUrl();
        final HttpServer server = startServer();
        host = InetAddress.getLocalHost();
        @SuppressWarnings("unused")
		String serv = "127.0.0.1";
        ftp.connect(host,server_port);
        ftp.login("user", "12345"); //à décommenter sur windows car on ne peut pas se connecter

        System.out.println("Connected to "+ InetAddress.getLocalHost() + " at " + server_port);
        System.out.println("Current dir is  "+ ftp.printWorkingDirectory());

        System.out.println("Connected to " + server + ".");        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));

        System.in.read();

        server.stop();
    }

    // GET AND SET
    public static FTPClient getFtp() { return ftp; }

    public static String getCurrent_dir() { return current_dir; }

    public static void setCurrent_dir(String current_dir) { Main.current_dir = current_dir; }

    public static String getRoot() { return root; }

    public static void setServer_port(int server_port) { Main.server_port = server_port; }

    public static int getServer_port() { return server_port; }

    public static int getRest_port() { return rest_port; }

    public static void setRest_port(int rest_port) { Main.rest_port = rest_port; }

    public static InetAddress getHost() { return host; }

    public static void setHost(InetAddress host) { Main.host = host; }

    public static String getHostName() { return hostName; }

    public static void setHostName(String hostName) { Main.hostName = hostName; }

    public static String getBaseUri() { return BASE_URI; }

    public static void setBaseUri(String baseUri) { BASE_URI = baseUri; }
}


