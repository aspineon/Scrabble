package prk.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import prk.model.Bag;
import prk.model.Game;
import prk.model.ScrabbleBoard;
import prk.model.ScrabblePlayer;
import prk.model.TextFieldLimited;

/**
 * Kontroler sterujący przebiegiem apliakcji
 */

public class MainWindowController {
	// komentarz
	private Stage primaryStage;
	private ServerApp serverApp;
	private ClientApp clientApp;
	private boolean isServer;
	private Game game;
	private TextField text;
	private ScrabblePlayer player1;
	private ScrabblePlayer player2;
	private Bag bag;
	private ScrabbleBoard board;

	@FXML
	private TextArea textarea;
	@FXML
	private Label labelBag, labelLetters, labelPlayer1Points, labelPlayer2Points;

	@FXML
	private ArrayList<ArrayList<TextFieldLimited>> textFieldBoard;

	String[][] tempBoard = new String[15][15];

	@FXML
	private TextFieldLimited txt00_00, txt00_01, txt00_02, txt00_03, txt00_04, txt00_05, txt00_06, txt00_07, txt00_08,
			txt00_09, txt00_10, txt00_11, txt00_12, txt00_13, txt00_14;
	@FXML
	private TextFieldLimited txt01_00, txt01_01, txt01_02, txt01_03, txt01_04, txt01_05, txt01_06, txt01_07, txt01_08,
			txt01_09, txt01_10, txt01_11, txt01_12, txt01_13, txt01_14;
	@FXML
	private TextFieldLimited txt02_00, txt02_01, txt02_02, txt02_03, txt02_04, txt02_05, txt02_06, txt02_07, txt02_08,
			txt02_09, txt02_10, txt02_11, txt02_12, txt02_13, txt02_14;
	@FXML
	private TextFieldLimited txt03_00, txt03_01, txt03_02, txt03_03, txt03_04, txt03_05, txt03_06, txt03_07, txt03_08,
			txt03_09, txt03_10, txt03_11, txt03_12, txt03_13, txt03_14;
	@FXML
	private TextFieldLimited txt04_00, txt04_01, txt04_02, txt04_03, txt04_04, txt04_05, txt04_06, txt04_07, txt04_08,
			txt04_09, txt04_10, txt04_11, txt04_12, txt04_13, txt04_14;
	@FXML
	private TextFieldLimited txt05_00, txt05_01, txt05_02, txt05_03, txt05_04, txt05_05, txt05_06, txt05_07, txt05_08,
			txt05_09, txt05_10, txt05_11, txt05_12, txt05_13, txt05_14;
	@FXML
	private TextFieldLimited txt06_00, txt06_01, txt06_02, txt06_03, txt06_04, txt06_05, txt06_06, txt06_07, txt06_08,
			txt06_09, txt06_10, txt06_11, txt06_12, txt06_13, txt06_14;
	@FXML
	private TextFieldLimited txt07_00, txt07_01, txt07_02, txt07_03, txt07_04, txt07_05, txt07_06, txt07_07, txt07_08,
			txt07_09, txt07_10, txt07_11, txt07_12, txt07_13, txt07_14;
	@FXML
	private TextFieldLimited txt08_00, txt08_01, txt08_02, txt08_03, txt08_04, txt08_05, txt08_06, txt08_07, txt08_08,
			txt08_09, txt08_10, txt08_11, txt08_12, txt08_13, txt08_14;
	@FXML
	private TextFieldLimited txt09_00, txt09_01, txt09_02, txt09_03, txt09_04, txt09_05, txt09_06, txt09_07, txt09_08,
			txt09_09, txt09_10, txt09_11, txt09_12, txt09_13, txt09_14;
	@FXML
	private TextFieldLimited txt10_00, txt10_01, txt10_02, txt10_03, txt10_04, txt10_05, txt10_06, txt10_07, txt10_08,
			txt10_09, txt10_10, txt10_11, txt10_12, txt10_13, txt10_14;
	@FXML
	private TextFieldLimited txt11_00, txt11_01, txt11_02, txt11_03, txt11_04, txt11_05, txt11_06, txt11_07, txt11_08,
			txt11_09, txt11_10, txt11_11, txt11_12, txt11_13, txt11_14;
	@FXML
	private TextFieldLimited txt12_00, txt12_01, txt12_02, txt12_03, txt12_04, txt12_05, txt12_06, txt12_07, txt12_08,
			txt12_09, txt12_10, txt12_11, txt12_12, txt12_13, txt12_14;
	@FXML
	private TextFieldLimited txt13_00, txt13_01, txt13_02, txt13_03, txt13_04, txt13_05, txt13_06, txt13_07, txt13_08,
			txt13_09, txt13_10, txt13_11, txt13_12, txt13_13, txt13_14;
	@FXML
	private TextFieldLimited txt14_00, txt14_01, txt14_02, txt14_03, txt14_04, txt14_05, txt14_06, txt14_07, txt14_08,
			txt14_09, txt14_10, txt14_11, txt14_12, txt14_13, txt14_14;
	
