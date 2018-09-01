package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

import hr.java.vjezbe.iznimke.NiskaTemperaturaException;
import hr.java.vjezbe.iznimke.VisokaTemperaturaException;



/**
 * Predstavlja entitet senzor temperature koji nasljeduje klasu senzor, te je
 * definiran elektronickom komponentom.
 * @author Kardum
 */

public class SenzorTemperature extends Senzor {

	private String elektronickaKomponenta;
	
	/**
	 * Inicijalizira podatak o mjernoj jedinici, preciznosti i elektronickoj
	 * komponenti.
	 * 
	 * @param elektronickaKomponenta
	 */

	public SenzorTemperature(String elektronickaKomponenta) {
		super(" °C", new BigDecimal(2));
		this.elektronickaKomponenta = elektronickaKomponenta;
	}
	
	/**
	 * Generira nasumicne int vrijednosti koje preko set metode upisuje u vrijednost
	 * senzora, te ako temperatura nije u zadanom intervalu baca exceptione.
	 * 
	 * @throws VisokaTemperaturaException
	 */

	public void generirajVrijednost() throws VisokaTemperaturaException {
		int randomNum = ThreadLocalRandom.current().nextInt(-50, 51);
		super.setVrijednostSenzora(new BigDecimal(randomNum));

		if (randomNum > 40) {
			throw new VisokaTemperaturaException(
					"Temperatura od " + randomNum + getMjernaJedinicaSenzora() + " je previsoka");
		}
		if (randomNum < -10) {
			throw new NiskaTemperaturaException(
					"Temperatura od " + randomNum + getMjernaJedinicaSenzora() + " je preniska");
		}
	}
	
	/**
	 * Vraca string u kojem se nalaze svi podaci o senzoru temperature.
	 * 
	 * @return s
	 */

	@Override
	public String dohvatiVrijednost() {
		String s = "Komponenta: " + elektronickaKomponenta + ", vrijednost: " + getVrijednostSenzora()
				+ getMjernaJedinicaSenzora() + ", Naèin rada: " + getRadSenzora();
		return s;
	}
}
