/**
 * Class Main
 * in method main, you have 2 options :
 * - 1 generate the nodes from a xml file and test
 * - 2 create nodes and make test
 * after this you can continue to modifies the nodes
 * @author phigr
 */
package com.tp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.tp.factory.ActorFactory;
import com.tp.node.child.AddChilds;
import com.tp.node.child.MessageTransfer;
import com.tp.node.child.RemoveChild;
import com.tp.parser.XMLParser;
import com.tp.node.Node;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;


public class Main {
	// ATTRIBUTS
	/**/
	protected static ActorSystem system;
	/**/
	private static HashMap<String, ActorRef> map;
	/**/
	private final static String commands = "List of commands :\n - HELP\n - MSG\n - QUIT\n - ADDNODE {node name} [node name parent]\n - ADDLINK {node name} {node name child}\n - RMNODE {node name}\n - RMLINK {node name parent} {node name child}";
	/**/
	private final static String help = " - HELP : displays the commands that the user can enter with the expected parameters \n";
	private final static String quit = " - QUIT : stop program execution \n";
	private final static String msg = "- MSG : message to transfer to nodes -> {MSG} [message] \n";
	private final static String addN = "- ADDNODE : add a node -> {ADDNODE} {node name} [node name parent] \n";
	private final static String addL = "- ADDLINK : add a link -> {ADDLINK} {node name} {node name child} \n";
	private final static String rmN = "- RMNODE : remove a node -> {RMNODE} {node name} \n";
	private final static String rmL = "- RMLINK : remove a link -> {RMLINK} {node name parent} {node name child} \n";
	private final static String display = " - DISPLAY : displays the nodes \n";
	private final static String completed = "Done !";
	private final static String missing = "Missing arguments : ";
	
	/**
	 * Constructor
	 * @param systemName name of the current system
	 */
	public Main(String systemName) {
		this.system = ActorSystem.create(systemName);
		System.out.println("created \"" + system.name() + "\" system");
	}
	// METHDOS
	/**
	 * 
	 */
	public static void helpCommands() {
		String display = "Display informations of commands : \n";
		System.out.println(display+help+quit+msg+addN+addL+rmN+rmL+completed);
	}
	/**
	 * Method to shutdown the current system
	 */
	public void turnoff() {
		this.system.terminate();
		System.out.println(this.system.name() + " has been shutdown");
	}
	
	/**
	 * 
	 */
	public static void promptCommands() {
		
	}
	
