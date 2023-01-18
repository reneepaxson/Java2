package edu.institution.actions.asn3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;
import edu.institution.UserRepository;
import edu.institution.ApplicationHelper;
import edu.institution.LinkedInCLI;

public class SerializedUserRepository implements UserRepository {

	private String fileName;
	private String filePath;
	private List<LinkedInUser> users = new ArrayList<LinkedInUser>();

	@SuppressWarnings("unchecked")
	@Override
	public void init(String filePath, String fileName) {
		this.filePath = filePath;
		this.fileName = fileName;

		File file = new File(filePath + fileName);
		// deserialize the list of linkedin users and set the list
		// as a property of the class
		if (file.exists()) {
			try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis);) {

				this.users = (List<LinkedInUser>) ois.readObject();

			} catch (Exception e) {
				e.printStackTrace();
			}
			ApplicationHelper.initSkillsetUsage(users);
		}
	}

	@Override
	public void add(LinkedInUser user) throws LinkedInException {

		// if the user name and user type are not supplied, throw a new
		// linkedInException
		if (user.getUsername().isEmpty() || user.getType().isEmpty()) {
			throw new LinkedInException("The user name and type are required to add a new user");
		} else {
			// valid user types are either "P" for premier or "S" for standard
			if (user.getType().equalsIgnoreCase("P") || user.getType().equalsIgnoreCase("S")) {
				if (!(this.users.contains(user))) {
					// if the supplied user is ready to be added add it to the list
					this.users.add(user);
					// and call the saveAll() method to save the data
					saveAll();
				} else if (this.users.contains(user)) {
					// if the supplied user already exists, throw new LinkedInException("A user
					// already exists with that user name.");
					throw new LinkedInException("A user already exists with that user name.");
				}
			} else {
				// if the user type is invalid throw new LinkedInException
				throw new LinkedInException("Invalid user type. Valid types are P or S.");
			}
		}
	}

	@Override
	public void saveAll() {

		File file = new File(filePath + fileName);

		// delete existing file first then recreate it
		if (file.exists()) {
			file.delete();
		}
		new File(filePath).mkdirs();

		try (FileOutputStream fout = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fout)) {
			oos.writeObject(this.users);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(LinkedInUser user) {
		// this method removes the supplied LinkedIn user from the list of LinkedIn
		// users
		// that was established in the init method.
		this.users.remove(user);
		// this method should call the saveAll() method to persist the deleted data
		saveAll();
	}

	@Override
	public LinkedInUser retrieve(String username) {
		// this method returns the LinkedIn user associated with the supplied user name
		for (int i = 0; i < users.size(); i++) {
			LinkedInUser user = users.get(i);

			if (user.getUsername().equals(username)) {

				return user;
			}

		}

		// or null if there is no user associated with the supplied user name
		return null;
	}

	@Override
	public List<LinkedInUser> retrieveAll() {

		if (users == null) {
			// or an empty List if no users exist.
			return new ArrayList<>();
		} else {
			// return the List of LinkedInUser's
			return users;
		}

	}

}
