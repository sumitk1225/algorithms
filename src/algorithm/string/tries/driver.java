package algorithm.string.tries;

import java.util.Arrays;
import java.util.Comparator;

public class driver {

	public static void main(String arg[]) {
	Tries tries = new Tries();
	String inputString = "cat cat dog ";
	//String inputString = "catx dog horse xyz rat ra cats ";
	for(String seq: inputString.split(" ")) {
		tries.add(seq);
	}
	tries.printTrie();
	//System.out.println("Found:" + tries.search("xyze"));
	//System.out.println("Found:" + tries.prefix("xy"));
	//String input[] = "horsecat catcatcats horsehorse horse".split(" ");
	String input[] = {"catsdogcats", "catxdogcatsra", "dog", "dogcatsdog", "hippopotamuses", "ratcatdogcat" , "catcat"};
	//String input[] = { "catcat"};
	Arrays.sort(input, new Comparator<String>() {

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			if(arg0.length() > arg1.length())
				return -1;
			if(arg0.length() == arg1.length())
				return 0;
			return 1;
		}
		
	});
	for(String inputStringForLongest:input) {
		if(tries.findLongestCharacter(inputStringForLongest))  {
			System.out.println("Found Longest Word:" + inputStringForLongest);
			break;
		}
		
	}
	}
}
