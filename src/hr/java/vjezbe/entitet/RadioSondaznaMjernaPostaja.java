package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * Predstavlja entitet radio sondazne mjerne postaje koji nasljeduje klasu
 * MjernaPostaja i implementira sucelje RadioSondazna.
 * @author Kardum
 */

public class RadioSondaznaMjernaPostaja extends MjernaPostaja implements RadioSondazna {
	private int visina;

	/**
	 * Inicijalizira podatak o nazivu mjerne postaje, visini, mjestu, geografskoj
	 * tocki i senzorima.
	 * 
	 * @param nazivMjernePostaje
	 * @param visina
	 * @param mjesto
	 * @param geografskaTocka
	 * @param senzori
	 */

	public RadioSondaznaMjernaPostaja(String nazivMjernePostaje, int visina, Mjesto mjesto, BigDecimal x,
			BigDecimal y) {
		super(nazivMjernePostaje, mjesto, x, y);
		this.visina = visina;
	}

	public RadioSondaznaMjernaPostaja(Integer id, String nazivMjernePostaje, int visina, Mjesto mjesto, BigDecimal x,
			BigDecimal y) {
		super(nazivMjernePostaje, mjesto, x, y);
		this.visina = visina;
	}

	/**
	 * Izmjenjuje vrijednost varijable visina.
	 * 
	 * @param visina
	 */

	@Override
	public void podesiVisinu(int visina) {
		this.visina = visina;
	}

	/**
	 * Dohvaca varijablu visina.
	 * 
	 * @return visina
	 */

	@Override
	public int dohvatiVisinuPostaje() {
		return this.visina;
	}
}
