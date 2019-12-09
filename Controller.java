import java.util.ArrayList;
import java.util.Objects;

public class Controller {
	
	ArrayList <Articolo> Magazzino; //Magazzino contenente prodotti 
	ArrayList <Articolo> Carrello; //Il carrello dell'utente
	SceltaUtenteAmministratore frameSceltaUtenteAmministratore;
	AggiungiArticoli frameAggiuntaArticoli;
	MenuUtente frameMenuUtente;
	AggiungiArticoliAlCarrello frameAggiungiArticoliAlCarrello; 
	RimuoviArticoli frameRimuoviArticoli;
	Pagamento framePagamento;
	
	public static void main(String[] args) {
		Controller TheController = new Controller();
		TheController.frameSceltaUtenteAmministratore = new SceltaUtenteAmministratore(TheController);
		TheController.frameSceltaUtenteAmministratore.setVisible(true);
	}
	
	public Controller() {
		Magazzino = new ArrayList<Articolo>();
		Carrello = new ArrayList<Articolo>();
	}
	
	// L'utente ha premuto "Amministratore"
	public void sceltaAmministratore() {
		frameSceltaUtenteAmministratore.setVisible(false);
		frameAggiuntaArticoli = new AggiungiArticoli(this);
		frameAggiuntaArticoli.setVisible(true);
	}
	
	//L'utente ha premuto "Utente"
	public void sceltaUtente() {
		this.frameSceltaUtenteAmministratore.setVisible(false);
		frameMenuUtente = new MenuUtente(this);
		frameMenuUtente.setVisible(true);
	}
	
	// Viene aggiunto un articolo al magazzino
	public void aggiungiArticolo(String nome, int codice, double prezzo) {
		Articolo nuovoArticolo = new Articolo(nome,codice,prezzo);
		this.Magazzino.add(nuovoArticolo);
		System.out.println(nome);
	}
	
	//Bottone per l'utente per aggiungere articoli al carrello
	public void AggiungiArticoliIsClicked() {
		frameAggiungiArticoliAlCarrello = new AggiungiArticoliAlCarrello(this);
		frameAggiungiArticoliAlCarrello.setVisible(true);
	}
	
	//Procedura che prende gli oggetti dal Magazzino e li mette nella combobox per aggiungerli al carrello
	public ArrayList<String> RestituisciAComboboxAggiunta() {
		ArrayList<String> StringhePerComboBox = new ArrayList<String>();
		for(Articolo art: Magazzino ) {
			StringhePerComboBox.add(art.getNome()+"  --  "+art.getPrezzo()+"€");
		}
		return StringhePerComboBox;
	}
	
	//Procedura che prende gli oggetti dal Carrello e li mette nella combobox per rimuoverli dal carrello
	public ArrayList<String> RestituisciAComboboxRimozione() {
		ArrayList<String> StringhePerComboBox = new ArrayList<String>();
		String stringaVisualizzata;
		for(Articolo art: Carrello ) {
			stringaVisualizzata = art.getNome()+"  --  "+art.getPrezzo()+"€";
			
			//impediamo escono due valori uguali
			if(!StringhePerComboBox.contains(stringaVisualizzata))
				StringhePerComboBox.add(stringaVisualizzata);
		}
		return StringhePerComboBox;
	}
	
	
	public void daComboBoxACarrello(Object prodotto, Object quantita) {
		String prodottoString = prodotto.toString();
		Integer quantitaInt = Integer.valueOf(quantita.toString());
		int codice;
		
		/*
		 *  La stringa "prodotto" in ingresso è formata così: pollo -- 1.0€
		 *  e grazie alla funzione split posso suddividere in due stringhe
		 *  diverse questi valori.
		 *  ProdottiEPrezzi[0] rappresenta il nome del prodotto (pollo)
		 *  ProdottiEPrezzi[1] rappresenta il prezzo 
		 */
		String[] ProdottiEPrezzi = prodottoString.split("  --  ",2);
		
		codice = retrieveCodice(ProdottiEPrezzi[0]);
		//Devo togliere il simbolo dell'euro
		String[] prezzo = ProdottiEPrezzi[1].split("€",0);
		
		Articolo newArticolo = new Articolo(ProdottiEPrezzi[0],codice,Double.valueOf(prezzo[0]));
		
		for(int i=1;i<=quantitaInt;i++) {
			Carrello.add(newArticolo);
		}
		System.out.println("Ho inserito "+newArticolo.toString()+" nel carrello");
		
		AggiungiATabella(newArticolo, quantitaInt);
		aggiornaLabelPrezzoTotale();
	}
	
	public void aggiornaLabelPrezzoTotale() {
		double totaleDaPagare = 0.0;
		for(Articolo art: Carrello) {
			totaleDaPagare += art.getPrezzo();
		}
		frameMenuUtente.labelTotale.setText(String.valueOf(totaleDaPagare)+" €");
	}

