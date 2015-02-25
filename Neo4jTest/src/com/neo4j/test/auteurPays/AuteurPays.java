package com.neo4j.test.auteurPays;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.neo4j.test.auteurPays.labels.NodeLabels;
import com.neo4j.test.auteurPays.labels.TypeRelation;

public class AuteurPays {

	public static void main(String[] args) throws IOException {

		GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
		GraphDatabaseService db = dbFactory.newEmbeddedDatabase("C:\\Zakaria\\NeoTests\\AuteurPays");
		
		Gson gson = new GsonBuilder()
							        .disableHtmlEscaping()
							        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
							        .setPrettyPrinting()
							        .serializeNulls()
							        .create();
		
		Writer writer = new FileWriter("sources\\Output.json");
		
		List<Node> nodes = new ArrayList<Node>();
		List<Relationship> links = new ArrayList<Relationship>();
		
		try (Transaction tx = db.beginTx()) {
			
			Node maroc = db.createNode(NodeLabels.PAYS);
			maroc.setProperty("name", "Maroc");
			maroc.setProperty("group", 1); //couleur
			
			nodes.add(maroc);
			
			Node france = db.createNode(NodeLabels.PAYS);
			france.setProperty("name", "France");
			france.setProperty("group", 1);
			
			nodes.add(france);
			
			Node zakaria = db.createNode(NodeLabels.AUTEUR);
			zakaria.setProperty("name", "Zakaria");
			zakaria.setProperty("group", 2);
			
			nodes.add(zakaria);
			
			Node bernard = db.createNode(NodeLabels.AUTEUR);
			bernard.setProperty("name", "Bernard");
			bernard.setProperty("group", 2);
			
			nodes.add(bernard);
			
			Node anass = db.createNode(NodeLabels.AUTEUR);
			anass.setProperty("name", "Anass");
			anass.setProperty("group", 2);
			
			nodes.add(anass);
			
			Relationship app1 = zakaria.createRelationshipTo(maroc, TypeRelation.APPARTENANCE);
			app1.setProperty("source", zakaria.getId());
			app1.setProperty("target", maroc.getId());
			app1.setProperty("Oriented", true);
			app1.setProperty("value", 1); //poids
			app1.setProperty("Date", 1988);
			
			links.add(app1);
			
			Relationship coll1 = zakaria.createRelationshipTo(france, TypeRelation.COLLABORATION);
			coll1.setProperty("source", zakaria.getId());
			coll1.setProperty("target", france.getId());
			coll1.setProperty("Oriented", false);
			coll1.setProperty("value", 1);
			coll1.setProperty("Date", 2015);
			
			links.add(coll1);
			
			List<Integer> l1 = new ArrayList<Integer>();
			l1.add(2006);
			l1.add(2010);
			l1.add(2015);
			
			Relationship coll2 = bernard.createRelationshipTo(maroc, TypeRelation.COLLABORATION);
			coll1.setProperty("source", zakaria.getId());
			coll1.setProperty("target", france.getId());
			coll2.setProperty("Oriented", false);
			coll2.setProperty("value", 3);
			coll2.setProperty("Date", l1.toString());
			
			links.add(coll2);
			
			tx.success();
			
		}
		
		System.out.println("Done!");

	}

}
