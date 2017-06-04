package prk.model;

/**@author Wojciech Krzywiec */
public class ScrabblePlayer {
	private boolean myTurn;
	private int points;
	private String[] letters;
	private String[] usedLetters = {"", "", "", "", "", "", ""};

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
			if (letters[i]!=null){
				if (!letters[i].equals(""))
					labelLetters.append(letters[i] + " ");
			}
		}
		return labelLetters.toString();
	}

	public void addUsedLetter(String letter){
		int i = 0;
		while (usedLetters[i]!="") i++;
		usedLetters[i] = letter;
	}
	
	public void addLetterFromBag(String letter) {
		int i = 0;
		if (!letters[i].equals("")) i++;
		letters[i] = letter;
	}

	public String[] getUsedLetters() {
		return usedLetters;
	}

	public void setUsedLetters(String[] usedLetters) {
		this.usedLetters = usedLetters;
	}

}
