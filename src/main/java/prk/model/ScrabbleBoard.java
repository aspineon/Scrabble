package prk.model;

import java.util.Scanner;

public class ScrabbleBoard {

	private String[][] stringCurrentBoard = new String[15][15];
	private String[][] tempBoard;
	private int[][] letterFactor = new int[][] { { 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1 },
			{ 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1 }, { 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1 },
			{ 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1 }, { 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1 },
			{ 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2 }, { 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 1, 1 }, { 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1 }, };

	private int[][] wordFactor = new int[][] { { 3, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 3 },
			{ 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1 }, { 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1 },
			{ 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1 }, { 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 3, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 3 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1 },
			{ 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1 }, { 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1 },
			{ 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1 }, { 3, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 3 }, };

	public ScrabbleBoard() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				this.stringCurrentBoard[i][j] = "";
			}
		}
	}

	/**@author Wojciech Krzywiec */
	public String getNewLettersFromBoard(String[][] newBoard) {
		tempBoard = newBoard;

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (tempBoard[i][j].equals((stringCurrentBoard[i][j]))) {
					tempBoard[i][j] = "";
				} else
					stringCurrentBoard[i][j] = tempBoard[i][j];
			}
		}

		// wyslij nowe literki
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

	/** @author Wojciech Krzywiec */
	public String getNewWordFromBoard(String[][] newBoard) {
		
		int iIndex = 0; // znajdz pierwsza literke
		int jIndex = 0; // znajdz pierwsza literke
		outerloop: for (iIndex = 0; iIndex < 15; iIndex++) {
			for (jIndex = 0; jIndex < 15; jIndex++) {
				if (!tempBoard[iIndex][jIndex].equals("")) {
					break outerloop;
				}
			}
		}

		// sprawdz czy slowo jest horyzonatlne czy wertykalne
		boolean isHorizontal = false;
		if ((jIndex + 1 > 14 && stringCurrentBoard[iIndex][jIndex - 1].equals("")) || (jIndex - 1 < 0 && stringCurrentBoard[iIndex][jIndex + 1].equals(""))) { 
			isHorizontal = false;
		} else if ((jIndex + 1 > 14 && !stringCurrentBoard[iIndex][jIndex - 1].equals("")) || (jIndex - 1 < 0 && !stringCurrentBoard[iIndex][jIndex + 1].equals(""))){
			isHorizontal = true;
		} else if ((iIndex + 1 > 14 && stringCurrentBoard[iIndex-1][jIndex].equals("")) || (iIndex - 1 < 0 && stringCurrentBoard[iIndex+1][jIndex].equals(""))) {
			isHorizontal = true;
		} else if ((iIndex + 1 > 14 && !stringCurrentBoard[iIndex-1][jIndex].equals("")) || (iIndex - 1 < 0 && !stringCurrentBoard[iIndex+1][jIndex].equals(""))) {
			isHorizontal = false;
		}else {
			// sprawdz czy wpisano jedna literke
			if (tempBoard[iIndex][jIndex - 1].equals("") && tempBoard[iIndex][jIndex + 1].equals("")
					&& tempBoard[iIndex + 1][jIndex].equals("") && tempBoard[iIndex - 1][jIndex].equals("")) {
				if (!stringCurrentBoard[iIndex][jIndex + 1].equals("")
						|| !stringCurrentBoard[iIndex][jIndex - 1].equals("")) {
					isHorizontal = true;
				} else {
					isHorizontal = false;
				}
			} else if (!tempBoard[iIndex][jIndex + 1].equals("") || !tempBoard[iIndex][jIndex - 1].equals("")) {
				isHorizontal = true;
			} else {
				isHorizontal = false;
			}
		}

		int newWordLength = 0;
		StringBuilder wholeWord = new StringBuilder();
		if (isHorizontal) {
			// ustal indeks pierwszej litery
			for (int i = 0; i < 15; i++) {
				jIndex--;
				if (jIndex < 0) {
					jIndex++;
					break;
				} else {
					if (stringCurrentBoard[iIndex][jIndex].equals("")) {
						jIndex++;
						break;
					}
				}
			}
			// ustal dlugosc calego nowego slowa
			for (int i = 0; i < 15; i++) {
				if (!stringCurrentBoard[iIndex][jIndex].equals("")) {
					newWordLength++;
					jIndex++;
					if (jIndex > 14) {
						jIndex -= newWordLength;
						break;
					}
				} else {
					jIndex -= newWordLength;
					break;
				}
			}

			// wypisz cale slowo
			for (int i = jIndex; i < jIndex + newWordLength; i++) {
				wholeWord.append(stringCurrentBoard[iIndex][i]);
			}
		} else {
			// ustal indeks pierwszej litery
			for (int i = 0; i < 15; i++) {
				iIndex--;
				if (iIndex < 0) {
					iIndex++;
					break;
				} else {
					if (stringCurrentBoard[iIndex][jIndex].equals("")) {
						iIndex++;
						break;
					}
				}
			}
			// ustal dlugosc calego nowego slowa
			for (int i = 0; i < 15; i++) {
				if (!stringCurrentBoard[iIndex][jIndex].equals("")) {
					newWordLength++;
					iIndex++;
					if (iIndex > 14) {
						iIndex -= newWordLength;
						break;
					}
				} else {
					iIndex -= newWordLength;
					break;
				}
			}

			// wypisz cale slowo
			for (int i = iIndex; i < iIndex + newWordLength; i++) {
				wholeWord.append(stringCurrentBoard[i][jIndex]);
			}
		}
		return wholeWord.toString();
	}

	/**@author Wojciech Krzywiec */
	public void addNewWordToStringBoard(String word) {
		Scanner in = new Scanner(word).useDelimiter(",");

		while (in.hasNext())
			stringCurrentBoard[in.nextInt()][in.nextInt()] = in.next();
		in.close();
	}

	public String[][] getStringCurrentBoard() {
		return stringCurrentBoard;
	}

	public void setStringCurrentBoard(String[][] charCurrentBoard) {
		this.stringCurrentBoard = charCurrentBoard;
	}

	public int[][] getLetterFactor() {
		return letterFactor;
	}

	public int[][] getWordFactor() {
		return wordFactor;
	}

}
