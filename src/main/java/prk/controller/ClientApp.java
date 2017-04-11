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
			TextArea textarea = mainWindowController.getTextarea();
			Bag bag = mainWindowController.getGame().getBag();
			Game game = mainWindowController.getGame();
			ScrabblePlayer player1 = mainWindowController.getGame().getPlayer1();
			ScrabblePlayer player2 = mainWindowController.getGame().getPlayer2();
			Label labelBag = mainWindowController.getLabelBag();
			Label labelLetters = mainWindowController.getLabelLetters();

			if (data.toString().matches("WELCOMELETTERS \\D* \\d*")) {
				textarea.appendText("Gracz 1 się połączył" + "\n");
				String player1Letters = data.toString().substring(15, 28);
				String player2Letters = data.toString().substring(29, 42);

				// wczytanie liter dla Playera 1 (serwera)
				int counter = 0;
				for (int i = 0; i < 7; i++) {
					char c = player1Letters.charAt(counter);
					bag.findAndSubtract(c);
					player1.getLetters()[i] = c;
					counter = counter + 2;
				}

				// wczytanie liter dla Playera 2 {klienta}
				counter = 0;
				for (int i = 0; i < 7; i++) {
					char c = player2Letters.charAt(counter);
					bag.findAndSubtract(c);
					player2.getLetters()[i] = c;
					counter = counter + 2;
				}

				// test czy dobrze się wczytały litery
				StringBuilder letters = new StringBuilder();
				for (char c : player2.getLetters()) {
					letters.append(c).append(" ");
				}
				labelLetters.setText(letters.toString());
				labelBag.setText("Worek: " + String.valueOf(bag.getLettersLeft()) + " płytek");

				// wczytanie informacji kto zaczyna grę
				if (data.toString().substring(43, 44).equals("1")) {
					game.setPlayer1Turn();
					textarea.appendText("Zaczyna Gracz 1!" + "\n");
				} else {
					game.setPlayer2Turn();
					textarea.appendText("Zaczyna Gracz 2!" + "\n");
				}

			} else if (data.toString().matches("LEAVETURN .+")) {
				String message = data.toString().substring(10);
				textarea.appendText(message + "\n");
				game.setPlayer2Turn();				
			} else {
				textarea.appendText(data.toString() + "\n");
			}
		});
	}

	public boolean isServer() {
		return false;
	}

	public NetworkConnection getConnection() {
		return connection;
	}
}
