package it.fabiobiscaro.prodotti;

public class NonAlimentare extends Prodotto {

	String materiale;

	public NonAlimentare(String codice, String descrizione, double prezzo, String materiale) {
		super(codice, descrizione, prezzo);
		this.materiale = materiale;
	}

	public void setMateriale(String materiale) {
		this.materiale = materiale;
	}

	public String getMateriale() {
		return materiale;
	}
	
	@Override
	public void applicaSconto() {
		// TODO Auto-generated method stub
		if (materiale.equalsIgnoreCase("carta") || materiale.equalsIgnoreCase("vetro") || materiale.equalsIgnoreCase("plastica") || materiale.equalsIgnoreCase("legno")) {
			prezzo = prezzo *0.90;
		} else {
			super.applicaSconto();
		}
	}
}
