package com.example.tp.node;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.tp.node.Node;
import com.tp.node.child.AddChilds;
import com.tp.node.child.MessageTransfer;
import com.tp.node.child.RemoveChild;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class TestNode {
	@Test
	synchronized public void testTransfertMessageToOneChild() {
		ActorSystem sys = ActorSystem.create();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		String message = "child";
		ActorRef actChild = sys.actorOf(Node.props(null, message));
		List<ActorRef> childs = new ArrayList<>();
		childs.add(actChild);
		ActorRef act = sys.actorOf(Node.props(childs, "childs"));
		act.tell(new MessageTransfer("message"), ActorRef.noSender());
		try {
			wait(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String actualMessage = out.toString();
		assertTrue(actualMessage.contains("child"));
	}
	
	@Test
	synchronized public void testTransfertMessageToMultipleChild() {
		ActorSystem sys = ActorSystem.create();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		String message1 = "child1";
		String message2 = "child2";
		ActorRef actChild1 = sys.actorOf(Node.props(null, message1));
		ActorRef actChild2 = sys.actorOf(Node.props(null, message2));
		List<ActorRef> childs = new ArrayList<>();
		childs.add(actChild1);
		childs.add(actChild2);
		ActorRef act = sys.actorOf(Node.props(childs, "childs"));
		act.tell(new MessageTransfer("message"), ActorRef.noSender());
		try {
			wait(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String actualMessage = out.toString();
		assertTrue(actualMessage.contains("child1"));
		assertTrue(actualMessage.contains("child2"));
	}
	
	@Test
	synchronized public void testTransferMessageToNoChild() {
		ActorSystem sys = ActorSystem.create();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		String message = "child";
		ActorRef actChild = sys.actorOf(Node.props(null, message));
		actChild.tell(new MessageTransfer("message"),ActorRef.noSender());
		try {
			wait(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String actualMessage = out.toString();
		assertTrue(actualMessage.contains("child"));
	}
	
	@Test
	synchronized public void testAddChild() {
		ActorSystem sys = ActorSystem.create();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		String message = "child";
		ActorRef actChild = sys.actorOf(Node.props(null, message));
		List<ActorRef> childs = new ArrayList<>();
		childs.add(actChild);
		ActorRef act = sys.actorOf(Node.props(childs, "childs"));
		act.tell(new AddChilds(childs), ActorRef.noSender());
		act.tell(new MessageTransfer("message"), ActorRef.noSender());
		try {
			wait(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String actualMessage = out.toString();
		assertTrue(actualMessage.contains("child"));
	}
	
	@Test
	synchronized public void testRemoveChild() {
		ActorSystem sys = ActorSystem.create();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		String message = "child";
		ActorRef actChild = sys.actorOf(Node.props(null, message));
		List<ActorRef> childs = new ArrayList<>();
		childs.add(actChild);
		ActorRef act = sys.actorOf(Node.props(childs, "childs"));
		act.tell(new RemoveChild(actChild), ActorRef.noSender());
		act.tell(new MessageTransfer("message"), ActorRef.noSender());
		try {
			wait(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String actualMessage = out.toString();
		assertTrue(actualMessage.contains("child"));
	}
	
}
