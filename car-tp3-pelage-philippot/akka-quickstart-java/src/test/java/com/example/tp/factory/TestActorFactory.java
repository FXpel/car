package com.example.tp.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.tp.factory.ActorFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class TestActorFactory {
	@Test
	public void testCreateActorReferences() {
		ActorSystem sys = ActorSystem.create();
		List<String> names = new ArrayList<>();
		names.add("1");
		names.add("2");
		Map<String,ActorRef> map = ActorFactory.createActorReferences(names,sys);
		assertEquals(map.size(),2);
		assertTrue(map.containsKey("1"));
		assertTrue(map.containsKey("2"));
	}
	
	@Test
	public void testAddActorReference() {
		ActorSystem sys = ActorSystem.create();
		Map<String,ActorRef> map = new HashMap<>();
		assertFalse(map.containsKey("name"));
		ActorFactory.addActorReference("name", sys, map);
		assertTrue(map.containsKey("name"));
	}
	
	/*@Test
	public void testAppendChilds() {
		ActorSystem sys = ActorSystem.create();
		List<String> names = new ArrayList<>();
		names.add("1");
		names.add("2");
		Map<String,ActorRef> map = ActorFactory.createActorReferences(names,sys);
		ActorRef act;
		List<String> childs = new ArrayList<>();
	}*/
}
