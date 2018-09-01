package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.List;

/**
 * Predstavlja entitet mjerne postaje koji je definiran nazivom mjerne postaje,
 * mjestom, geografskom tockom i listom senzora.
 */

public class MjernaPostaja extends BazniEntitet {
	private String nazivMjernePostaje;
	private Mjesto mjesto;
	private BigDecimal x;
	private BigDecimal y;
	private Senzori<Senzor> senzori;

	/**
	 * Inicijalizira podatak o nazivu mjerne postaje, mjestu, geografskoj tocki i
	 * senzorima.
	 * 
	 * @param nazivMjernePostaje
	 * @param mjesto
	 * @param geografskaTocka
	 * @param senzori
	 */

	public MjernaPostaja(String nazivMjernePostaje, Mjesto mjesto, BigDecimal x, BigDecimal y) {
		this.nazivMjernePostaje = nazivMjernePostaje;
		this.mjesto = mjesto;
		this.x = x;
		this.y = y;
	}
	
	public MjernaPostaja(Integer id, String nazivMjernePostaje, Mjesto mjesto, BigDecimal x, BigDecimal y) {
		this.id = id;
		this.nazivMjernePostaje = nazivMjernePostaje;
		this.mjesto = mjesto;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Sortira listu senzora po odredenom kriteriju, a to je mjerna jedinica
	 * senzora.
	 * 
	 * @return senzori
	 */

	public List<Senzor> dohvatiSenzore() {
		return senzori.getSortedList();
	}
	
	public String toString() {
		return this.nazivMjernePostaje;
	}

	public String getNazivMjernePostaje() {
		return nazivMjernePostaje;
	}

	public void setNazivMjernePostaje(String nazivMjernePostaje) {
		this.nazivMjernePostaje = nazivMjernePostaje;
	}

	public Mjesto getMjesto() {
		return mjesto;
	}

	public void setMjesto(Mjesto mjesto) {
		this.mjesto = mjesto;
	}

	public BigDecimal getX() {
		return x;
	}

	public void setX(BigDecimal x) {
		this.x = x;
	}

	public BigDecimal getY() {
		return y;
	}

	public void setY(BigDecimal y) {
		this.y = y;
	}

	public Senzori<Senzor> getSenzori() {
		return senzori;
	}

	public void setSenzori(Senzori<Senzor> senzori) {
		this.senzori = senzori;
	}
}
