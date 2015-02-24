package com.neo4j.test;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import com.neo4j.test.labels.NodeLabels;
import com.neo4j.test.labels.TypeRelation;

public class Test1 {

	public static void main(String[] args) {

		GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
		GraphDatabaseService db = dbFactory.newEmbeddedDatabase("C:\\Zakaria\\NeoTests\\Test1");
		
		try (Transaction tx = db.beginTx()) {
			
			Node zakaria = db.createNode(NodeLabels.ZAKARIA);
			zakaria.setProperty("Nom", "BOULOUARD");
			zakaria.setProperty("Prénom", "Zakaria");
			
			Node bernard = db.createNode(NodeLabels.BERNARD);
			bernard.setProperty("Nom", "DOUSSET");
			bernard.setProperty("Prénom", "Bernard");
			
			Node anass = db.createNode(NodeLabels.ANASS);
			anass.setProperty("Nom", "EL HADDADI");
			anass.setProperty("Prénom", "Anass");
			
			Relationship encadrement_these1 = bernard.createRelationshipTo(anass, TypeRelation.ENCADREMENT_THESE);
			encadrement_these1.setProperty("Année de Soutenance", 2011);
			
			Relationship encadrement_these2 = anass.createRelationshipTo(zakaria, TypeRelation.ENCADREMENT_THESE);
			encadrement_these2.setProperty("Année de Soutenance", 2016);
			
			tx.success();
			
		}
		
		System.out.println("Done!");

	}

}
