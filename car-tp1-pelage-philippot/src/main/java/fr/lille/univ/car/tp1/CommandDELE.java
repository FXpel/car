package fr.lille.univ.car.tp1;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
* @autor pelage
*
*Implémentation de la commande DELE qui permet de supprimer un fichier
*
*
*/
public class CommandDELE {
    /**
    *méthode permettant de supprimer un fichier
    *
    *@param out code réponse de la requête
    *@param path_file chemin du fichier à supprimer
    *
    */
	public boolean delFile(PrintStream out ,String path_file) throws IOException {
        File tmp = new File(path_file);
        if (tmp.isDirectory()) {
        	deleteDir(tmp);
        	return true;
        }
        else if(tmp.exists()) 
        { 
        	tmp.delete();
            out.println("200 File renamed and moved successfully"); 
            return true;
        } 
        else
        { 
            out.println("400 Failed to remove the file"); 
            return false;
        }
	}
	
	void deleteDir(File file) {
	    File[] contents = file.listFiles();
	    if (contents != null) {
	        for (File f : contents) {
	            deleteDir(f);
	        }
	    }
	    file.delete();
	}
}
