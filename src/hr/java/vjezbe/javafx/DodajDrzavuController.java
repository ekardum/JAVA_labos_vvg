package hr.java.vjezbe.javafx;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.Drzava;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DodajDrzavuController {

	@FXML
	private TextField nazivTextfield;
	@FXML
	private TextField povrsinaCombobox;
	@FXML
	private Button spremiButton;

	@FXML
	public void initialize() throws Throwable {
		try {
//			DrzaveController.obzListaDrzava = FXCollections.observableArrayList(PocetniEkranController.listaDrzava);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public void spremiPodatke() throws Throwable {
		String naziv = nazivTextfield.getText();
		Integer noviId = getZadnjiId() + 1;
		String povrsina = povrsinaCombobox.getText();
		Drzava drzava = new Drzava(noviId, naziv, new BigDecimal(povrsina));

		if (naziv.isEmpty() || naziv.matches(".*\\d+.*") || povrsina.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Neuspješno spremanje države!");
			alert.setHeaderText("Neuspješno spremanje države!");
			alert.setContentText("Uneseni podaci za državu su pogrešni, molim ponovno unesite ispravne podatke.");
			alert.showAndWait();
			Stage stage = (Stage) spremiButton.getScene().getWindow();
			stage.close();
		} else {
			try {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Uspješno spremanje države!");
				alert.setHeaderText("Uspješno spremanje države!");
				alert.setContentText("Uneseni podaci za državu su uspješno spremljeni.");
				alert.showAndWait();
				Stage stage = (Stage) spremiButton.getScene().getWindow();
				stage.close();
				DrzaveController.dodajNovuDrzavu(drzava);

			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	public int getZadnjiId() throws SQLException, IOException {
		return BazaPodataka.dohvatiDrzavu().size();
	}
}
