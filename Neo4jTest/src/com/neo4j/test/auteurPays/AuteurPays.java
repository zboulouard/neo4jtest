package com.neo4j.test.auteurPays;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.neo4j.test.auteurPays.beans.Graphe;
import com.neo4j.test.auteurPays.beans.Lien;
import com.neo4j.test.auteurPays.beans.Noeud;
import com.neo4j.test.auteurPays.labels.NodeLabels;
import com.neo4j.test.auteurPays.labels.TypeRelation;

public class AuteurPays {

	public static void main(String[] args) throws IOException {

		GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
		GraphDatabaseService db = dbFactory.newEmbeddedDatabase("C:\\Zakaria\\NeoTests\\AuteurPays");
		
		Gson gson = new GsonBuilder()
							        .disableHtmlEscaping()
							        //.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
							        .setPrettyPrinting()
							        .serializeNulls()
							        .create();
		
		List<Noeud> nodes = new ArrayList<Noeud>();
		List<Lien> links = new ArrayList<Lien>();
		
		try (Transaction tx = db.beginTx()) {
			
			Node maroc = db.createNode(NodeLabels.PAYS);
			maroc.setProperty("name", "Maroc");
			maroc.setProperty("group", 1); //couleur
			
			Noeud ma = new Noeud();
			ma.setName(maroc.getProperty("name").toString());
			ma.setGroup(Integer.parseInt(maroc.getProperty("group").toString()));
			nodes.add(ma);
			
			Node france = db.createNode(NodeLabels.PAYS);
			france.setProperty("name", "France");
			france.setProperty("group", 1);
			
			Noeud fr = new Noeud();
			fr.setName(france.getProperty("name").toString());
			fr.setGroup(Integer.parseInt(france.getProperty("group").toString()));
			nodes.add(fr);
			
			Node zakaria = db.createNode(NodeLabels.AUTEUR);
			zakaria.setProperty("name", "Zakaria");
			zakaria.setProperty("group", 2);
			
			Noeud zak = new Noeud();
			zak.setName(zakaria.getProperty("name").toString());
			zak.setGroup(Integer.parseInt(zakaria.getProperty("group").toString()));
			nodes.add(zak);
			
			Node bernard = db.createNode(NodeLabels.AUTEUR);
			bernard.setProperty("name", "Bernard");
			bernard.setProperty("group", 2);
			
			Noeud be = new Noeud();
			be.setName(bernard.getProperty("name").toString());
			be.setGroup(Integer.parseInt(bernard.getProperty("group").toString()));
			nodes.add(be);
			
			Node anass = db.createNode(NodeLabels.AUTEUR);
			anass.setProperty("name", "Anass");
			anass.setProperty("group", 2);
			
			Noeud an = new Noeud();
			an.setName(anass.getProperty("name").toString());
			an.setGroup(Integer.parseInt(anass.getProperty("group").toString()));
			nodes.add(an);
			
//			String gsonNodes = gson.toJson(nodes);
//			System.out.println(gsonNodes);
			
			Relationship app1 = zakaria.createRelationshipTo(maroc, TypeRelation.APPARTENANCE);
			app1.setProperty("source", zakaria.getId());
			app1.setProperty("target", maroc.getId());
			app1.setProperty("oriented", true);
			app1.setProperty("value", 1); //poids
			app1.setProperty("date", 1988);
			
			Lien a1 = new Lien();
			a1.setSource(Integer.parseInt(app1.getProperty("source").toString()));
			a1.setTarget(Integer.parseInt(app1.getProperty("target").toString()));
			a1.setOriented((boolean) app1.getProperty("oriented"));
			a1.setValue(Integer.parseInt(app1.getProperty("value").toString()));
			a1.setDate(app1.getProperty("date").toString());
			links.add(a1);
			
			Relationship coll1 = zakaria.createRelationshipTo(france, TypeRelation.COLLABORATION);
			coll1.setProperty("source", zakaria.getId());
			coll1.setProperty("target", france.getId());
			coll1.setProperty("oriented", false);
			coll1.setProperty("value", 1);
			coll1.setProperty("date", 2015);
			
			Lien c1 = new Lien();
			c1.setSource(Integer.parseInt(coll1.getProperty("source").toString()));
			c1.setTarget(Integer.parseInt(coll1.getProperty("target").toString()));
			c1.setOriented((boolean) coll1.getProperty("oriented"));
			c1.setValue(Integer.parseInt(coll1.getProperty("value").toString()));
			c1.setDate(coll1.getProperty("date").toString());
			links.add(c1);
			
			List<Integer> l1 = new ArrayList<Integer>();
			l1.add(2006);
			l1.add(2010);
			l1.add(2015);
			
			Relationship coll2 = bernard.createRelationshipTo(maroc, TypeRelation.COLLABORATION);
			coll2.setProperty("source", bernard.getId());
			coll2.setProperty("target", maroc.getId());
			coll2.setProperty("oriented", false);
			coll2.setProperty("value", 3);
			coll2.setProperty("date", l1.toString());
			
			Lien c2 = new Lien();
			c2.setSource(Integer.parseInt(coll2.getProperty("source").toString()));
			c2.setTarget(Integer.parseInt(coll2.getProperty("target").toString()));
			c2.setOriented((boolean) coll2.getProperty("oriented"));
			c2.setValue(Integer.parseInt(coll2.getProperty("value").toString()));
			c2.setDate(coll2.getProperty("date").toString());
			links.add(c2);
			
			Graphe graphe = new Graphe(nodes, links);
			String gsonGraphe = gson.toJson(graphe);
			File file = new File("src/com/neo4j/test/auteurPays/sources/Output.json");
			//System.out.println(file.getAbsoluteFile());
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter writer = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(gsonGraphe);
			bufferedWriter.close();
			//System.out.println(gsonGraphe);
			
			tx.success();
			
		}
		
		System.out.println("Done!");

	}

}
