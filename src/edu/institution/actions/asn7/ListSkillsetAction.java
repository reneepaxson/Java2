package edu.institution.actions.asn7;

import java.util.Scanner;

import edu.institution.ApplicationHelper;
import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class ListSkillsetAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {

		if (loggedInUser.getSkillsets().isEmpty()) {
			System.out.println("You have not entered any skillsets.");
			return true;
		}
		for (String skill : loggedInUser.getSkillsets()) {
			System.out.println(skill + ": " + ApplicationHelper.retrieveSkillSetCount(skill) + " total user(s)");
		}

		return true;
	}

}
