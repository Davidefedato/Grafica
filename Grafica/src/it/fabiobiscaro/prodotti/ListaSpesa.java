package it.fabiobiscaro.prodotti;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import org.eclipse.jface.dialogs.MessageDialog;

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
	public Prodotto getProdotto(int i){
		return lista[i];
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
	
	public void elimina(int pos) throws Exception{
		if (numProdotti!=0 || pos < maxProdotti){
			for (int i = pos; i< numProdotti; i++){
			 lista[i] = lista[i+1];
			}
			numProdotti--;
		}
		else {
			throw new Exception ("Errore nell'eliminazione del prodotto!");
		}
		
		
	}
	
	

}

