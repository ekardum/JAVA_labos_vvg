package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Predstavlja entitet senzori, genericke klase, koja je definirana listom
 * senzora.
 * @author Kardum
 */

public class Senzori<T extends Senzor> {
	private List<T> listaSenzora;

	/**
	 * Inicijalizira podatak o senzorima.
	 * 
	 * 
	 */

	public Senzori() {
		listaSenzora = new ArrayList<>();
	}

	/**
	 * Pridodaje indeks svakom objektu senzora.
	 * 
	 * @param i
	 * @return senzor
	 */

	public Senzor get(Integer i) {
		Senzor senzor;
		senzor = this.listaSenzora.get(i);
		return senzor;
	}

	/**
	 * Dodaje u listu objekt senzor, ciji tip jos nije definiran.
	 * 
	 * @param optional
	 */

	public void add(T optional) {
		listaSenzora.add(optional);
	}

	/**
	 * Sortira listu senzora po odredenom kriteriju, a to je mjerna jedinica
	 * senzora.
	 * 
	 * 
	 * @return listaSenzora
	 */

	public List<T> getSortedList() {
		Comparator<Senzor> c = (p1, p2) -> p1.getMjernaJedinicaSenzora().compareTo(p2.getMjernaJedinicaSenzora());
		listaSenzora.sort(c);
		return listaSenzora;
	}
	
	public List<T> getSenzori() {
		return listaSenzora;
	}
	
	public Optional<T> pronadiSenzor(int id) {
		Optional<T> senzor = listaSenzora.stream().filter(s -> s.getId() == id).findAny();
		return senzor;
	}
}
