package edu.institution.actions.asn4;

import java.util.List;
import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class DegreeOfSeparationAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		// USING RECURSION, this action will display the path and the number of
		//connections between the logged in user and the entered user.
		String username;
		int degree = 0;
		do {
			System.out.print("Enter Username to Check Degree of Separation With: ");
			username = scanner.nextLine();
		} while (username == "");
		//the degree of separation doesn't include the initial user and the end user
		degree = degree(loggedInUser,userRepository.retrieve(username), degree);
		System.out.println("There is/are "+ degree + " degree(s) of separation between you and " + username);
		return true;
	}
	
	public int degree(LinkedInUser user1, LinkedInUser user2, int degree) {
		//loops through the method until the logged in user appears in another user's
		//connections list - in theory
		
		if (user2.getConnections().contains(user1)) {
			
			return degree;
		} else {
			for (LinkedInUser user : user2.getConnections()) {
				if (user.getConnections().contains(user1)) {
					
					return degree + 1;
				} else {
					return degree(user1, user, degree+1);
				}
			}
		}
		return degree;
		
	}

}
