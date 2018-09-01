package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * Predstavlja apstraktnu klasu senzor koja je definirana mjernom jedinicom,
 * preciznoscu, vrijednoscu senzora i radom senzora.
 * @author Kardum
 *
 */

public class Senzor extends BazniEntitet {
	private String mjernaJedinicaSenzora;
	private BigDecimal preciznostSenzora;
	private BigDecimal vrijednostSenzora;
	private RadSenzora radSenzora;
	private MjernaPostaja mjernaPostaja;
	private String slikaSenzora;

	/**
	 * Inicijalizira podatak o mjernoj jedinici senzora i preciznosti senzora.
	 * 
	 * @param mjernaJedinicaSenzora
	 * @param preciznostSenzora
	 */

	public Senzor(String mjernaJedinicaSenzora, BigDecimal preciznostSenzora) {
		this.mjernaJedinicaSenzora = mjernaJedinicaSenzora;
		this.preciznostSenzora = preciznostSenzora;
	}

	public Senzor(String mjernaJedinicaSenzora, BigDecimal preciznostSenzora, BigDecimal vrijednostSenzora, 
			RadSenzora radSenzora, MjernaPostaja mjernaPostaja, String slikaSenzora) {
		this.vrijednostSenzora = vrijednostSenzora;
		this.preciznostSenzora = preciznostSenzora;
		this.radSenzora = radSenzora;
		this.mjernaJedinicaSenzora = mjernaJedinicaSenzora;
		this.mjernaPostaja = mjernaPostaja;
		this.slikaSenzora = slikaSenzora;
	}

	/**
	 * Dohvaca vrijednost senzora.
	 */

	public String dohvatiVrijednost() {
		return dohvatiVrijednost();
	}

	public String getMjernaJedinicaSenzora() {
		return mjernaJedinicaSenzora;
	}

	public void setMjernaJedinicaSenzora(String mjernaJedinicaSenzora) {
		this.mjernaJedinicaSenzora = mjernaJedinicaSenzora;
	}

	public BigDecimal getPreciznostSenzora() {
		return preciznostSenzora;
	}

	public void setPreciznostSenzora(BigDecimal preciznostSenzora) {
		this.preciznostSenzora = preciznostSenzora;
	}

	public BigDecimal getVrijednostSenzora() {
		return vrijednostSenzora;
	}

	public void setVrijednostSenzora(BigDecimal vrijednostSenzora) {
		this.vrijednostSenzora = vrijednostSenzora;
	}

	public RadSenzora getRadSenzora() {
		return radSenzora;
	}

	public void setRadSenzora(RadSenzora radSenzora) {
		this.radSenzora = radSenzora;
	}

	public MjernaPostaja getMjernaPostaja() {
		return mjernaPostaja;
	}

	public void setMjernaPostaja(MjernaPostaja mjernaPostaja) {
		this.mjernaPostaja = mjernaPostaja;
	}

	public String getSlikaSenzora() {
		return slikaSenzora;
	}

	public void setSlikaSenzora(String slikaSenzora) {
		this.slikaSenzora = slikaSenzora;
	}
	
}
