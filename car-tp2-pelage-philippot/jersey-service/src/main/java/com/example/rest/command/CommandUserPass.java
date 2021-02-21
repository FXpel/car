package com.example.rest.command;

import com.example.rest.page.PageAuthentification;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * USER and PASS commands wich allows us to connect to the server
 * @author fxpel
 */
@Path("auth")
public class CommandUserPass implements CommandInterface {
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
     * Implementation of USER and PASS commands
     * @param log the log of the user
     * @param pass the password of the user
     * @return the user has been connected or not
     * @throws IOException
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public String Command(@FormParam("login") String log, @FormParam("psw") String pass)throws IOException {
        PageAuthentification p = new PageAuthentification();
        if(this.log.equals(log) && this.pass.equals(pass)) {
            ftp.login(log, pass);
//            ftp.cwd()
            return "<h1>ok</h1>";
        }else {
            return "<h1>not ok</h1>";
        }

    }
}
