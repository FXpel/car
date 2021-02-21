package com.example.rest.command;

import com.example.rest.Main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * RETR command wich allws us to retrieve a file
 * @author fxpel
 */
@Path("retr:{filename}")
public class CommandRETR implements CommandInterface {
    static String current_dir = Main.getCurrent_dir();
    @Override
    public String Command() throws IOException {
        return null;
    }

    @Override
    public String Command(int i) throws IOException {
        return null;
    }

    /**
     * Implementation of RETR command
     * @param file file we want to retrieve
     * @return the file has been uploaded or not
     * @throws IOException
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public String Command(@PathParam("filename") String file) throws IOException {
        String res="";
        String currentDir = System.getProperty("user.dir");
        System.out.println(ftp.getReplyString());

        File source = new File(currentDir + this.current_dir + "/" + file);
        //File f = new File(this.localDir + "/"+file);
        //System.out.println("chemin absolu f :" + f.getAbsolutePath());
        System.out.println("chemin absolu source :" + source.getAbsolutePath());
        if(source.exists()){

            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(this.localDir + "/"+file));
            boolean result = this.ftp.retrieveFile(file, out);
            System.out.print(ftp.getReplyString());
            if (result){
                res ="<h1>" +file + " was downloaded succefully</h1>";
                out.flush();
                return res;
            }

        }


        res ="<h1> error file wasn't downloaded</h1>";
        return res;
    }

    @Override
    public String Command(String s1, String s2) throws IOException {
        return null;
    }
}
