package hr.java.vjezbe.sortiranje;

import java.util.Comparator;

import hr.java.vjezbe.entitet.Zupanija;

/**
 * Predstavlja klasu zupanija sorter koja implementira sucelje comparator sa
 * listom zupanija.
 * @author Kardum
 */

public class ZupanijaSorter implements Comparator<Zupanija> {

	/**
	 * Sortira listu zupanija po odredenom kriteriju, a to je naziv zupanije.
	 * 
	 * 
	 * @return zupanija
	 */

	@Override
	public int compare(Zupanija zupanija1, Zupanija zupanija2) {
		return zupanija1.getNazivZupanije().compareTo(zupanija2.getNazivZupanije());
	}
}
