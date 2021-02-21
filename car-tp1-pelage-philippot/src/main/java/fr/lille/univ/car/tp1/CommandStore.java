package fr.lille.univ.car.tp1;

import java.io.PrintStream;
/*
*
*@autor pelage
*
* Implémentation de la commande STOR qui permet d'enregistrer un fichier sur le serveur
*
*/
public class CommandStore {
	/*
	*méthode permettant de stocker un fichier
	*
	*@param out code réponse de la requête
	*@param rep fichier qu'il faut stocker
	*@param current_rep endroit où l'on va stocker le fichier
	*
	*@return retourne l'adresse du répertoir qui vient d'être ajouter
	*/
	public String storeFile(PrintStream out,String rep,String current_rep) {

		DoFileExist dfe = new DoFileExist();
		boolean b = dfe.existInRep(current_rep,rep);
		if (b) {
			current_rep += rep + "/";
			out.println("200 commande passée");
		}
		else {
			out.println("400 wrong pathname");
		}
		return current_rep;
		
    	
	}
}
