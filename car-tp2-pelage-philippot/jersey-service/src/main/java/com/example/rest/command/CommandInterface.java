package com.example.rest.command;

import java.io.IOException;
import java.net.InetAddress;

import org.apache.commons.net.ftp.FTPClient;

import com.example.rest.Main;

/**
 * Interface that implements the differents commands
 *
 * @author fxpel
 */
public interface CommandInterface {
    /**
     *contains the name of a user
     */
    String log = "user";
    /**
     *contains the password of a user
     */
    String pass = "12345";
    /**
     *contains the local directory, where we upload our files
     */
    static String localDir = System.getProperty("user.dir") + "/reptest";
    /**
     *contains the ftp client
     */
    static FTPClient ftp = Main.getFtp();
    /**
     *contains the root of the ftp client
     */
    static String root = Main.getRoot();
    /**
     *contains the port number of the server
     */
    static int server_port = Main.getServer_port();
    /**
     *contains the port number of REST
     */
    static int rest_port = Main.getRest_port();
    /**
     *contains the inetAddress of the server
     */
    static InetAddress host = Main.getHost();
    /**
     *contains the adress of the server
     */
    static String hostName = Main.getHostName();
    /**
     * contains the URL that we use
     */
    public static String BASE_URI = Main.getBaseUri();

    /**
     *Implement a command without parameters
     * @return
     * @throws IOException
     */
    public String Command()throws IOException;

    /**
     *Implement a command that need 1 parameters
     * @param i
     * @return
     * @throws IOException
     */
    public String Command(int i)throws IOException;

    /**
     *Implement a command that need 1 parameters
     * @param s
     * @return
     * @throws IOException
     */
    public String Command(String s)throws IOException;

    /**
     *Implement a command that needs 2 parameter
     * @param s1
     * @param s2
     * @return
     * @throws IOException
     */
    public String Command(String s1,String s2)throws IOException;

}
