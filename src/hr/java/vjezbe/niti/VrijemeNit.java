package hr.java.vjezbe.niti;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import hr.java.vjezbe.javafx.MjestaController;
import javafx.application.Platform;
import javafx.scene.control.TextField;

public class VrijemeNit implements Runnable{
	
	static int running = 1;
	private static LocalDateTime trenutnoVrijeme;
	DateTimeFormatter formatter;
	static String formatiranoVrijeme;
	
	TextField vrijemeText;

	
	@Override
	public void run() {
		if (running == 1) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				
				trenutnoVrijeme = LocalDateTime.now();
				//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
				//formatiranoVrijeme = trenutnoVrijeme.format(formatter);
				//vrijemeLabel.setText(trenutnoVrijeme.toString());
				//MjestaController.refreshVrijeme();
				//vrijemeText.setText(trenutnoVrijeme.toString());
				
			}
		});
	}
	}
	


	public VrijemeNit(TextField vrijemeText) {
		trenutnoVrijeme = LocalDateTime.now();
		vrijemeText.setText(trenutnoVrijeme.toString());
	}
	

    public static void stop() {
        running = 0;
        //Thread.currentThread().interrupt();
    }
    public static void start() {
        running = 1;
        //Thread.currentThread().start();
    }

	public static LocalDateTime getTrenutnoVrijeme() {
		return trenutnoVrijeme;
	}

	public static String getFormatiranoVrijeme() {
		return formatiranoVrijeme;
	}

	

    
}
