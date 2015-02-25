package com.neo4j.test.auteurPays.beans;


public class Graphe {

	private String nodes;
	private String links;
	
	public Graphe(String nodes, String links) {
		super();
		this.nodes = nodes;
		this.links = links;
	}

	@Override
	public String toString() {
		return "Graphe [nodes=" + nodes + ", links=" + links + "]";
	}
	
	
	
}
