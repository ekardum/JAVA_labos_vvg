package hr.java.vjezbe.entitet;

/**
 * Predstavlja sucelje koje æe biti implementirano u odredenim klasama, te koje
 * sadrzi apstraktne i normalne metode.
 * @author Kardum
 */

public interface RadioSondazna {
	
	/**
	 * Podesava visinu radio sondazne mjerne postaje.
	 * 
	 * @param visina
	 */

	public void podesiVisinu(int visina);
	
	/**
	 * Dohvaca visinu radio sondazne mjerne postaje.
	 * 
	 * @return s
	 */

	public int dohvatiVisinuPostaje();
	
	/**
	 * Provjerava je li dohvacena visina iznad vrijednosti 1000, te ako jest vraca
	 * ju na vrijednost 1000.
	 */

	private void provjeriVisinu() {
		if (dohvatiVisinuPostaje() > 1000) {
			podesiVisinu(1000);
		}
	}

	/**
	 * Uvecava vrijednost visine za 1, te nakon toga preko funkcije provjeriVisinu
	 * provjerava je li vrijednost visine iznad 1000.
	 */
	
	public default void povecajVisinu() {
		podesiVisinu(dohvatiVisinuPostaje() + 1);
		provjeriVisinu();
	}
}
