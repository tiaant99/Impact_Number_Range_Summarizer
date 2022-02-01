package numberrangesummarizer;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Tiaan
 * 
 * Assumptions:
 * 	duplicate values are allowed and the String representation of the number list need not neccesaraly be sorted.
 * 
 * There are 2 Num_Range_Summarizer functions, one were only the String is passed to it and another were the String
 * and a boolean is passed to it. The boolean is called sort and indicates whether the list of integers need to be sorted.
 * When no boolean is provided it is assumed that the list does not need to be sorted. 
 */

public class RangeSummarizer implements NumberRangeSummarizer {

	public static void main(String[] args) {
		String test = "1,3,5,6,7,8,9,11,13";		
		RangeSummarizer testt = new RangeSummarizer();
		System.out.println(testt.Num_Range_Summarizer(test,true));
		//Integer[] check = ((List<Integer>)testt.collect("1,2,3,4,5,6,7,8,9,10")).toArray(Integer[]::new);
	}
	
	/**
	 * This method take a string representation of a comma delimited list and then groups numbers into ranges if they are subsequent.
	 * This method assumes that the list does not need to be sorted before it is summarized
	 * @param input
	 * @return summarized number list
	 */	
	public String Num_Range_Summarizer (String input) {
		return Num_Range_Summarizer(input,false);
	}
	
	/**
	 * This method take a string representation of a comma delimited list and then groups numbers into ranges if they are subsequent.
	 * This method also takes a boolean as input which determines whether the list of integers need to be sorted before summarizing 
	 * @param input string representation of a comma delimited list
	 * @param sort whether or not the list needs to be sorted
	 * @return summarized number list
	 */
	public String Num_Range_Summarizer (String input, boolean sort) {	
		List <Integer> num = (List<Integer>) collect(input);
		if (sort) { Collections.sort(num); }
		return summarizeCollection(num);
	}

	/**
	 * Converts a string representation of a comma delimited list to an List of Integers
	 * @param input string representation of a comma delimited list
	 * @return a Collection<Integers> in the form of a list 
	 */
	@Override
	public Collection<Integer> collect(String input) {	
		input = input.replaceAll("\\s+","");
		return Arrays.asList(Stream.of(input.split(",")).map(Integer::valueOf).toArray(Integer[]::new));
	}

	/**
	 * This function takes a List of integers and returns a string representation of a summarized version of the given list.
	 * @param input List of integers to be summarized
	 * @return string representation of the summarized list.
	 */
	@Override
	public String summarizeCollection(Collection<Integer> input) {
		List<Integer> numm = (List<Integer>)input;
		StringBuilder rez = new StringBuilder();
		int i=0; Integer start = 0;
		while (i < numm.size()){
			if (i >= numm.size()-1) { rez.append(numm.get(i).toString()); break; }
			start = numm.get(i);
			while (numm.get(i+1)-1 == numm.get(i) || numm.get(i) == numm.get(i+1)) {
				i++;
				if (i >=numm.size() - 1) {
					if (numm.get(i) != start) { rez.append(start.toString() + "-" + numm.get(i).toString()); }
					else { rez.append(start.toString()); }
					break;
				}
			}
			if (i < numm.size() - 1) {
				if (numm.get(i) == start) { rez.append(numm.get(i).toString() + ", "); }
				else if (numm.get(i) != start && i< numm.size() - 1) { rez.append(start.toString() + "-" + numm.get(i).toString() + ", "); }
			}
			i++;
		}
		return rez.toString();
	}

}
