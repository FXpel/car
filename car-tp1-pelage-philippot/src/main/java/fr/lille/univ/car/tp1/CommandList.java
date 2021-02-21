package fr.lille.univ.car.tp1;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.File;
import java.nio.file.Files;
import java.io.PrintStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.lang.StringBuilder;
import java.nio.file.Paths;


/**
* @autor pelage
*
*Implémentation de la commande liste
*
*
*/
public class CommandList {
    /**
    *méthode permettant de lister le contenu du dossier courant
    *
    *@param out code réponse de la requête
    *@param path chemin du dossier courant
    *@param port port sur le quel les tansfert se feront
    *
    *
    */
	public boolean printDir(PrintStream out,String path,ServerSocket tmp) throws IOException{

        Socket s = tmp.accept();
        PrintWriter dout = new PrintWriter(s.getOutputStream(), true);
        String owner = Files.getOwner(Paths.get(path)).getName();
    	File rep = new File(path);
    	File[] liste = rep.listFiles();
    	if(liste != null) {

            out.println("150 Data connection accepted from 127.0.0.1:1025; transfer starting. \n");
            for (int i = 0; i < liste.length; i++) {
            StringBuilder  permission=  new StringBuilder("----------");
            long size = liste[i].length();
            long fLm = liste[i].lastModified();
            Date fD = new Date(fLm);
            SimpleDateFormat time = new SimpleDateFormat("MMM dd hh:mm", Locale.ENGLISH);
            String fInfo = size + " "+time.format(fD) + " ";
            if(liste[i].isDirectory()){

                permission.setCharAt(0, 'd');
            }
            if(liste[i].canRead()){

                permission.setCharAt(1, 'r');
                permission.setCharAt(4, 'r');
                permission.setCharAt(4, 'r');
            }
            if(liste[i].canWrite()){
                permission.setCharAt(2, 'w');
                permission.setCharAt(5, 'w');
                permission.setCharAt(8, 'w');

            }
            if(liste[i].canExecute()){

                permission.setCharAt(3, 'x');
                permission.setCharAt(6, 'x');
                permission.setCharAt(9, 'x');
            }
            
            
            dout.print(permission + " 1 "+owner+" "+owner + " "+fInfo+ liste[i].getName() +"\n");
                            
    			
			
			}
            dout.close();
            s.close();
            out.println("226 Listing completed.");    		
    		return true;
    	}
    	else {
    		out.println("400 Nom de repertoire invalide");
    		return false;
    	}
    }
}
