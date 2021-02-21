package fr.lille.univ.car.tp1;
import java.io.*;
import java.io.File;
import java.io.PrintStream;
/**
*
*@autor pelage
*
* Implémentation de la commande MKD créant un nouveau répertoir
*/
public class CommandMKD {
	/**
	*méthode créant un répertoire
	*
	*@param out code réponse de la requête
	*@param path chemin du répertoir à créer
	*
	*/
	public boolean newRep(PrintStream out,String path){
		
		
		File file = new File(path);
        if (!file.exists()) {
            if (file.mkdir()) {
                out.println("250 Directory is created!");
                return true;
            } else {
                out.println("500 Failed to create directory!");
                return false;
               
            }
        }
        return false;
	}
	
}
