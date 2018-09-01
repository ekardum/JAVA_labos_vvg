package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Predstavlja entitet mjerne postaje, genericke klase, koja je definirana
 * listom mjernih postaja.
 * @author Kardum
 */

public class MjernePostaje<T extends MjernaPostaja> {
	private List<MjernaPostaja> listaMjernihPostaja;

	/**
	 * Inicijalizira podatak o mjernim postajama.
	 * 
	 * 
	 */

	public MjernePostaje() {
		listaMjernihPostaja = new ArrayList<>();
	}
	
	/** 
	 * Pridodaje indeks svakom objektu mjerne postaje.
	 * 
	 * @param i
	 * @return mjernaPostaja
	 */

	public MjernaPostaja get(Integer i) {
		MjernaPostaja mjernaPostaja;
		mjernaPostaja = this.listaMjernihPostaja.get(i);
		return mjernaPostaja;
	}
	
	/**
	 * Dodaje u listu objekt mjerna postaja, ciji tip jos nije definiran.
	 * 
	 * @param mjernaPostaja
	 */

	public void add(T mjernaPostaja) {
		listaMjernihPostaja.add(mjernaPostaja);
	}

	/**
	 * Sortira listu mjernih postaja po odredenom kriteriju, a to je naziv mjerne
	 * postaje.
	 * 
	 * @return listaMjernihPostaja
	 */

	public List<MjernaPostaja> getSortedList() {
		Comparator<MjernaPostaja> c = (p1, p2) -> p1.getNazivMjernePostaje().compareTo(p2.getNazivMjernePostaje());
		listaMjernihPostaja.sort(c);
		return listaMjernihPostaja;
	}
}
