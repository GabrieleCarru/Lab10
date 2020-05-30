package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Event implements Comparable<Event>{
	
	public enum EventType {
		ARRIVO_GRUPPO_CLIENTI, // Arriva un nuovo gruppo. Tavolo/bancone/abbandona
		TABOLO_LIBERATO, // Gruppo precedentemente seduto tavolo, lo libera.
	}

	private Duration time; // orario di arrivo rispetto all'inizio della simulazione
	private EventType type;
	private int numPersone;
	private Duration permanenza;
	private float tolleranza;
	private Tavolo tavolo;
	// Solo a scopo di controllo:
	private int permanenzaInt;
	
	/**
	 * Costruttore di {@link Event} a cui non passo solo il paramentro {@code time} 
	 * perché l'istrante temporale viene gestito dalla classe {@link Simulatior} secondo 
	 * le specifiche del problema. 
	 * Gli altri parametri li genererò random (rispettando le indicazioni) 
	 * come da specifica del problema.
	 * ATTENZIONE: IL LIMITE DI LASCIARE AL COSTRUTTORE DI {@code EVENT} LA CREAZIONE RANDOM DI 
	 * ALCUNI DATI È CHE, QUALORA CAMBIASSERO I CRITERI DI GENERAZIONE CASUALE (ES. NUM MAX PERSONE), 
	 * BISOGNEREBBE CAMABIARE MANUALMENTE QUESTI DATI.   
	 */
	public Event(Duration time, EventType type, Tavolo tavolo) {
		// Assegnazione variabili parametri
		this.time = time;
		this.type = type;
		this.tavolo = tavolo;
		
		// Generatore random per numero di persone
		double np = (Math.random() * 10) + 1;
		this.numPersone = (int) np;
		
		// Generatore random per durata (min 60 max 120)
		Random r = new Random();
		int min = 60;
		int max = 121;
		int result = r.nextInt(max-min) + min;
		this.permanenza = Duration.of(result, ChronoUnit.MINUTES);
		// Solo a scopo di controllo:
		setPermanenzaInt(result);
		
		// Generatore random percentuale di probabilità nell'accettare il bancone in assenza di tavolo
		this.tolleranza = (float) Math.random(); 
		
	}
	
	// Solo a scopo di controllo:
	private void setPermanenzaInt(int result) {
		this.permanenzaInt = result;
	}
	
	// METODI GETTER PER RESTITUIRE I VALORI DELL'EVENTO
	public Duration getTime() {
		return time;
	}

	public int getNumPersone() {
		return this.numPersone;
	}
	
	public Duration getPermanenza() {
		return this.permanenza;
	}
	
	public float getTolleranza() {
		return this.tolleranza;
	}
	
	public Tavolo getTavolo() {
		return this.tavolo;
	}
	
	private int getPermanenzaInt() {
		return this.permanenzaInt;
	}
	
	@Override
	public String toString() {
		return "EventoArrivoGruppo [Arrivo=" + time + ", numPersone=" + numPersone + ", permanenza=" + permanenzaInt + "min]";
	}

	@Override
	public int compareTo(Event other) {
		return this.time.compareTo(other.time);
	}
	
	
	// A SCOPO DI DEBBUGING, DA ELIMINARE NEL PROGETTO FINALE
//	public static void main(String args[]) {
//		Duration d = Duration.ofMinutes(60*8 + 45);
//		Event e = new Event(d, new Tavolo(), ); 
//		System.out.println(String.format("Il numero di persone che entrano nel locale è: %d \n",
//				e.getNumPersone()));
//		System.out.println(String.format("La durata della permanenza nel locale è: %d \n",
//				e.getPermanenzaInt()));
//		System.out.println(String.format("La tolleranza del gruppo è: %f \n",
//				e.getTolleranza()));
//		
//		System.out.println(e);
//	}
}
