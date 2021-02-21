package fr.lille.univ.car.tp1;

import java.io.PrintStream;
import java.io.File;

/**
* @autor pelage
*
*Implémentation de la commence CDUP pour passser au répertoire père
*
*
*/
public class CommandCDUP {
	/*
	*méthode permettant de rentrer dans le répertoire père
	*
	*@param out code réponse de la requête
	*@param current_rep répertoire courant
	*
	*@return retourne le nouveau répertoire dans lequel on se trouve
	*/
	public String toUpRep(PrintStream out,String current_rep,String root) {
		String[] pars_root = root.split("/");
		String[] pars_cur = current_rep.split("/");
		File f = new File(current_rep);
		if(!pars_cur[pars_cur.length -1 ].equals(pars_root[pars_root.length-1])) {
			out.println("250 go to upper repository");
			return f.getParentFile().toString();
			
		}
		else {
			out.println("400 you're already at the root");
			return root;
		}
		
		
		
    	
	}
}
