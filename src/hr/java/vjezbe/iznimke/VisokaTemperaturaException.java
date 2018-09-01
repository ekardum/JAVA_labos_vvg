package hr.java.vjezbe.iznimke;

/**
 * Predstavlja klasu visoka temperatura koja nasljeduje klasu exception.
 * @author Kardum
 */

public class VisokaTemperaturaException extends Exception {

	private static final long serialVersionUID = 1L;

	public VisokaTemperaturaException() {
		super("Dogodila se pogreška u radu programa!");
	}

	public VisokaTemperaturaException(String message) {
		super(message);
	}

	public VisokaTemperaturaException(String message, Throwable cause) {
		super(message, cause);
	}

	public VisokaTemperaturaException(Throwable cause) {
		super(cause);
	}	
}
