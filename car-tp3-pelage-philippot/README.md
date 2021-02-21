# car-tp3-pelage-philippot

But du TP:
Le but de ce TP est de mettre en œuvre des acteurs répartis avec Akka.
On considère une application répartie qui permet de transférer en Akka des données à un
ensemble d’acteurs organisés selon une topologie en arbre. Chaque nœud de l’arbre propage les
données à ses fils. Par exemple, dans la figure ci-dessous, pour diffuser son message, l’acteur
1 l’envoie à 2 et 5, puis 2 l’envoie à 3 et 4 et 5 l’envoie à 6. Les messages sont des chaînes de
caractères.


compilation du projet : mvn compile

javadoc : mvn javadoc:javadoc

tests unitaires : mvn test

Ensuite executer le fichier car-tp3-pelage-philippot/akka-quickstart-java/src/main/java/com/tp/Main.java

Lors de l'execution appuyer sur la touche "entrée" afin de poursuivre l'éxécution et suivre le parcours des messages.

Nous avons déjà dans cette éxécution une répattition des acteurs sur deux système. d'acteurs différents.

Et juste avant la fermeture des système, que la création d'un arc permet bien le bon transfert du message (arc entre les noeuds 4 et 6)
