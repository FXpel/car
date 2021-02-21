package com.example.rest.command;

import com.example.rest.Main;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.InetAddress;

/**
 * LIST command wich allows us to see the list of files of the current directory
 * @author fxpel
 */
@Path("list")
public class CommandLIST implements CommandInterface{
    /**
     * Implementation of LIST command
     * @return  the list of file in the current directory
     * @throws IOException
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String Command() throws IOException {
        String res = "";
        System.out.println(ftp.getReplyString());
        FTPFile[] ln = ftp.listFiles();
        for (FTPFile tamer : ln) {
            //res += "<br><h1>"+ tamer.getName() +"</h1><br/>";
            res += "<h1>"+ tamer.getName() +"</h1>\n";
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
