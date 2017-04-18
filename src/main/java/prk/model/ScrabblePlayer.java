package prk.model;

public class ScrabblePlayer {
	private boolean myTurn;
	private int points;
	private char[] letters;

	public ScrabblePlayer() {
		this.myTurn = false;
		this.points = 0;
		this.letters = new char[7];
	}
	
	public void setLetter(int index, char letter){
		letters[index] = letter;
	}

	public boolean isMyTurn() {
		return myTurn;
	}

	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public char[] getLetters() {
		return letters;
	}

	public void setLetters(char[] letters) {
		this.letters = letters;
	}

}
