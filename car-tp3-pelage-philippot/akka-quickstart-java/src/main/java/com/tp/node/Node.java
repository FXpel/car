/**
 * Class Node
 * @author phigr
 */
package com.tp.node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.tp.node.child.AddChilds;
import com.tp.node.child.MessageTransfer;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;


public class Node extends UntypedAbstractActor{
	// ATTIBUTS
	/* name of node */
	private final String nodeName;
	
	/* node's childs */
	private List<ActorRef> children = new ArrayList<ActorRef>();
	/**/
	private LoggingAdapter log = Logging.getLogger(getContext().getSystem(),this);
	
	// CONSTRUCTOR
	/**
	 * Constructor
	 * @param nodeName name of the node
	 */
	public Node(String nodeName) {
		this.nodeName = nodeName;
		this.children = new LinkedList<ActorRef>();
	}

	// METHODS
	/**
	 * Method to create a node
	 * @param childs children of the node
	 * @param nodeName name of the node
	 * @return return the creation of the node
	 */
	static public Props props(List<ActorRef> childs, String nodeName) {
		System.out.println("created \"" + nodeName );

		return Props.create(Node.class,()->new Node(nodeName));
	}
	
//	@Override
//	public Receive createReceive() {
//		return receiveBuilder().match(MessageTransfer.class, mc->{
//			System.out.println(mc.message + " || Sender : "+this.nodeName+"  || From "+getSender());
//			for(ActorRef c : this.childs) {
//				c.tell(new MessageTransfer(mc.message), getSelf());
//			}
//		}).match(AddChilds.class, ac->{
//			for(ActorRef aC : this.childs) {
//				this.childs.add(aC);
//			}
//		}).match(RemoveChild.class, rc->{
//			if(this.childs.contains(rc.child)) {
//				this.childs.remove(rc.child);
//			}
//		}).build();
//	}

	// GETTER AND SETTER
	/**
	 * @return the log
	 */
	public LoggingAdapter getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(LoggingAdapter log) {
		this.log = log;
	}

	/**
	 * @return the nodeName
	 */
	public String getNodeName() {
		return nodeName;
	}
	
	/**
	 * send the message to the children of the current node
	 * @param message the message to send
	 */
	public void sendMessageToChildren(MessageTransfer message) {
		for (ActorRef child : children) {
			child.forward(message, getContext());
		}
	}
	/**
	 * Methode wich process differently according to the instance:
	 * 	-if the message is an instance of MessageTransfer print the message
	 * 	-if the message is an instance of AddChilds add a child
	 */
	@Override
	public void onReceive(Object message) throws Throwable {
		if (message instanceof MessageTransfer) {
			MessageTransfer greetingsMessage = (MessageTransfer) message;
			if (!greetingsMessage.hasAlreadyVisited(getSelf())) {
				System.out.println(this.nodeName + " received \"" + greetingsMessage
						+ "\"");
				((MessageTransfer) message).addToVisited(getSelf());
				sendMessageToChildren(greetingsMessage);
			}
			return;
		}

		if (message instanceof AddChilds) {
			AddChilds addChild = (AddChilds) message;
			System.out.println(nodeName + " received \"" + addChild + "\"");
			children.add(addChild.child);
			return;
		}

		unhandled(message);
		
	}

}
