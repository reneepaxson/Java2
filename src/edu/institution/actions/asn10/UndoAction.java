package edu.institution.actions.asn10;

import java.util.Scanner;
import java.util.Stack;

import edu.institution.ApplicationHelper;
import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public class UndoAction implements MenuAction {

	public static Stack<String> history = new Stack<>();
	

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		String ch;
		if (history.isEmpty()) {
			System.out.println("There are no actions to undo!");
			return true;
		}
		System.out.println("The last menu option selected was "+ history.peek());
		do {
			System.out.print("Undo? Y/N: ");
			ch = scanner.nextLine();
			ch.toLowerCase();
		} while (!(ch.equals("y")||ch.equals("n")));
		
		if (ch.equals("n")) {
			return true;
		}
		
		//assign the top of the stack to a string for easy use
		String str = history.peek();
		//parse string to retrieve username
		String[] parts = str.split(": ");
		String username = parts[1];
		System.out.println(username);
		//assign username to user
		LinkedInUser user = userRepository.retrieve(username);
		
		if (str.contains("Add Connection")) {
			try {
				//retrieves the last added user and removes them from the logged in user's connections list
				LinkedInUser user2 = user.getConnections().get(user.getConnections().size()-1);
				user.removeConnection(user2);
				System.out.println(user2.getUsername() +" successfully removed.");
				history.pop();
			} catch (LinkedInException e) {
				e.printStackTrace();
			}
			
		} else if (str.contains("Remove Connection")){
			try {
				loggedInUser.addConnection(user);
				System.out.println(user.getUsername() +" successfully added.");
				history.pop();
			} catch (LinkedInException e) {
				e.printStackTrace();
			}
			
		} else if (str.contains("Add New User")) {
			userRepository.delete(user);
			System.out.println(user.getUsername() + " successfully deleted.");
			history.pop();
			
		}
		//"delete user" got wonky because of the need to establish a password 
		/*else if (str.contains("Delete User")) {
			
			try {
				userRepository.add(user);
				//parts[2] will contain the user type
				user.setType(parts[2]);
				System.out.println(user.getUsername()+ " successfully added.");
				history.pop();
			} catch (LinkedInException e) {
				e.printStackTrace();
			}
			
		}*/else if (str.contains("Add New Skillset")) {
			//parts[2] is the skillset
			user.removeSkillset(parts[2]);
			System.out.println("Successfully removed skillset from "+user.getUsername()+": "+parts[2]);
			ApplicationHelper.decrementSkillsetCount(parts[2]);
			history.pop();
			
		}else if (str.contains("Remove Skillset")) {
			user.addSkillset(parts[2]);
			ApplicationHelper.incrementSkillsetCount(parts[2]);
			System.out.println("Successfully added skillset to "+user.getUsername()+": "+parts[2]);
			history.pop();
		}
		
		return true;
	}

}
