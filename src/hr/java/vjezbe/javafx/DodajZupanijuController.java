package hr.java.vjezbe.javafx;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.Drzava;
import hr.java.vjezbe.entitet.Zupanija;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DodajZupanijuController {
	private List<Drzava> listaDrzava;

	@FXML
	private TextField nazivTextfield;
	@FXML
	private ComboBox<Drzava> drzavaCombobox;
	@FXML
	private Button spremiButton;

	@FXML
	public void initialize() throws Throwable {
		try {
			listaDrzava = BazaPodataka.dohvatiDrzavu();

			for (Drzava drzava : listaDrzava) {
				drzavaCombobox.getItems().add(drzava);
			}
			drzavaCombobox.getSelectionModel().selectFirst();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		ZupanijeController.obzListaZupanija = FXCollections.observableArrayList(PocetniEkranController.listaZupanija);
	}

	public void spremiPodatke() throws Throwable {
		String naziv = nazivTextfield.getText();
		int noviId = getZadnjiId() + 1;
		Drzava drzava = drzavaCombobox.getValue();
		Zupanija zupanija = new Zupanija(noviId, naziv, drzava);
		
		if (naziv.isEmpty() || naziv.matches(".*\\d+.*")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Neuspješno spremanje županije!");
			alert.setHeaderText("Neuspješno spremanje županije!");
			alert.setContentText("Uneseni podaci za županiju su pogrešni, molim ponovno unesite ispravne podatke.");
			alert.showAndWait();
			Stage stage = (Stage) spremiButton.getScene().getWindow();
			stage.close();
		} else {
			try {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Uspješno spremanje županije!");
				alert.setHeaderText("Uspješno spremanje županije!");
				alert.setContentText("Uneseni podaci za županiju su uspješno spremljeni.");
				alert.showAndWait();
				Stage stage = (Stage) spremiButton.getScene().getWindow();
				stage.close();
				ZupanijeController.dodajNovuZupaniju(zupanija);
				
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	public int getZadnjiId() throws SQLException, IOException {
		return BazaPodataka.dohvatiZupanije().size();
	}
}
