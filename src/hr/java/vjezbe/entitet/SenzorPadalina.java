package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * Predstavlja entitet senzor padalina koji nasljeduje klasu senzor, te je
 * definiran velicinom senzora.
 * @author Kardum
 */

public class SenzorPadalina extends Senzor {
	private String velicinaSenzora;
	
	/**
	 * Inicijalizira podatak o mjernoj jedinici, preciznosti i velicini senzora.
	 * 
	 * @param velicinaSenzora
	 */

	public SenzorPadalina(String velicinaSenzora) {
		super(" mm", new BigDecimal(5));
		this.velicinaSenzora = velicinaSenzora;
	}

	/**
	 * Vraca string u kojem se nalaze svi podaci o senzoru padalina.
	 * 
	 * @return s
	 */
	
	@Override
	public String dohvatiVrijednost() {
		String s = "Velicina: " + velicinaSenzora + ", vrijednost: " + getVrijednostSenzora()
				+ getMjernaJedinicaSenzora() + ", Nacin rada: " + getRadSenzora();
		return s;
	}

}
