/*
One string is strictly smaller than another when the frequency of occurrence of
the smallest character in the string is less than the freqneyc of occurrence of
the smallest character in the comparison string.

"abcd" < "aaa"

Write a function that, given string A (which contains M strings delimited by ',')
and string B (which contains N string delimited by ',') returns an array of N
integers where each integer specifies the number of strings in A that are
strictly smaller than the comparison j-th string in B (zero-indexed).

https://leetcode.com/discuss/interview-question/352458/Google-or-OA-Fall-Internship-2019-or-Compare-Strings
*/

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Scanner;

public class CompareStrings {
	public static final int alphabet = 26;
	public static final int a = 'a';
	
	public static void main(String[] args) {
		if (args.length !=2) {
			System.out.println("Usage: CompareStrings <string a> <string b>");
			throw new IllegalArgumentException();
		}
		int[] answer = solve(args[0], args[1]);
		System.out.print("Answer: [  ");
		for (int a : answer) {
			System.out.print(a + "  ");
		};
		System.out.println("].");

	}
	public static int[] solve(String a, String b) {
		// break A into substrings
		int[][] a_indexed = indexStrings(a.split(","));
		int[][] b_indexed = indexStrings(b.split(","));

		int[] answer = new int[b_indexed.length];
		int j = 0;
		for (int[] comparison_string : b_indexed) {
			int count_smaller = 0;
			for (int[] a_string : a_indexed) {
				if (lessThan(a_string, comparison_string)) {
					count_smaller ++;
				}
			}
			answer[j] = count_smaller;
			j++;
		}
		return answer;
	}

	private static boolean lessThan(int[] a, int[] b) {
		int a_index = 0;
		int b_index = 0;
		while (a[a_index] == 0) {
			a_index ++;
		}
		while (b[b_index] == 0) {
			b_index ++;
		}
		return a[a_index] < b[b_index];
	}

	private static int[][] indexStrings(String[] strings) {
		int[][] index = new int[strings.length][];
		int string_index = 0;
		for (String str : strings) {
			CharacterIterator itr = new StringCharacterIterator(str);
			index[string_index] = new int[alphabet];
			while (itr.current() != CharacterIterator.DONE) {
				index[string_index][itr.current() - a] ++;
				itr.next();
			}
			string_index ++;
		}
		return index;
	}
}