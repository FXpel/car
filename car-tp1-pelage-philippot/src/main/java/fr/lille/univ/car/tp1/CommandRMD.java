package fr.lille.univ.car.tp1;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
/**
* @autor pelage
*
*Implémentation de la commande RMD qui permet de supprimer un répertoire
*
*
*/
public class CommandRMD {
	/**
    *méthode permettant de supprimer un répertoir du serveur de manière itérative
    *@param out code réponse de la requête
    *@param p chemin du répertoire à supprimer
    */

    public static boolean delFile(PrintStream out,File p) throws IOException {
    	File[] allContents = p.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
            	delFile(out,file);
            	if(file.delete()) {
            		out.println("250 file removed ");
            	}
            	else {
            		out.println("500 file can't be removed ");
            	}
            }
        }
        
        return p.delete();
    }	
 

}
