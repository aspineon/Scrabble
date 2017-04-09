package prk.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import prk.model.Game;
import prk.model.ScrabbleBoard;
import prk.model.TextFieldLimited;

public class MainWindowController {

	private Stage primaryStage;
	private ServerApp serverApp;
	private ClientApp clientApp;
	private boolean isServer;
	private Game game;

	@FXML
	private TextArea textarea;
	@FXML
	private Label labelBag, labelLetters;
	@FXML
	private TextFieldLimited txt00, txt01;

	public void setServerApp(ServerApp app, Stage primaryStage) {
		this.serverApp = app;
		this.primaryStage = primaryStage;
		this.isServer = true;

		game = new Game(true);
		StringBuilder letters = new StringBuilder();
		for (char c : game.getPlayer1().getLetters()) {
			letters.append(c).append(" ");
		}
		labelLetters.setText(letters.toString());
		labelBag.setText("Worek: " + String.valueOf(game.getBag().getLettersLeft()) + " płytek");
	}

	public void setClientApp(ClientApp app, Stage primaryStage) {
		this.clientApp = app;
		this.primaryStage = primaryStage;
		this.isServer = false;
		
		game = new Game(false);
	}

	public void initialize() {
	}

	public void confirm() {
		String message = this.isServer ? "Server: " : "Client: ";
		message += "confirmPressed";
		textarea.appendText(message + "\n");
		game.getBag().findAndSubtract('A');
		getLabelBag().setText("Worek: " + String.valueOf(game.getBag().getLettersLeft()) + " płytek");
		try {
			if (this.isServer)
				serverApp.getConnection().send(message);
			else
				clientApp.getConnection().send(message);
		} catch (Exception e) {
			textarea.appendText("Failed to send" + "\n");
		}
	}

	public void confirmConnection() throws Exception {
		clientApp.getConnection().send("Gracz 2 się połączył");
	}

	public TextArea getTextarea() {
		return textarea;
	}

	public void textFieldToMatrix() {

	}

	public ServerApp getServerApp() {
		return serverApp;
	}

	public Game getGame() {
		return game;
	}

	public Label getLabelLetters() {
		return labelLetters;
	}

	public Label getLabelBag() {
		return labelBag;
	}
}
