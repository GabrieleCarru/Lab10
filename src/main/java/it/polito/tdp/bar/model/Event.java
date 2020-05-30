package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Event implements Comparable<Event>{
	
	public enum EventType {
		ARRIVO_GRUPPO_CLIENTI, // Arriva un nuovo gruppo. Tavolo/bancone/abbandona
		TAVOLO_LIBERATO, // Gruppo precedentemente seduto tavolo, lo libera.
	}

	private Duration time; // orario di arrivo rispetto all'inizio della simulazione
	private EventType type;
	private int numPersone;
	private Duration permanenza;
	private double tolleranza;
	private Tavolo tavolo;
	// Solo a scopo di controllo:
	private int permanenzaInt;
	
	/**
	 * Costruttore di {@link Event} a cui vengono passati i parametri: 
	 * @param time : Duration che indica l'arrivo del gruppo in minuti successivi all'apertura
	 * @param type : EventType che può essere 'ARRIVO_GRUPPO_CLIENTI' oppure 'TAVOLO_LIBERATO'
	 * @param tavolo : Oggetto di classe Tavolo che indica il tavolo che viene assegnato al gruppo (qualora riescano ad ottenerne uno)
	 *  
	 *  
	 * <p>Gli altri parametri vengono generati random (rispettando le indicazioni nel testo del problema) direttamente da questo costruttore. </p>
	 * 
	 * ERRORE COMMESSO: Conviene sempre utilizzare delle variabili statiche per quei valori che potrebbero voler essere cambiati in futuro
	 * (esempio: numero massimo persone gruppo, durata minima nel locale, etc) cosi da poter effettura una simulazione differente 
	 * dovendo semplicemente cambiare il valore di qualche variabile.
	 * Per lo stesso motivo e anche per una questione di ordine, sarebbe stato meglio lasciare che fosse la classe {@link Simulator} 
	 * a gestire in maniera causale (random) anche gli altri parametri.   
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
		this.tolleranza = Math.random(); 
		
	}
	
	// Solo a scopo di controllo:
	private void setPermanenzaInt(int result) {
		this.permanenzaInt = result;
	}
	
	// METODI GETTER PER RESTITUIRE I VALORI DELL'EVENTO
	public Duration getTime() {
		return time;
	}
	
	public int getTimeInt() {
		return time.toMinutesPart();
	}

	public int getNumPersone() {
		return this.numPersone;
	}
	
	public Duration getPermanenza() {
		return this.permanenza;
	}
	
	public double getTolleranza() {
		return this.tolleranza;
	}
	
	public Tavolo getTavolo() {
		return this.tavolo;
	}

	public EventType getType() {
		return type;
	}
	
	private int getPermanenzaInt() {
		return this.permanenzaInt;
	}
	
	@Override
	public String toString() {
		return type.name() + " [Arrivo=" + getTimeInt() + ", numPersone=" + numPersone + ", permanenza=" + permanenzaInt + "min]";
	}

	@Override
	public int compareTo(Event other) {
		return this.time.compareTo(other.time);
	}

	
	
	// A SCOPO DI DEBBUGING, DA ELIMINARE NEL PROGETTO FINALE
	public static void main(String args[]) {

		Event e = new Event(Duration.of(45, ChronoUnit.MINUTES), EventType.ARRIVO_GRUPPO_CLIENTI, null); 
		
		
			System.out.println(String.format("Il numero di persone che entrano nel locale è: %d \n",
					e.getNumPersone()));
			System.out.println(String.format("La durata della permanenza nel locale è: %d minuti \n",
					e.getPermanenzaInt()));
			System.out.println(String.format("La tolleranza del gruppo è: %f \n",
					e.getTolleranza()));
			
			System.out.println(e);
			System.out.println("['Arrivo' è il numero di minuti - successivi all'apertura - in cui è arrivato il gruppo]");
		
		
	}
}