	/**@author Maciej Gawlowski */
	public void setServerApp(ServerApp app, Stage primaryStage) {
		this.serverApp = app;
		this.primaryStage = primaryStage;
		this.isServer = true;

		game = new Game(true);
		player1 = game.getPlayer1();
		player2 = game.getPlayer2();
		bag = game.getBag();
		board = game.getBoard();

		StringBuilder letters = new StringBuilder();
		for (String c : game.getPlayer1().getLetters()) {
			letters.append(c).append(" ");
		}
		labelLetters.setText(letters.toString());
		labelBag.setText("Worek: " + String.valueOf(game.getBag().getLettersLeft()) + " płytek");
		this.enableStartingTextFields();
	}

	
	/**@author Maciej Gawlowski */
	public void setClientApp(ClientApp app, Stage primaryStage) {
		this.clientApp = app;
		this.primaryStage = primaryStage;
		this.isServer = false;

		game = new Game(false);
		player1 = game.getPlayer1();
		player2 = game.getPlayer2();
		bag = game.getBag();
		board = game.getBoard();
		this.enableStartingTextFields();
	}
	/**@author Maciej Gawlowski
	 * @author Wojciech Krzywiec */
	public void confirm() {

		ScrabblePlayer currentPlayer;
		if (this.isServer) {
			currentPlayer = player1;
		} else {
			currentPlayer = player2;
		}
		
		if (currentPlayer.isMyTurn()) {
			if (textFieldBoard.get(7).get(7).getText().trim().isEmpty()){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Brak litery na środku planszy!");
				alert.setHeaderText("Pierszwy wyraz zawsze musi mieć zawsze literę na środku planszy!");
				alert.showAndWait();
			} else if(!isNewWordNextToExisting()){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Nowe słowo nie przylega do już istniejących!");
				alert.setHeaderText("Każde nowe słowo musi mieć choć   jedną wspólną literę z już podanymi oraz musi do nich przylegać!");
				alert.showAndWait();
			} else {
				String letters = game.getBoard().getNewLettersFromBoard(convertTextFieldToString());
				String wholeWord = game.getBoard().getNewWordFromBoard(convertTextFieldToString());
				String message = "NEWWORD " + letters + " " + wholeWord;
				textarea.appendText(message + "\n");
				disableTextFields();
				game.setAnotherPlayerTurn();
				try {
					if (this.isServer) {
						serverApp.getConnection().send(message);
					} else
						clientApp.getConnection().send(message);
				} catch (Exception e) {
					textarea.appendText("Failed to send \n");
				}
			}
		} else {
			textarea.appendText("Czekaj na swoją kolej! \n");
		}
	}


