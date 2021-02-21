package fr.lille.univ.car.tp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.ServerSocket;

/**
* @autor pelage
*
*Implémentation du client
*
*
*/

public class Client implements Runnable {
	private static final String[] users = {"test"};
	private static final String[] passwords = {"0000"};
	private static final String root = System.getProperty("user.dir")+"\\reptest";
/*	private String current_rep = System.getProperty("user.dir");
*/    private static String current_rep = root;
    private BufferedReader in;
    private static PrintStream out; // out.println(...)
    private int pport;
    private ServerSocket psocket;
    private static String file_to_rename = "";
    
    public Client(Socket socket) throws IOException {
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintStream(socket.getOutputStream());
        this.psocket = new ServerSocket(0);
        this.pport = psocket.getLocalPort();
    }

    @Override
    public void run() {
        out.println("220 anonyme");
        String cmd;
        try {
	    	while ((cmd = in.readLine()) != null) {
	    		process2(cmd);
	    	}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private static void process(String request) throws IOException {
        System.out.println(request);       
    }
    
    public void process2 (String request) throws IOException {
    	String[] tab = request.split(" ");
    	String c = tab[0];
    	switch(c) {
    	case "USER":
    		String name = tab[1];
    		CommandUser cmd_user = new CommandUser();
    		cmd_user.tryUSer(out, users,name);
    		break;    		
    	case "ACCT":
    		out.println("230 password unrequired");
    		break;
    	case "PASS":
    		String pass = tab[1]; 
    		CommandPass cmd_pass = new CommandPass();
    		cmd_pass.tryPassword(out, passwords, pass);
    		//out.println("332 password needed");
    		break;
    	case "QUIT":
    		out.println("221 quit");
    		break;
    	case "PWD":
    		CommandPwd cmd_pwd = new CommandPwd();
    		cmd_pwd.displayCurrentRep(out, current_rep);
    		break;
    	case "CWD":
    		CommandCwd cmd_cwd = new CommandCwd();
    		System.out.println(current_rep + "\\" + tab[1] + " dans le client");
    		current_rep = cmd_cwd.changeRep(out, tab[1], current_rep,root);
    		break;
    	case "CDUP":
    		CommandCDUP cmd_cdup = new CommandCDUP();
    		
    		current_rep = cmd_cdup.toUpRep(out,current_rep,root);
    		//CommandCwd cmd_cwd = new CommandCwd();
    		//current_rep = cmd_cwd.changeRep(out, tab[1], current_rep);
    		break;
    	case "RETR":
    		//out.println("150 File status okay; about to open data connection.");
    		CommandRETR cmd_dwnld = new CommandRETR();
    		cmd_dwnld.copy(out,current_rep+"/"+tab[1],this.psocket);
    		break;
    	case "STOR":
    		CommandSTOR cmd_stor = new CommandSTOR();
    		File f = new File(tab[1]);
    		cmd_stor.newFile(out,tab[1],this.psocket,current_rep);
	    		break;
    	case "DELE":
    		String file = tab[1];
    		CommandDELE cmd_del = new CommandDELE();
    		cmd_del.delFile(out,current_rep+"\\"+file);
    		break;
    	case "RMD":
            String rep = tab[1];
            File frep = new File(rep);
            CommandRMD cmd_rmd = new CommandRMD();
            cmd_rmd.delFile(out,frep);
    		break;
    	case "SYST":
    		out.println("215 NOM de type de système");
    		break;
    	case "MKD":
    		CommandMKD cmd_mkd = new CommandMKD();
    		cmd_mkd.newRep(out, current_rep + "\\" + tab[1]);
    		break;
    	case "PASV":
    		out.println("227 Entering Passive Mode (127,0,0,1,"+(this.pport/256)+","+(this.pport % 256)+")");
    		break;
        case "EPSV":
            out.println( "227 Entering Extended Passive Mode (|||"+(this.pport)+"|)");
    		break;
    	case "LIST":
    		CommandList cmd_disp = new CommandList();
    		cmd_disp.printDir(out,current_rep,this.psocket);
    		break;
    	case "AUTH":
    		out.println("502 no TLS");
    		break;
    	case "TYPE":
    		out.println("200 ASCII");
    		break;
    	case "RNFR":
    		this.file_to_rename = tab[1];
    		out.println("350 ASCII");
    		break;
    	case "RNTO":
    		String new_name= tab[1];
    		CommandRNTO cmd_rnm = new CommandRNTO();
    		cmd_rnm.rename(out, current_rep + "\\" +file_to_rename,current_rep + "\\" + new_name);
    		out.println("200");
    		break;
    	default:
    		out.println("500 CONEECTED");
    	}
//    	case "GETB":
//    		break;
//    	case "GETB"
//    	case "GETR"
//    	case "PUTT"
//    	case "PUTB"
//    	case "PUTR"
//    	case "RENF"
//    	case "MKD"
    	
    }
    
    
    
     
        
    
}




