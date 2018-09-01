package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja entitet drzave koji je definiran nazivom, povrsinom i listom zupanija.
 * @author Kardum
 */

public class Drzava extends BazniEntitet {
	private String nazivDrzave;
	private BigDecimal povrsinaDrzave;
	private List<Zupanija> listaZupanija;
	
	/**
	 * Inicijalizira podatak o nazivu i povrsini drzave.
	 * 
	 * @param nazivDrzave
	 * @param povrsinaDrzave
	 */

	public Drzava(String nazivDrzave, BigDecimal povrsinaDrzave) {
		this.nazivDrzave = nazivDrzave;
		this.povrsinaDrzave = povrsinaDrzave;
		listaZupanija = new ArrayList<>();
	}
	
	public Drzava(Integer id, String nazivDrzave, BigDecimal povrsinaDrzave) {
		this.id = id;
		this.nazivDrzave = nazivDrzave;
		this.povrsinaDrzave = povrsinaDrzave;
	}
	
	public String toString() {
		return this.nazivDrzave;
	}
		
	public List<Zupanija> getListaZupanija() {
		return listaZupanija;
	}

	public String getNazivDrzave() {
		return nazivDrzave;
	}

	public void setNazivDrzave(String nazivDrzave) {
		this.nazivDrzave = nazivDrzave;
	}
	
	public BigDecimal getPovrsinaDrzave() {
		return povrsinaDrzave;
	}

	public void setPovrsinaDrzave(BigDecimal povrsinaDrzave) {
		this.povrsinaDrzave = povrsinaDrzave;
	}	
}