	/**@author Maciej Gawlowski
	 * @author Wojciech Krzywiec */
	public void getMessage(String message) {

		if (isServer) {
			if (message.equals("Gracz 2 się połączył")) {
				Platform.runLater(() -> {
					getTextarea().appendText(message + "\n");
				});
				StringBuilder welcomeLetters = new StringBuilder();
				welcomeLetters.append("WELCOMELETTERS ");
				for (String c : getGame().getPlayer1().getLetters()) {
					welcomeLetters.append(c).append(" ");
				}
				for (String c : getGame().getPlayer2().getLetters()) {
					welcomeLetters.append(c).append(" ");
				}
				if (getGame().getPlayer1().isMyTurn()) {
					welcomeLetters.append("1");
				} else {
					welcomeLetters.append("2");
					disableTextFields();
				}
				try {
					getServerApp().getConnection().send(welcomeLetters);
				} catch (Exception e) {
					e.printStackTrace();
				}
				getTextarea().appendText("Zaczyna " + getGame().getStartingPlayer() + "\n");
			} else if (message.matches("LEAVETURN .+")) {
				leaveTurnAction(message);
			} else if (message.matches("NEWLETTERS \\D*")) {
				newLettersAction(message, true);
			} else if ((message.matches("REJECTWORD,.+"))) {
				rejectWordAction(message);
			} else if (message.matches("NEWWORD .+")) {
				newWordAction(message, true);
			} else if (message.matches("POINTS \\d*")) {
				getPointsAction(message, true);
				getAdditionalLettersFromBag();
			} else if (message.matches("REMOVELETTERS .+")){
				System.out.println(message);
				this.removeLettersFromOpponentBag(message);
				Platform.runLater(
						() -> {
							labelLetters.setText(player1.getLabelLetters());
							labelBag.setText("Worek: " + String.valueOf(game.getBag().getLettersLeft()) + " płytek");
						  }
				);
			}
		} else {
			if (message.matches("WELCOMELETTERS \\D* \\d*")) {
				textarea.appendText("Gracz 1 się połączył" + "\n");
				String player1Letters = message.substring(15, 28);
				String player2Letters = message.substring(29, 42);

				// wczytanie liter dla Playera 1 (serwera)
				int counter = 0;
				for (int i = 0; i < 7; i++) {
					String c = String.valueOf(player1Letters.charAt(counter));
					game.getBag().findAndSubtract(c);
					player1.getLetters()[i] = c;
					counter = counter + 2;
				}

				// wczytanie liter dla Playera 2 {klienta}
				counter = 0;
				for (int i = 0; i < 7; i++) {
					String c = String.valueOf(player2Letters.charAt(counter));
					game.getBag().findAndSubtract(c);
					player2.getLetters()[i] = c;
					counter = counter + 2;
				}

				// test czy dobrze się wczytały litery
				StringBuilder letters = new StringBuilder();
				for (String c : player2.getLetters()) {
					letters.append(c).append(" ");
				}
				labelLetters.setText(letters.toString());
				labelBag.setText("Worek: " + String.valueOf(game.getBag().getLettersLeft()) + " płytek");

				// wczytanie informacji kto zaczyna grę
				if (message.substring(43, 44).equals("1")) {
					game.setPlayer1Turn();
					textarea.appendText("Zaczyna Gracz 1!" + "\n");
					disableTextFields();
				} else {
					game.setPlayer2Turn();
					textarea.appendText("Zaczyna Gracz 2!" + "\n");
				}

			} else if (message.matches("LEAVETURN .+")) {
				this.leaveTurnAction(message);
			} else if (message.matches("NEWLETTERS \\D*")) {
				this.newLettersAction(message, false);
			} else if ((message.matches("REJECTWORD,.+"))) {
				this.rejectWordAction(message);
			} else if (message.matches("NEWWORD .+")) {
				this.newWordAction(message, false);
			} else if (message.matches("POINTS \\d*")) {
				this.getPointsAction(message, false);
				this.getAdditionalLettersFromBag();
			} else if (message.matches("REMOVELETTERS .+")){
				System.out.println(message);
				this.removeLettersFromOpponentBag(message);
				Platform.runLater(
						() -> {
							labelLetters.setText(player2.getLabelLetters());
							labelBag.setText("Worek: " + String.valueOf(game.getBag().getLettersLeft()) + " płytek");
						  }
				);
			}
		}
	}

	


	/**@author Wojciech Krzywiec */
	private void getAdditionalLettersFromBag() {
		//tutaj trzeba bedzie pobrac tablice liter z klasy scrabbleplayer i sprawdzic co brakuje "", pobrac z worka brakujace litery, dodac litery do labelki oraz przeslac literki do drugiego gracza
		int count = 0;
		String lettersFromBag[];
		if (isServer){
			for (int i = 0; i < player1.getLetters().length; i++) {
				if (player1.getLetters()[i].equals("")) {
					count++;
				}
			}
		} else {
			for (int i = 0; i < player2.getLetters().length; i++) {
				if (player2.getLetters()[i].equals("")) {
					count++;
				}
			}
		}
		lettersFromBag = game.getBag().randomLetters(count);
		
		int j = 0;
		if (isServer){
			for (int i = 0; i < player1.getLetters().length; i++) {
					if (player1.getLetters()[i].equals("")){
						player1.getLetters()[i] = lettersFromBag[j];
						j++;
					}
			}
			for (int i = 0; i < player1.getLetters().length; i++) {
				System.out.println(player1.getLetters()[i]);
			}
			Platform.runLater(
					() -> {
						labelLetters.setText(player1.getLabelLetters());
						labelBag.setText("Worek: " + String.valueOf(game.getBag().getLettersLeft()) + " płytek");
					  }
			);
			StringBuilder out = new StringBuilder("REMOVELETTERS ");
			for (int i = 0; i < lettersFromBag.length; i++){
				out.append(lettersFromBag[i]);
				out.append(",");
			}
			out.deleteCharAt(out.length() - 1);
			try {
				serverApp.getConnection().send(out.toString());
			} catch (Exception e) {
				textarea.appendText("Failed to send \n");
			}
			
		} else {
			for (int i = 0; i < player2.getLetters().length; i++) {
				if (player2.getLetters()[i].equals("")){
					player2.getLetters()[i] = lettersFromBag[j];
					j++;
				}
		}
			
			for (int i = 0; i < player2.getLetters().length; i++) {
				System.out.println(player2.getLetters()[i]);
			}

			Platform.runLater(
					() -> {
						labelLetters.setText(player2.getLabelLetters());
						labelBag.setText("Worek: " + String.valueOf(game.getBag().getLettersLeft()) + " płytek");
					  }
			);
		}
		StringBuilder out = new StringBuilder("REMOVELETTERS ");
		for (int i = 0; i < lettersFromBag.length; i++){
			out.append(lettersFromBag[i]);
			out.append(",");
		}
		out.deleteCharAt(out.length() - 1);
		try {
			clientApp.getConnection().send(out.toString());
		} catch (Exception e) {
			textarea.appendText("Failed to send \n");
		}

	}


