package edu.institution.actions.asn4;

import java.util.List;
import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class ListConnectionAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		/* loop through the users connections and display the user name of 
		 * each linked in user in the connections list
		 */
		List<LinkedInUser> users = loggedInUser.getConnections();
		if (users.isEmpty()) {
			System.out.println("You have no connections.");
		} else {
			System.out.println("User Connections:");
			for (int i = 0; i< users.size(); i++) {
				System.out.println(users.get(i));
			}
		}
		
		
		return true;
	}

}
