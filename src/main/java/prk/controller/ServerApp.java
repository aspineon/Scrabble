package prk.controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
			if (data.toString().equals("Gracz 2 się połączył")) {
				Platform.runLater(() -> {
					mainWindowController.getTextarea().appendText(data.toString() + "\n");
				});
				StringBuilder welcomeLetters = new StringBuilder();
				welcomeLetters.append("WELCOMELETTERS ");
				for (char c : mainWindowController.getGame().getPlayer1().getLetters()) {
					welcomeLetters.append(c).append(" ");
				}
				for (char c : mainWindowController.getGame().getPlayer2().getLetters()) {
					welcomeLetters.append(c).append(" ");
				}
				if (mainWindowController.getGame().getPlayer1().isMyTurn()) {
					welcomeLetters.append("1");
				} else {
					welcomeLetters.append("2");
				}
				try {
					mainWindowController.getServerApp().getConnection().send(welcomeLetters);
				} catch (Exception e) {
					e.printStackTrace();
				}
				mainWindowController.getTextarea()
						.appendText("Zaczyna " + mainWindowController.getGame().getStartingPlayer() + "\n");
			} else if (data.toString().matches("LEAVETURN .+")) {
				String message = data.toString().substring(10);
				mainWindowController.getTextarea().appendText(message + "\n");
				mainWindowController.getGame().setPlayer1Turn();
			} else {
					mainWindowController.getTextarea().appendText(data.toString() + "\n");
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
