package edu.institution.actions.asn6;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class ListUserByConnectionAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		List<LinkedInUser> users = userRepository.retrieveAll();
		Collections.sort(users, connectComparator);

		for (LinkedInUser user : users) {

			int connectCount = 0;
			for (LinkedInUser subUser : user.getConnections()) {
				connectCount++;
			}
			System.out.println(user + "; connection size = " + connectCount);
		}
		return true;
	}

	public Comparator<LinkedInUser> connectComparator = new Comparator<LinkedInUser>() {
		@Override
		public int compare(LinkedInUser user1, LinkedInUser user2) {
			int user1Count = 0;
			int user2Count = 0;

			for (LinkedInUser user : user1.getConnections()) {
				user1Count++;
			}
			for (LinkedInUser user : user2.getConnections()) {
				user2Count++;
			}
			int countComp = Integer.compare(user2Count, user1Count);
			if (countComp != 0) {
				return countComp;
			}
			return 0;
		}
	};

}
