package it.fabiobiscaro.prodotti;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Combo;

import org.eclipse.swt.widgets.FileDialog;

import org.eclipse.jface.dialogs.MessageDialog;

@SuppressWarnings("unused")

public class GraficaSpesa {

	protected Shell shlLista;
	private Text txtCodice;
	private Text txtDescrizione;
	private Text txtPrezzo;
	private Text txtTotale;
	private Text txtAlimentari;
	private Double prezzo;
	private String descrizione;
	private String codice;
	private String materiale;
	private ListaSpesa listaSpesa;
	private String A;
	private String B;
	private List list;
	private Text txtMateriale;
	private int scelta;

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

		listaSpesa = new ListaSpesa(false);

		//
		shlLista = new Shell();
		shlLista.setSize(641, 537);
		shlLista.setText("Lista");
		Group bgAlimentari = new Group(shlLista, SWT.SHADOW_IN);

		txtCodice = new Text(shlLista, SWT.BORDER);
		txtCodice.setBounds(137, 39, 76, 21);

		Label lblCodice = new Label(shlLista, SWT.NONE);
		lblCodice.setBounds(29, 42, 55, 15);
		lblCodice.setText("Codice :");

		txtDescrizione = new Text(shlLista, SWT.BORDER);
		txtDescrizione.setBounds(137, 86, 76, 21);

		txtPrezzo = new Text(shlLista, SWT.BORDER);
		txtPrezzo.setBounds(137, 133, 76, 21);

		Label lblDescrizione = new Label(shlLista, SWT.NONE);
		lblDescrizione.setBounds(29, 89, 71, 15);
		lblDescrizione.setText("Descrizione :");

		Label lblPrezzo = new Label(shlLista, SWT.NONE);
		lblPrezzo.setBounds(29, 136, 55, 15);
		lblPrezzo.setText("Prezzo : ");

		Label lblAlimentari = new Label(shlLista, SWT.NONE);
		lblAlimentari.setBounds(29, 177, 71, 15);
		lblAlimentari.setText("Alimentari :");

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
						if (A.equalsIgnoreCase("si")) {
							Alimentare a = new Alimentare(codice, descrizione, prezzo, new Data(26, 1, 2016));
							listaSpesa.aggiungiProdotto(a);
						} else {
							materiale = txtMateriale.getText();
							NonAlimentare na = new NonAlimentare(codice, descrizione, prezzo, materiale);
							
							listaSpesa.aggiungiProdotto(na);
						}
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					list.add(descrizione + "	  " + prezzo + " €");
				
