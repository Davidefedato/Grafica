package it.fabiobiscaro.prodotti;

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
}

