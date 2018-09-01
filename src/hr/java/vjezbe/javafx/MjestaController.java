package hr.java.vjezbe.javafx;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.Mjesto;
import hr.java.vjezbe.niti.SenzoriNit;
import hr.java.vjezbe.niti.VrijemeNit;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MjestaController {
	
	final PseudoClass errorClass = PseudoClass.getPseudoClass("error");
	static ObservableList<Mjesto> obzListaMjesta;

	@FXML
	private TextField mjestaFilterTextField;
	@FXML
	private TableView<Mjesto> mjestaTableView;
	@FXML
	private TableColumn<Mjesto, String> nazivColumn;
	@FXML
	private TableColumn<Mjesto, String> tipColumn;
	@FXML
	private TableColumn<Mjesto, String> zupanijaColumn;
	@FXML 
    protected static Label vrijemeLabel;
	@FXML
	private ToggleButton vrijemeToggle;
	@FXML
	private TextField vrijemeText;

	@FXML
	public void initialize() throws Throwable {	
		try {
			nazivColumn.setCellValueFactory(new PropertyValueFactory<>("nazivMjesta"));

			tipColumn.setCellValueFactory(
					new Callback<TableColumn.CellDataFeatures<Mjesto, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(CellDataFeatures<Mjesto, String> param) {
							return new ReadOnlyObjectWrapper<String>(param.getValue().getVrstaMjesta().toString());
						}
					});

			zupanijaColumn.setCellValueFactory(
					new Callback<TableColumn.CellDataFeatures<Mjesto, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(CellDataFeatures<Mjesto, String> param) {
							return new ReadOnlyObjectWrapper<String>(param.getValue().getZupanija().getNazivZupanije());
						}
					});		
			
			mjestaTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event){

			
				//mjestaTableView.getItems().removeAll(mjestaTableView.getSelectionModel().getSelectedItem());
				
				
				FXMLLoader loader = new FXMLLoader(Main.class.getResource("/PostajePoMjestu.fxml"));
				BorderPane postajaPane = null;
				try {
					postajaPane = loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Scene scene = new Scene(postajaPane, 800, 600);
				scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
				Stage stage = new Stage();
				stage.setScene(scene);
				PostajePoMjestuController kontroler = loader.getController();
				
				kontroler.idPostaje = mjestaTableView.getSelectionModel().getSelectedItem().getId();
				
				stage.show();

				}
			
				
				});
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		PocetniEkranController.listaDrzava = BazaPodataka.dohvatiDrzavu();
		PocetniEkranController.listaZupanija = BazaPodataka.dohvatiZupanije();
		PocetniEkranController.listaMjesta = BazaPodataka.dohvatiMjesta();
		PocetniEkranController.listaSenzora = BazaPodataka.dohvatiSenzore();
		PocetniEkranController.listaPostaja = BazaPodataka.dohvatiPostaje();
		obzListaMjesta = FXCollections.observableArrayList(PocetniEkranController.listaMjesta);

		//LocalDateTime trenutnoVrijeme = LocalDateTime.now();
		//vrijemeLabel.setText("4343");
		//vrijemeText.setText(VrijemeNit.getTrenutnoVrijeme().toString());
		//za threadove
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		SenzoriNit senzoriNit = new SenzoriNit();
		scheduler.scheduleAtFixedRate(senzoriNit, 0, 10, TimeUnit.SECONDS);
		
		//vrijeme
		ScheduledExecutorService vrijemeScheduler = Executors.newScheduledThreadPool(1);
		VrijemeNit vrijemeNit = new VrijemeNit(vrijemeText);
		vrijemeScheduler.scheduleAtFixedRate(vrijemeNit, 0, 1, TimeUnit.SECONDS);
		
	}
	
	/*public static void refreshVrijeme() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				vrijemeText.setText(VrijemeNit.getTrenutnoVrijeme().toString());
			}
		});
	}*/
	
	
	public void ukljuciVrijeme() {
		if (vrijemeToggle.isSelected()) {
			
			VrijemeNit.start();

		}else {
			VrijemeNit.stop();
		}
	}
	
	public static void prikaziGreskuZaSenzor(int broj) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Senzori < 0");
				alert.setHeaderText("Senzori < 0 ");
				alert.setContentText("Broj senzora ispod nule: " + broj);
				alert.showAndWait();
			}
		});
	}

	public void prikaziMjesta() {
		List<Mjesto> filtriranaMjesta = new ArrayList<>();
		if (mjestaFilterTextField.getText().isEmpty() == false) {
			filtriranaMjesta = PocetniEkranController.listaMjesta.stream()
					.filter(m -> m.getNazivMjesta().contains(mjestaFilterTextField.getText()))
					.collect(Collectors.toList());
		} else {
			mjestaFilterTextField.pseudoClassStateChanged(errorClass, true);
			filtriranaMjesta = PocetniEkranController.listaMjesta;
		}
		obzListaMjesta = FXCollections.observableArrayList(filtriranaMjesta);
		mjestaTableView.setItems(obzListaMjesta);
		mjestaTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	public static void dodajNovoMjesto(Mjesto novoMjesto) throws SQLException, IOException {
		BazaPodataka.spremiMjesto(novoMjesto);
		obzListaMjesta.add(novoMjesto);
	}
}
