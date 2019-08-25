/*
Given an array of positive integers nums and an integer k, find the length of
the longest sequence where all elements are consecutive multiples of k.

https://leetcode.com/discuss/interview-question/355544/Google-or-Onsite-or-Longest-Divisible-Consecutive-Subsequence
*/

import java.util.*;

public class LongestDivisibleSubsequence {
	public static void main(String[] args) {
		int vals[] = {2, 6, 8, 10, 12, 14, 16, 32};
		int k = 2;
		System.out.println(solve(vals, k));
	}
	public static int solve(int[] nums, int k) {
    	Set<Integer> divisible = new HashSet<Integer>();
    	int largestMultiple = 0;
    	for (int i = 0; i < nums.length; i++) {
			if (nums[i] % k == 0) {
				divisible.add(nums[i] / k);
	  			if (nums[i] / k > largestMultiple) {
	  				largestMultiple = nums[i] / k;
	  			}
	  		}
		}
	    int[] values = new int[largestMultiple + 1];
	    for (int i : divisible) {
			values[i] = 1;
	    }
	    List<Integer> boundaries = new ArrayList<Integer>();
	    for (int i = 0; i < values.length; i++) {
			if (values[i] == 0) boundaries.add(i);
	    }
	    boundaries.add(values.length);
	    int prev = boundaries.get(0);
	    int maxLength = 0;
	    for (int i = 1; i < boundaries.size(); i++) {
			int curLength = boundaries.get(i) - prev - 1;
			if (curLength > maxLength) maxLength = curLength;
			prev = boundaries.get(i);
	    }
	    return maxLength;
  }

}