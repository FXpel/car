/**
 * Class MessageTransfer
 * @author phigr
 */
package com.tp.node.child;

import java.util.LinkedList;
import java.util.List;

import akka.actor.ActorRef;

public class MessageTransfer {
	// ATTRIBUTS
	/**/
	public final String message;
	public List<ActorRef> visitedActors;

	
	// CONSTRUCTOR
	/**
	 * Constructor
	 * @param msg message to transfert
	 */
	public MessageTransfer(String msg) {
		this.message = msg;
		this.visitedActors = new LinkedList<ActorRef>();

	}
	
	/**
	 * Returns a String representing the object
	 * @return a String representing the object
	 */
	public String toString() {
		return message;
	}

	/**
	 * Adds an ActorRef to the list of visited ActorRef (if it has not already been visited)
	 * @param actor the actor to add
	 * @return true if the actor has been successfully added, false otherwise
	 */
	public boolean addToVisited(ActorRef actor) {
		if(!visitedActors.contains(actor))
			return visitedActors.add(actor);
		return false;
	}
	
	/**
	 * Checks if an ActorRef has already been visited
	 * @param actor the actor to be checked
	 * @return true if the actor has already been visited, false otherwise
	 */
	public boolean hasAlreadyVisited(ActorRef actor) {
		return visitedActors.contains(actor);
	}
}
