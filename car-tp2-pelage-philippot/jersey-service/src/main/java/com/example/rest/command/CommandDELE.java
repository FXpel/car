package com.example.rest.command;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;



/**
 * DELE command wich allows us to delete a file
 * @author fxpel
 */
@Path("dele:{pathName}")
public class CommandDELE implements CommandInterface {
    @Override
    public String Command() throws IOException {
        return null;
    }

    @Override
    public String Command(int i) throws IOException {
        return null;
    }

    /**
     * Implementation of DELE command
     * @param pathName the path name of the file
     * @return the file is deleted or not
     * @throws IOException
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public String Command(@PathParam("pathName") String pathName) throws IOException {
        String res;
        //System.out.println(ftp.getReplyString());

        if(ftp.deleteFile(pathName)){
            res ="<h1>" +pathName + " was succefully deleted</h1>";
            return res;
        }
        res = "<h1>wrong pathname or file doesn't exist</h1>";
        return res;

    }

    @Override
    public String Command(String s1, String s2) throws IOException {
        return null;
    }
}
