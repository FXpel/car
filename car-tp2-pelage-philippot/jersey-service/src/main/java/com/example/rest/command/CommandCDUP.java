package com.example.rest.command;

import com.example.rest.Main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.File;
import java.io.IOException;

/**
 * CDUP command that allow us to go to the parent directory if not at the root
 * @author fxpel
 */
@Path("cdup")
public class CommandCDUP implements CommandInterface {

    /**
     * Implementation of the CDUP command
     * @return a String that who tell us the current directory
     * @throws IOException
     */
    @GET
    @Override
    public String Command() throws IOException {

        String res;
        File f = new File(Main.current_dir);
        if (!Main.current_dir.equals(this.root)){
            ftp.changeToParentDirectory();
            Main.current_dir = ftp.printWorkingDirectory();
            res ="<h1>" +Main.current_dir + "</h1>";

        }
        else{
            res ="<h1> You're already at the root !</h1>";
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
