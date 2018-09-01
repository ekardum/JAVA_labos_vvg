package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja entitet zupanije koji je definiran nazivom, drzavom i listom
 * mjesta.
 * @author Kardum
 */

public class Zupanija extends BazniEntitet {
	private String nazivZupanije;
	private Drzava drzava;
	private List<Mjesto> listaMjesta;

	/**
	 * Inicijalizira podatak o nazivu i drzavi.
	 * 
	 * @param nazivZupanije
	 * @param drzava
	 */

	public Zupanija(String nazivZupanije, Drzava drzava) {
		this.nazivZupanije = nazivZupanije;
		this.drzava = drzava;
		listaMjesta = new ArrayList<>();
	}
	
	public Zupanija(Integer id, String nazivZupanije, Drzava drzava) {
		this.id = id;
		this.nazivZupanije = nazivZupanije;
		this.drzava = drzava;
	}
	
	public String toString() {
		return this.nazivZupanije;
	}

	public List<Mjesto> getListaMjesta() {
		return listaMjesta;
	}

	public String getNazivZupanije() {
		return nazivZupanije;
	}

	public void setNazivZupanije(String nazivZupanije) {
		this.nazivZupanije = nazivZupanije;
	}

	public Drzava getDrzava() {
		return drzava;
	}

	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
	}
}
