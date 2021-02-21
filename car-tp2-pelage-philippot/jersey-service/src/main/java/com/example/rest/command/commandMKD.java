package com.example.rest.command;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;


/**
 * MKD command wich allows us to create a directory
 * @author fxpel
 */
@Path("mkd:{dirName}")
public class commandMKD implements CommandInterface {
    @Override
    public String Command() throws IOException {
        return null;
    }

    @Override
    public String Command(int i) throws IOException {
        return null;
    }

    /**
     * Implementation of MKD command
     * @param dirName name of the directory we want to create
     * @return the directory is created or not
     * @throws IOException
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public String Command(@PathParam("dirName") String dirName) throws IOException {
        String res;
        System.out.println(ftp.getReplyString());
        if(ftp.makeDirectory(dirName)){
            res ="<h1>" +dirName + " was succefully created</h1>";
            return res;
        }
        res = "<h1>" +dirName + " creation failed</h1>";
        return res;

    }

    @Override
    public String Command(String s1, String s2) throws IOException {
        return null;
    }
}
