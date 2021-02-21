package fr.lille.univ.car.tp1;

import java.io.File;
import java.io.PrintStream;
/*
*
*@autor pelage
*
* Implémentation de la commande RNTO permettant d'afficher le répertoire courant
*
*/
public class CommandRNTO {
	/**
	    *méthode copiant un fichier
	    *
	    *@param out code réponse de la requête
	    *@param oldfile chemin de la source du fichier 
	    *@param newfile chemin de la destination du fichier
	    *
	    *
	    */
	public boolean rename(PrintStream out,String oldfile, String newfile) {
		File of =new File(oldfile);
		File nf =new File(newfile);
		
		if(of.renameTo(nf)){
			out.println("200 rename ok");
			return true;
		}else{
			out.println("500 rename failed");
			return false;
		}
	}
	
}
