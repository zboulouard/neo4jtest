package com.neo4j.test.auteurPays;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import com.neo4j.test.auteurPays.labels.NodeLabels;
import com.neo4j.test.auteurPays.labels.TypeRelation;

public class AuteurPays {

	public static void main(String[] args) {

		GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
		GraphDatabaseService db = dbFactory.newEmbeddedDatabase("C:\\Zakaria\\NeoTests\\AuteurPays");
		
		try (Transaction tx = db.beginTx()) {
			
			Node maroc = db.createNode(NodeLabels.PAYS);
			maroc.setProperty("Name", "Maroc");
			
			Node france = db.createNode(NodeLabels.PAYS);
			france.setProperty("Name", "France");
			
			Node zakaria = db.createNode(NodeLabels.AUTEUR);
			zakaria.setProperty("Nom", "BOULOUARD");
			zakaria.setProperty("Prénom", "Zakaria");
			
			Node bernard = db.createNode(NodeLabels.AUTEUR);
			bernard.setProperty("Nom", "DOUSSET");
			bernard.setProperty("Prénom", "Bernard");
			
			Node anass = db.createNode(NodeLabels.AUTEUR);
			anass.setProperty("Nom", "EL HADDADI");
			anass.setProperty("Prénom", "Anass");
			
			Relationship app1 = zakaria.createRelationshipTo(maroc, TypeRelation.APPARTENANCE);
			app1.setProperty("Oriented", true);
			app1.setProperty("Weigtht", 1);
			app1.setProperty("Date", 1988);
			
			Relationship coll1 = zakaria.createRelationshipTo(france, TypeRelation.COLLABORATION);
			coll1.setProperty("Oriented", false);
			coll1.setProperty("Weight", 3);
			coll1.setProperty("Date", 2015);
			
//			List<Integer> l1 = new ArrayList<Integer>();
//			l1.add(2006);
//			l1.add(2010);
//			l1.add(2015);
//			
//			Relationship coll2 = bernard.createRelationshipTo(maroc, TypeRelation.COLLABORATION);
//			coll2.setProperty("Oriented", false);
//			coll2.setProperty("Weight", 3);
//			coll2.setProperty("Date", l1);
			
			tx.success();
			
		}
		
		System.out.println("Done!");

	}

}
