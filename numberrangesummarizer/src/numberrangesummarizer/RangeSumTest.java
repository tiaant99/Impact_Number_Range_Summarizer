package numberrangesummarizer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;


/**
 * @author Tiaan
 * 
 * Assumptions:
 * 	duplicate values are allowed and the String representation of the number list need not neccesaraly be sorted.
 */

public class RangeSumTest {

	String testsinglerange;
	String testmiddlerange;
	String testnegatives;
	String testunorderd;
	String testspaces;
	String testduplicates;
	RangeSummarizer testcls = new RangeSummarizer();
	
	/**
	 * sets up test strings for all test cases.
	 * contains test cases for base case 1-10 and then with gapes in between
	 * also tests negative numbers, duplicates and spaces
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		 testsinglerange = "1,2,3,4,5,6,7,8,9,10";
		 testmiddlerange = "1,3,5,6,7,8,9,11,13";
		 testnegatives = "-2,-1,3,4,5,6,7,8,9,10";
		 testunorderd = "1,2,7,10,5,6,3,8,9,4";
		 testspaces = "1,   2,3,4    ,5,6,7,8,9,10    ";
		 testduplicates = "1,1,1,1,4,4,4,4,5,5";
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Tests the functionality of converting the input string to an integer collection
	 */
	@Test
	public void testCollect() {
		assertArrayEquals(((List<Integer>)testcls.collect(testsinglerange)).toArray(Integer[]::new),new Integer[] {1,2,3,4,5,6,7,8,9,10});
		assertArrayEquals(((List<Integer>)testcls.collect(testspaces)).toArray(Integer[]::new),new Integer[] {1,2,3,4,5,6,7,8,9,10});
		assertArrayEquals(((List<Integer>)testcls.collect(testduplicates)).toArray(Integer[]::new),new Integer[] {1,1,1,1,4,4,4,4,5,5});
	}

	/**
	 * test taking a integer collection as input and summarizing the list
	 */
	@Test
	public void testSummarizeCollection() {
		List<Integer> testsinglerangecol = (List<Integer>)testcls.collect(testsinglerange); 
		assertEquals(testcls.summarizeCollection(testsinglerangecol),"1-10");
		List<Integer> testmiddlerangecol = (List<Integer>)testcls.collect(testmiddlerange);
		assertEquals(testcls.summarizeCollection(testmiddlerangecol),"1,3,5,6,7,8,9,11,13");
		List<Integer> testduplicatescol = (List<Integer>)testcls.collect(testduplicates);
		assertEquals(testcls.summarizeCollection(testduplicatescol),"1, 4-5");
	}
	
	
	/**
	 * test the driver method that uses all the other methods that have been tested. Does not sort
	 */
	@Test
	public void testNum_Range_SummarizerString() {
		assertEquals(testcls.Num_Range_Summarizer(testsinglerange),"1-10");
		assertEquals("1, 3, 5-9, 11, 13",testcls.Num_Range_Summarizer(testmiddlerange));
		assertEquals(testcls.Num_Range_Summarizer(testnegatives),"-2--1, 3-10");
		assertEquals(testcls.Num_Range_Summarizer(testunorderd),"1-2, 7, 10, 5-6, 3, 8-9, 4");
		assertEquals(testcls.Num_Range_Summarizer(testspaces),"1-10");
		assertEquals(testcls.Num_Range_Summarizer(testduplicates),"1, 4-5");
		
		
	}

	/**
	 * tests the sorting functionality 
	 */
	@Test
	public void testNum_Range_SummarizerStringBoolean() {
		assertEquals(testcls.Num_Range_Summarizer(testunorderd,false),"1-2, 7, 10, 5-6, 3, 8-9, 4");
		assertEquals(testcls.Num_Range_Summarizer(testunorderd,true),"1-10");
		
	}

	

}
