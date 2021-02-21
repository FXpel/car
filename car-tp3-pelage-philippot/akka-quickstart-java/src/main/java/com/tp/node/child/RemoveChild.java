/**
 * Class RemoveChild
 * @author phigr
 */
package com.tp.node.child;

import akka.actor.ActorRef;

public class RemoveChild {
	// ATTRIBUTS
	/**/
	public ActorRef child;
	
	// CONSTRUCTOR
	public RemoveChild(ActorRef c) {
		this.child = c;
	}
}
