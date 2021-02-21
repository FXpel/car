package com.example.rest.command;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;


/**
 * PASV command wich allows us to activate the passive mode
 * @author fxpel
 */
@Path("pasv")
public class CommandPASV implements CommandInterface {
    /**
     * Implementation of PASV command
     * @return the server is set to passive mode or not
     * @throws IOException
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String Command() throws IOException {
        String res;
        if (ftp.isConnected()){
            ftp.enterLocalPassiveMode();
            res = "<h1>server is set to passive mode</h1>";
            return res;
        }
        res = "<h1>can't reach the server</h1>";

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
