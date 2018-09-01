package hr.java.vjezbe.javafx;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.Senzor;
import hr.java.vjezbe.niti.SenzoriNit;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.util.Callback;

public class SenzoriController {

	final PseudoClass errorClass = PseudoClass.getPseudoClass("error");
	static ObservableList<Senzor> obzListaSenzora;

	@FXML
	private TextField senzoriFilterTextField;
	@FXML
	private TableView<Senzor> senzoriTableView;
	@FXML
	private TableColumn<Senzor, String> mjernaJedinicaColumn;
	@FXML
	private TableColumn<Senzor, String> preciznostColumn;
	@FXML
	private TableColumn<Senzor, String> vrijednostColumn;
	@FXML
	private TableColumn<Senzor, String> radSenzoraColumn;
	@FXML
	private TableColumn<Senzor, String> postajaColumn;
	@FXML
	private ToggleButton provjeraSenzoraToggle;

	@FXML
	public void initialize() throws Throwable {
		try {
			provjeraSenzoraToggle.setSelected(true);
			mjernaJedinicaColumn.setCellValueFactory(
					new Callback<TableColumn.CellDataFeatures<Senzor, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(CellDataFeatures<Senzor, String> param) {
							return new ReadOnlyObjectWrapper<String>(
									param.getValue().getMjernaJedinicaSenzora().toString());
						}
					});
			
			preciznostColumn.setCellValueFactory(
					new Callback<TableColumn.CellDataFeatures<Senzor, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(CellDataFeatures<Senzor, String> param) {
							return new ReadOnlyObjectWrapper<String>(
									param.getValue().getPreciznostSenzora().toString());
						}
					});

			vrijednostColumn.setCellValueFactory(
					new Callback<TableColumn.CellDataFeatures<Senzor, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(CellDataFeatures<Senzor, String> param) {
							return new ReadOnlyObjectWrapper<String>(
									param.getValue().getVrijednostSenzora().toString());
						}
					});
			
			radSenzoraColumn.setCellValueFactory(
					new Callback<TableColumn.CellDataFeatures<Senzor, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(CellDataFeatures<Senzor, String> param) {
							return new ReadOnlyObjectWrapper<String>(
									param.getValue().getRadSenzora().toString());
						}
					});
			
			postajaColumn.setCellValueFactory(
					new Callback<TableColumn.CellDataFeatures<Senzor, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(CellDataFeatures<Senzor, String> param) {
							return new ReadOnlyObjectWrapper<String>(
									param.getValue().getMjernaPostaja().getNazivMjernePostaje());
						}
					});
			
			senzoriTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event){
					//System.out.println(System.getProperty("user.dir"));
					//UPDATE
					FXMLLoader loader = new FXMLLoader(Main.class.getResource("/NoviSenzor.fxml"));
					BorderPane noviSenzorPane = null;
					try {
						noviSenzorPane = loader.load();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Scene scene = new Scene(noviSenzorPane, 800, 600);
					scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
					Stage stage = new Stage();
					stage.setScene(scene);
					DodajSenzorController kontroler = loader.getController();
											
					//kontroler.postajaZaIzmjenu = postajeTableView.getSelectionModel().getSelectedItem().getId();
					kontroler.mjernaJedinicaTextField.setText(senzoriTableView.getSelectionModel().getSelectedItem().getMjernaJedinicaSenzora());
					kontroler.preciznostTextField.setText(senzoriTableView.getSelectionModel().getSelectedItem().getPreciznostSenzora().toString());
					kontroler.vrijednostTextField.setText(senzoriTableView.getSelectionModel().getSelectedItem().getVrijednostSenzora().toString());
					kontroler.radCombobox.getSelectionModel().select(senzoriTableView.getSelectionModel().getSelectedItem().getRadSenzora());
					kontroler.postajaCombobox.getSelectionModel().select(senzoriTableView.getSelectionModel().getSelectedItem().getMjernaPostaja());
					//kontroler.idTextField.setText(senzoriTableView.getSelectionModel().getSelectedItem().getId().toString());
					kontroler.idSenzoraZaIzmjenu = senzoriTableView.getSelectionModel().getSelectedItem().getId().toString();
					
					if(senzoriTableView.getSelectionModel().getSelectedItem().getSlikaSenzora() != null) {
						kontroler.senzorImageView.setImage(new Image(senzoriTableView.getSelectionModel().getSelectedItem().getSlikaSenzora()));
					}else
					{
						kontroler.senzorImageView.setImage(new Image("No_Image_Available.png"));
					}
					//kontroler.senzorImageView.setImage(new Image(senzoriTableView.getSelectionModel().getSelectedItem().getSlikaSenzora()));
					kontroler.slikaPath = senzoriTableView.getSelectionModel().getSelectedItem().getSlikaSenzora();
					
					
					stage.show();
					//BRISANJE
					/*Integer idSenzoraZaBrisanje = senzoriTableView.getSelectionModel().getSelectedItem().getId();
					senzoriTableView.getItems().removeAll(senzoriTableView.getSelectionModel().getSelectedItem());

					try {
						BazaPodataka.obrisiSenzor(idSenzoraZaBrisanje);
						senzoriTableView.refresh();
						PocetniEkranController.listaSenzora.clear();
						PocetniEkranController.listaSenzora = BazaPodataka.dohvatiSenzore();
					} catch (SQLException | IOException e) {
						e.printStackTrace();
					}*/	
				}
			});

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		obzListaSenzora = FXCollections.observableArrayList(PocetniEkranController.listaSenzora);
	}

	
	public void aktivan() throws SQLException, IOException {
		
		if (provjeraSenzoraToggle.isSelected()) {
			
			SenzoriNit.start();
			/*
			Connection veza = BazaPodataka.spajanjeNaBazuPodataka();
			veza.setAutoCommit(false);
			try {
				PreparedStatement updateSenzorAktivan = veza
						.prepareStatement("UPDATE POSTAJE.SENZOR SET AKTIVAN = TRUE;");
				updateSenzorAktivan.executeUpdate();
				veza.commit();
				veza.setAutoCommit(true);
				
			} catch (Throwable ex) {
				veza.rollback();
				throw ex;
			}
			BazaPodataka.zatvaranjeVezeNaBazuPodataka(veza);*/
		} else {
			SenzoriNit.stop();
			/*
			Connection veza = BazaPodataka.spajanjeNaBazuPodataka();
			veza.setAutoCommit(false);
			try {
				PreparedStatement updateSenzorAktivan = veza
						.prepareStatement("UPDATE POSTAJE.SENZOR SET AKTIVAN = FALSE;");
				updateSenzorAktivan.executeUpdate();
				veza.commit();
				veza.setAutoCommit(true);
				
			} catch (Throwable ex) {
				veza.rollback();
				throw ex;
			}
			BazaPodataka.zatvaranjeVezeNaBazuPodataka(veza);*/
		}
	}
	
	public void prikaziSenzore() {
		List<Senzor> filtriraniSenzori = new ArrayList<>();
		senzoriFilterTextField.pseudoClassStateChanged(errorClass, true);
		filtriraniSenzori = PocetniEkranController.listaSenzora;
		obzListaSenzora = FXCollections.observableArrayList(filtriraniSenzori);
		senzoriTableView.setItems(obzListaSenzora);
		senzoriTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	public static void dodajNoviSenzor(Senzor noviSenzor, String id, String slikaPath) throws SQLException, IOException {
		BazaPodataka.spremiSenzor(noviSenzor, id, slikaPath);
		obzListaSenzora.add(noviSenzor);
	}
	

}
