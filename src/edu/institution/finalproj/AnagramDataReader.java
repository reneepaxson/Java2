package edu.institution.finalproj;

import java.util.Set;

public interface AnagramDataReader {
	
	/*
	 * returns a set containing all words read from the anagram data text file
	 * 
	 * @return the set or empty set if no words are found
	 */
	
	Set<String> readData();
	
}