	//Aggiunge l'articolo alla jtable del MenuUtente con la rispettiva quantita, aggiornandola se è il caso
	public void AggiungiATabella(Articolo art, int quantita) {
		int rows = frameMenuUtente.table.getRowCount(); //controlla il numero di righe presenti nella tabella
		int nuovaQuantita;
		boolean flag=false; //se il flag rimane a falso, allora l'articolo non è già presente in carrello e quindi deve essere inserito
		for(int i=0;i<rows;i++) {
			if(frameMenuUtente.model.getValueAt(i, 0).toString().equals(art.getNome())) { //Se la riga corrente equivale al nome dell'articolo da inserire
				nuovaQuantita = Integer.valueOf(frameMenuUtente.model.getValueAt(i, 2).toString()) + quantita;
				frameMenuUtente.model.setValueAt(nuovaQuantita, i, 2); //Aggiornamento valore quantita
				flag = true;
				break;
			}
			
		}
		if(!flag)
			frameMenuUtente.model.addRow(new Object[]{art.getNome(), art.getPrezzo(), quantita});
	}
	
	//Dato in pasto il nome di un prodotto presente in magazzino, restituisce il codice corrispondente.
	public int retrieveCodice(String prodotto) {
		for(Articolo art: Magazzino) {
			if(art.getNome().equals(prodotto)) {
				return art.getCodice();
			}
		}
		return -1;
	}
	
	public void closeAggiungiArticoli() {
		frameAggiungiArticoliAlCarrello.setVisible(false);
	}
	
	void SvuotaCarrello() {
		
		/*
		 *   [anche questo codice funziona] ma ho preferito un semplice setRowCount
		int rows = frameMenuUtente.table.getRowCount();
		for(int i=0;i<rows;i++) {
			frameMenuUtente.model.removeRow(0);
		}*/
		
		frameMenuUtente.model.setRowCount(0);
		
		Carrello.removeAll(Carrello);
		frameMenuUtente.labelTotale.setText("0.0 €");
		for(Articolo art: Carrello) {
			System.out.println(art.toString());
		}
	}

	public void rimuoviArticoliIsClicked() {
		frameRimuoviArticoli = new RimuoviArticoli(this);
		frameRimuoviArticoli.setVisible(true);
	}
	
	public void RimuoviArticoli(Object prodotto, Object quantita) {
		String prodottoString = prodotto.toString();
		Integer quantitaInt = Integer.valueOf(quantita.toString());
		int codice;
		int elementiEliminati=0;
		String[] ProdottiEPrezzi = prodottoString.split("  --  ",2);
		codice = retrieveCodice(ProdottiEPrezzi[0]);
		//Devo togliere il simbolo dell'euro
		String[] prezzo = ProdottiEPrezzi[1].split("€",0);
		
		Articolo prototipoArticoloDaRimuovere = new Articolo(ProdottiEPrezzi[0],codice,Double.valueOf(prezzo[0]));
		
		java.util.Iterator<Articolo> itr = Carrello.iterator();
		Articolo art;
		
		// https://www.geeksforgeeks.org/remove-element-arraylist-java/
		while (itr.hasNext()) 
        { 
            art = (Articolo)itr.next(); 
            if (art.isEqual(prototipoArticoloDaRimuovere) && elementiEliminati < quantitaInt) {
                itr.remove();
                elementiEliminati++;
                System.out.println("ho eliminato un' occorrenza di "+art.toString()+" dal carrello");
            }else if(elementiEliminati>=quantitaInt) break;
        } 
		
		/* Questo codice funziona ma genera un ConcurrentModificationException
		for(Articolo art: Carrello) {
			if(art.isEqual(prototipoArticoloDaRimuovere) && elementiEliminati<quantitaInt) {
				Carrello.remove(art);
				elementiEliminati++;
				System.out.println("ho eliminato un' occorrenza di "+art.toString());
			}else if(elementiEliminati>=quantitaInt) break;
		}
		*/
		RimuoviDaTabella(prototipoArticoloDaRimuovere, quantitaInt);
		aggiornaLabelPrezzoTotale();
	}
	
	void RimuoviDaTabella(Articolo art, int quantita) {
		int rows = frameMenuUtente.table.getRowCount();
		String riga;
		int nuovaQuantita = 0;
			for(int i=0;i<rows;i++) {
				riga = frameMenuUtente.model.getValueAt(i, 0).toString();
				if(Objects.equals(art.getNome(),riga)) {
					nuovaQuantita = Integer.valueOf(frameMenuUtente.model.getValueAt(i, 2).toString()) - quantita;
					if(nuovaQuantita<=0) {
						frameMenuUtente.model.removeRow(i);
						break;
					}else {
						frameMenuUtente.model.setValueAt(nuovaQuantita, i, 2); //Aggiornamento valore quantita
						break;
					}
				}
			}
		}
	
	public void chiudiFrameRimozione() {
		frameRimuoviArticoli.setVisible(false);
	}
	
	public void stampaCarrello() {
		for(Articolo art: Carrello) {
			System.out.print("\n\n - - - - \n\n");
			System.out.println(art.toString());
		}
	}
	
	public void ProcediAlPagamento() {
		framePagamento = new Pagamento(this);
		frameMenuUtente.setVisible(false);
		framePagamento.setVisible(true);
	}
	
	public void TornaIndietroDalPagamento() {
		frameMenuUtente.setVisible(true);
		framePagamento.setVisible(false);
	}
}
