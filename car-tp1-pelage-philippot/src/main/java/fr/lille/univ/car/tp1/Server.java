package fr.lille.univ.car.tp1;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
/**
* @autor pelage
*
*Implémentation du serveur
*
*
*/
public class Server {
    
    private static int PORT = 1025;
//    private static String host = "127.0.0.1";
//    private String mdp = "motdepasse";
    
    public static void main(String... args) throws IOException {
    	if (args.length>1) {
    		PORT = Integer.parseInt(args[0]);
    	}
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connecté");
            new Thread(new Client(clientSocket)).start();

        }
    }
}
