package hr.java.vjezbe.iznimke;

/**
 * Predstavlja klasu visoka temperatura koja nasljeduje klasu runtimeException.
 * @author Kardum
 */

public class NiskaTemperaturaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NiskaTemperaturaException() {
		super("Dogodila se pogreška u radu programa!");
	}

	public NiskaTemperaturaException(String message) {
		super(message);
	}

	public NiskaTemperaturaException(String message, Throwable cause) {
		super(message, cause);
	}

	public NiskaTemperaturaException(Throwable cause) {
		super(cause);
	}
}
