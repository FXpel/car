package fr.lille.univ.car.tp1;

import java.io.PrintStream;
/**
*
*@autor pelage
*
* Implémentation de la commande USER vérifiant l'identifiant de l'utilisateur
*/
public class CommandUser {
	/**
	*méthode vérifiant si l'utilisateur est autorisé à se connecter au serveur
	*
	*@param out code réponse de la requête
	*@param user_ok liste des identifiant autorisé à se connecter
	*@param user_name identifiant à vérifier
	*
	*@return retournevrai si l'utilisateur est authorisé à se connecter au serveur, faux sinon
	*/
	public boolean tryUSer(PrintStream out,String[] users_ok,String user_name){
		
		
		for (int i = 0; i < users_ok.length; i++) {
			if(user_name.equals(users_ok[i])) {
				out.println("331"+ user_name +"demande mot de passe");
        		return true;
			}
		}
		out.println("430 invalid username or password");
		return false;
	}
	
}
