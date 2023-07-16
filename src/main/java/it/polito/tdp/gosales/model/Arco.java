package it.polito.tdp.gosales.model;

public class Arco implements Comparable<Arco>{
	
	Integer rCode1;
	Integer rCode2;
	double peso;
	
	public Arco(Integer rCode1, Integer rCode2, double peso) {
		super();
		this.rCode1 = rCode1;
		this.rCode2 = rCode2;
		this.peso = peso;
	}

	public Integer getrCode1() {
		return rCode1;
	}

	public void setrCode1(Integer rCode1) {
		this.rCode1 = rCode1;
	}

	public Integer getrCode2() {
		return rCode2;
	}

	public void setrCode2(Integer rCode2) {
		this.rCode2 = rCode2;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public int compareTo(Arco other) {
		return (int)(this.peso - other.peso);
	}
}
