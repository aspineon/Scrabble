package prk.model;

public class ScrabblePlayer {
	private boolean myTurn;
	private int points;
	private String[] letters;

	public ScrabblePlayer() {
		this.myTurn = false;
		this.points = 0;
		this.letters = new String[7];
	}
	
	public void setLetter(int index, String letter){
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

	public String[] getLetters() {
		return letters;
	}

	public void setLetters(String[] letters) {
		this.letters = letters;
	}
}
