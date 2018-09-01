package hr.java.vjezbe.javafx;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.Drzava;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class DrzaveController {
	
	final PseudoClass errorClass = PseudoClass.getPseudoClass("error");
	static ObservableList<Drzava> obzListaDrzava;

	@FXML
	private TextField drzaveFilterTextField;
	@FXML
	private TableView<Drzava> drzaveTableView;
	@FXML
	private TableColumn<Drzava, String> nazivColumn;
	@FXML
	private TableColumn<Drzava, String> povrsinaColumn;

	@FXML
	public void initialize() throws Throwable {
		try {
			nazivColumn.setCellValueFactory(new PropertyValueFactory<>("nazivDrzave"));

			povrsinaColumn.setCellValueFactory(
					new Callback<TableColumn.CellDataFeatures<Drzava, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(CellDataFeatures<Drzava, String> param) {
							return new ReadOnlyObjectWrapper<String>(param.getValue().getPovrsinaDrzave().toString());
						}
					});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		obzListaDrzava = FXCollections.observableArrayList(PocetniEkranController.listaDrzava);
	}

	public void prikaziDrzave() {
		List<Drzava> filtriraneDrzave = new ArrayList<>();
		if (drzaveFilterTextField.getText().isEmpty() == false) {
			filtriraneDrzave = PocetniEkranController.listaDrzava.stream()
					.filter(m -> m.getNazivDrzave().contains(drzaveFilterTextField.getText()))
					.collect(Collectors.toList());
		} else {
			drzaveFilterTextField.pseudoClassStateChanged(errorClass, true);
			filtriraneDrzave = PocetniEkranController.listaDrzava;
		}
		obzListaDrzava = FXCollections.observableArrayList(filtriraneDrzave);
		drzaveTableView.setItems(obzListaDrzava);
		drzaveTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	public static void dodajNovuDrzavu(Drzava novaDrzava) throws SQLException, IOException {
		BazaPodataka.spremiDrzavu(novaDrzava);
		obzListaDrzava.add(novaDrzava);
	}
}
