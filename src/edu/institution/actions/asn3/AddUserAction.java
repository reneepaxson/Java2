package edu.institution.actions.asn3;

import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.actions.asn10.UndoAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public class AddUserAction implements MenuAction{

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		System.out.println("Add New User:");	
		try {
			String username, password, type;
			
			//prompt user for the user name to add
			do {
				System.out.print("Enter Username: ");
				username = scanner.nextLine();
				if (userRepository.retrieve(username) != null) {
					System.out.println("A user already exists with that user name.");
					username = "";
				}
			} while (username == "");
			
			//prompt the user to enter a pw 
			do {
				System.out.print("Enter Password: ");
				password = scanner.nextLine();
			} while (password == "");
			
			//and what type of user they are (P or S)
			do {
				System.out.print("Enter User Type (P or S): ");
				type = scanner.nextLine();
				
			} while (!(type.equalsIgnoreCase("P")|| type.equalsIgnoreCase("s")));
			
			//construct a new LinkedInUser, setting the supplied user name, pw, and type
			LinkedInUser user = new LinkedInUser(username,password);
			user.setType(type);
			
			//and call the add method on the supplied user repository
			userRepository.add(user);
			UndoAction.history.push("Add New User, involving: "+ user);
			
		} catch (LinkedInException exception){
			//if the LinkedInException is thrown, catch it and display the message
			//from the exception
			System.out.println(exception.getMessage());
		}
		
		//return true to keep the user signed in
		return true;
	}
	
	

}
