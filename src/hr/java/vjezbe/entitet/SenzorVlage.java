package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * Predstavlja entitet senzor vlage koji nasljeduje klasu senzor.
 * @author Kardum
 */

public class SenzorVlage extends Senzor {

	/**
	 * Inicijalizira podatak o mjernoj jedinici i preciznosti.
	 * 
	 * 
	 */

	public SenzorVlage() {
		super(" % relativne vlažnosti", new BigDecimal(1));
	}

	/**
	 * Vraca string u kojem se nalaze svi podaci o senzoru vlage.
	 * 
	 * @return s
	 */

	@Override
	public String dohvatiVrijednost() {
		String s = "Vrijednost: " + getVrijednostSenzora() + getMjernaJedinicaSenzora() + ", Naèin rada: "
				+ getRadSenzora();
		return s;
	}
}
