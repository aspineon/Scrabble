package prk.controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import prk.model.Bag;
import prk.model.Game;
import prk.model.ScrabblePlayer;
import prk.network.NetworkConnection;
import prk.network.Server;

/**
 * Klasa uruchomieniowa dla aplikacji po stronie Serwera
 * 
 * @author Maciej Gawlowski 
 */

public class ServerApp extends Application {

	private MainWindowController mainWindowController;

	private Stage primaryStage;
	private Stage welcomeStage;
	private NetworkConnection connection = createServer();

	public void mainWindow() {
		FXMLLoader loader = new FXMLLoader(ServerApp.class.getResource("/prk/view/mainWindow.fxml"));
		AnchorPane root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainWindowController = loader.getController();
		mainWindowController.setServerApp(this, primaryStage);

		Scene scene = new Scene(root);
		scene.getStylesheets().add("/prk/view/mainWindow.css");
		primaryStage.setMinHeight(560);
		primaryStage.setMinWidth(800);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Scrabble: Gracz 1");
		primaryStage.show();
		
		welcomeStage = new Stage();
		Label label1= new Label("Oczekiwanie na przeciwnika");
		label1.setTextFill(Color.WHITE);
		HBox layout= new HBox(10);
		layout.getChildren().addAll(label1);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color: #01584f;");
		Scene scene1= new Scene(layout, 300, 250);
		welcomeStage.setScene(scene1); 
		welcomeStage.initOwner(primaryStage);
		welcomeStage.initModality(Modality.WINDOW_MODAL);
		welcomeStage.showAndWait();
	}

	@Override
	public void init() throws Exception {
		connection.startConnection();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		mainWindow();
	}

	@Override
	public void stop() throws Exception {
		connection.closeConnection();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private Server createServer() {
		return new Server(7007, data -> {
			mainWindowController.getMessage(data.toString());
		});
	}

	public boolean isServer() {
		return true;
	}

	public NetworkConnection getConnection() {
		return connection;
	}

	public Stage getWelcomeStage() {
		return welcomeStage;
	}
}
