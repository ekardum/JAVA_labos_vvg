package hr.java.vjezbe.javafx;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.MjernaPostaja;
import hr.java.vjezbe.entitet.RadSenzora;
import hr.java.vjezbe.entitet.Senzor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class DodajSenzorController {
	private List<MjernaPostaja> listaPostaja;
	String idSenzoraZaIzmjenu;
	String slikaPath;

	@FXML
	protected TextField mjernaJedinicaTextField;
	@FXML
	protected TextField preciznostTextField;
	@FXML
	protected TextField vrijednostTextField;
	@FXML
	protected ComboBox<RadSenzora> radCombobox;
	@FXML
	protected ComboBox<MjernaPostaja> postajaCombobox;
	@FXML
	protected ImageView senzorImageView;
	
	//@FXML
	//protected TextField idTextField;
	@FXML
	private Button odaberiSlikuButton;
	@FXML
	private Button spremiButton;
	
	public void odaberiSliku(ActionEvent event) {
		FileChooser fc = new FileChooser();
		
		fc.getExtensionFilters().addAll(new ExtensionFilter("Slike", "*.jpg", "*.png", "*.gif"));
		
		File selectedFile = fc.showOpenDialog(null);
		
		if (selectedFile != null){
			Path source = Paths.get(selectedFile.getAbsolutePath()); //odabrana slika
			Path targetDir = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources");
			//"C:\\Users\\Kardum\\eclipse-workspace\\Kardum-9\\src\\main\\resources"
			
			String name = selectedFile.getName();
			Path target = targetDir.resolve(name);
			System.out.println("kopiranje u " + target);
			try {
				Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}

			
			final Image slika = new Image(selectedFile.toURI().toString());
			
			slikaPath = selectedFile.getName();
			System.out.println("ime slike :" + slikaPath);
			senzorImageView.setImage(slika);
		}else {
			
			System.out.println("Krivo odabrana datoteka!");
		}
	}

	@FXML
	public void initialize() throws Throwable {
		//slikaPath = senzorImageView.toString();
		try {
			for (RadSenzora radSenzora : RadSenzora.values()) {
				radCombobox.getItems().add(radSenzora);
			}
			radCombobox.getSelectionModel().selectFirst();

			listaPostaja = BazaPodataka.dohvatiPostaje();

			for (MjernaPostaja mjernaPostaja : listaPostaja) {
				postajaCombobox.getItems().add(mjernaPostaja);
			}
			postajaCombobox.getSelectionModel().selectFirst();

		} catch (Exception e) {
			e.printStackTrace();
		}
//		SenzoriController.obzListaSenzora = FXCollections.observableArrayList(PocetniEkranController.listaSenzora);
	}

	public void spremiPodatke() throws Throwable {
		RadSenzora radSenzora = radCombobox.getValue();
		String mjernaJedinica = mjernaJedinicaTextField.getText();
		String vrijednost = vrijednostTextField.getText();
		String preciznost = preciznostTextField.getText();
		MjernaPostaja mjernaPostaja = postajaCombobox.getValue();
		
		//String  idSenzora = idTextField.getText();
		Senzor senzor = new Senzor(mjernaJedinica, new BigDecimal(preciznost), new BigDecimal(vrijednost),
				radSenzora, mjernaPostaja,slikaPath);

		if (vrijednost.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Neuspješno spremanje senzora!");
			alert.setHeaderText("Neuspješno spremanje senzora!");
			alert.setContentText("Uneseni podaci za senzor su pogrešni, molim ponovno unesite ispravne podatke.");
			alert.showAndWait();
			Stage stage = (Stage) spremiButton.getScene().getWindow();
			stage.close();
		} else {
			try {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Uspješno spremanje senzora!");
				alert.setHeaderText("Uspješno spremanje senzora!");
				alert.setContentText("Uneseni podaci za senzor su uspješno spremljeni.");
				alert.showAndWait();
				Stage stage = (Stage) spremiButton.getScene().getWindow();
				stage.close();
				SenzoriController.dodajNoviSenzor(senzor, idSenzoraZaIzmjenu, slikaPath);
				
				PocetniEkranController.listaSenzora.clear();
				PocetniEkranController.listaSenzora = BazaPodataka.dohvatiSenzore();

			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
	
	/*public void updateSenzor() throws Throwable {
		RadSenzora radSenzora = radCombobox.getValue();
		String mjernaJedinica = mjernaJedinicaTextField.getText();
		String vrijednost = vrijednostTextField.getText();
		String preciznost = preciznostTextField.getText();
		MjernaPostaja mjernaPostaja = postajaCombobox.getValue();
		Senzor senzor = new Senzor(mjernaJedinica, new BigDecimal(preciznost), new BigDecimal(vrijednost),
				radSenzora, mjernaPostaja);

		if (vrijednost.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Neuspješno spremanje senzora!");
			alert.setHeaderText("Neuspješno spremanje senzora!");
			alert.setContentText("Uneseni podaci za senzor su pogrešni, molim ponovno unesite ispravne podatke.");
			alert.showAndWait();
			Stage stage = (Stage) spremiButton.getScene().getWindow();
			stage.close();
		} else {
			try {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Uspješno spremanje senzora!");
				alert.setHeaderText("Uspješno spremanje senzora!");
				alert.setContentText("Uneseni podaci za senzor su uspješno spremljeni.");
				alert.showAndWait();
				Stage stage = (Stage) spremiButton.getScene().getWindow();
				stage.close();

				BazaPodataka.updateSenzor(senzor);
				PocetniEkranController.listaSenzora.clear();
				PocetniEkranController.listaSenzora = BazaPodataka.dohvatiSenzore();

			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}*/
}
