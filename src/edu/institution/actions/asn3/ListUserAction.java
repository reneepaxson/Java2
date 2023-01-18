package edu.institution.actions.asn3;

import java.util.List;
import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class ListUserAction implements MenuAction {

	//retrieve all of LinkedIn users from the user repository
	//and print each user's usernames to the console.
	
	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
		System.out.println("List User Action");		
		
		//loop through list one by one to print users
		List<LinkedInUser> users = userRepository.retrieveAll();
		for (int i = 0; i< users.size(); i++) {
			System.out.println(users.get(i));
		}
		
		//process method should return true to keep the user signed in
		return true;
	}

}
