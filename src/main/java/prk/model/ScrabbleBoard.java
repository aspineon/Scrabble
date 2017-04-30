package prk.model;

import java.util.Scanner;

public class ScrabbleBoard {

	private String[][] stringCurrentBoard = new String[15][15];
	private int[][] letterFactor = new int[][] { 
		{ 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1 }, 
		{ 1, 1, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1 },
		{ 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2 },
		{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
		{ 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1 },
		{ 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1 },
		{ 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1 },
		{ 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1 },
		{ 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1 },
		{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
		{ 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2 },
		{ 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1 }, 
		};
	private boolean[][] letterFactorUsed = new boolean[15][15];
		
	private int[][] wordFactor = new int[][] { 
		{ 3, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 3 }, 
		{ 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1 }, 
		{ 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1 }, 
		{ 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1 }, 
		{ 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1 }, 
		{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
		{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
		{ 3, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 3 }, 
		{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
		{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
		{ 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1 }, 
		{ 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1 }, 
		{ 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1 }, 
		{ 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1 }, 
		{ 3, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 3 }, 
			};	
	private boolean[][] wordFactorUsed = new boolean[15][15];	

	public ScrabbleBoard() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				this.stringCurrentBoard[i][j] = "";
				this.letterFactorUsed[i][j] = false;
				this.wordFactorUsed[i][j] = false;
			}
		}
	}

	public String getNewWordFromBoard(String[][] newBoard) {

		String[][] tempBoard = newBoard;

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (tempBoard[i][j].equals((stringCurrentBoard[i][j]))) {
					tempBoard[i][j] = "";
				} else
					stringCurrentBoard[i][j] = tempBoard[i][j];
			}
		}

		StringBuilder out = new StringBuilder();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (!tempBoard[i][j].equals("")) {
					out.append(i).append(",").append(j).append(",").append(tempBoard[i][j]).append(",");
				}
			}
		}
		out.deleteCharAt(out.length() - 1);
		return out.toString();
	}

	public void addNewWordToStringBoard(String word) {
		Scanner in = new Scanner(word).useDelimiter(",");

		while (in.hasNext())
			stringCurrentBoard[in.nextInt()][in.nextInt()] = in.next();
		in.close();
	}

	public String[][] getCharCurrentBoard() {
		return stringCurrentBoard;
	}

	public void setCharCurrentBoard(String[][] charCurrentBoard) {
		this.stringCurrentBoard = charCurrentBoard;
	}

	public int[][] getLetterFactor() {
		return letterFactor;
	}

	public boolean[][] getLetterFactorUsed() {
		return letterFactorUsed;
	}

	public int[][] getWordFactor() {
		return wordFactor;
	}

	public boolean[][] getWordFactorUsed() {
		return wordFactorUsed;
	}

}
