public class Articolo {
	private String nome;
	private int codice;
	private double prezzo;
	
	public Articolo(String nome, int codice, double prezzo) {
		this.nome=nome;
		this.codice=codice;
		this.prezzo=prezzo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getCodice() {
		return codice;
	}
	
	public double getPrezzo() {
		return prezzo;
	}
	
	public String toString() {
		return nome+" "+prezzo+" € ["+codice+"]";
	}
	
	public boolean isEqual(Articolo art) {
		if(this.nome.equals(art.getNome()) && this.codice==art.getCodice() && this.prezzo == art.getPrezzo() ) return true;
		else return false;
	}
	
}
