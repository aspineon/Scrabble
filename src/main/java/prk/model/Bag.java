package prk.model;

import java.util.Arrays;
import java.util.Random;

public class Bag {

	private final String[] letters = { "A", "Ą", "B", "C", "Ć", "D", "E", "Ę", "F", "G", "H", "I", "J", "K", "L", "Ł",
			"M", "N", "Ń", "O", "Ó", "P", "R", "S", "Ś", "T", "U", "W", "Y", "Z", "Ż", "Ź", "*" };
	private int[] numberOfLetters = { 9, 1, 2, 3, 1, 3, 7, 1, 1, 2, 2, 8, 2, 3, 3, 2, 3, 5, 1, 6, 1, 3, 4, 4, 1, 3, 2,
			4, 4, 5, 1, 1, 2 };
	private final int[] pointsOfLetters = { 1, 5, 3, 2, 6, 2, 1, 5, 5, 3, 3, 1, 3, 2, 2, 3, 2, 1, 7, 1, 5, 2, 1, 1, 5,
			2, 3, 1, 2, 1, 9, 5, 0 };
	private int lettersLeft = 100;
	private Random rand = new Random();

	// jako parametr licba literek do wylosowania
	public String[] randomLetters(int number) {
		String[] randomLetters = new String[number];
		for (int i = 0; i < number; i++) {
			int index = rand.nextInt(33);
			while (numberOfLetters[index] == 0) {
				index = rand.nextInt(33);
			}
			String letter = letters[index];
			randomLetters[i] = letter;
			numberOfLetters[index]--;
		}
		lettersLeft -= number;
		return randomLetters;
	}

	public void findAndSubtract(String c) {
		int i = 0;
		while (!String.valueOf(c).equals(letters[i])) {
			i++;
		}
		if (numberOfLetters[i] > 0) {
			numberOfLetters[i]--;
			lettersLeft--;
		}
	}

	public void returnLetters(String[] returnLetters) {
		for (String c : returnLetters) {
			int i = 0;
			while (!String.valueOf(c).equals(letters[i])) {
				i++;
			}
			numberOfLetters[i]++;
			lettersLeft++;
		}
	}

	public int returnPointsOfLetter(String letter) {
		int index = 0;
		for (int i = 0; i < letters.length; i++) {
			if (letters[i].equals(letter)) {
				index = i;
			}
		}
		return pointsOfLetters[index];
	}

	public int[] getNumberOfLetters() {
		return numberOfLetters;
	}

	public void setNumberOfLetters(int[] numberOfLetters) {
		this.numberOfLetters = numberOfLetters;
	}

	public String[] getLetters() {
		return letters;
	}

	public int[] getPointsOfLetters() {
		return pointsOfLetters;
	}

	public int getLettersLeft() {
		return lettersLeft;
	}

	public void removeLetterFromBag(String letter) {
		numberOfLetters[Arrays.asList(letters).indexOf(letter)]--;
		this.lettersLeft--;
	}

}
