package hr.java.vjezbe.javafx;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.MjernaPostaja;
import hr.java.vjezbe.entitet.Mjesto;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DodajMjernuPostajuController {
	private List<Mjesto> listaMjesta;

	@FXML
	private TextField nazivTextfield;
	@FXML
	private TextField koordinataXCombobox;
	@FXML
	private TextField koordinataYCombobox;
	@FXML
	private ComboBox<Mjesto> mjestoCombobox;
	@FXML
	private Button spremiButton;

	@FXML
	public void initialize() throws Throwable {
		try {	
			listaMjesta = BazaPodataka.dohvatiMjesta();
			for (Mjesto mjesto : listaMjesta) {
				mjestoCombobox.getItems().add(mjesto);
			}
			mjestoCombobox.getSelectionModel().selectFirst();

		} catch (Exception e) {
			e.printStackTrace();
		}
//		PostajeController.obzListaPostaja = FXCollections.observableArrayList(PocetniEkranController.listaPostaja);
	}

	public void spremiPodatke() throws Throwable {	
		
		try {
		
		String naziv = nazivTextfield.getText();
		int noviId = getZadnjiId() + 1;
		Mjesto mjesto = mjestoCombobox.getValue();
		String x = koordinataXCombobox.getText();
		String y = koordinataYCombobox.getText();
		
		MjernaPostaja mjernaPostaja = new MjernaPostaja(noviId, naziv, mjesto, new BigDecimal(x), new BigDecimal(y));

		if (naziv.isEmpty() || naziv.matches(".*\\d+.*") || x.isEmpty() || y.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Neuspješno spremanje mjerne postaje!");
			alert.setHeaderText("Neuspješno spremanje mjerne postaje!");
			alert.setContentText("Uneseni podaci za mjernu postaju su pogrešni, molim ponovno unesite ispravne podatke.");
			alert.showAndWait();
			Stage stage = (Stage) spremiButton.getScene().getWindow();
			stage.close();
		} else {			
			try {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Uspješno spremanje mjerne postaje!");
				alert.setHeaderText("Uspješno spremanje mjerne postaje!");
				alert.setContentText("Uneseni podaci za mjernu postaju su uspješno spremljeni.");
				alert.showAndWait();
				Stage stage = (Stage) spremiButton.getScene().getWindow();
				stage.close();
				PostajeController.dodajNovuPostaju(mjernaPostaja);
				
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}catch(NumberFormatException e){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Neuspješno spremanje mjerne postaje!");
		alert.setHeaderText("Neuspješno spremanje mjerne postaje!");
		alert.setContentText("Krivo su unesene koordinate - morate unijeti broj!");
		alert.showAndWait();
		Stage stage = (Stage) spremiButton.getScene().getWindow();
		stage.close();
	}
		finally {
		
	}
}

	public int getZadnjiId() throws SQLException, IOException {
		return BazaPodataka.dohvatiPostaje().size();
	}
}
