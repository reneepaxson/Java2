package edu.institution.asn5;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import edu.institution.actions.asn5.Utilities;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public class UtilitiesTest {

	Utilities utilities = new Utilities();

	// test that uses an array of integers
	@Test
	public void testInts() throws LinkedInException {

		List<Integer> intList = new ArrayList<Integer>();
		List<Integer> testList = new ArrayList<Integer>();

		intList = utilities.removeDuplicates(intList);

		// test empty list
		Assert.assertEquals(testList, intList);

		intList.add(Integer.valueOf(10));
		intList.add(Integer.valueOf(4));
		intList.add(Integer.valueOf(10));

		testList.add(Integer.valueOf(10));
		testList.add(Integer.valueOf(4));

		intList = utilities.removeDuplicates(intList);

		// test list of [10, 4, 10]
		Assert.assertEquals(testList, intList);

		intList.add(Integer.valueOf(10));
		intList.remove(Integer.valueOf(4));
		testList.remove(Integer.valueOf(4));

		intList = utilities.removeDuplicates(intList);

		// test list of [10,10]
		Assert.assertEquals(testList, intList);

		intList.add(Integer.valueOf(4));
		intList.add(Integer.valueOf(10));
		testList.add(Integer.valueOf(4));

		// search list of [10,4,10] for value 9
		Assert.assertEquals(null, utilities.linearSearch(intList, 9));
		// search list of [10,4,10] for value 4
		Assert.assertEquals(Integer.valueOf(4), utilities.linearSearch(intList, 4));
		// search list of [10,4,10] with null key
				Assert.assertEquals(null, utilities.linearSearch(intList, null));

		intList = null;
		intList = utilities.removeDuplicates(intList);
		Assert.assertEquals(null, intList);
		Assert.assertEquals(null, utilities.linearSearch(intList, 4));
	}

	// test that uses an array of strings
	@Test
	public void testStrings() throws LinkedInException {

		List<String> strList = new ArrayList<String>();
		List<String> testList = new ArrayList<String>();

		strList = utilities.removeDuplicates(strList);

		// test empty list
		Assert.assertEquals(testList, strList);

		strList.add("a");
		strList.add("b");
		strList.add("a");

		testList.add("a");
		testList.add("b");

		strList = utilities.removeDuplicates(strList);

		// test list of ["a","b","a"]
		Assert.assertEquals(testList, strList);

		strList.add("a");
		strList.remove("b");
		testList.remove("b");

		strList = utilities.removeDuplicates(strList);

		// test list of ["a","a"]
		Assert.assertEquals(testList, strList);

		strList.add("b");
		strList.add("a");
		testList.add("b");

		// search list of ["a","b","a"] for value "c"
		Assert.assertEquals(null, utilities.linearSearch(strList, "c"));
		// search list of ["a","b","a"] for value "b"
		Assert.assertEquals("b", utilities.linearSearch(strList, "b"));
		// search list of ["a","b","a"] with null key
		Assert.assertEquals(null, utilities.linearSearch(strList, null));

		// search list of null values
		strList = null;
		strList = utilities.removeDuplicates(strList);
		Assert.assertEquals(null, strList);
		Assert.assertEquals(null, utilities.linearSearch(strList, "c"));

	}

	// test that uses an array of LinkedInUsers
	@Test
	public void testUsers() throws LinkedInException {
		List<LinkedInUser> userList = new ArrayList<LinkedInUser>();
		List<LinkedInUser> testList = new ArrayList<LinkedInUser>();

		userList = utilities.removeDuplicates(userList);

		// test empty list
		Assert.assertEquals(testList, userList);

		LinkedInUser user1 = new LinkedInUser("user1", null);
		LinkedInUser user2 = new LinkedInUser("user2", null);
		LinkedInUser user3 = new LinkedInUser("user3", null);

		userList.add(user1);
		userList.add(user2);
		userList.add(user1);

		testList.add(user1);
		testList.add(user2);

		userList = utilities.removeDuplicates(userList);

		// test list of [user1,user2,user1]
		Assert.assertEquals(testList, userList);

		userList.add(user1);
		userList.remove(user2);
		testList.remove(user2);

		userList = utilities.removeDuplicates(userList);

		// test list of [user1,user1]
		Assert.assertEquals(testList, userList);

		userList.add(user2);
		userList.add(user1);
		testList.add(user2);

		// search list of [user1,user2,user1] for value user3
		Assert.assertEquals(null, utilities.linearSearch(userList, user3));
		// search list of [user1,user2,user1] for value user2
		Assert.assertEquals(user2, utilities.linearSearch(userList, user2));
		// search list of [user1,user2,user1] with null key
		Assert.assertEquals(null, utilities.linearSearch(userList, null));

		// search list of null values
		userList = null;
		System.out.println("user list before sort: " + userList);
		userList = utilities.removeDuplicates(userList);
		Assert.assertEquals(null, userList);
		Assert.assertEquals(null, utilities.linearSearch(userList, user3));

	}

}
