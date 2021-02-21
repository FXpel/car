package com.example.rest.command;

import com.example.rest.Main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.IOException;


/**
 * PWD command wich allows us to print the current directory
 * @author fxpel
 */
@Path("pwd")
public class CommandPWD implements CommandInterface{
    /**
     * contains the current directory
     */
    private String current_dir = Main.getCurrent_dir();

    /**
     *Implementation of pwd command
     * @return the current directory
     * @throws IOException
     */
    @GET
    @Override
    public String Command() throws IOException {

        String res;
        //String currentDir = System.getProperty("user.dir");
        //System.out.println(currentDir);
        current_dir = ftp.printWorkingDirectory();
        System.out.println("le repertoir courant "+current_dir);
        res ="<h1>" +current_dir + "</h1>";
        if(current_dir == null){
            res ="<h1>/</h1>";
        }
        return res;
    }

    @Override
    public String Command(int i) throws IOException {
        return null;
    }

    @Override
    public String Command(String s) throws IOException {
        return null;
    }

    @Override
    public String Command(String s1, String s2) throws IOException {
        return null;
    }
}