	/**@author Maciej Gawlowski */
	public void getPointsAction(String message, boolean isServer) {
		String points = message.substring(7);
		disableTextFields();
		if (isServer) {
			player1.setPoints(player1.getPoints() + Integer.valueOf(points));
			Platform.runLater(() -> {
				labelPlayer1Points.setText(String.valueOf(player1.getPoints()) + " puntków");
			});
		} else {
			player2.setPoints(player2.getPoints() + Integer.valueOf(points));
			Platform.runLater(() -> {
				labelPlayer2Points.setText(String.valueOf(player2.getPoints()) + " puntków");
			});
		}
	}

	/**@author Maciej Gawlowski */
	public void newWordAction(String message, boolean isServer) {
		// utnij napis newword
		String newLetters = message.substring(8);
		// znajdz przerwe miedzy wspolrzednymi i calym wyrazem
		boolean isSpace = false;
		int coordinatesLength = 0;
		for (int i = 0; i < newLetters.length(); i++) {
			if (newLetters.substring(i, i + 1).equals(" ")) {
				isSpace = true;
				coordinatesLength += i;
				break;
			}
		}
		String newWord = newLetters.substring(coordinatesLength+1);
		newLetters = newLetters.substring(0, coordinatesLength);
		
		textarea.appendText(newLetters + "\n");
		addNewWordToBoard(newLetters);
		if (isWordValid(newLetters, newWord)) {
			int i = 0; // pozycja wspolrzednej i w ciągu znaków message
			int iValue; // wartość i
			int j = 2; // pozycja wspolrzednej j w ciągu znaków message
			int jValue; // wartość j
			int nextI = 6; // do zwiekszania i pobierania nastepnej pozycji i
			int letterIndex = 4; // na ktorym miejscu w ciągu znaków stoi litera
			int points = 0; // punkty za slowo
			boolean wordDoubleBonus = false; // czy jest podwojna premia slowna
			boolean wordTripleBonus = false; // czy jest potrojna premia slowna
			String letter = ""; // litera
			while (i < newLetters.length()) {
				nextI = 6;
				iValue = 0;
				jValue = 0;
				// ogarnięcie czy wspolrzedna jest jednocyforwa czy dwucyfrowa
				if (newLetters.charAt(i + 1) == ',') {
					iValue = Integer.valueOf(newLetters.substring(i, i + 1));
					j = i + 2;
				} else {
					iValue = Integer.valueOf(newLetters.substring(i, i + 2));
					j = i + 3;
					nextI++;
				}
				if (newLetters.charAt(j + 1) == ',') {
					jValue = Integer.valueOf(newLetters.substring(j, j + 1));
					letterIndex = j + 2;
				} else {
					jValue = Integer.valueOf(newLetters.substring(j, j + 2));
					letterIndex = j + 3;
					nextI++;
				}
				letter = newLetters.substring(letterIndex, letterIndex + 1);

				// doliczenie premii literowej
				int letterFactor = board.getLetterFactor()[jValue][iValue];
				points += bag.returnPointsOfLetter(letter) * letterFactor;

				// doliczenie premii słownej
				int wordFactor = board.getWordFactor()[iValue][jValue];
				if (wordFactor > 1) {
						if (wordFactor == 2)
							wordDoubleBonus = true;
						if (wordFactor == 3)
							wordTripleBonus = true;
				}
				
				//usun z nowego wyrazu litery ktore sa polozone na plansze jako nowe
				newWord = newWord.replace(letter, "");
				i += nextI;
			}
			
			//doliczenie punktow za litery ktore juz lezaly na planszy
			for (int ii=0; ii<newWord.length(); ii++){
				String letter2 = newWord.substring(ii, ii+1);
				points += bag.returnPointsOfLetter(letter2);
			}
			
			if (wordDoubleBonus) {
				points = points * 2;
			}
			if (wordTripleBonus) {
				points = points * 3;
			}
			String out = "POINTS ";
			if (isServer) {
				// policz punkty dla przeciwnika i mu je wyslij
				player2.setPoints(player2.getPoints() + points);
				Platform.runLater(() -> {
					labelPlayer2Points.setText(player2.getPoints() + " punktów");
				});
				out = out + String.valueOf(points);
				try {
					serverApp.getConnection().send(out);
				} catch (Exception e) {
					textarea.appendText("Failed to send \n");
				}
			} else {
				// policz punkty dla przeciwnika i mu je wyslij
				player1.setPoints(player1.getPoints() + points);
				Platform.runLater(() -> {
					labelPlayer1Points.setText(player1.getPoints() + " punktów");
				});
				out = out + String.valueOf(points);
				try {
					clientApp.getConnection().send(out);
				} catch (Exception e) {
					textarea.appendText("Failed to send \n");
				}
			}
			enableTextFields();
			game.setAnotherPlayerTurn();
		} else {
			removeNewLettersFromBoard(newLetters);
			rejectNewLetters(newLetters);
		}
		
	}

