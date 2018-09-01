package hr.java.vjezbe.javafx;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.Zupanija;
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

public class ZupanijeController {

	final PseudoClass errorClass = PseudoClass.getPseudoClass("error");
	static ObservableList<Zupanija> obzListaZupanija;

	@FXML
	private TextField zupanijeFilterTextField;
	@FXML
	private TableView<Zupanija> zupanijeTableView;
	@FXML
	private TableColumn<Zupanija, String> nazivColumn;
	@FXML
	private TableColumn<Zupanija, String> drzavaColumn;

	@FXML
	public void initialize() throws Throwable {
		try {
			nazivColumn.setCellValueFactory(new PropertyValueFactory<>("nazivZupanije"));

			drzavaColumn.setCellValueFactory(
					new Callback<TableColumn.CellDataFeatures<Zupanija, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(CellDataFeatures<Zupanija, String> param) {
							return new ReadOnlyObjectWrapper<String>(param.getValue().getDrzava().getNazivDrzave());
						}
					});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		obzListaZupanija = FXCollections.observableArrayList(PocetniEkranController.listaZupanija);
	}

	public void prikaziZupanije() {
		List<Zupanija> filtriraneZupanije = new ArrayList<>();
		if (zupanijeFilterTextField.getText().isEmpty() == false) {
			filtriraneZupanije = PocetniEkranController.listaZupanija.stream()
					.filter(m -> m.getNazivZupanije().contains(zupanijeFilterTextField.getText()))
					.collect(Collectors.toList());
		} else {
			zupanijeFilterTextField.pseudoClassStateChanged(errorClass, true);
			filtriraneZupanije = PocetniEkranController.listaZupanija;
		}
		obzListaZupanija = FXCollections.observableArrayList(filtriraneZupanije);
		zupanijeTableView.setItems(obzListaZupanija);
		zupanijeTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	public static void dodajNovuZupaniju(Zupanija novaZupanija) throws SQLException, IOException {
		BazaPodataka.spremiZupaniju(novaZupanija);
		obzListaZupanija.add(novaZupanija);
	}
}
