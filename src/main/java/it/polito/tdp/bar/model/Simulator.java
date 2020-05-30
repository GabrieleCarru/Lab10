package it.polito.tdp.bar.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.bar.model.Event.EventType;

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
			// Nella creazione del nuovo evento lascio che sia la classe Event a creare casualmente il resto.
			Event e = new Event(arrivo, EventType.ARRIVO_GRUPPO_CLIENTI, null);
			this.queue.add(e);
			arrivo = arrivo.plusMinutes(1 + (int)(Math.random() * this.T_MIN_ARRIVO_MAX));
		}
	}
	
	public void init() {
		caricaTavoli();
		
		this.queue = new PriorityQueue<Event>();
		caricaEventi();
		this.stats = new Statistiche();
	}
	
	public void run() {
		while(!queue.isEmpty()) {
			Event e = queue.poll();
			System.out.println(e);
			processEvent(e);
		}
	}
	
	private void processEvent(Event e) {
		switch(e.getType()) {
		
		case ARRIVO_GRUPPO_CLIENTI:
			
			// Aggiungo i clienti ai clientiTot
			stats.addNumClientiTot(e.getNumPersone());
			
			// Cerco un tavolo 
			Tavolo trovato = null;
			for(Tavolo t : this.tavoli) {
				if( (!t.isOccupato())  &&  (t.getnPosti() >= e.getNumPersone())  
						&&  ((t.getnPosti()*this.OCCUPAZIONE_MIN) <= e.getNumPersone()) ) {
					trovato = t;
					break; // Il primo che trovo sarà il più piccolo che soddisfa il requisito avendoli precedentemente ordinati
				}
			}
			
			if(trovato != null) {
				// IL TAVOLO E' STATO TROVATO E ASSEGNATO AL GRUPPO
				System.out.println(String.format("Sedute %d persione ad un tavolo da %d posti. \n", 
														e.getNumPersone(), trovato.getnPosti()));
				stats.addNumClientiSoddisfatti(e.getNumPersone());
				trovato.setOccupato(true);
				
				// Prima o poi si alzeranno e, all'interno di Event, c'è l'informazione calcolata casualmente sulla permanenza.
				queue.add(new Event(e.getTime().plus(e.getPermanenza()), EventType.TAVOLO_LIBERATO, trovato));
			} else {
				// NESSUN TAVOLO DISPONIBILE. ACCETTERANNO IL BANCONE?
				double bancone = Math.random();
				if(bancone <= e.getTolleranza()) {
					// Accettano il bancone
					stats.addNumClientiSoddisfatti(e.getNumPersone());
				} else {
					// rifiutano il bancone e vanno via
					stats.addNumClientiInsoddisfatti(e.getNumPersone());
				}
			}
			
			break;
			
		case TAVOLO_LIBERATO:
			e.getTavolo().setOccupato(false); // Libero il tavolo
			break;
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
	
	
	
}
