package it.polito.tdp.bar.model;

public class Statistiche {

	private int numClientiTot;
	private int numClientiSoddisfatti;
	private int numClientiInsoddisfatti;
	
	
	
	/**
	 * Costruttore di {@link Statistiche}. Set di tutti i paramentri a zero.
	 */
	public Statistiche() {
		super();
		this.numClientiTot = 0;
		this.numClientiSoddisfatti = 0;
		this.numClientiInsoddisfatti = 0;
	}
	
	// METODI GETTER PER RESTITUIRE I VALORI
	public int getNumClientiTot() {
		return numClientiTot;
	}
	
	public int getNumClientiSoddisfatti() {
		return numClientiSoddisfatti;
	}
	
	public int getNumClientiInsoddisfatti() {
		return numClientiInsoddisfatti;
	}
	
	public void addNumClientiTot (int numClientiTot){
		this.numClientiTot += numClientiTot;
	}
	
	public void addNumClientiSoddisfatti (int numClientiSoddisfatti){
		this.numClientiSoddisfatti += numClientiSoddisfatti;
	}

	public void addNumClientiInsoddisfatti (int numClientiInsoddisfatti){
		this.numClientiInsoddisfatti += numClientiInsoddisfatti;
	}
	
	
}
