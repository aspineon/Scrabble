package prk.controller;

import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import prk.model.Bag;
import prk.model.Game;
import prk.model.ScrabblePlayer;
import prk.network.Client;
import prk.network.NetworkConnection;

public class ClientApp extends Application {

	private MainWindowController mainWindowController;

	private Stage primaryStage;
	private NetworkConnection connection = createClient();

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
		primaryStage.setScene(scene);
		primaryStage.show();
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

	private Client createClient() {
		return new Client("localhost", 55555, data -> {
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
