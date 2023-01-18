/*
 * written with assistance from the following source: https://www.geeksforgeeks.org/level-order-tree-traversal/
 */
package edu.institution.asn11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class BST<E extends Comparable<E>> {

	protected TreeNode<E> root;
	protected int size = 0;
	private List<E> list = new ArrayList<>();

	private int left = 0, right = 0, height = 0;

	public List<E> breadthFirstTraversal() {
		list.clear();

		int h = getHeight(root) + 1;
		int i;
		for (i = 1; i <= h; i++) {
			currentLevel(root, i);
		}
		return list;
	}

	public void currentLevel(TreeNode<E> node, int level) {

		if (node == null) {
			return;
		}
		if (level == 1) {
			list.add(node.element);
		} else if (level > 1) {
			currentLevel(node.left, level - 1);
			currentLevel(node.right, level - 1);
		}

	}

	public int getHeight(TreeNode<E> node) {
		if (node == null) {
			return height;
		} else if (node.left != null || node.right != null) {

			left = getHeight(node.left);
			right = getHeight(node.right);

			return Math.max(left, right) + 1;
		} else {
			// final height
			return height - 1;
		}
	}

	public List<E> nonRecursiveInOrder() {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode<E> current = root;
		List<E> list = new ArrayList<>();
		while (current != null || stack.size() > 0) {
			while (current != null) {
				stack.push(current);
				current = current.left;
			}
			current = stack.pop();
			list.add(current.element);
			// System.out.print(current.element + " ");
			current = current.right;
		}

		return list;
	}

	public BST() {
	}

	public BST(E[] objects) {
		for (int i = 0; i < objects.length; i++) {
			insert(objects[i]);
		}
	}

	public boolean search(E e) {
		TreeNode<E> current = root;

		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else {
				return true;
			}
		}

		return false;
	}

	public boolean insert(E e) {
		if (root == null) {
			root = createNewNode(e);
		} else {
			TreeNode<E> parent = null;
			TreeNode<E> current = root;
			while (current != null) {
				if (e.compareTo(current.element) < 0) {
					parent = current;
					current = current.left;
				} else if (e.compareTo(current.element) > 0) {
					parent = current;
					current = current.right;
				} else {
					return false;
				}
			}

			if (e.compareTo(parent.element) < 0) {
				parent.left = createNewNode(e);
			} else {
				parent.right = createNewNode(e);
			}
		}

		size++;
		return true;
	}

	protected TreeNode<E> createNewNode(E e) {
		return new TreeNode<E>(e);
	}

	public void inorder() {
		inorder(root);
	}

	protected void inorder(TreeNode<E> root) {
		if (root == null)
			return;
		inorder(root.left);
		System.out.print(root.element + " ");
		inorder(root.right);
	}

	public void postorder() {
		postorder(root);
	}

	protected void postorder(TreeNode<E> root) {
		if (root == null)
			return;
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.element + " ");
	}

	public void preorder() {
		preorder(root);
	}

	protected void preorder(TreeNode<E> root) {
		if (root == null)
			return;
		System.out.print(root.element + " ");
		preorder(root.left);
		preorder(root.right);
	}

	public int getSize() {
		return size;
	}

	public TreeNode<E> getRoot() {
		return root;
	}

	public ArrayList<TreeNode<E>> path(E e) {
		ArrayList<TreeNode<E>> list = new ArrayList<>();
		TreeNode<E> current = root;

		while (current != null) {
			list.add(current);
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else {
				break;
			}
		}

		return list;
	}

	public boolean delete(E e) {
		TreeNode<E> parent = null;
		TreeNode<E> current = root;

		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				parent = current;
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				parent = current;
				current = current.right;
			} else {
				break;
			}
		}

		if (current == null) {
			return false;
		}

		if (current.left == null) {
			if (parent == null) {
				root = current.right;
			} else {
				if (e.compareTo(parent.element) < 0) {
					parent.left = current.right;
				} else {
					parent.right = current.right;
				}
			}
		} else {
			TreeNode<E> parentOfRightMost = current;
			TreeNode<E> rightMost = current.left;

			while (rightMost.right != null) {
				parentOfRightMost = rightMost;
				rightMost = rightMost.right;
			}

			current.element = rightMost.element;

			if (parentOfRightMost.right == rightMost) {
				parentOfRightMost.right = rightMost.left;
			} else {
				parentOfRightMost.left = rightMost.left;
			}
		}

		size--;
		return true;
	}

	public Iterator<E> iterator() {
		return new InorderIterator();
	}

	public void clear() {
		root = null;
		size = 0;
	}

	private class InorderIterator implements Iterator<E> {

		private ArrayList<E> list = new ArrayList<>();
		private int current = 0;

		public InorderIterator() {
			inorder();
		}

		private void inorder() {
			inorder(root);
		}

		private void inorder(TreeNode<E> root) {
			if (root == null)
				return;
			inorder(root.left);
			list.add(root.element);
			inorder(root.right);
		}

		public boolean hasNext() {
			return current < list.size();
		}

		public E next() {
			return list.get(current++);
		}

		public void remove() {
			delete(list.get(current));
			list.clear();
			inorder();
		}
	}
}
