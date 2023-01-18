package edu.institution.actions.asn6;

import java.util.List;
import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class ListUserAlphabeticallyAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
		List<LinkedInUser> users = userRepository.retrieveAll();
		if (users.isEmpty()) {
			System.out.println("This line should never run unless there's something seriously wrong");
		} else {
			
			System.out.println("User List Alphabetically: ");
			java.util.Collections.sort(users);
			for(LinkedInUser user: users) {
				System.out.println(user);
			}
			
		}

		return true;
	}

}
