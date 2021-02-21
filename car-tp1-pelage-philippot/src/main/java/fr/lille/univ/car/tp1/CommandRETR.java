package fr.lille.univ.car.tp1;
import java.util.Scanner;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
* @autor pelage
*
*Implémentation de la commande RETR pour récupérer la copie d'un fichier
*
*
*/
public class CommandRETR {
    /**
    *méthode copiant un fichier
    *
    *@param out code réponse de la requête
    *@param path1 chemin de la source du fichier 
    *@param path2 chemin de la destination du fichier
    *
    *
    */
    public static boolean copy (PrintStream out,String path, ServerSocket tmp) throws IOException{
    	Socket s = tmp.accept();
    	PrintStream ps = new PrintStream(s.getOutputStream());
        File file_to_cpy = new File(path);
        if (file_to_cpy.exists()) {
//            Scanner inputFile = new Scanner(file_to_cpy);
//            PrintWriter outputFile = new PrintWriter(path2);
//            while (inputFile.hasNext()){
//                outputFile.println(inputFile.nextLine());
//        
//            }
//            outputFile.close(); 
//            inputFile.close();
        	FileInputStream fis = new FileInputStream(file_to_cpy.getAbsolutePath());
        	DataOutputStream dos = new DataOutputStream(ps);
        	int count;
        	byte[] buff = new byte[56000];
        	while((count = fis.read(buff))>0) {
        		ps.write(buff, 0, count);
        	}
            out.println("250 copy done");
            return true;
            
        }
        else{
            out.println("500 file doesn't exists");
        }
        s.close();
        return false;
        

        
    }
}
        
        

