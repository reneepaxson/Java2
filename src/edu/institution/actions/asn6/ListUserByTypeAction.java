//made with assistance from the following source: 
//https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
package edu.institution.actions.asn6;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class ListUserByTypeAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		List<LinkedInUser> users = userRepository.retrieveAll();
		Collections.sort(users, typeComparator);

		for (LinkedInUser user : users) {
			System.out.println(user + "; type = " + user.getType());
		}

		return true;
	}

	public Comparator<LinkedInUser> typeComparator = new Comparator<LinkedInUser>() {
		@Override
		public int compare(LinkedInUser user1, LinkedInUser user2) {
			String userType1 = user1.getType().toUpperCase();
			String userType2 = user2.getType().toUpperCase();
			int typeComp = userType1.compareTo(userType2);

			if (typeComp != 0) {
				return typeComp;
			}

			String userName1 = user1.getUsername().toUpperCase();
			String userName2 = user2.getUsername().toUpperCase();
			return userName1.compareTo(userName2);
		}
	};

}
