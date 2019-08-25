/*
n students [0, ..., n-1] participate in a marathon. You are given an int array
standings where standings[i] = j means that student j finished just before
student i. standings[k] = -1 means that k is the first student. There are no ties.
List out the students in the order in which they finished the marathon.

https://leetcode.com/discuss/interview-question/355686/Google-or-Marathon
*/

import java.util.*;

public class Marathon {
	public static void main(String[] args) {
		// Input [-1, 0, 1]
		int [] standings = {-1, 0, 1};
		solveNoTies(standings);

		// Input [-1, 0, 0]
		int [] standings2 = {-1, 0, 0};
		solveTies(standings2);

		int[] standings3 = {-1, 0, 1, 2, 3, 3, 3, 5, 7, 8};
		solveTies(standings3);
	}

	public static void solveNoTies(int[] standings) {
		// map of who came before to who came next
		Map<Integer, Integer> pairings = new HashMap<Integer, Integer>();
		for (int i = 0; i < standings.length; i++) {
			pairings.put(standings[i], i);
		}
		int nextPerson = pairings.get(-1);
		System.out.print("[" + nextPerson);
		for(int i = 1; i < standings.length; i++) {
			nextPerson = pairings.get(nextPerson);
			System.out.print(", " + nextPerson);
		}
		System.out.println("]");
	}

	public static void solveTies(int[] standings) {
	// map of who came before to who (all) came next
		Map<Integer, Set<Integer>> order = new HashMap<Integer, Set<Integer>>();
		for (int i = 0; i < standings.length; i++) {
			if (!order.containsKey(standings[i])) {
				Set<Integer> newSet = new HashSet<Integer>();
				newSet.add(i);
				order.put(standings[i], newSet);
			} else {
				order.get(standings[i]).add(i);
			}
		}
		Queue<Integer> rankings = new LinkedList<Integer>();
		Queue<Integer> leftToAdd = new LinkedList<Integer>();
		leftToAdd.add(-1);
		while (!leftToAdd.isEmpty()) {
			int popped = leftToAdd.remove();
			Set<Integer> gotten = order.get(popped);
			if (gotten == null) { continue; }
			putSetInQueue(rankings, order.get(popped));
			for (Integer val : order.get(popped)) {
				putSetInQueue(leftToAdd, order.get(val));
				putSetInQueue(rankings, order.get(val));
			}
		}
		System.out.print("[" + rankings.remove());
		while (!rankings.isEmpty()) {
			System.out.print(", " + rankings.remove());
		}
		System.out.println("]");
	}

	private static void putSetInQueue(Queue<Integer> q, Set<Integer> s) {
		if (s != null) { 
			for (Integer i : s) {
				q.add(i);
			}
		}
	}
}

