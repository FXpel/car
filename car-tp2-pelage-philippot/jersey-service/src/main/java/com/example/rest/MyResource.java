package com.example.rest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.example.rest.page.PageHome;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	/* logs to MyRessource*/
    private String log = "user";
    private String pass = "12345";
    
    private static String localDir = System.getProperty("user.dir") + "/reptest";
	private static FTPClient ftp = Main.getFtp();
	private static String root = Main.getRoot();
	private static String current_dir = Main.getCurrent_dir();
	private static int server_port = Main.getServer_port();
	private static int rest_port = Main.getRest_port();
    private static InetAddress host = Main.getHost();
    private static String hostName = Main.getHostName();
    public static String BASE_URI = Main.getBaseUri();

    /**
     * It's a test of display
     * @return : (type-String)
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    /**
     * Connection to the server with logs : <code>log</code> and <code>pass</code>
     * @param log : (type-String) the login
     * @param pass : (type-String) the password
     * @return : (type-String) status of this connection
     * @throws IOException
     */
    @POST
    @Path("auth")
    @Produces(MediaType.TEXT_PLAIN)
    public String connection(@FormParam("login") String log,@FormParam("psw") String pass)throws IOException {
        if(this.log.equals(log) && this.pass.equals(pass)) {
            ftp.login(log, pass);
            return "<h1>ok</h1>";
        }else {
            return "<h1>not ok</h1>";
        }
    }

    /**
     * It's a test, display a page
     * @return : (type-String) web page
     */
    @GET
    @Path("/home")
    public String getHome() {
    	PageHome p = new PageHome(BASE_URI+"/auth");
    	return p.getPage();
    }

    /**
     * Files contains in this directory
     * @return : (type-String) list of files
     * @throws IOException
     */
    @GET
    @Path("list")
    @Produces(MediaType.TEXT_PLAIN)
    public String listDir() throws IOException {
    	String res = "<ul>";
    	FTPFile[] ln = ftp.listFiles();
    	for (FTPFile tamer : ln) {
    		res += "<li>"+ tamer.getName() +"</li>";
    	}
    	res += "</ul>";
    	return res;
    }

    /**
     * Display the current directory
     * @return : (type-String) the current directory
     * @throws IOException
     */
   @GET
   @Path("pwd")
   public String currentDir() throws IOException {
       String res;
	   current_dir = ftp.printWorkingDirectory();
	   res ="<h1>" +current_dir + "</h1>";
	   if(current_dir == null){
	       res ="<h1>/</h1>";
	   }
	   return res;
    }

    /**
     * Change the current directory to <code>r</code>
     * @param r : (type-String) the new current directory
     * @return : (type-String) status of current directory
     * @throws IOException
     */
    @GET
    @Path("cwd:{rep: .*}")
    public String changeDir(@PathParam("rep") String r) throws IOException {
        String res;
        if (ftp.changeWorkingDirectory(r)){
        	current_dir = ftp.printWorkingDirectory();
            res ="<h1>" +current_dir + "</h1>";
        } else{
            res ="<h1> The path is wrong !</h1>";
        }
        return res;
    }

    /**
     * Set the current directory to the parent
     * @return : (type-String) status of current directory
     * @throws IOException
     */
    @GET
    @Path("cdup")
    public String parentDir() throws IOException {
        String res;
        //File f = new File(current_dir);
        if (!current_dir.equals(root)){
        	ftp.changeToParentDirectory();
            current_dir = ftp.printWorkingDirectory();;
            res ="<h1>" +current_dir + "</h1>";
        } else{
            res ="<h1> You're already at the root !</h1>";
        }
        return res;
    }

    /***
     * Set the current directory to <code>r</code>
     * @param r : (type-String) the new current directory
     * @return : (type-String) status of <code>r</code>
     * @throws IOException
     */
    @GET
    @Path("home:{rep: .*}")
    public String currentDir(@PathParam("rep") String r) throws IOException {
        String res;
        File f = new File(r);
        if (f.exists()){
        	ftp.changeWorkingDirectory(r);
            current_dir = ftp.printWorkingDirectory();
            root = ftp.printWorkingDirectory();
            res ="<h1>" +current_dir + "</h1>";
        } else{
            res = "<h1> not a good directory </h1>";
        }
        return res;
    }

    /**
     * Download <code>file</code>
     * @param file : (type-String) file to download
     * @return : (type-String) status of <code>file</code>
     * @throws IOException
     */
    @GET
    @Path("retr:{filename}")
    @Produces(MediaType.TEXT_PLAIN)
    public String downloadFile(@PathParam("filename") String file) throws IOException {
        String res="";
        String currentDir = System.getProperty("user.dir");
        System.out.println(ftp.getReplyString());
        File source = new File(currentDir + current_dir + "/" + file);
        //File f = new File(this.localDir + "/"+file);
        //System.out.println("chemin absolu f :" + f.getAbsolutePath());
        System.out.println("chemin absolu source :" + source.getAbsolutePath());
        if(source.exists()){      	
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(localDir + "/"+file));
            boolean result = ftp.retrieveFile(file, out);
            System.out.print(ftp.getReplyString());
            if (result){
                res ="<h1>" +file + " was uploaded succefully</h1>";
                out.flush();
                return res;
            } 
        }
        res ="<h1> error file wasn't downloaded</h1>";
        return res;
    }

    /**
     * Upload <code>file</code>
     * @param file : (type-String) the file to upload
     * @return: (type-String) status of <code>file</code>
     * @throws IOException
     */
    @GET
    @Path("store:{filename: .*}")
    @Produces(MediaType.TEXT_PLAIN)
    public String uploadFile(@PathParam("filename") String file) throws IOException {
        String res="";
        System.out.println(ftp.getReplyString());
        File source = new File(file);
        if(source.exists()){
        	FileInputStream is = new FileInputStream(source.getAbsolutePath());
        	ftp.enterLocalPassiveMode();
            boolean result = ftp.storeFile(source.getName(),is);
            if (result){
                res ="<h1>" +file + " was uploaded succefully</h1>";
                is.close();;
                return res;
            }
            ftp.logout();  
        }
        res ="<h1> error file wasn't downloaded</h1>";
        return res;
    }

    /**
     * Rename the <code>oldName</code> to <code>newName</code>
     * @param oldName : (type-String) the old of this file
     * @param newName : (type-String) the new of this file
     * @return : (type-String) the status of <code>oldName</code>
     * @throws IOException
     */
    @GET
    @Path("rename:{oldFileName},{newFileName}")
    @Produces(MediaType.TEXT_PLAIN)
    public String renameFile(@PathParam("oldFileName") String oldName,@PathParam("newFileName") String newName) throws IOException {
        String res;
        if(ftp.rename(oldName, newName)){
            res ="<h1>" +oldName + " was succefully rename to " + newName+ "</h1>";
        }else {
        	res = "<h1>" +oldName + " rename's failed</h1>";
        }
        return res;
    }

    /**
     * Create a directory <code>dirName</code>
     * @param dirName : (type-String) the directory to create
     * @return : (type-String) the status of <code>dirName</code>
     * @throws IOException
     */
    @GET
    @Path("mkd:{dirName}")
    @Produces(MediaType.TEXT_PLAIN)
    public String createDir(@PathParam("dirName") String dirName) throws IOException {
        String res;
        System.out.println(ftp.getReplyString());
        if(ftp.makeDirectory(dirName)){
            res ="<h1>" +dirName + " was succefully created</h1>";
        }else {
        	res = "<h1>" +dirName + " creation failed</h1>";
        }
        return res;
    }

    /**
     * Delete the file <code>pathName</code>
     * @param pathName : (type-String) the file to delete
     * @return : (type-String) the status of <code>pathName</code>
     * @throws IOException
     */
    @GET
    @Path("rmd:{pathName}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteFile(@PathParam("pathName") String pathName) throws IOException {
        String res;
        if(ftp.deleteFile(pathName)){
            res ="<h1>" +pathName + " was succefully deleted</h1>";
        }else {
        	res = "<h1>wrong pathname or file doesn't exist</h1>";
        }
        return res;
    }

    /**
     * Remove the directory <code>pathName</code>
     * @param pathName : (type-String) the directory to delete
     * @return : (type-String) the status of <code>pathName</code>
     * @throws IOException
     */
    @GET
    @Path("dele:{pathName}")
    @Produces(MediaType.TEXT_PLAIN)
    public String removeDir(@PathParam("pathName") String pathName) throws IOException {
        String res;
        if(ftp.removeDirectory(pathName)){
            res ="<h1>" +pathName + " was succefully deleted</h1>";
        }else {
        	res = "<h1>wrong pathname or file doesn't exist</h1>";
        }
        return res;

    }

    /**
     * Disconnect the server
     * @return : (type-String) server status
     * @throws IOException
     */
    @GET
    @Path("quit")
    @Produces(MediaType.TEXT_PLAIN)
    public String closeConnection() throws IOException {
        String res;
        ftp.disconnect();
        res = "<h1>connection is closed</h1>";
        return res;
    }

    /**
     * Change server port
     * @param newPort : (type-Integer) the new port
     * @return : (type-String) the new status of the server
     * @throws IOException
     */
    @GET
    @Path("port:{newPort}")
    @Produces(MediaType.TEXT_PLAIN)
    public String changePort(@PathParam("newPort") int newPort) throws IOException {
        String res;
        if (ftp.isConnected()){
            ftp.disconnect();
        }
        Main.setServer_port(newPort);
        System.out.println(server_port);
        ftp.connect(hostName,server_port);
        ftp.login(this.log,this.pass);
        res = "<h1>" + newPort + " is the new port of the server</h1>";
        return res;
    }

    /**
     * If the server is connected places it in passive mode
     * @param newPort : (type-Integer) the new port
     * @return : (type-String) server status
     * @throws IOException
     */
    @GET
    @Path("pasv")
    @Produces(MediaType.TEXT_PLAIN)
    public String passivMod(@PathParam("newPort") int newPort) throws IOException {
        String res;
        if (ftp.isConnected()){
            ftp.enterLocalPassiveMode();
            res = "<h1>server is set to passiv mode</h1>";
        }else {
        	res = "<h1>can't reach the server</h1>";
        }
        return res;
    }

    // GET AND SET    
	/**
	 * @return the rest_port
	 */
	public static int getRest_port() {
		return rest_port;
	}
	/**
	 * @param rest_port the rest_port to set
	 */
	public static void setRest_port(int rest_port) {
		MyResource.rest_port = rest_port;
	}
	/**
	 * @return the host
	 */
	public static InetAddress getHost() {
		return host;
	}
	/**
	 * @param host the host to set
	 */
	public static void setHost(InetAddress host) {
		MyResource.host = host;
	}
}
