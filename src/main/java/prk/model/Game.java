package prk.model;

import java.util.Random;
import java.util.Scanner;

public class Game {
	private ScrabblePlayer player1; // serwer
	private ScrabblePlayer player2; // client
	private ScrabbleBoard board;
	private Bag bag;
	private String startingPlayer;

	public Game(boolean isServer) {
		player1 = new ScrabblePlayer();
		player2 = new ScrabblePlayer();
		board = new ScrabbleBoard();
		bag = new Bag();

		// losowanie literek tylko po stronie serwera i serwer wyśle literki dla
		// clienta
		if (isServer) {
			char[] random = new char[7];
			random = bag.randomLetters(7);
			player1.setLetters(random);
			random = bag.randomLetters(7);
			player2.setLetters(random);
			randomStartingPlayer();
		}
	}

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

	public void setPlayer1Turn() {
		player1.setMyTurn(true);
		player2.setMyTurn(false);
	}

	public void setPlayer2Turn() {
		player1.setMyTurn(false);
		player2.setMyTurn(true);
	}

	public void setAnotherPlayerTurn() {
		if (player1.isMyTurn()) {
			player1.setMyTurn(false);
			player2.setMyTurn(true);
		} else {
			player1.setMyTurn(true);
			player2.setMyTurn(false);
		}
	}

	public ScrabblePlayer getPlayer1() {
		return player1;
	}

	public ScrabblePlayer getPlayer2() {
		return player2;
	}

	public ScrabbleBoard getBoard() {
		return board;
	}

	public Bag getBag() {
		return bag;
	}

	public String getStartingPlayer() {
		return startingPlayer;
	}
}
