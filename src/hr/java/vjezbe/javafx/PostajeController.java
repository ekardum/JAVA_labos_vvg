package hr.java.vjezbe.javafx;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.MjernaPostaja;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import javafx.util.Callback;

public class PostajeController {

	final PseudoClass errorClass = PseudoClass.getPseudoClass("error");
	static ObservableList<MjernaPostaja> obzListaPostaja;

	@FXML
	private TextField postajeFilterTextField;
	@FXML
	protected TableView<MjernaPostaja> postajeTableView;
	@FXML
	protected TableColumn<MjernaPostaja, String> nazivColumn;
	@FXML
	protected TableColumn<MjernaPostaja, String> mjestoColumn;
	@FXML
	protected TableColumn<MjernaPostaja, String> geografskaTockaColumn;

	@FXML
	public void initialize() throws Throwable {
		try {
			nazivColumn.setCellValueFactory(new PropertyValueFactory<>("nazivMjernePostaje"));

			mjestoColumn.setCellValueFactory(
					new Callback<TableColumn.CellDataFeatures<MjernaPostaja, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(CellDataFeatures<MjernaPostaja, String> param) {
							return new ReadOnlyObjectWrapper<String>(param.getValue().getMjesto().getNazivMjesta());
						}
					});

			geografskaTockaColumn.setCellValueFactory(
					new Callback<TableColumn.CellDataFeatures<MjernaPostaja, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(CellDataFeatures<MjernaPostaja, String> param) {
							return new ReadOnlyObjectWrapper<String>(
									param.getValue().getX().toString() + " " + param.getValue().getY().toString());
						}
					});
			
			postajeTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event){
				
				if (event.getButton() == MouseButton.SECONDARY) {
				ContextMenu cm = new ContextMenu();
				
			    final MenuItem item1 = new MenuItem("Obrisi");
			    final MenuItem item2 = new MenuItem("Odustani");

			    //cm.getItems().addAll(item1, item2);
			    //Window prozor = null;
				//cm.show(prozor.getScene().getWindow());
			    
				//cm.show(null);
			//postajeTableView.
				
				Integer idPostajeZaBrisanje = postajeTableView.getSelectionModel().getSelectedItem().getId();
				postajeTableView.getItems().removeAll(postajeTableView.getSelectionModel().getSelectedItem());

				try {
					BazaPodataka.obrisiPostaju(idPostajeZaBrisanje);
					postajeTableView.refresh();
					PocetniEkranController.listaPostaja.clear();
					PocetniEkranController.listaPostaja = BazaPodataka.dohvatiPostaje();
				} catch (SQLException | IOException e) {
					e.printStackTrace();
				}
				}
			
				}
				});

		} catch (Exception e) {
			e.printStackTrace();
		}
		obzListaPostaja = FXCollections.observableArrayList(PocetniEkranController.listaPostaja);
	}

	public void prikaziPostaje() {
		List<MjernaPostaja> filtriranePostaje = new ArrayList<>();
		if (postajeFilterTextField.getText().isEmpty() == false) {
			filtriranePostaje = PocetniEkranController.listaPostaja.stream()
					.filter(m -> m.getNazivMjernePostaje().contains(postajeFilterTextField.getText()))
					.collect(Collectors.toList());
		} else {
			postajeFilterTextField.pseudoClassStateChanged(errorClass, true);
			filtriranePostaje = PocetniEkranController.listaPostaja;
		}
		obzListaPostaja = FXCollections.observableArrayList(filtriranePostaje);
		postajeTableView.setItems(obzListaPostaja);
		postajeTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	public static void dodajNovuPostaju(MjernaPostaja novaPostaja) throws SQLException, IOException {
		BazaPodataka.spremiMjernuPostaju(novaPostaja);
		obzListaPostaja.add(novaPostaja);
	}
}
