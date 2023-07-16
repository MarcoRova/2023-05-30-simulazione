package it.polito.tdp.gosales.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.gosales.dao.GOsalesDAO;

public class Model {
	
	private GOsalesDAO dao;
	private Graph<Retailers, DefaultWeightedEdge> grafo;
	private List<Retailers> vertici;
	private Map<Integer, Retailers> retIdMap;
	
	
	public Model() {
		this.dao = new GOsalesDAO();
		this.vertici = new ArrayList<>();
	}
	
	
	public void creaGrafo(String country,int anno, int prod) {
		
		this.grafo = new SimpleWeightedGraph<Retailers, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		this.retIdMap = new HashMap<>();
		
		this.vertici = this.dao.getRetailersByCountry(country);
		
		for(Retailers r : vertici) {
			this.retIdMap.put(r.getCode(), r);
		}
		
		Graphs.addAllVertices(this.grafo, vertici);
		
		aggiungiArchi(country, anno, prod);
		
	}
	
	public void aggiungiArchi(String country,int anno, int prod) {
		
		List<Arco> archi = this.dao.getArchi(country, anno, prod);
		
		for(Arco a : archi) {
			if(!this.grafo.containsEdge(this.retIdMap.get(a.getrCode1()), this.retIdMap.get(a.getrCode2()))) {
				Graphs.addEdgeWithVertices(this.grafo, this.retIdMap.get(a.getrCode1()), this.retIdMap.get(a.getrCode2()), a.getPeso());
			}
		}
	}
	
	public List<ArcoStampa> getArchi(){
		
		List<ArcoStampa> archi = new ArrayList<>();
		
		for(DefaultWeightedEdge d : this.grafo.edgeSet()) {
			
			archi.add(new ArcoStampa(grafo.getEdgeSource(d), grafo.getEdgeTarget(d), grafo.getEdgeWeight(d)));
		}
		
		Collections.sort(archi);
		
		return archi;
		
	}
	
	public String calcolaComponente(Retailers r) {
		
		ConnectivityInspector<Retailers, DefaultWeightedEdge> ci = new  ConnectivityInspector<>(this.grafo);
		
		int peso = 0;
		
		Set<Retailers> componente = ci.connectedSetOf(r);
		
		for(DefaultWeightedEdge arco : this.grafo.edgeSet()) {
			if(componente.contains(this.grafo.getEdgeSource(arco)) && componente.contains(this.grafo.getEdgeTarget(arco)))
			peso +=(int)this.grafo.getEdgeWeight(arco);
		}
		
		return "La componente connessa di "+r.getName()+" ha dimensione: "+componente.size()+
				"\nIl peso totale della componente connessa è: "+peso;
		
		
	}
	
	public List<String> getCountry(){
		return this.dao.getCountry();
	}
	
	public String infoGrafo() {
		return "Grafo creato!\n#Vertici: "+this.grafo.vertexSet().size()+"\n#Archi: "+this.grafo.edgeSet().size();
	}

	public List<Retailers> getVertici() {
			return vertici;
	}
	
	public Graph<Retailers, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}
	
}
