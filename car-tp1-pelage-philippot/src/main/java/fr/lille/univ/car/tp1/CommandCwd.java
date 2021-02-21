package fr.lille.univ.car.tp1;

import java.io.File;
import java.io.PrintStream;
/**
* @autor pelage
*
*Implémentation de la commande CWD pour changer de répertoire
*
*
*/
public class CommandCwd {
	/**
	*méthode vérifiant si l'utilisateur est autorisé à se connecter au serveur
	*
	*@param out code réponse de la requête
	*@param p chemin du répertoire où l'on veut aller 
	*@param current_rep répertoire courrant
	*
	*@return retourne le nouveau répertoire courant s'il existe
	*/
	public String changeRep(PrintStream out,String p,String current_rep,String root) {
		File path = new File(p);
		System.out.println(path.getAbsolutePath() + " tu me fais quoi?");
		if (isInRoot(path, root)) {
			current_rep = p;
			System.out.println(current_rep);
			out.println("250 "+current_rep+" is new woking directory");
		}
//		else {
//			DoFileExist dfe = new DoFileExist();
//			boolean b = dfe.existInRep(current_rep,p);
//			if(b){
//
//				current_rep += "/" + p;
//				out.println("250 "+current_rep+" is new woking directory");
//			}
			else{
				out.println("500 wrong pathname");
			}
			
		
		return current_rep;
	
    	
	}
	/**
	*méthode pour vérifier si on se trouve dans la racine du dossier
	*
	*@param path
	*@param root
	*
	*@return vrai si on est à la racine faux sinon
	*/
	public boolean isInRoot(File path,String root){
		String p = path.getAbsolutePath();
		for (int i = 0; i < root.length(); i++) {
			if(root.charAt(i) != p.charAt(i)) {
				System.out.println(root.charAt(i) + " <> " + p.charAt(i));
				return false;
			}
		}
		return true;
	}


	
}
