package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja entitet mjesta koji je definiran nazivom mjesta, zupanijom,
 * vrstom mjesta i listom mjernih postaja.
 * @author Kardum
 */

public class Mjesto extends BazniEntitet {
	private String nazivMjesta;
	private Zupanija zupanija;
	private VrstaMjesta vrstaMjesta;
	private List<MjernaPostaja> listaMjernePostaje;

	/**
	 * Inicijalizira podatak o nazivu mjesta, vrsti mjesta i zupaniji.
	 * 
	 * @param nazivMjesta
	 * @param vrstaMjesta
	 * @param zupanija
	 */

	public Mjesto(String nazivMjesta, VrstaMjesta vrstaMjesta, Zupanija zupanija) {
		this.nazivMjesta = nazivMjesta;
		this.zupanija = zupanija;
		this.vrstaMjesta = vrstaMjesta;
		listaMjernePostaje = new ArrayList<>();
	}
	
	public Mjesto(Integer id, String nazivMjesta, VrstaMjesta vrstaMjesta, Zupanija zupanija) {
		this.id = id;
		this.nazivMjesta = nazivMjesta;	
		this.vrstaMjesta = vrstaMjesta;	
		this.zupanija = zupanija;				
	}
	
	public String toString() {
		return this.nazivMjesta;
	}

	public List<MjernaPostaja> getListaMjernePostaje() {
		return listaMjernePostaje;
	}

	public String getNazivMjesta() {
		return nazivMjesta;
	}

	public void setNazivMjesta(String nazivMjesta) {
		this.nazivMjesta = nazivMjesta;
	}

	public Zupanija getZupanija() {
		return zupanija;
	}

	public void setZupanija(Zupanija zupanija) {
		this.zupanija = zupanija;
	}

	public VrstaMjesta getVrstaMjesta() {
		return vrstaMjesta;
	}

	public void setVrstaMjesta(VrstaMjesta vrstaMjesta) {
		this.vrstaMjesta = vrstaMjesta;
	}
}
