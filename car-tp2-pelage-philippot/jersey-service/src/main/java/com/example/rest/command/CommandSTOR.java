package com.example.rest.command;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * STOR command wich allows us to store a file
 * @author fxpel
 */
@Path("store:{filename: .*}")
public class CommandSTOR implements CommandInterface {
    @Override
    public String Command() throws IOException {
        return null;
    }

    @Override
    public String Command(int i) throws IOException {
        return null;
    }

    /**
     *Implementation of STOR command
     * @param file the name file we want to download
     * @return the file has been
     * @throws IOException
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public String Command(@PathParam("filename") String file) throws IOException {
        String res="";
        System.out.println(ftp.getReplyString());
        File source = new File(file);
        if(source.exists()){
            FileInputStream is = new FileInputStream(source.getAbsolutePath());
            ftp.enterLocalPassiveMode();
            boolean result = ftp.storeFile(source.getName(),is);
            if (result){
                res ="<h1>" +file + " was uploaded succefully</h1>";
                is.close();;
                return res;
            }
            ftp.logout();
        }
        res ="<h1> error file wasn't uploaded</h1>";
        return res;
    }
    @Override
    public String Command(String s1, String s2) throws IOException {
        return null;
    }
}
