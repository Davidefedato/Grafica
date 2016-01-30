package it.fabiobiscaro.prodotti;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import javax.swing.ButtonGroup;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Combo;

public class GraficaSpesa {

	protected Shell shlLista;
	private Text txtCodice;
	private Text txtDescrizione;
	private Text txtPrezzo;
	private Text txtTotale;
	private Text txtTessera;
	private Text txtAlimentari;
	private Double prezzo;
	private String descrizione;
	private String codice;
	private ListaSpesa listaSpesa;
	private String A;
	private String B;
	private List list;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GraficaSpesa window = new GraficaSpesa();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();

		shlLista.open();
		shlLista.layout();
		while (!shlLista.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		// Creo le variabili che servono a me

		listaSpesa = new ListaSpesa(true);

		//
		shlLista = new Shell();
		shlLista.setSize(641, 537);
		shlLista.setText("Lista");
		Group bgAlimentari = new Group(shlLista, SWT.SHADOW_IN);

		txtCodice = new Text(shlLista, SWT.BORDER);
		txtCodice.setBounds(137, 39, 76, 21);

		Label lblCodice = new Label(shlLista, SWT.NONE);
		lblCodice.setBounds(29, 42, 55, 15);
		lblCodice.setText("Codice");

		txtDescrizione = new Text(shlLista, SWT.BORDER);
		txtDescrizione.setBounds(137, 86, 76, 21);

		txtPrezzo = new Text(shlLista, SWT.BORDER);
		txtPrezzo.setBounds(137, 133, 76, 21);

		Label lblDescrizione = new Label(shlLista, SWT.NONE);
		lblDescrizione.setBounds(29, 89, 71, 15);
		lblDescrizione.setText("Descrizione");

		Label lblPrezzo = new Label(shlLista, SWT.NONE);
		lblPrezzo.setBounds(29, 136, 55, 15);
		lblPrezzo.setText("Prezzo");

		Label lblTessera = new Label(shlLista, SWT.NONE);
		lblTessera.setBounds(29, 184, 55, 15);
		lblTessera.setText("Tessera");

		Label lblAlimentari = new Label(shlLista, SWT.NONE);
		lblAlimentari.setBounds(29, 230, 55, 15);
		lblAlimentari.setText("Alimentari");

		Button btnAggiungiP = new Button(shlLista, SWT.NONE);
		btnAggiungiP.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// prende i valori di tutte le etichette
				// crea il prodotto alim o non alim
				// aggiunge a listaSpesa il prodotto nuovo
				// aggiorna la lsita grafica dei prodotti
			
					try {
						codice = txtCodice.getText();
						prezzo = Double.valueOf(txtPrezzo.getText());
						descrizione = txtDescrizione.getText();
						A = txtAlimentari.getText();
						if (A == "Si") {
							Alimentare a = new Alimentare(codice, descrizione, prezzo, new Data(26, 1, 2016));
							listaSpesa.aggiungiProdotto(a);
						} else {
							NonAlimentare na = new NonAlimentare(codice, descrizione, prezzo, "");
							listaSpesa.aggiungiProdotto(na);
						}
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					list.add(descrizione);
				
				// System.out.println(listaSpesa.calcolaTotale());
			}
		});
		btnAggiungiP.setBounds(293, 37, 75, 25);
		btnAggiungiP.setText("AggiungiP");

		Button btnTotale = new Button(shlLista, SWT.NONE);
		btnTotale.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// richiama da listaspesa il metodo totale
				// aggiorna il valore della casellina
				txtTotale.setText(String.valueOf(listaSpesa.calcolaTotale()));
			}
		});
		btnTotale.setBounds(111, 360, 75, 25);
		btnTotale.setText("Totale");

		txtTotale = new Text(shlLista, SWT.BORDER);
		txtTotale.setBounds(266, 362, 76, 21);

		Label label = new Label(shlLista, SWT.NONE);
		label.setBounds(205, 365, 55, 15);
		label.setText(":");

		list = new List(shlLista, SWT.BORDER);
		list.setBounds(432, 39, 164, 248);

		txtTessera = new Text(shlLista, SWT.BORDER);
		txtTessera.setBounds(137, 178, 76, 21);

		txtAlimentari = new Text(shlLista, SWT.BORDER);
		txtAlimentari.setBounds(137, 230, 76, 21);
		
		Button btnCaricaScontrino = new Button(shlLista, SWT.NONE);
		btnCaricaScontrino.setBounds(61, 283, 125, 25);
		btnCaricaScontrino.setText("Carica scontrino");
		
		Button btnSalvaScontrino = new Button(shlLista, SWT.NONE);
		btnSalvaScontrino.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//listaSpesa.Scrittura();
				MessageDialog.openInformation(shlLista, "i", "i1");
				FileOutputStream F = null;
				try {
					F = new FileOutputStream("z:\\Scontrino.txt");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					MessageDialog.openInformation(shlLista, "ERRORE", "E1");
				}
				PrintStream scrivi = new PrintStream(F);
				 for(int i=0;i<2;i++){
						MessageDialog.openInformation(shlLista, "i", ""+i);

		               Prodotto p = listaSpesa.getProdotto(i);
		               if (p instanceof Alimentare){
		            	   scrivi.println("---Alimentare---");
		            	   scrivi.println(p.getDescrizione());
		            	   scrivi.println(p.getCodice());
		            	   scrivi.println(p.getPrezzo());
		            	   scrivi.println(((Alimentare) p).getScadenza().getGiorno());
		            	   scrivi.println(((Alimentare) p).getScadenza().getMese());
		            	   scrivi.println(((Alimentare) p).getScadenza().getAnno());
		               }
		               else if (p instanceof NonAlimentare){
		            	   scrivi.println("---Non Alimentare---");
		            	   scrivi.println(p.getDescrizione());
		            	   scrivi.println(p.getCodice());
		            	   scrivi.println(p.getPrezzo());
		            	   scrivi.println(((NonAlimentare) p).getMateriale());
		               }
		         }
				 scrivi.close();
				 
			}
		});
		btnSalvaScontrino.setBounds(61, 314, 114, 25);
		btnSalvaScontrino.setText("Salva scontrino");

	}
}
