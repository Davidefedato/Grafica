package it.fabiobiscaro.prodotti;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ListaSpesa {
		private Prodotto lista [];
		private int numProdotti;
		private int maxProdotti;
		boolean tessera;
	
	public ListaSpesa(boolean tessera){
		this.numProdotti=0;
		this.maxProdotti=100;
		this.lista=new Prodotto[100];
		this.tessera = tessera;
	}
	
	public void aggiungiProdotto(Prodotto P) throws Exception{
		if(numProdotti<maxProdotti){
			lista[numProdotti++]=P;
			if(tessera){
				P.applicaSconto();
			}
		}
		else{
			throw exception("La lista e' piena");
		}
	}
	
	private Exception exception(String string) {
		return null;
	}

	public double calcolaTotale(){
		double totale=0;
		for(int i=0;i<numProdotti;i++){
			totale=totale+lista[i].getPrezzo();
		}
		return totale;
	}
	public void salva() throws IOException{
		BufferedWriter writer;
		writer = new BufferedWriter(new FileWriter("Scontrino.txt"));
		
		// Ciclo tutti gli oggetti e li salvi
		for (int i=0; i<numProdotti; i++){
			Prodotto p = lista[i];
			
			if (p instanceof Alimentare){
				
				writer.write("Alimentare");
				writer.write(p.getDescrizione());
				writer.write(p.getCodice());
				writer.write(String.valueOf(p.getPrezzo()));
				writer.write(((Alimentare) p).getScadenza());
				
			}
			else if(p instanceof NonAlimentare) {
				
				writer.write("Non Alimetare");
				writer.write(p.getDescrizione());
				writer.write(p.getCodice());
				//writer.write(p.getPrezzo());
				writer.write(((NonAlimentare) p).getMateriale());
			}
			
		}
		
		// Chiudi il file
	}
}

