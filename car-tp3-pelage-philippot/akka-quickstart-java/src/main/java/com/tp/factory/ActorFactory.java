/**
 * Class ActorFactory 
 * @author phigr
 */
package com.tp.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tp.node.Node;
import com.tp.node.child.AddChilds;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class ActorFactory {
	/**
	 * 
	 * @param names names
	 * @param sys system
	 * @return a map
	 */
	public static Map<String, ActorRef> createActorReferences(List<String> names, ActorSystem sys){
		HashMap<String, ActorRef> map = new HashMap<>();
		for(String n : names) {
			ActorRef a = sys.actorOf(Node.props(null, n));
			map.put(n, a);
		}
		return map;
	}
	
	/**
	 * 
	 * @param n name
	 * @param sys systeme
	 * @param map a map
	 */
	public static void addActorReference(String n, ActorSystem sys, Map<String, ActorRef> map) {
		ActorRef a = sys.actorOf(Node.props(null, n));
		map.put(n, a);
	}
	
	/**
	 * 
	 * @param act node
	 * @param children  children
	 * @param map a mapping
	 */
	public static void appendChilds(ActorRef act, List<String> children, Map<String, ActorRef> map) {
		ArrayList<ActorRef> a = new ArrayList<>();
		for(String c : children) {
			if(map.containsKey(c)) {
				a.add(map.get(c));
			}
		}
		act.tell(new AddChilds(a), act);
		System.out.println("sent message to add child");
	}
}
