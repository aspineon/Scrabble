package prk.model;

import java.util.Arrays;
import java.util.Scanner;

public class ScrabbleBoard {

	private char[][] charCurrentBoard = new char[15][15];

	public String getNewWord(TextFieldLimited[][] TxtFieldBoard){
		
		char[][] tempBoard = new char[15][15];
		
		for(int i =0; i<15; i++){
			for (int j = 0; j<15; j++){
				tempBoard[i][j] = TxtFieldBoard[i][j].getText().charAt(0);
			}
		}
		
		for(int i =0; i<15; i++){
			for (int j = 0; j<15; j++){
				if (tempBoard[i][j] == charCurrentBoard[i][j])
				tempBoard[i][j] = '\u0000';
			}
		}
		
		StringBuilder out = new StringBuilder();
		for (int i = 0; i<15; i++){
			for (int j = 0; j<15; j++){
				if(tempBoard[i][j]!= '\u0000'){
					out.append(i).append(",").append(j).append(",").append(charCurrentBoard[i][j]).append(",");
				}
			}
		}
		out.deleteCharAt(out.length()-1);
		return out.toString();
	}
	
	public void addNewWordToCharBoard(String word){
		Scanner in = new Scanner(word).useDelimiter(",");
		
		while (in.hasNext())
			charCurrentBoard[in.nextInt()][in.nextInt()]=in.next().charAt(0);
	}

	
	public char[][] getCharCurrentBoard() {
		return charCurrentBoard;
	}

	public void setCharCurrentBoard(char[][] charCurrentBoard) {
		this.charCurrentBoard = charCurrentBoard;
	}
	
}
