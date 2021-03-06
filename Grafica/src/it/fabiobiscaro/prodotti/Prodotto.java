package it.fabiobiscaro.prodotti;

public class Prodotto implements Cloneable {
	String codice;
	String descrizione;
	double prezzo;

	public Prodotto(String codice, String descrizione, double prezzo) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
	}

	/**
	 * Ciao a tutti!
	 * @return
	 */
	public String getCodice() {
		return codice;
	}


	/**
	 * Questo metodo imposta un bel codice 
	 * @param codice
	 */

	public void setCodice(final String codice) {
		this.codice = codice;
	}

	/**
	 * Restituisce la descrizione
	 * @return
	 */
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public void applicaSconto() {
		this.prezzo = this.prezzo * 0.95;
	}

	@Override
	public String toString() {
		return "Cod: " + codice + " descrizione: " + descrizione + " prezzo:" + prezzo;
	}

	@Override
	public boolean equals(Object obj) {
		// Sono uguali se sono uguali tutti i campi
		Prodotto p = (Prodotto) obj;
		if (p.getCodice().equals(codice) && p.getDescrizione().equals(descrizione) && p.getPrezzo() == prezzo) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}
