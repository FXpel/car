java -jar target/tp1-1.0-SNAPSHOT.jar
Connecté
# CAR TP1 skeleton

Commande maven :
  * $ mvn package -> compile le projet
  * $ mvn clean ->
  * $ mvn test -> exécute les tests
  * $

Scénarii :
1. lancement du serveur FTP
  -> envoi du code réponse 332 'Compte utilisateur demandé'
2. l'utilisateur saisi son nom d'utilisateur
2. a) l'utilisateur saisi "USER [user]"
   a) i. [user] est reconnu
    -> envoi du code réponse 331 'Nom d'utilisateur reçu, mot de passe demandé'
    -> envoi du code réponse 200 'Commande conclue'
   a) ii. [user] n'est pas reconnu
    -> envoi du code réponse 530 'Session non ouverte' spécification 'Nom d'utilisateur inconnu'
   b) l'utilisateur effectue une erreur dans la commande "USER"
    -> envoi du code réponse 500 'Erreur de syntaxe, commande non reconnue. Inclut le cas d'une ligne de commande trop longue.' spécification '[cmd] non reconnue'
3. l'utilisateur a mis un bon [user]
4. l'utilisateur saisi son mot de passe
4. a) l'utilisateur saisi "PASS [pwd]"
   a) i. [pwd] est reconnu
    -> envoi du code réponse 200 'Commande conclue'
    -> envoi du code réponse 230 'Session ouverte'
   a) ii. [pwd] n'est pas reconnu
    -> envoi du code réponse 530 'Session non ouverte' spécification 'Mot de passe inconnu'
   b) l'utilisateur effectue une erreur dans la commande "USER"
    -> envoi du code réponse 500 'Erreur de syntaxe, commande non reconnue. Inclut le cas d'une ligne de commande trop longue.' spécification '[cmd] non reconnue'
5. l'utilisateur est bien connecté
5. a)
6.
7.
8.

Listes des commandes à implémenter :
- USER username : utilisé pour l’authentification de l’utilisateur
- PASS password : utilisé pour le mot de passe de l’utilisateur
- LIST : permet à l’utilisateur de demander l’envoi de la liste des fichiers du répertoire courant
- RETR filename : utilisé pour prendre un fichier du répertoire distant et le déposer dans le répertoire local
- STOR filename : utilisé pour déposer un fichier venant du répertoire local dans le répertoire distant
- QUIT : permet à l’utilisateur de terminer la session FTP en cours
- PWD : permet à l’utilisateur de connaître la valeur du répertoire de travail distant.
- CWD directory : permet à l’utilisateur de changer de répertoire de travail distant.
- CDUP : cette commande est équivalente à CWD ..

Listes des codes réponses à utiliser :
-

Problème :
 problème lors de la suppression d'un répertoire avec le cwd. Le dossier se supprime mais cwd ne se remet pas au bon endroit
 
