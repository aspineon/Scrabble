package prk.model;

import java.util.ArrayList;

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
	
	public String getLabelLetters(){
		StringBuilder labelLetters = new StringBuilder();
		for (int i =0; i<7; i++){
			System.out.println(letters[i]);
			if (letters[i]!=null){
				if (!letters[i].equals(""))
					labelLetters.append(letters[i] + " ");
			}
		}
		return labelLetters.toString();
	}

}
