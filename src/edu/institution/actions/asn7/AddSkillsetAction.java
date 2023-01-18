package edu.institution.actions.asn7;

import java.util.Scanner;

import edu.institution.ApplicationHelper;
import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.actions.asn10.UndoAction;
import edu.institution.asn2.LinkedInUser;

public class AddSkillsetAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		String skill;
		System.out.println();
		int i = 0;
		do {
			System.out.println("Add new skillset: ");
			skill = scanner.nextLine();
			if (skill == "") {
				i++;
				if (i > 2) {
					System.out.println("Invalid skillset.");
					return true;
				}
			}
		} while (skill == "");
		skill = skill.toLowerCase();
		if (loggedInUser.getSkillsets().contains(skill)) {
			System.out.println("You already have this skillset.");
			return true;
		}
		loggedInUser.addSkillset(skill);
		ApplicationHelper.incrementSkillsetCount(skill);
		System.out.println(skill + " has been added to your skillsets.");
		UndoAction.history.push("Add New Skillset, involving: " +loggedInUser +": "+skill);

		return true;
	}

}
