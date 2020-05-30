package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Simulator {

	// PARAMETRI DI SIMULAZIONE
	private int NUM_EVENT = 2000;
	private int T_MIN_ARRIVO_MAX = 10;
	private double OCCUPAZIONE_MIN = 0.5;
	
	// SOLUZIONE MIGLIORE: LASCIARE CHE SIA 'SIMULATOR' A GESTIRE LA CREAZIONE 
	// RANDOM DEI PARAMETRI DA PASSARE AD EVENT, COSÌ CHE, QUALORA DEBBANO ESSERE MODIFICATI
	// DEI CRITERI (ES. NUM_PERSONE_MAX), SIA SUFFICIENTE CAMBIARE IL VALORE DI UNA VARIABILE!!!
//	private int NUM_PERSONE_MAX = 10;
//	private int DURATA_MIN = 60;
//	private int DURATA_MAX = 120;
//	private double TOLLERANZA_MAX = 0.9;
	
	// VALORI CALCOLATI
	private Statistiche stats;
	
	// MODELLO DEL MONDO
	private List<Tavolo> tavoli;
	
	// CODA DEGLI EVENTI
	private PriorityQueue<Event> queue;
	
	private void caricaTavoli() {
		this.tavoli = new ArrayList<>();
		
		aggiungiTavolo(2,10);
		aggiungiTavolo(4,8);
		aggiungiTavolo(4,6);
		aggiungiTavolo(5,4);
		
		// Ordino i tavoli dal più piccolo al più grande, così da semplificare la ricerca
		Collections.sort(this.tavoli, new Comparator<Tavolo>() {

			@Override
			public int compare(Tavolo t1, Tavolo t2) {
				return -(t1.getnPosti() - t2.getnPosti());
			}	
		});
	}
	
	private void caricaEventi() {
		// Non ci sono specifiche riguardo l'orario di apertura, parto dunque da zero!
		Duration arrivo = Duration.ofMinutes(0);
		
		for(int i = 0; i < NUM_EVENT; i++) {
			
		}
	}
	
	private void aggiungiTavolo(int num, int nPosti) {
		for(int i=0; i<num; i++) {
			Tavolo t = new Tavolo(nPosti, false);
			this.tavoli.add(t);
		}
	}
	
	public Statistiche getStats() {
		return stats;
	}

	// VALORI DA CALCOLARE
	
	// METODI PER IMPOSTARE I PARAMETRI
	
	// METODI PER RESTITUIRE I RISULTATI
	
	// SIMULAZIONE (!!!)
	public void run() {
		
	}
	
	private void processEvent(Event e) {
//		switch(e.getType()) {
		
//		case blahblah:
//			break;
//			
//		
//		
//		}
	}
	
	
}