	/** @author Wojciech Krzywiec */
	public void rejectWordAction(String message) {
		String modifiedMessage = message.replace("REJECTWORD,", "");
		textarea.appendText("Przeciwnik nie zaakceptował słowa: " + game.decryptMessage(modifiedMessage) + "\n");
		removeNewLettersFromBoard(modifiedMessage);
		game.setAnotherPlayerTurn();
		enableTextFields();
	}
	/**@author Maciej Gawlowski */
	public void newLettersAction(String message, boolean isServer) {
		if (isServer) {
			textarea.appendText("Gracz 2 wymienił litery, kolej Gracza 1" + "\n");
			bag.returnLetters(player2.getLetters());
		} else {
			textarea.appendText("Gracz 1 wymienił litery, kolej Gracza 2" + "\n");
			bag.returnLetters(player1.getLetters());
		}
		enableTextFields();

		String newLetters = message.substring(11);
		int numberOfNewLetters = newLetters.length() / 2;

		int counter = 0;
		for (int i = 0; i < numberOfNewLetters; i++) {
			String c = String.valueOf(newLetters.charAt(counter));
			bag.findAndSubtract(c);
			if (isServer) {
				player2.getLetters()[i] = c;
			} else {
				player1.getLetters()[i] = c;
			}
			counter = counter + 2;
		}
		labelBag.setText("Worek: " + String.valueOf(bag.getLettersLeft()) + " płytek");
		game.setAnotherPlayerTurn();
		;
	}
	/**@author Maciej Gawlowski */
	public void leaveTurnAction(String message) {
		textarea.appendText(message.substring(10) + "\n");
		enableTextFields();
		game.setAnotherPlayerTurn();
	}
	
