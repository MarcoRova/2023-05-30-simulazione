package it.polito.tdp.gosales.model;

public class ArcoStampa  implements Comparable<ArcoStampa>{
	
	Retailers r1;
	Retailers r2;
	double peso;
	
	public ArcoStampa(Retailers r1, Retailers r2, double peso) {
		super();
		this.r1 = r1;
		this.r2 = r2;
		this.peso = peso;
	}

	public Retailers getR1() {
		return r1;
	}

	public void setR1(Retailers r1) {
		this.r1 = r1;
	}

	public Retailers getR2() {
		return r2;
	}

	public void setR2(Retailers r2) {
		this.r2 = r2;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public int compareTo(ArcoStampa other) {
		return (int)(this.peso - other.peso);
	}

	@Override
	public String toString() {
		return ""+(int)peso+": " + r1 + " <--> " + r2;
	}
	
	
}