	// MAIN
	/**
	 * 
	 * @param args
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public static void main(String[] args) throws IOException {
		ActorRef noeud1, noeud2, noeud3, noeud4, noeud5, noeud6;
		Main sys1 = new Main("sys1");
		Main sys2 = new Main("sys2");
		map = new HashMap<String, ActorRef>();
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("enter 1 to using configuration from \"./src/main/resources/node.xml\" with only one system or enter anything else to use an application with 2 systems");
	    

	    int mode = myObj.nextInt();// Read user input
	    System.out.println("mode is: " + mode);  // Output user input
	    
	    if(mode == 1) {
			ActorRef tmp_node_parent,tmp_node_child;
			XMLParser p = new XMLParser("./src/main/resources/node.xml");
			ArrayList<String> actN = (ArrayList<String>) p.generatorNodeListFromXML();
			
			for(String n : actN) {
				if(!map.containsKey(n)) {
					tmp_node_parent = sys1.createNode(n);
					map.put(n, tmp_node_parent);
				}
				else {
					tmp_node_parent = map.get(n);
				}
				
				//ActorRef a = map.get(n);
				ArrayList<String> c = (ArrayList<String>) p.getChildsFromNode(n);
				System.out.println(c.isEmpty());
				//system.actorOf(Props.create(Node.class, n), n);
				//ActorFactory.appendChilds(tmp_node_parent, c, map);
				if(!c.isEmpty()){
					for (String child : c) {
						if(!map.containsKey(child)) {
							tmp_node_child = sys1.createNode(child);
							addChild(tmp_node_parent, tmp_node_child);
							map.put(child, tmp_node_child);
						}
						else {
							addChild(tmp_node_parent, map.get(child));
						}
						
					}
				}
				
				
			}
			waitInput();
			System.out.println("/////////////////////////////////");
			System.out.println("nodes have been created");
			System.out.println("/////////////////////////////////");
			
			System.out.println("/////////////////////////////////");
			System.out.println("send a message from 1 and all nodes should receive since 1 is the root");
			System.out.println("/////////////////////////////////");
			map.get("1").tell(new MessageTransfer("message from 1"), ActorRef.noSender());
			
			
			System.out.println("/////////////////////////////////");
			System.out.println("sends message from 2 here 2, 3 and 4 should receive");
			System.out.println("/////////////////////////////////");

			map.get("2").tell(new MessageTransfer("message from 2"), ActorRef.noSender());
			waitInput();
			System.out.println("/////////////////////////////////");
			System.out.println("Q5 : We are adding an edge between 4 and 6");
			addNeighbour(map.get("4"), map.get("6"));
			waitInput();

			map.get("1").tell(new MessageTransfer("second message from 1"), ActorRef.noSender());
			waitInput();

			System.out.println("sends message from 2 here 2, 3, 4 and 6 should receive");

			map.get("2").tell(new MessageTransfer("second message from 2"), ActorRef.noSender());
			waitInput();
			
	    }
	    else {
			noeud1 = sys1.createNode("noeud1");
			map.put("1", noeud1);
			noeud2 = sys2.createNode("noeud2");
			addChild(noeud1, noeud2);
			
			noeud3 = sys1.createNode("noeud3");
			addChild(noeud2, noeud3);
	
			noeud4 = sys2.createNode("noeud4");
			addChild(noeud2, noeud4);
	
			noeud5 = sys2.createNode("noeud5");
			addChild(noeud1, noeud5);
	
			noeud6 = sys1.createNode("noeud6");
			addChild(noeud5, noeud6);
			waitInput();
			System.out.println("/////////////////////////////////");
			System.out.println("nodes have been created");
			System.out.println("/////////////////////////////////");
			
			System.out.println("/////////////////////////////////");
			System.out.println("send a message from 1 and all nodes should receive since 1 is the root");
			System.out.println("/////////////////////////////////");
			noeud1.tell(new MessageTransfer("message from 1"), ActorRef.noSender());
			waitInput();
			System.out.println("/////////////////////////////////");
			System.out.println("nodes have been created");
			System.out.println("/////////////////////////////////");
			
			
			
			System.out.println("/////////////////////////////////");
			System.out.println("send a message from 1 and all nodes should receive since 1 is the root");
			System.out.println("/////////////////////////////////");

			noeud1.tell(new MessageTransfer("message from 1"), ActorRef.noSender());
			waitInput();
			
			System.out.println("/////////////////////////////////");
			System.out.println("sends message from 2 here 2, 3 and 4 should receive");
			System.out.println("/////////////////////////////////");

			noeud2.tell(new MessageTransfer("message from 2"), ActorRef.noSender());
			waitInput();
			
			System.out.println("/////////////////////////////////");
			System.out.println("Q5 : We are adding an edge between 4 and 6");
			addNeighbour(noeud4, noeud6);
			waitInput();

			noeud1.tell(new MessageTransfer("second message from 1"), ActorRef.noSender());
			waitInput();

			System.out.println("sends message from 2 here 2, 3, 4 and 6 should receive");

			noeud2.tell(new MessageTransfer("second message from 2"), ActorRef.noSender());
			waitInput();
	    }
		
	    System.out.println("/////////////////////////////////");
		System.out.println(commands);
		//promptCommands();
		Scanner input = new Scanner(System.in);
		while(true) {
			System.out.print("Prompt your command : ");
			String l = input.nextLine();
			String[] cmd = l.split(" ");
			switch(cmd[0].toUpperCase()) {
				case "QUIT":
				    sys2.turnoff();
				    sys1.turnoff();
					input.close();
					system.terminate();
					System.exit(0);
				case "HELP":
					helpCommands();
					break;
				case "MSG":
					if(cmd.length < 2) {
						System.out.println(missing+msg);
					}else {
						String message = "";
						for(String c : cmd) {
							message += c;
						}
						String node = null;
						if(!cmd[cmd.length-1].contains(".node")) {
							message += " "+cmd[cmd.length-1];
						}else {
							node = cmd[cmd.length-1];
							node = node.substring(0,node.length()-5);
						}
						ActorRef toSend = map.get(node);
						if(toSend == null) {
							System.out.println("Unknow node, using a random node");
							List<Entry<String,ActorRef>> list = new ArrayList<>(map.entrySet());
							toSend = list.get(0).getValue();
						}
						toSend.tell(new MessageTransfer(message), ActorRef.noSender());
						System.out.println(completed);
					}
					break;
				case "ADDNODE":
					if(cmd.length < 2) {
						System.out.println(missing+addN);
					}else {
						ActorFactory.addActorReference(cmd[1], system, map);
						if(cmd.length > 2) {
							ArrayList<String> childs = new ArrayList<>();
							ActorRef act = map.get(cmd[2]);
							childs.add(cmd[1]);
							ActorFactory.appendChilds(act, childs, map);
						}
						System.out.println(completed);
					}
					break;
				case "ADDLINK":
					if(cmd.length < 3) {
						System.out.println(missing+addL);
					}else {
						ActorRef act = map.get(cmd[1]);
						ArrayList<String> childs = new ArrayList<>();
						childs.add(cmd[2]);
						ActorFactory.appendChilds(act, childs, map);
						System.out.println(completed);
					}
					break;
				case "RMNODE":
					if(cmd.length < 2) {
						System.out.println(missing+rmN);
					}else {
						ActorRef act = map.get(cmd[1]);
						Iterator<Entry<String,ActorRef>> iterator = map.entrySet().iterator();
						while(iterator.hasNext()) {
							Map.Entry<String, ActorRef> actP = (Map.Entry<String, ActorRef>) iterator.next();
							ActorRef actC = actP.getValue();
							actC.tell(new RemoveChild(act), ActorRef.noSender());
						}
						map.remove(cmd[1]);
						System.out.println(completed);
					}
					break;
				case "RMLINK":
					if(cmd.length < 3) {
						System.out.println(missing+rmL);
					}else {
						ActorRef act = map.get(cmd[1]);
						act.tell(new RemoveChild(map.get(cmd[2])), ActorRef.noSender());
						System.out.println(completed);
					}
					break;
				case "DISPLAY":
					break;
				default:
					System.out.println(commands);
					break;
			}
		}
	}
	
	/**
	 * 
	 * @param args no args needed
	 * @throws IOException exception of waitInput()
	 */
//	public static void main(String[] args) throws IOException {
//		ActorRef noeud1, noeud2, noeud3, noeud4, noeud5, noeud6;
//		Main sys1 = new Main("sys1");
//		Main sys2 = new Main("sys2");		
//		waitInput();
//
//		noeud1 = sys1.createNode("noeud1");
//
//		noeud2 = sys2.createNode("noeud2");
//		addChild(noeud1, noeud2);
//		
//		noeud3 = sys1.createNode("noeud3");
//		addChild(noeud2, noeud3);
//
//		noeud4 = sys2.createNode("noeud4");
//		addChild(noeud2, noeud4);
//
//		noeud5 = sys2.createNode("noeud5");
//		addChild(noeud1, noeud5);
//
//		noeud6 = sys1.createNode("noeud6");
//		addChild(noeud5, noeud6);
//		waitInput();
//
//		System.out.println("/////////////////////////////////");
//		System.out.println("nodes have been created");
//		System.out.println("/////////////////////////////////");
//		
//		System.out.println("/////////////////////////////////");
//		System.out.println("send a message from 1 and all nodes should receive since 1 is the root");
//		System.out.println("/////////////////////////////////");
//
//		noeud1.tell(new MessageTransfer("message from 1"), ActorRef.noSender());
//		waitInput();
//		
//		System.out.println("/////////////////////////////////");
//		System.out.println("sends message from 2 here 2, 3 and 4 should receive");
//		System.out.println("/////////////////////////////////");
//
//		noeud2.tell(new MessageTransfer("message from 2"), ActorRef.noSender());
//		waitInput();
//
//
//		System.out.println("/////////////////////////////////");
//		System.out.println("Q5 : We are adding an edge between 4 and 6");
//		addNeighbour(noeud4, noeud6);
//		waitInput();
//
//		noeud1.tell(new MessageTransfer("second message from 1"), ActorRef.noSender());
//		waitInput();
//
//		System.out.println("sends message from 2 here 2, 3, 4 and 6 should receive");
//
//		noeud2.tell(new MessageTransfer("second message from 2"), ActorRef.noSender());
//		waitInput();
//		sys1.turnoff();
//		sys2.turnoff();
//	}
	
