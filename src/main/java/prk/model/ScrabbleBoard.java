package prk.model;

import java.util.Scanner;

public class ScrabbleBoard {

	private String[][] stringCurrentBoard = new String[15][15];
	
	public ScrabbleBoard(){
		
		for(int i=0; i<15; i++){
			for (int j = 0; j<15; j++){
				stringCurrentBoard[i][j] = "Q";
			}
		}
	}
	public String getNewWordFromBoard(String[][] newBoard){
		
		String[][] tempBoard = newBoard;
		
		for(int i=0; i<15; i++){
			for (int j = 0; j<15; j++){
				if (tempBoard[i][j].equals((stringCurrentBoard[i][j]))){
					tempBoard[i][j] = "Q";
				} else stringCurrentBoard[i][j] = tempBoard[i][j];
			}
		}
		
		StringBuilder out = new StringBuilder();
		for (int i=0; i<15; i++){
			for (int j = 0; j<15; j++){
				if(!tempBoard[i][j].equals("Q")){
					out.append(i).append(",").append(j).append(",").append(tempBoard[i][j]).append(",");
				}
			}
		}
		out.deleteCharAt(out.length()-1);
		return out.toString();
	}
	
	public void addNewWordToCharBoard(String word){
		Scanner in = new Scanner(word).useDelimiter(",");
		
		while (in.hasNext())
			stringCurrentBoard[in.nextInt()][in.nextInt()]=in.next();
		
		in.close();
	}

	
	public String[][] getCharCurrentBoard() {
		return stringCurrentBoard;
	}

	public void setCharCurrentBoard(String[][] charCurrentBoard) {
		this.stringCurrentBoard = charCurrentBoard;
	}
	
}
