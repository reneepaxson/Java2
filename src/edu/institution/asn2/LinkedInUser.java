/*
 Copyright (C) 2020. Doug Estep -- All Rights Reserved.
 Copyright Registration Number: TXU002159309.

 This file is part of the Tag My Code application.

 This application is protected under copyright laws and cannot be used, distributed, or copied without prior written
 consent from Doug Estep.  Unauthorized distribution or use is strictly prohibited and punishable by domestic and
 international law.

 Proprietary and confidential.
 */
package edu.institution.asn2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents a LinkedIn user account.
 * 
 * @author dougestep
 */
public class LinkedInUser extends UserAccount implements Comparable<LinkedInUser>, Serializable {
	private static final long serialVersionUID = -5823250237299862919L;
	private String type;
	private List<LinkedInUser> connections = new ArrayList<>();
	private Set<String> skillSets = new TreeSet<>();

	/**
	 * Creates an instance of this class.
	 * 
	 * @param username sets the user name.
	 * @param password sets the password.
	 */
	public LinkedInUser(String username, String password) {
		super(username, password);
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Returns the type of this LinkedIn user.
	 * 
	 * @return the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Adds the supplied connection to the list of connections for this user.
	 *
	 * @param user the connection to add.
	 * @throws LinkedInException thrown if the supplied user is already a connected
	 *                           to this user.
	 */
	public void addConnection(LinkedInUser user) throws LinkedInException {
		int idx = connections.indexOf(user);
		if (idx < 0) {
			connections.add(user);
		} else {
			throw new LinkedInException("You are already connected with this user");
		}
	}

	/**
	 * Removes the supplied user from the list of connections for this user.
	 * 
	 * @param user the user to remove.
	 * @throws LinkedInException thrown if the supplied user is not a connected of
	 *                           this user.
	 */
	public void removeConnection(LinkedInUser user) throws LinkedInException {
		int idx = connections.indexOf(user);
		if (idx < 0) {
			throw new LinkedInException("You are NOT connected with this user");
		} else {
			connections.remove(idx);
		}
	}

	/**
	 * Returns a list of users that are connected with this user.
	 * 
	 * @return the list or empty list if this user has no connections.
	 */
	public List<LinkedInUser> getConnections() {
		return new ArrayList<>(connections);
	}

	/**
	 * Adds the supplied skillset to this user.
	 * 
	 * @param skillset the skillset.
	 */
	public void addSkillset(final String skillset) {
		this.skillSets.add(skillset);
	}

	/**
	 * Removes the supplied skillset from this user.
	 * 
	 * @param skillset the skillset.
	 */
	public void removeSkillset(final String skillset) {
		this.skillSets.remove(skillset);
	}

	/**
	 * Returns a list of skillsets associated with this user.
	 * 
	 * @return the skillsets.
	 */
	public Set<String> getSkillsets() {
		return this.skillSets;
	}

	@Override
	public int compareTo(LinkedInUser user) {
		int rc = 0;
		if (getUsername() == null) {
			rc = -1;
		} else if (user == null || user.getUsername() == null) {
			rc = 1;
		} else {
			String myUserName = getUsername().toLowerCase();
			String otherUserName = user.getUsername().toLowerCase();
			rc = myUserName.compareTo(otherUserName);
		}
		return rc;
	}

	/**
	 * Initiates this class after the deserialization process completes.
	 * 
	 * @param in the object input stream.
	 * @throws IOException            thrown if the read fails.
	 * @throws ClassNotFoundException thrown if any dependent class does not exist
	 *                                in the class path.
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		this.connections = new ArrayList<>();
		this.skillSets = new TreeSet<>();

		in.defaultReadObject();
	}

	/**
	 * Serializes the properties of this class.
	 * 
	 * @param out the object output stream.
	 * @throws IOException            thrown if the write fails.
	 * @throws ClassNotFoundException thrown if any dependent class does not exist
	 *                                in the class path.
	 */
	private void writeObject(ObjectOutputStream out) throws IOException, ClassNotFoundException {
		out.defaultWriteObject();
	}
}
