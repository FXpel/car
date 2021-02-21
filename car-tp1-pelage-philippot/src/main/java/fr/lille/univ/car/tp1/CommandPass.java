package fr.lille.univ.car.tp1;

import java.io.PrintStream;
/*
* @autor pelage
*
*
*Implémentation de la commande pour la vérification du mot de passe
*/
public class CommandPass {
	/*
	*méthode permettant de vérifier le mot de passe rentré
	*
	*@param out code réponse de la requête
	*@param passwords lists des mots de passes valides
	*@param user_password mot de passe entré par l'utilisateur
	*
	*@return retourne vrai si le mot de passe est le bon, faux sinon
	*/
public boolean tryPassword(PrintStream out,String[] passwords,String user_password){
		
		
		for (int i = 0; i < passwords.length; i++) {
			if(user_password.equals(passwords[i])) {
        		out.println("220 valid password");
        		return true;
			}
		}
		out.println("430 invalid username or password");
		return false;
	}
}