	/**@author Maciej Gawlowski */
	public void changeLetters() {
		// ustalenie kto nacisnął przycisk, aby nie powielać kodu
		ScrabblePlayer currentPlayer;
		if (this.isServer) {
			currentPlayer = player1;
		} else {
			currentPlayer = player2;
		}

		if (currentPlayer.isMyTurn()) {
			String[] random = null;
			if (bag.getLettersLeft() == 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Worek jest pusty!");
				alert.setHeaderText("Worek jest pusty!");
				alert.showAndWait();
			} else {
				if (bag.getLettersLeft() > 6) {
					random = new String[7];
					random = bag.randomLetters(7);
				} else {
					random = new String[bag.getLettersLeft()];
					random = bag.randomLetters(bag.getLettersLeft());
				}
				bag.returnLetters(currentPlayer.getLetters());
				currentPlayer.setLetters(random);

				// budowanie stringa do wysłania
				StringBuilder newLetters = new StringBuilder();
				newLetters.append("NEWLETTERS ");
				for (String c : currentPlayer.getLetters()) {
					newLetters.append(c).append(" ");
				}

				StringBuilder lettersForLabel = new StringBuilder();
				for (String c : currentPlayer.getLetters()) {
					lettersForLabel.append(c).append(" ");
				}
				labelLetters.setText(lettersForLabel.toString());

				String message = null;
				if (currentPlayer == player1) {
					message = "Gracz 1 wymienił litery, kolej Gracza 2";
					try {
						serverApp.getConnection().send(newLetters);
						textarea.appendText(message + "\n");
					} catch (Exception e) {
						textarea.appendText("Failed to send \n");
					}
				} else if (currentPlayer == player2) {
					message = "Gracz 2 wymienił litery, kolej Gracza 1";
					try {
						clientApp.getConnection().send(newLetters);
						textarea.appendText(message + "\n");
					} catch (Exception e) {
						textarea.appendText("Failed to send \n");
					}
				}
				labelBag.setText("Worek: " + String.valueOf(game.getBag().getLettersLeft()) + " płytek");
				game.setAnotherPlayerTurn();
			}
		} else {
			textarea.appendText("Czekaj na swoją kolej! \n");
		}
		disableTextFields();
	}
	/**@author Maciej Gawlowski */
	public void leaveTurn() {
		if (this.isServer) {
			if (player1.isMyTurn()) {
				game.setPlayer2Turn();
				String message = "Gracz 1 spasował, kolej Gracza 2";
				try {
					serverApp.getConnection().send("LEAVETURN " + message);
					textarea.appendText(message + "\n");
				} catch (Exception e) {
					textarea.appendText("Failed to send \n");
				}
			} else {
				textarea.appendText("Czekaj na swoją kolej! \n");
			}
		} else {
			if (player2.isMyTurn()) {
				game.setPlayer1Turn();
				String message = "Gracz 2 spasował, kolej Gracza 1";
				try {
					clientApp.getConnection().send("LEAVETURN " + message);
					textarea.appendText(message + "\n");
				} catch (Exception e) {
					textarea.appendText("Failed to send \n");
				}
			} else {
				textarea.appendText("Czekaj na swoją kolej! \n");
			}
		}
		disableTextFields();
	}
	/** @author Wojciech Krzywiec */
	public void removeLettersFromOpponentBag(String message) {
		String lettersToRemove = message.substring(14);
		Scanner in = new Scanner(lettersToRemove).useDelimiter(",");
		StringBuilder out = new StringBuilder();
		while (in.hasNext()) {
			game.getBag().removeLetterFromBag(in.next());
		}
		in.close();

	}
	
