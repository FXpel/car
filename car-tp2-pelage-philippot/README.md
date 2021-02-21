Pelage François-Xavier
Philippot Grégoire

### Commandes :

* Création de la doc ->  $mvn javadoc:javadoc
* Compilation -> $mvn compile
* Exécution -> $mvn exec:java
* Clean -> $mvn clean


Lors de la connexion:
	-il faut tout d'abord lancer le serveur à l'aide du fichier python server.py en utilisant la commande: 
		python server.py 
	-ensuite, pour lancer note passerelle REST, il suffit taper les commandes suivantes :
		-pour compiler : mvn clean compiler 
		-pour executer : mvn exec:java
	-un message s'affiche pour choisir le port :
		-entrez 0 pour le port de base "8080"
		- ou alors entrez un port non utilisé entre 1024 65535
	-ensuite un message s'affiche pour choisir l'url:
		-appuyez directement sur entré pour utilisez l'url de base : "http://localhost:8080/myapp/"
		-ou entrez l'url de votre choix en terminant par '/'

La connexion au serveur est alors faites (à l'inverse de linux sur windows la commande authentification ne fonctionne pas, il faut décommenter la ligne 119 du fichier Main.java afin de lancer la connexion au début.).
Vous pouvez utilser : user:12345, user2:12345 ou anonymous:anonymous
comme <identifiant>:<motdepasse> pour vous identifier

voici quelques exemple de commandes permettant de comprendre le fonctionnement de notre passerelle :
	-s'identifier:
		curl -v -d 'login=user&psw=12345'  'http://localhost:8080/myapp/myresource/auth'





	-se déplacer dans un répertoire:
		curl -v http://localhost:8080/myapp/myresource2/home/.idea/

	-remonter dans le répertoire parent
		(sur navigateur cliquer sur les  petits point)

	-télécharger un fichier 
		curl -v http://localhost:8080/myapp/myresource2/download/image/index.jpeg

	-renomer un fichier:
		curl -v http://localhost:8080/myapp/myresource2/rename:salut,testfile 

	-stocker un fichier dans le répertoire courant:
		curl -v -d 'fileName=Membre.png&path=/home/m1/pelage/Pictures'  'http://localhost:8080/myapp/myresource2/upload'


	-supprimer un fichierdu répertoire courant:
		curl -v http://localhost:8080/myapp/myresource2/dele:todele.txt  

	-supprimer un dossier du repertoire courant:
		curl -v http://localhost:8080/myapp/myresource2/rmd:todele.txt
 
	-créer un dossier:
		curl -v http://localhost:8080/myapp/myresource2/mkd:dossiertest 
