/*
Given two strings, A and B, determine the minimum number of cocatenations of
subsequences of B to create A. Return -1 if not possible.

Ex: A: allan B: lan    Answer: 3 (lan + lan +lan)

https://leetcode.com/discuss/interview-question/358669/Google-or-Shortest-Way-to-Form-String
*/

import java.util.*;

public class ShortestWayToFormAString {
	public static void main(String[] args) {
		String a = "a";
		String b = "";
		System.out.println("String a = " + a);
		System.out.println("String b = " + b);
		System.out.println("number of concatenations = " + solve(a, b));
	}

	public static int solve(String a, String b) {
  		if (a == null || b == null || b.isEmpty()) return -1;
  		return breakString(a, b);
	}

	// function breaks off as many characters from the beginning of String a as are a 	// valid substring of string b.
	private static int breakString(String a, String b) {
		System.out.println("String a: " + a);
		// base case
		if (a.isEmpty() || a.equals(b)) return 1;
	  	List<Integer> cacheStartChars = new ArrayList<Integer>();
	  	for (int i = 0; i < b.length(); i++) {
	    	if (b.charAt(i) == a.charAt(0)) cacheStartChars.add(i);
	  	}
	  	if (cacheStartChars.isEmpty()) return -1;
	  	int curLength = 0;
	  	int maxLength = 0;
	  	String curSubstring = "";
	  	String maxSubstring = "";
	  	for (int startChar : cacheStartChars) {
	    	int a_index = 0;
	    	int b_index = startChar;
	    	curLength = 0;
	    	curSubstring = "";
	    	while (a_index < a.length() && b_index < b.length() &&
	    		a.charAt(a_index) == b.charAt(b_index)) {
				curLength ++;
				curSubstring += a.charAt(a_index);
				a_index++;
	       		b_index++;
	    	}
	    	if (curLength > maxLength) {
				maxSubstring = curSubstring;
	    	}
	  	}
	  	int nextStep = breakString(a.substring(maxSubstring.length(), a.length()), b);  
	  	if (nextStep == -1) return -1;
	  	return 1 + nextStep;
	}

}