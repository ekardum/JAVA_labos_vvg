package hr.java.vjezbe.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static BorderPane root;
	private Stage primaryStage;
	
	@Override
	public void start(Stage stage) {
		primaryStage = stage;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("/PocetniEkran.fxml"));
			Scene scene = new Scene(root, 800, 600);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			BorderPane mjestaPane = FXMLLoader.load(Main.class.getResource("/Mjesta.fxml"));
			Main.setCenterPane(mjestaPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static void setCenterPane(BorderPane centerPane) {
		root.setCenter(centerPane);
	}
}
