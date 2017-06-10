package prk.model;

import java.util.Random;
import java.util.Scanner;

/** 
 * Klasa przechowująca dane dotyczące rozgrywki
 */
public class Game {
	private ScrabblePlayer player1; // serwer
	private ScrabblePlayer player2; // client
	private ScrabbleBoard board;
	private Bag bag;
	private String startingPlayer;

	/**
	 * Konstruktor klasy
	 * 
	 * Jeżeli obiekt jest tworzony po stronie serwera, losowane są litery dla obu graczy oraz losowany jest gracz, który zaczyna rozgrywkę
	 * 
	 * @param isServer określa, czy obiekt gry jest tworzony po stronie serwera, czy klienta
	 * @author Maciej Gawlowski */
	public Game(boolean isServer) {
		player1 = new ScrabblePlayer();
		player2 = new ScrabblePlayer();
		board = new ScrabbleBoard();
		bag = new Bag();

		if (isServer) {
			String[] random = new String[7];
			random = bag.randomLetters(7);
			player1.setLetters(random);
			random = bag.randomLetters(7);
			player2.setLetters(random);
			randomStartingPlayer();
		}
	}

	/**
	 * Metoda losująca gracza zaczynającego grę
	 * 
	 * @author Maciej Gawlowski 
	 */
	public void randomStartingPlayer() {
		Random rand = new Random();
		int i = rand.nextInt(2);
		if (i == 0) {
			setPlayer1Turn();
			startingPlayer = "Gracz 1!";
		} else {
			setPlayer2Turn();
			startingPlayer = "Gracz 2!";
		}
	}
	
	/**
	 * Metoda filtruje z ciągu znaków litery
	 * 
	 * Metoda w podanym ciągu znaków wybiera tylko te, które są literami i tworzy z nich słowo
	 * 
	 * @param message ciąg znaków do przefiltrowania
	 * @return słowo po przefiltrowaniu 
	 * @author Wojciech Krzywiec 
	 */
	public String decryptMessage(String message){
		Scanner in = new Scanner(message).useDelimiter(",");
		StringBuilder out = new StringBuilder();
		while (in.hasNext()) {
			in.nextInt();
			in.nextInt();
			out.append(in.next().charAt(0));
		}
		in.close();
		return out.toString();
	}

	/**
	 * Metoda ustawia kolej gracza 1
	 * 
	 * @author Maciej Gawlowski 
	 */
	public void setPlayer1Turn() {
		player1.setMyTurn(true);
		player2.setMyTurn(false);
	}

	/**
	 * Metoda ustawia kolej gracza 2
	 * 
	 * @author Maciej Gawlowski 
	 */
	public void setPlayer2Turn() {
		player1.setMyTurn(false);
		player2.setMyTurn(true);
	}

	/**
	 * Metoda ustawia kolej przeciwnika
	 * 
	 * @author Maciej Gawlowski 
	 */
	public void setAnotherPlayerTurn() {
		if (player1.isMyTurn()) {
			player1.setMyTurn(false);
			player2.setMyTurn(true);
		} else {
			player1.setMyTurn(true);
			player2.setMyTurn(false);
		}
	}

	/**
	 * Metoda zwraca gracza 1
	 * 
	 * @return gracz 1
	 */
	public ScrabblePlayer getPlayer1() {
		return player1;
	}

	/**
	 * Metoda zwraca gracza 2
	 * 
	 * @return gracz 2
	 */
	public ScrabblePlayer getPlayer2() {
		return player2;
	}

	/**
	 * Metoda zwraca planszę
	 * 
	 * @return plansza
	 */
	public ScrabbleBoard getBoard() {
		return board;
	}

	/**
	 * Metoda zwraca worek z literkami
	 * 
	 * @return worek
	 */
	public Bag getBag() {
		return bag;
	}

	/**
	 * Metoda zwraca gracza zaczynającego rozgrywkę
	 * 
	 * @return gracz zaczynający rozgrywkę
	 */
	public String getStartingPlayer() {
		return startingPlayer;
	}
}
