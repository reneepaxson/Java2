package edu.institution.actions.asn3;

import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.actions.asn10.UndoAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public class DeleteUserAction implements MenuAction{

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		System.out.println("Delete User:");
		String username, password, type;
		
		//prompt for the user name to delete
		do {
			System.out.print("Enter Username to Delete: ");
			username = scanner.nextLine();
			//check  if the supplied user name exists in the user list
			if (userRepository.retrieve(username) == null) {
				System.out.println("There is no user with that user name.");
				return true;
			}
		} while (username == "");
		
		//if user does exist prompt for the pw of the user being deleted
		do {
			System.out.print("Enter Password: ");
			password = scanner.nextLine();
			//if the pw is not correct, display an error message to the 
			//console and return out of the action
			if (!(userRepository.retrieve(username).isPasswordCorrect(password))) {
				System.out.println("That is not the correct password.");
				return true;
			} else {
				//if the pw is correct call the delete method on the user repository
				//passing the user name
				if (username.equalsIgnoreCase(loggedInUser.getUsername())) {
					//UndoAction.history.push("Delete User, involving: "+ userRepository.retrieve(username)+ ": "+userRepository.retrieve(username).getType());
					userRepository.delete(userRepository.retrieve(username));
					return false;
				} else {
					//UndoAction.history.push("Delete User, involving: "+ userRepository.retrieve(username) + ": "+userRepository.retrieve(username).getType());
					userRepository.delete(userRepository.retrieve(username));
					return true;
				}
				
				
				//otherwise return true to keep the user signed in
				
				
			}
		} while (password == "");
		
		
	}

}
