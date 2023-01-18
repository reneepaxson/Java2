/*
 Copyright (C) 2020. Doug Estep -- All Rights Reserved.
 Copyright Registration Number: TXU002159309.

 This file is part of the Tag My Code application.

 This application is protected under copyright laws and cannot be used, distributed, or copied without prior written
 consent from Doug Estep.  Unauthorized distribution or use is strictly prohibited and punishable by domestic and
 international law.

 Proprietary and confidential.
 */
package edu.institution;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import edu.institution.asn2.LinkedInUser;

/**
 * Contains static helper methods to aid with the command line user interface.
 */
public class ApplicationHelper {

	/*
	 * I chose to use the TreeMap for storing skillsets because it orders the values
	 * alphabetically and it is easy to manipulate
	 */
	private static Map<String, Integer> skillMap = new TreeMap<>();

	public static Map<String, Integer> getSkillMap() {
		return skillMap;
	}

	/**
	 * Displays the supplied message to the console.
	 * 
	 * @param message the message.
	 */
	public static void showMessage(String message) {
		System.out.println("\n" + message);
	}

	/*
	 * Increments the number of users associated with the supplied skillset If this
	 * is the first occurrence of the supplied skillset, then add the skillset to
	 * your collection and default the count to one
	 * 
	 * @param skillset the skillset to increment
	 */
	public static void incrementSkillsetCount(String skillset) {
		skillset = skillset.toLowerCase();
		if (skillMap.containsKey(skillset)) {
			skillMap.put(skillset, skillMap.get(skillset) + 1);
		} else {
			skillMap.put(skillset, 1);
		}
	}

	/*
	 * Decrements the number of users associated with the supplied skillset If the
	 * number of users associated with the supplied skillset is zero, then remove
	 * the skillset from your collection
	 * 
	 * @param skillset the skillset to decrement
	 */
	public static void decrementSkillsetCount(String skillset) {
		skillset = skillset.toLowerCase();
		if (skillMap.containsKey(skillset)) {
			skillMap.put(skillset, skillMap.get(skillset) - 1);
			if (skillMap.get(skillset) < 1) {
				skillMap.remove(skillset);
			}
		}
	}

	/*
	 * Retrieves the number of users associated with the supplied skillset if the
	 * skillset is not in the collection, return -1
	 * 
	 * @param skillset the skillset to look up
	 */
	public static int retrieveSkillSetCount(String skillset) {
		skillset = skillset.toLowerCase();
		if (skillMap.containsKey(skillset)) {
			return skillMap.get(skillset);
		} else {
			return -1;
		}

	}

	/*
	 * Loops through each user and increments the global skillset usage count for
	 * each skillset associated with the user
	 * 
	 * @param users the list of users
	 */
	public static void initSkillsetUsage(List<LinkedInUser> users) {
		for (LinkedInUser user : users) {
			for (String skill : user.getSkillsets()) {
				incrementSkillsetCount(skill.toLowerCase());
			}
		}
		//System.out.println(skillMap);
	}

}
