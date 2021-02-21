package fr.lille.univ.car.tp1;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
/*
*
*@autor pelage
*
* Implémentation de la commande ? créant un nouveau fichier
*/
public class CommandSTOR {
	/**
	*méthode créant un fichier
	*
	*@param out code réponse de la requête
	*@param path chemin du fichier à créer
	*
	*@throws IOException si le fichier n'existe pas
	*/
	public boolean newFile(PrintStream out,String path, ServerSocket tmp,String current_rep)throws IOException{
		
		Socket s = tmp.accept();
    	InputStream ps = s.getInputStream();
        File file_to_cpy = new File(path);

        if (!file_to_cpy.exists()) {
//            Scanner inputFile = new Scanner(file_to_cpy);
//            PrintWriter outputFile = new PrintWriter(path2);
//            while (inputFile.hasNext()){
//                outputFile.println(inputFile.nextLine());
//        
//            }
//            outputFile.close(); 
//            inputFile.close();
        	FileOutputStream fos = new FileOutputStream(current_rep + "/" + file_to_cpy);
        	DataInputStream dis = new DataInputStream(ps);
        	int count;
        	byte[] buff = new byte[56000];
        	while((count = dis.read(buff))>0) {
        		fos.write(buff, 0, count);
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
