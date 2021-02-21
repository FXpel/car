package com.example.rest.command;

import com.example.rest.Main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.io.IOException;


/**
 * CWD command wich allows us to go to a directory if it's exists
 * @author fxpel
 */
@Path("cwd:{rep: .*}")
public class CommandCWD implements CommandInterface{
    private static String current_dir = Main.getCurrent_dir();
    @Override
    public String Command() throws IOException {
        return null;
    }

    @Override
    public String Command(int i) throws IOException {
        return null;
    }

    /**
     *Implementation of CWD command
     * @param r the name of the directory we want to go
     * @return the current directory
     * @throws IOException
     */
    @GET
    @Override
    public String Command(@PathParam("rep") String r) throws IOException {
        String res;
        if (ftp.changeWorkingDirectory(r)){
            Main.current_dir = ftp.printWorkingDirectory();
            res ="<h1>" +Main.current_dir + "</h1>";
        }
        else{
            res ="<h1> The path is wrong !</h1>";
        }
        return res;
    }

    @Override
    public String Command(String s1, String s2) throws IOException {
        return null;
    }
}
