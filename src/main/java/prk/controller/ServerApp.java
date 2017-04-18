package prk.controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import prk.model.Bag;
import prk.model.Game;
import prk.model.ScrabblePlayer;
import prk.network.NetworkConnection;
import prk.network.Server;

public class ServerApp extends Application {

	private MainWindowController mainWindowController;

	private Stage primaryStage;
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

	private Server createServer() {
		return new Server(55555, data -> {
			TextArea textarea = mainWindowController.getTextarea();
			Bag bag = mainWindowController.getGame().getBag();
			Game game = mainWindowController.getGame();
			ScrabblePlayer player1 = mainWindowController.getGame().getPlayer1();
			ScrabblePlayer player2 = mainWindowController.getGame().getPlayer2();
			Label labelBag = mainWindowController.getLabelBag();
			Label labelLetters = mainWindowController.getLabelLetters();

			if (data.toString().equals("Gracz 2 się połączył")) {
				Platform.runLater(() -> {
					textarea.appendText(data.toString() + "\n");
				});
				StringBuilder welcomeLetters = new StringBuilder();
				welcomeLetters.append("WELCOMELETTERS ");
				for (char c : player1.getLetters()) {
					welcomeLetters.append(c).append(" ");
				}
				for (char c : player2.getLetters()) {
					welcomeLetters.append(c).append(" ");
				}
				if (player1.isMyTurn()) {
					welcomeLetters.append("1");
				} else {
					welcomeLetters.append("2");
				}
				try {
					mainWindowController.getServerApp().getConnection().send(welcomeLetters);
				} catch (Exception e) {
					e.printStackTrace();
				}
				textarea.appendText("Zaczyna " + mainWindowController.getGame().getStartingPlayer() + "\n");
			} else if (data.toString().matches("LEAVETURN .+")) {
				String message = data.toString().substring(10);
				textarea.appendText(message + "\n");
				game.setPlayer1Turn();
			} else if (data.toString().matches("NEWLETTERS \\D*")) {
				textarea.appendText("Gracz 2 wymienił litery, kolej Gracza 1" + "\n");
				bag.returnLetters(player2.getLetters());
				String newLetters = data.toString().substring(11);
				int numberOfNewLetters = newLetters.length() / 2;

				int counter = 0;
				for (int i = 0; i < numberOfNewLetters; i++) {
					char c = newLetters.charAt(counter);
					bag.findAndSubtract(c);
					player2.getLetters()[i] = c;
					counter = counter + 2;
				}
				labelBag.setText("Worek: " + String.valueOf(bag.getLettersLeft()) + " płytek");
				game.setPlayer1Turn();
			} else {
				textarea.appendText(data.toString() + "\n");
				mainWindowController.addNewWordToBoard(data.toString());
			}
		});
	}

	public boolean isServer() {
		return true;
	}

	public NetworkConnection getConnection() {
		return connection;
	}
}
