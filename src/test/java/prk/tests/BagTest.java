package prk.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import prk.model.Bag;

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
}
