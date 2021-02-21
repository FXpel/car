package com.example.rest.command;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;


/**
 * Rename command wich allows us to rename a file or a directory
 * @author fxpel
 */
@Path("rename:{oldFileName},{newFileName}")
public class CommandRename implements CommandInterface {
    @Override
    public String Command() throws IOException {
        return null;
    }

    @Override
    public String Command(int i) throws IOException {
        return null;
    }

    @Override
    public String Command(String s) throws IOException {
        return null;
    }

    /**
     * Implementation of rename command
     * @param oldName the current name of the file
     * @param newName the news name of the file
     * @return the file name has been changed or not
     * @throws IOException
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public String Command(@PathParam("oldFileName") String oldName, @PathParam("newFileName") String newName) throws IOException {
        String res;
        System.out.println(ftp.getReplyString());

        if(ftp.rename(oldName, newName)){
            res ="<h1>" +oldName + " was succefully rename to " + newName+ "</h1>";
            return res;
        }
        res = "<h1>" +oldName + " rename's failed</h1>";
        return res;

    }
}
