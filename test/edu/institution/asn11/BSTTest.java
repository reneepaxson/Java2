package edu.institution.asn11;

import org.junit.Assert;
import org.junit.Test;

public class BSTTest {

	@Test
	public void BSTTest() {

		BST<Integer> tree2 = new BST<>(new Integer[] { 50, 45, 35, 48, 59, 51, 58 });

		// test for proper tree initialization by checking the root
		Assert.assertEquals(tree2.getRoot().toString(), "50");

		// test proper height
		Assert.assertEquals(tree2.getHeight(tree2.getRoot()), 3);

		// test correct breadth first traversal
		Assert.assertEquals(tree2.breadthFirstTraversal().toString(), "[50, 45, 59, 35, 48, 51, 58]");

		// test nonrecursive inorder sort
		Assert.assertEquals(tree2.nonRecursiveInOrder().toString(), "[35, 45, 48, 50, 51, 58, 59]");

	}

}
