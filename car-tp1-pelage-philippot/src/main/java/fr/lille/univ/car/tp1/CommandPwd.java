package fr.lille.univ.car.tp1;

import java.io.PrintStream;
/**
*
*@autor pelage
*
* Implémentation de la commande PWD permettant d'afficher le répertoire courant
*
*/
public class CommandPwd {
	/*
	*méthode permettant d'afficher le répertoir courrant
	*
	*@param out code réponse de la requête
	*@param current_rep endroit où l'on va stocker le fichier
	*
	*/
	public String displayCurrentRep(PrintStream out,String current_rep) {
		out.println("257 "+current_rep);
		return current_rep;
	}
}
