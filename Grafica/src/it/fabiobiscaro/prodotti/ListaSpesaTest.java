package it.fabiobiscaro.prodotti;

public class ListaSpesaTest {

	public static void main(String[] args) {
	
		
		ListaSpesa ls = new ListaSpesa(true);
		Prodotto p = new Prodotto("pane", "7",1.0f);
		Prodotto s = new Prodotto("scarpe", "7",10.0f);
		try {
			ls.aggiungiProdotto(p);
			ls.aggiungiProdotto(s);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		System.out.println("totale"+ls.calcolaTotale());
	}

}
