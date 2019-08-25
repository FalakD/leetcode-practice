/*
Given a binary tree, where an arbitary node has 2 parents i.e two nodes in the
tree have the same child. Identify the defective node and remove an extra edge
to fix the tree.

https://leetcode.com/discuss/interview-question/358676/Google-or-Remove-Extra-Edge
*/

import java.util.HashSet;
import java.util.Set;

public class RemoveExtraEdge {
	static class Node {
		public final int value;
		public final Node left;
		public final Node right;

		public Node(int value, Node left, Node right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}

		public static void printChildren(Node n) {
			System.out.println("     " + n.value);
			System.out.println("  /     \\");
			String a = " ";
			if (n.left != null) {
				a += n.left.value;
			} else {
				a += "N";
			}
			a += "       ";
			if (n.right != null) {
				a += n.right.value;
			} else {
				a += "N";
			}
			System.out.println(a);
		}

		@Override
		public boolean equals(Object o) {
			if (o == this) {
				return true;
			}
			if (!(o instanceof Node)) {
				return false;
			}
			Node n = (Node) o;
			return this.value == n.value;
		}

	}

	public static void main(String[] args) {
		// create several inputs.
		Node irregular = new Node(5, null, null);
		Node top = new Node(1, new Node(2, 
										new Node(4, null, null),
										irregular),
								new Node(3, irregular, null));
		Node newTop = solve(top);
		Node.printChildren(newTop.right);
		Node.printChildren(newTop.left);
	}
	public static Node solve(Node top) {
		Set<Integer> childrenSeen = new HashSet<Integer>();
		childrenSeen.add(top.value);
		Node newTop = helper(top, childrenSeen);
		return newTop;
	}

	private static Node helper(Node root, Set<Integer> s) {
		if (root == null) {
			return null;
		}
		if (root.right == null && root.left == null) {
			return root;
		}
		if (root.right != null && s.contains(root.right.value)) {
			if (root.left != null) {
				s.add(root.left.value);
			}
			return new Node(root.value, null, helper(root.left, s));
		}
		if (root.left != null && s.contains(root.left.value)){
			if (root.right != null) {
				s.add(root.right.value);
			}
			return new Node(root.value, helper(root.right, s), null);
		}
		return new Node(root.value, helper(root.right, s), helper(root.left, s));
	} 
}