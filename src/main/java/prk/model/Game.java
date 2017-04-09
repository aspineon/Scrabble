package prk.model;

public class Game {
	private ScrabblePlayer player1; // serwer
	private ScrabblePlayer player2; // client
	private ScrabbleBoard board;
	private Bag bag;

	public Game(boolean isServer) {
		player1 = new ScrabblePlayer();
		player2 = new ScrabblePlayer();
		board = new ScrabbleBoard();
		bag = new Bag();

		//losowanie literek tylko po stronie serwera i serwer wyśle literki dla clienta
		if (isServer) {
			char[] random = new char[7];
			random = bag.randomLetters(7);
			player1.setLetters(random);
			random = bag.randomLetters(7);
			player2.setLetters(random);
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
}
