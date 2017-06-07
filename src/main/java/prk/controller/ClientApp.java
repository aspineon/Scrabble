package prk.controller;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
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
	private NetworkConnection connection = createClient();
	private String ip;

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
	public void init() throws Exception {
		connection.startConnection();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		openingDialogToSetIP();
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
		return new Client(ip, 55555, data -> {
			mainWindowController.getMessage(data.toString());
		});
	}
	
	public void openingDialogToSetIP(){
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Wprowadzanie IP serwera");
		dialog.setHeaderText("Podaj adres IP serwera z którym chcesz się połączyć");
		dialog.setContentText("Adres serwera: ");

		
		Optional<String> result = dialog.showAndWait();
		ip = result.get();

	}

	public boolean isServer() {
		return false;
	}

	public NetworkConnection getConnection() {
		return connection;
	}
}
