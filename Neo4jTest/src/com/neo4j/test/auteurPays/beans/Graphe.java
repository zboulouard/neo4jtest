package com.neo4j.test.auteurPays.beans;

import java.util.List;


public class Graphe {

	private List<Noeud> nodes;
	private List<Lien> links;
	
	public Graphe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Graphe(List<Noeud> nodes, List<Lien> links) {
		super();
		this.nodes = nodes;
		this.links = links;
	}

}