	/**
	 * Method to create an edge between both nodes
	 * @param node1 first node
	 * @param node2 second node
	 */
	public static void addNeighbour(ActorRef node1, ActorRef node2) {
		if (node1 == null) {
			System.err.println("can't add edge, node1 is null");
		}
		if (node2 == null) {
			System.err.println("can't add edge, node2 is null");
		}

		node1.tell(new AddChilds(node2), node1);
		node2.tell(new AddChilds(node1), node2);
		System.out.println("neighbour added");
	}
	/**
	 * Method to temporise the program
	 * @throws IOException exception
	 */
	public static void waitInput() throws IOException {
		System.out.println("Press enter to continue");
		System.in.read();
	}
	/**
	 * Method to Create a node in the current system
	 * @param name name of the node
	 * @return creation of the node
	 */
	public ActorRef createNode(String name) {
		System.out.println("created \"" + name + "\" node ");
		return system.actorOf(Props.create(Node.class, name), name);
	}
	/**
	 * Method to associate a child to a parent node
	 * @param parent parent of the node
	 * @param child node to add
	 */
	public static void addChild(ActorRef parent, ActorRef child) {
		if (parent == null) {
			System.err.println("parent is null or doesn't exist");
		}
		if (child == null) {
			System.err.println("child is null or doesn't exist");
		}
		parent.tell(new AddChilds(child), parent);
		System.out.println("child has been added");
	}
	/**
	 * @return the map
	 */
	public static HashMap<String, ActorRef> getMap() {
		return map;
	}
	
	

	/**
	 * @param map the map to set
	 */
	public static void setMap(HashMap<String, ActorRef> map) {
		Main.map = map;
	}

	/**
	 * @return the sys
	 */
	public static ActorSystem getSys() {
		return system;
	}
}
