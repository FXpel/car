package fr.lille.univ.car.tp1;

import java.io.File;
/*
*@autor pelage
*
*Implémentation de la vérification existence fichier
*
*/
public class DoFileExist {
	/*
	*vérifie l'éxistance d'un fichier
	*
	*@param rep répertoire courant
	*@param file fichier à vérifier
	*
	*@return retourne vrai si le fichier est dans le répertoire, faux sinon
	*/
	public boolean existInRep(String rep,String file) {
		File r = new File(rep);
    	String[] current_rep_liste = r.list();
    	for (String f : current_rep_liste) {
			if (f.equals(file)) {
				return true;
			}
			
		}
    	return false;
	}
}