	/** @author Wojciech Krzywiec */
	public void checkLetter(KeyEvent event) {

		if (!event.isAltDown() && event.getCode() != KeyCode.DELETE && event.getCode() != KeyCode.BACK_SPACE) {

			TextFieldLimited textfield = (TextFieldLimited) event.getSource();
			String letter = textfield.getText();
			ScrabblePlayer currentPlayer;
			if (this.isServer) {
				currentPlayer = player1;
			} else {
				currentPlayer = player2;
			}

			if (currentPlayer.isMyTurn()) {
				if (letter != null) {
					boolean letterIsOK = false;
					if (isServer) {
						for (int i = 0; i < player1.getLetters().length; i++) {
							String s = player1.getLetters()[i];
							if (s.equals(letter) || letter.equals("")) {
								letterIsOK = true;
								player1.addUsedLetter(letter);
								player1.setLetter(i, "");
								break;
							}
						}
					} else {
						for (int i = 0; i < player2.getLetters().length; i++) {
							String s = player2.getLetters()[i];
							if (s.equals(letter) || letter.equals("")) {
								letterIsOK = true;
								player2.addUsedLetter(letter);
								player2.setLetter(i, "");
								break;
							}
						}
					}

					if (letterIsOK == false) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Niedozwolona litera");
						alert.setHeaderText("Niedozwolona litera");
						alert.setContentText("Nie posiadasz literki: " + letter);
						alert.showAndWait();
						textfield.setText("");
					}

					if (isServer)
						labelLetters.setText(player1.getLabelLetters());
					else
						labelLetters.setText(player2.getLabelLetters());
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("To nie twoja kolej");
				alert.setHeaderText("Teraz przeciwnik wykonuje swój ruch");
				alert.setContentText("Poczekaj aż drugi gracz skończy swoją turę");
				alert.showAndWait();
				textfield.setText("");
			}
		}
		
		if(event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.BACK_SPACE){
			String[] currentBoard = convertNewWordToStringArray();
			Arrays.sort(currentBoard, Collections.reverseOrder());
			
			if (isServer){
				String[] usedLetters = player1.getUsedLetters();
				Arrays.sort(usedLetters, Collections.reverseOrder());
				
				checkingForUsedLetter:
				for (int i = 0; i < usedLetters.length; i++) {
					if (usedLetters[i]!= "" && !usedLetters[i].equals(currentBoard[i])){
						for (int j=0; j < usedLetters.length; j++)	{
							if (player1.getLetters()[j]== "" && usedLetters[i]!= ""){
								player1.getLetters()[j]= usedLetters[i];
								usedLetters[i] = "";
								labelLetters.setText(player1.getLabelLetters());
								break checkingForUsedLetter;
							}
						}
					}
				}
			} else {
				String[] usedLetters = player2.getUsedLetters();
				Arrays.sort(usedLetters, Collections.reverseOrder());
				
				checkingForUsedLetter:
				for (int i = 0; i < usedLetters.length; i++) {
					if (usedLetters[i]!= "" && !usedLetters[i].equals(currentBoard[i])){
						for (int j=0; j < player2.getLetters().length; j++)	{
							if (player2.getLetters()[j]== "" && usedLetters[i]!= ""){
								player2.getLetters()[j]=usedLetters[i];
								usedLetters[i] = "";
								labelLetters.setText(player2.getLabelLetters());
								break checkingForUsedLetter;
							}
						}
					}
				}
			}
		}

}
	/** @author Wojciech Krzywiec */
	public String[][] convertTextFieldToString() {
		String[][] tempBoard = new String[15][15];
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (textFieldBoard.get(i).get(j).getText()!=null){
					if (!textFieldBoard.get(i).get(j).getText().trim().isEmpty())
						tempBoard[i][j] = textFieldBoard.get(i).get(j).getText();
					else
						tempBoard[i][j] = "";
				} else tempBoard[i][j] = "";
				
			}
		}
		return tempBoard;
	}
	/** @author Wojciech Krzywiec */
	public void addNewWordToBoard(String message) {

		game.getBoard().addNewWordToStringBoard(message);
		Scanner in = new Scanner(message).useDelimiter(",");

		while (in.hasNext()) {
			textFieldBoard.get(in.nextInt()).get(in.nextInt()).setText(in.next());
		}
	}
	/** @author Wojciech Krzywiec */
	private void removeNewLettersFromBoard(String message) {
		game.getBoard().addNewWordToStringBoard(message);
		Scanner in = new Scanner(message).useDelimiter(",");

		while (in.hasNext()) {
			textFieldBoard.get(in.nextInt()).get(in.nextInt()).setText("");
			in.next();
		}
	}
	/**@author Wojciech Krzywiec */
	private void rejectNewLetters(String message) {

		StringBuilder out = new StringBuilder();
		out.append("REJECTWORD,").append(message);

		try {
			if (this.isServer) {
				serverApp.getConnection().send(out.toString());
			} else
				clientApp.getConnection().send(out.toString());
		} catch (Exception e) {
			textarea.appendText("Failed to send \n");
		}
		disableTextFields();
	}
	/** @author Wojciech Krzywiec */
	public boolean isWordValid(String newLetters, String newWord) {
		boolean answer = false;
		colorNewWords(newLetters);
		final FutureTask query = new FutureTask(new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {
				Boolean answer = new Boolean(false);
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Przeciwnik zaproponował nowe słowo");
				alert.setHeaderText("Przeciwnik zaproponował nowe słowo. Sprawdz czy może je dodać.");
				alert.setContentText("Nowe słowo: " + newWord);
				// alert.initOwner(primaryStage);
				alert.initModality(Modality.APPLICATION_MODAL);

				ButtonType buttonConfirm = new ButtonType("Akceptuj");
				ButtonType buttonReject = new ButtonType("Odrzuć");
				ButtonType buttonCancel = new ButtonType("Anuluj", ButtonData.CANCEL_CLOSE);

				alert.getButtonTypes().setAll(buttonConfirm, buttonReject, buttonCancel);

				Optional<ButtonType> result = alert.showAndWait();

				if (result.get() == buttonConfirm) {
					decolorNewLetters(newLetters);
					return answer.TRUE;
				} else if (result.get() == buttonReject) {
					decolorNewLetters(newLetters);
					// removeNewWordFromBoard(message);
					return answer.FALSE;
				} else {
					decolorNewLetters(newLetters);
					return answer.FALSE;
				}
			}
		});

		Platform.runLater(query);
		try {
			answer = (boolean) query.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		;
		return answer;
	}
	
	/** @author Wojciech Krzywiec */
	private void colorNewWords(String message) {
		Scanner in = new Scanner(message).useDelimiter(",");
		StringBuilder out = new StringBuilder();
		while (in.hasNext()) {
			int x = in.nextInt();
			int y = in.nextInt();
			textFieldBoard.get(x).get(y).setStyle("-fx-control-inner-background: orange");
			in.next();
		}
	}

	/** @author Wojciech Krzywiec */
	private void decolorNewLetters(String message) {
		Scanner in = new Scanner(message).useDelimiter(",");
		StringBuilder out = new StringBuilder();
		while (in.hasNext()) {
			int x = in.nextInt();
			int y = in.nextInt();
			textFieldBoard.get(x).get(y).setStyle(null);
			in.next();
		}

	}

	/** @author Wojciech Krzywiec */
	public void disableTextFields() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				textFieldBoard.get(i).get(j).setDisable(true);;
			}
		}
	}
	/**@author Wojciech Krzywiec */
	public void enableTextFields() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if(!textFieldBoard.get(i).get(j).getText().trim().isEmpty()){
					textFieldBoard.get(i).get(j).setDisable(true);
				} else {
					for (int k = 1; k < 8; k++){
						if(i-k>0){
							if(!textFieldBoard.get(i-k).get(j).getText().trim().isEmpty()){
								textFieldBoard.get(i).get(j).setDisable(false);
								break;
							}	
						}
						if(j-k>0){
							if(!textFieldBoard.get(i).get(j-k).getText().trim().isEmpty()){
								textFieldBoard.get(i).get(j).setDisable(false);
								break;
							}	
						}
						if(i+k<15){
							if(!textFieldBoard.get(i+k).get(j).getText().trim().isEmpty()){
								textFieldBoard.get(i).get(j).setDisable(false);
								break;
							}	
						}
						if (j+k<15){
							if(!textFieldBoard.get(i).get(j+k).getText().trim().isEmpty()){
								textFieldBoard.get(i).get(j).setDisable(false);
								break;
							}
						}	
					}
				}
			}
		}
	}
	
	/**@author Wojciech Krzywiec */
	private void enableStartingTextFields() {
		
		for (int i= 1; i<14; i++) textFieldBoard.get(7).get(i).setDisable(false);
		for (int i= 1; i<14; i++) textFieldBoard.get(i).get(7).setDisable(false);
	}
	/** @author Wojciech Krzywiec */
	public String[] convertNewWordToStringArray(){
		String[] newWordArray = new String[7];
		int count = 0;
		
		String[][] tempBoard = new String[15][15];
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (textFieldBoard.get(i).get(j).getText()!=null){
					if (!textFieldBoard.get(i).get(j).getText().trim().isEmpty()) {
						tempBoard[i][j] = textFieldBoard.get(i).get(j).getText();
					} else tempBoard[i][j] = "";
				} else tempBoard[i][j] = "";
				
			}
		}
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (!tempBoard[i][j].equals((game.getBoard().getCurrentStringBoard()[i][j]))) {
					newWordArray[count] = tempBoard[i][j];
					count++;
				}
			}
		}
		while(count<7){
			newWordArray[count] = "";
			count++;
		}
		return newWordArray;
	}
	
	/** @author Wojciech Krzywiec */
	private boolean isNewWordNextToExisting() {
		boolean wordIsNextToExisiting = false;
		String[][] currentBoard = this.convertTextFieldToString();
		String[][] previousBoard = board.getCurrentStringBoard();
		checkBoard:
		for (int i = 1; i < 14; i++) {
			for (int j = 1; j < 14; j++) {
				if (!currentBoard[i][j].equals("") && previousBoard[i][j].equals("")){
					if (!previousBoard[i+1][j].equals("") || !previousBoard[i][j+1].equals("") 
							|| !previousBoard[i-1][j].equals("") || !previousBoard[i][j-1].equals("")){
						wordIsNextToExisiting = true;
						break checkBoard;
					}	
				}
			}
		}
		if (previousBoard[7][7].equals("")){
			wordIsNextToExisiting = true;
		}
		return wordIsNextToExisiting;
	}


	/**@author Maciej Gawlowski */
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
