/*
Give a N by N matrix, a person start to move from upper left corner and can
move in four directions(up down left right), however, cannot move out of matrix
or re-visit same spot, find all longest paths and output.(As long as it cannot
move, we consider it as longest path)

https://leetcode.com/discuss/interview-question/358151/Google-or-Onsite-or-Find-all-longest-paths
*/
import java.util.List;
import java.util.ArrayList;

public class FindLongestPaths {

	public static void main(String [] args) {
		System.out.println("Dimension 10x10 matrix:");
		List<String> answers = solve(10);
		for (String str : answers) {
			System.out.println(str);
		}
	}

	public static List<String> solve(int dim) {
		int[][] matrix = new int[dim][dim];
		matrix[0][0] = 1;
		List<String> answers = new ArrayList<String>();
		int maxLength = helper(matrix, 0, 0, "", 0, answers);
		return answers;

	}

	private static int helper(int[][] matrix, int x, int y, String soFar, int maxLength, List<String> paths) {
		if (!inBounds(matrix, x, y)) {
			return maxLength;
		}
		if (!canMove(matrix, x, y)) {
			if (soFar.length() > maxLength) {
				paths.clear();
				paths.add(soFar);
				return soFar.length();
			} else if (soFar.length() == maxLength) {
				paths.add(soFar);
				return maxLength;
			}
		}
		if ((x > 0) && (matrix[x - 1][y] == 0)) {
			// we can move west!
			matrix[x - 1][y] = 1;
			int newMax = helper(matrix, x - 1, y, soFar + "W", maxLength, paths);
			if (newMax > maxLength) {
				maxLength = newMax;
			}
			matrix[x - 1][y] = 0;
		}
		if ((y > 0) && (matrix[x][y - 1] == 0)) {
			// we can move north!
			matrix[x][y - 1] = 1;
			int newMax = helper(matrix, x, y - 1, soFar + "N", maxLength, paths);
			if (newMax > maxLength) {
				maxLength = newMax;
			}
			matrix[x][y - 1] = 0;
		}
		if (x < (matrix.length - 1) && (matrix[x + 1][y] == 0)) {
			// we can move east!
			matrix[x + 1][y] = 1;
			int newMax = helper(matrix, x + 1, y, soFar + "E", maxLength, paths);
			if (newMax > maxLength) {
				maxLength = newMax;
			}
			matrix[x + 1][y] = 0;
		}
		if (y < (matrix.length - 1) && (matrix[x][y + 1] == 0)) {
			// we can move south!
			matrix[x][y + 1] = 1;
			int newMax = helper(matrix, x, y + 1, soFar + "S", maxLength, paths);
			if (newMax > maxLength) {
				maxLength = newMax;
			}
			matrix[x][y + 1] = 0;
		}
		return maxLength;

	}

	private static boolean inBounds(int[][] matrix, int x, int y) {
		if (x < 0 || x >= matrix.length) {
			return false;
		}
		if (y < 0 || y >= matrix.length) {
			return false;
		}
		return true;
	}

	private static boolean canMove(int[][] matrix, int x, int y) {
		boolean can_north = (y - 1) >= 0 && matrix[x][y - 1] == 0;
		boolean can_south = (y + 1) < matrix[x].length && matrix[x][y + 1] == 0;
		boolean can_east = (x + 1) < matrix.length && matrix[x + 1][y] == 0;
		boolean can_west = (x - 1) >= 0 && matrix[x - 1][y] == 0;
		return can_north || can_south || can_east || can_west;
	}
}