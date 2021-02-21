package com.example.rest.command;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;


/**
 * QUIT command wich allows us to disconnect from REST
 * @author fxpel
 */
@Path("quit")
public class CommandQUIT implements CommandInterface {
    /**
     * Implementation of QUIT command
     * @return that the connection is closed
     * @throws IOException
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public String Command() throws IOException {
        String res;
        ftp.disconnect();
        res = "<h1>connection is closed</h1>";
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