				// System.out.println(listaSpesa.calcolaTotale());
			}
		});
		btnAggiungiP.setBounds(257, 37, 111, 25);
		btnAggiungiP.setText("Aggiungi prodotto");

		Button btnTotale = new Button(shlLista, SWT.NONE);
		btnTotale.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// richiama da listaspesa il metodo totale
				// aggiorna il valore della casellina
				txtTotale.setText(String.valueOf(listaSpesa.calcolaTotale()));
			}
		});
		btnTotale.setBounds(29, 314, 75, 25);
		btnTotale.setText("Totale");

		txtTotale = new Text(shlLista, SWT.BORDER);
		txtTotale.setBounds(198, 316, 76, 21);

		Label label = new Label(shlLista, SWT.NONE);
		label.setBounds(137, 319, 55, 15);
		label.setText(":");

		list = new List(shlLista, SWT.BORDER);
		list.setBounds(432, 39, 164, 248);

		txtAlimentari = new Text(shlLista, SWT.BORDER);
		txtAlimentari.setBounds(137, 174, 76, 21);
		
		Button btnCaricaScontrino = new Button(shlLista, SWT.NONE);
		btnCaricaScontrino.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
				BufferedReader lettore = new BufferedReader(new FileReader("z:\\Scontrino.txt"));
				// nb lettore == null (se errore)
				String riga;
					riga = lettore.readLine();
					while (riga != null) {
						//...
						System.out.println(riga);
						riga = lettore.readLine();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnCaricaScontrino.setBounds(29, 267, 125, 25);
		btnCaricaScontrino.setText("Carica scontrino");
		
		Button btnSalvaScontrino = new Button(shlLista, SWT.NONE);
		btnSalvaScontrino.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//listaSpesa.Scrittura();
				FileOutputStream F = null;
				try {
					F = new FileOutputStream("z:\\Scontrino.txt");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					MessageDialog.openInformation(shlLista, "ERRORE", "E1");
				}
				PrintStream scrivi = new PrintStream(F);
				 for(int i=0;i<20;i++){
					 Prodotto p = listaSpesa.getProdotto(i);
		               if (p instanceof Alimentare){
		            	   scrivi.println("---Alimentare---");
		            	   scrivi.println("Descrizione: " + p.getDescrizione());
		            	   scrivi.println("Codice: " + p.getCodice());
		            	   scrivi.println("Prezzo: " + p.getPrezzo() + " €");
		            	   scrivi.print("Data di scadenza: " + ((Alimentare) p).getScadenza().getGiorno() + "/");
		            	   scrivi.print(((Alimentare) p).getScadenza().getMese() + "/");
		            	   scrivi.println(((Alimentare) p).getScadenza().getAnno());
		            	   scrivi.println();
		               }
		               else if (p instanceof NonAlimentare){
		            	   scrivi.println("---Non Alimentare---");
		            	   scrivi.println("Descrizione: " + p.getDescrizione());
		            	   scrivi.println("Codice: " + p.getCodice());
		            	   scrivi.println("Prezzo: " + p.getPrezzo() + " €");
		            	   scrivi.println("Materiale: " + ((NonAlimentare) p).getMateriale());
		            	   scrivi.println();
		               }
		         }
				 scrivi.close();
				 
			}
		});
		btnSalvaScontrino.setBounds(160, 267, 114, 25);
		btnSalvaScontrino.setText("Salva scontrino");
		
		Label lblsiNo = new Label(shlLista, SWT.NONE);
		lblsiNo.setBounds(219, 177, 55, 15);
		lblsiNo.setText("(Si / No)");
		
		txtMateriale = new Text(shlLista, SWT.BORDER);
		txtMateriale.setBounds(137, 216, 76, 21);
		
		Label lblMateriale = new Label(shlLista, SWT.NONE);
		lblMateriale.setBounds(29, 219, 55, 15);
		lblMateriale.setText("Materiale :");
		
		Label lblsoloSeNon = new Label(shlLista, SWT.NONE);
		lblsoloSeNon.setBounds(219, 219, 164, 15);
		lblsoloSeNon.setText("(Solo se non alimentare)");
		
		Button btnNuovo = new Button(shlLista, SWT.NONE);
		btnNuovo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Custom button text
				Object[] options = {"Si, con tessera",
				                    "Si, senza tessera",
				                    "Annulla"};
				scelta = JOptionPane.showOptionDialog(null,
				    "Vuoi creare un nuovo scontrino? ",
				    "Nuovo scontrino",
				    JOptionPane.YES_NO_CANCEL_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,
				    options,
				    options[2]);
				if (scelta == 0){
					listaSpesa = new ListaSpesa(true);
				}
				if (scelta == 1){
					listaSpesa = new ListaSpesa(false);
				}
				
			}
		});
		btnNuovo.setBounds(293, 267, 114, 25);
		btnNuovo.setText("Nuovo scontrino");
		
		Button btnElimina = new Button(shlLista, SWT.NONE);
		btnElimina.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				try {
					listaSpesa.elimina(list.getSelectionIndex());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				list.remove(list.getSelectionIndex());
				txtTotale.setText(String.valueOf(listaSpesa.calcolaTotale()));
			}
		});
		btnElimina.setBounds(308, 314, 75, 25);
		btnElimina.setText("Elimina");
		

		Button btnApriFile = new Button(shlLista, SWT.NONE);
			btnApriFile.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					
					FileDialog fileDialog = new FileDialog(shlLista);
					fileDialog.setFilterExtensions(new String[]{"*.txt", "*.csv", "*.*"}); //opzionale
					String fileScelto = fileDialog.open();

					if(fileScelto != null) {
						MessageDialog.openInformation(shlLista, "File ", fileScelto);
						MessageDialog.openInformation(shlLista, "File (solo nome)", fileDialog.getFileName());
					}
				}
			});
			btnApriFile.setBounds(29, 365, 75, 25);
			btnApriFile.setText("Apri");
			
			Button btnInizia = new Button(shlLista, SWT.NONE);
			btnInizia.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
						//Custom button text
						Object[] options = {"Si, con tessera",
						                    "No, senza tessera",
						                    "Annulla"};
						scelta = JOptionPane.showOptionDialog(null,
						    "Hai la tessera? ",
						    "Tessera",
						    JOptionPane.YES_NO_CANCEL_OPTION,
						    JOptionPane.QUESTION_MESSAGE,
						    null,
						    options,
						    options[2]);
						if (scelta == 0){
							listaSpesa = new ListaSpesa(true);
						}
						if (scelta == 1){
							listaSpesa = new ListaSpesa(false);
						}
						
					}
				
			});
			btnInizia.setBounds(271, 86, 75, 25);
			btnInizia.setText("Inizia");
		}
	}

