package hr.java.vjezbe.niti;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.javafx.MjestaController;
import hr.java.vjezbe.javafx.SenzoriController;
import javafx.scene.control.ToggleButton;

public class SenzoriNit implements Runnable {
	static int running = 1;

	@Override
	public void run() {
		try {
			Connection veza = BazaPodataka.spajanjeNaBazuPodataka();
			Statement statementSenzora = veza.createStatement();
			ResultSet resultSetSenzora = statementSenzora.executeQuery(
					"SELECT COUNT(*) AS broj FROM POSTAJE.SENZOR WHERE VRIJEDNOST < 0 AND AKTIVAN = TRUE;");
			while (resultSetSenzora.next()) {
				int broj = resultSetSenzora.getInt("broj");
				
				if (broj > 0 && running == 1) {
					MjestaController.prikaziGreskuZaSenzor(broj);
					System.out.printf("%s je ime niti a ovo je broj: %2d",
							Thread.currentThread().getName(), broj);
				}
			}
			BazaPodataka.zatvaranjeVezeNaBazuPodataka(veza);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
    public static void stop() {
        running = 0;
        //Thread.currentThread().interrupt();
    }
    public static void start() {
        running = 1;
        //Thread.currentThread().start();
    }

	
}
