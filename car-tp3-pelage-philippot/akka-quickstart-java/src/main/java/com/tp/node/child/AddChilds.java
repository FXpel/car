/**
 * Class AddChilds 
 * @author phigr
 */
package com.tp.node.child;

import java.io.Serializable;
import java.util.List;

import akka.actor.ActorRef;

public class AddChilds implements Serializable{
	private static final long serialVersionUID = 1704626346614851031L;
	// ATTRIBUTS
	/**/
	public final List<ActorRef> childs;
	public ActorRef child;
	
	// CONSTRUCTOR
	/**
	 * Constructor
	 * @param c list of childs
	 */
	public AddChilds(List<ActorRef> c) {
		this.childs = c;
	}
	//METHODS
	/**
	 * Constructor
	 * @param child child to add
	 */
	public AddChilds (ActorRef child) {
		this.childs = null;
		this.child = child;
	}
	/**
	 * description of the action
	 */
	public String toString() {
		return "Action : add child";
	}
}
