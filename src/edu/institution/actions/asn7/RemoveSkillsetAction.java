package edu.institution.actions.asn7;

import java.util.Scanner;

import edu.institution.ApplicationHelper;
import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.actions.asn10.UndoAction;
import edu.institution.asn2.LinkedInUser;

public class RemoveSkillsetAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {

		if (loggedInUser.getSkillsets().isEmpty()) {
			System.out.println("You have not entered any skillsets.");
			return true;
		}
		String skill;
		int i = 0;
		do {
			System.out.println("Remove skillset: ");
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
			loggedInUser.removeSkillset(skill);
			ApplicationHelper.decrementSkillsetCount(skill);
			UndoAction.history.push("Remove Skillset, involving: " +loggedInUser +": "+skill);
		} else {
			System.out.println("You do not have this skillset.");
		}
		return true;
	}

}
