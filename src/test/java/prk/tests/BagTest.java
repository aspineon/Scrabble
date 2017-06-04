package prk.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import prk.model.Bag;

/**@author Maciej Gawlowski */

public class BagTest {
	private Bag bag;
	
	@Before
	public void setUp(){
		bag = new Bag();
	}
	
	@Test
	public void GivenBag_WhenGettingLengthOfArrays_ThenRightLengthIsReturned(){
		assertEquals(bag.getLetters().length,33);
		assertEquals(bag.getNumberOfLetters().length,33);
		assertEquals(bag.getPointsOfLetters().length,33);
	}
	
	@Test
	public void GivenBag_WhenGettingNumberOfAllLetters_ThenRightNumberIsReturned(){
		int sum=0;
		for(int i: bag.getNumberOfLetters()){
			sum+=i;
		}		
		assertEquals(sum, bag.getLettersLeft());
	}
	
	@Test
	public void GivenBag_WhenRandomLetters_ThenRightLettersLeftValueIsReutrned(){
		bag.randomLetters(6);
		assertEquals(bag.getLettersLeft(), 94);
	}
	
	@Test
	public void findAndSubtractWhenLetterIsInBag(){
		bag.findAndSubtract("C");
		assertEquals(bag.getNumberOfLetters()[3],2);
		assertEquals(bag.getLettersLeft(),99);
	}
	
	@Test
	public void findAndSubtractWhenLetterIsNotInBag(){
		bag.findAndSubtract("C");
		bag.findAndSubtract("C");
		bag.findAndSubtract("C");
		assertEquals(bag.getNumberOfLetters()[3],0);
		assertEquals(bag.getLettersLeft(),97);
		
		bag.findAndSubtract("C");
		assertEquals(bag.getNumberOfLetters()[3],0);
		assertEquals(bag.getLettersLeft(),97);		
	}
	
	@Test
	public void returnLetters(){
		String[] returnLetters = {"A", "B", "C"};
		bag.returnLetters(returnLetters);
		assertEquals(bag.getLettersLeft(), 103);
		assertEquals(bag.getNumberOfLetters()[0], 10);
		assertEquals(bag.getNumberOfLetters()[2], 3);
		assertEquals(bag.getNumberOfLetters()[3], 4);
	}
	
	@Test
	public void returnPointsOfLetter(){
		assertEquals(bag.returnPointsOfLetter("A"),1);
		assertEquals(bag.returnPointsOfLetter("K"),2);
		assertEquals(bag.returnPointsOfLetter("*"),0);
	}
	
	@Test
	public void removeLetterFromBag(){
		bag.removeLetterFromBag("C");
		assertEquals(bag.getNumberOfLetters()[3],2);
		assertEquals(bag.getLettersLeft(),99);
	}
}
