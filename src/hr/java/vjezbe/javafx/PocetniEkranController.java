package hr.java.vjezbe.javafx;

import java.io.IOException;
import java.util.List;

import hr.java.vjezbe.entitet.Drzava;
import hr.java.vjezbe.entitet.MjernaPostaja;
import hr.java.vjezbe.entitet.Mjesto;
import hr.java.vjezbe.entitet.Senzor;
import hr.java.vjezbe.entitet.Zupanija;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PocetniEkranController {
	static List<Drzava> listaDrzava;
	static List<Zupanija> listaZupanija;
	static List<Mjesto> listaMjesta;
 	static List<Senzor> listaSenzora;
 	static List<MjernaPostaja> listaPostaja; 
	
	public void prikaziEkranMjesta() {
		try {
			BorderPane mjestaPane = FXMLLoader.load(Main.class.getResource("/Mjesta.fxml"));
			Main.setCenterPane(mjestaPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void prikaziEkranZaNovoMjesto() {
		try {
			BorderPane novoMjestoPane = FXMLLoader.load(Main.class.getResource("/NovoMjesto.fxml"));
			Scene scene = new Scene(novoMjestoPane, 600, 400);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void prikaziEkranZupanija() {
		try {
			BorderPane zupanijePane = FXMLLoader.load(Main.class.getResource("/Zupanije.fxml"));
			Main.setCenterPane(zupanijePane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void prikaziEkranZaNovuZupaniju() {
		try {
			BorderPane novaZupanijaPane = FXMLLoader.load(Main.class.getResource("/NovaZupanija.fxml"));
			Scene scene = new Scene(novaZupanijaPane, 600, 400);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void prikaziEkranDrzava() {
		try {
			BorderPane drzavePane = FXMLLoader.load(Main.class.getResource("/Drzave.fxml"));
			Main.setCenterPane(drzavePane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void prikaziEkranZaNovuDrzavu() {
		try {
			BorderPane novaDrzavaPane = FXMLLoader.load(Main.class.getResource("/NovaDrzava.fxml"));
			Scene scene = new Scene(novaDrzavaPane, 600, 400);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void prikaziEkranPostaja() {
		try {
			BorderPane postajePane = FXMLLoader.load(Main.class.getResource("/Postaje.fxml"));
			Main.setCenterPane(postajePane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void prikaziEkranZaNovuPostaju() {
		try {
			BorderPane novaPostajaPane = FXMLLoader.load(Main.class.getResource("/NovaMjernaPostaja.fxml"));
			Scene scene = new Scene(novaPostajaPane, 600, 400);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void prikaziEkranSenzora() {
		try {
			BorderPane senzoriPane = FXMLLoader.load(Main.class.getResource("/Senzori.fxml"));
			Main.setCenterPane(senzoriPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void prikaziEkranZaNoviSenzor() {
		try {
			BorderPane noviSenzorPane = FXMLLoader.load(Main.class.getResource("/NoviSenzor.fxml"));
			Scene scene = new Scene(noviSenzorPane, 800, 600);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
