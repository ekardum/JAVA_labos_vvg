package hr.java.vjezbe.javafx;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.Mjesto;
import hr.java.vjezbe.entitet.VrstaMjesta;
import hr.java.vjezbe.entitet.Zupanija;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DodajMjestoController {
	private List<Zupanija> listaZupanija;

	@FXML
	private TextField nazivTextfield;
	@FXML
	private ComboBox<Zupanija> zupanijaCombobox;
	@FXML
	private ComboBox<VrstaMjesta> vrstaCombobox;
	@FXML
	private Button spremiButton;

	@FXML
	public void initialize() throws Throwable {
		try {
			listaZupanija = BazaPodataka.dohvatiZupanije();

			for (Zupanija zupanija : listaZupanija) {
				zupanijaCombobox.getItems().add(zupanija);
			}
			zupanijaCombobox.getSelectionModel().selectFirst();

			for (VrstaMjesta vrstaMjesta : VrstaMjesta.values()) {
				vrstaCombobox.getItems().add(vrstaMjesta);
			}
			vrstaCombobox.getSelectionModel().selectFirst();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		MjestaController.obzListaMjesta = FXCollections.observableArrayList(PocetniEkranController.listaMjesta);
	}

	public void spremiPodatke() throws Throwable {
		String naziv = nazivTextfield.getText();
		int noviId = getZadnjiId() + 1;
		Zupanija zupanija = zupanijaCombobox.getValue();
		VrstaMjesta vrstaMjesta = vrstaCombobox.getValue();
		Mjesto mjesto = new Mjesto(noviId, naziv, vrstaMjesta, zupanija);
		
		if (naziv.isEmpty() || naziv.matches(".*\\d+.*")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Neuspješno spremanje mjesta!");
			alert.setHeaderText("Neuspješno spremanje mjesta!");
			alert.setContentText("Uneseni podaci za mjesto su pogrešni, molim ponovno unesite ispravne podatke.");
			alert.showAndWait();
			Stage stage = (Stage) spremiButton.getScene().getWindow();
			stage.close();
		} else {
			try {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Uspješno spremanje mjesta!");
				alert.setHeaderText("Uspješno spremanje mjesta!");
				alert.setContentText("Uneseni podaci za mjesto su uspješno spremljeni.");
				alert.showAndWait();
				Stage stage = (Stage) spremiButton.getScene().getWindow();
				stage.close();
				MjestaController.dodajNovoMjesto(mjesto);
						
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	public int getZadnjiId() throws SQLException, IOException {
		return BazaPodataka.dohvatiMjesta().size();
	}
}
