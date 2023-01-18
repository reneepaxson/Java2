package edu.institution.actions.asn4;

import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.actions.asn10.UndoAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public class AddConnectionAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		System.out.println("Add new connection:");
		// prompt for the user name of the user to connect with
		String username;
		do {
			System.out.print("Enter Username to Connect With: ");
			username = scanner.nextLine();
		} while (username == "");

		// check if the supplied user name exists in the user list
		if (userRepository.retrieve(username) == null) {
			// if the user does not exist display the message and return true to keep the
			// user signed in
			System.out.println("There is no user with that user name.");
			return true;
		} else {
			try {
				loggedInUser.addConnection(userRepository.retrieve(username));
				System.out.println("The Connection was Added Successfully.");
				UndoAction.history.push("Add Connection, involving: " + loggedInUser);
				
			} catch (LinkedInException exception) {
				// if the user is already in the logged in user's connection list, then catch
				// the LinkedInException that was thrown in the second assignment and display
				// the
				// message contained within the exception
				System.out.println(exception.getMessage());
			}
		}
		return true;
	}

}
