package prk.controller;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import prk.network.Client;
import prk.network.NetworkConnection;

/**
 * Klasa uruchomieniowa dla aplikacji po stronie Klienta
 * 
 * @author Maciej Gawlowski 
 */

public class ClientApp extends Application {

	private MainWindowController mainWindowController;

	private Stage primaryStage;
	private NetworkConnection connection = null;
	private String ip="";

	public void mainWindow() throws Exception {
		FXMLLoader loader = new FXMLLoader(ClientApp.class.getResource("/prk/view/mainWindow.fxml"));
		AnchorPane root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainWindowController = loader.getController();
		mainWindowController.setClientApp(this, primaryStage);
		mainWindowController.confirmConnection();

		Scene scene = new Scene(root);
		scene.getStylesheets().add("/prk/view/mainWindow.css");
		primaryStage.setMinHeight(560);
		primaryStage.setMinWidth(800);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Scrabble: Gracz 2");
		primaryStage.show();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Stage popup = new Stage();
		Label label1= new Label("Adres IP drugiego gracza: ");
		label1.setTextFill(Color.WHITE);
		TextField ipField = new TextField();
		Button btnConfirm = new Button("Zatwierdź");
        
		btnConfirm.setOnAction(e -> {
			ip = ipField.getText();
			connection = createClient();
			try {
				connection.startConnection();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			popup.close();
			this.primaryStage = primaryStage;
			try {
				mainWindow();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		HBox layout= new HBox(10);
		layout.getChildren().addAll(label1, ipField, btnConfirm);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color: #01584f;");
		
		Scene scene1= new Scene(layout, 400, 100);
		popup.setScene(scene1); 
		popup.showAndWait();
	}

	@Override
	public void stop() throws Exception {
		connection.closeConnection();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private Client createClient() {
		return new Client(ip, 7007, data -> {
			mainWindowController.getMessage(data.toString());
		});
	}

	public boolean isServer() {
		return false;
	}

	public NetworkConnection getConnection() {
		return connection;
	}
}
