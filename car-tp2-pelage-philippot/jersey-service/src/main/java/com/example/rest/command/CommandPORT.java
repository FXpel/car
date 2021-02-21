package com.example.rest.command;

import com.example.rest.Main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Command that allows us to change the port number of the server
 * @author fxpel
 */
@Path("port:{newPort}")
public class CommandPORT implements CommandInterface {
    @Override
    public String Command() throws IOException {
        return null;
    }

    /**
     * Implementation of the command which change the port number of REST
     * @param newPort the new port
     * @return the change of the port number
     * @throws IOException
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public String Command(@PathParam("newPort") int newPort) throws IOException {
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

    @Override
    public String Command(String s) throws IOException {
        return null;
    }

    @Override
    public String Command(String s1, String s2) throws IOException {
        return null;
    }
}
