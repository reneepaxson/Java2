// made with assistance from geeksforgeeks user @dekay and @nikhatkhan11: 
// https://www.geeksforgeeks.org/print-all-permutations-of-a-string-in-java/
package edu.institution.finalproj;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnagramEvaluatorImpl implements AnagramEvaluator {

	AnagramDataReader dataReader = new AnagramDataReaderImpl();
	Set<String> wordSet = dataReader.readData();

	Set<String> anagramSet = new HashSet<String>();

	@Override
	public List<String> evaluate(String anagram, String option) {
		List<String> anagramList = new ArrayList<String>();

		permutation(anagram, "");

		if (option.equalsIgnoreCase("nf")) {
			for (String s : anagramSet) {
				anagramList.add(s);
			}
			return anagramList;

		} else if (option.equalsIgnoreCase("words")) {

			List<String> wordAnagrams = new ArrayList<String>();
			for (String s : anagramSet) {
				if (wordSet.contains(s)) {
					wordAnagrams.add(s);
				}
			}

			return wordAnagrams;

		} else {
			return null;
		}

	}

	// made with assistance from geeksforgeeks user @dekay and @nikhatkhan11, linked above
	public void permutation(String str1, String str2) {
		// recursive functions that slices and passes each character of the anagram into
		// different "bins" (str1, str2, str3) until all characters fall into bin 2, the
		// base case
		// i used a hash set here to ensure there are no duplicates
		if (str1.length() == 0) {
			anagramSet.add(str2);
		}

		for (int i = 0; i < str1.length(); i++) {
			// saves the character at i to ch
			char ch = str1.charAt(i);
			// slices character at i out of str1 and saves it to str3
			String str3 = str1.substring(0, i) + str1.substring(i + 1);
			// passes str3 to str1 and adds the sliced out ch to the end of str2
			permutation(str3, str2 + ch);
		}
	}

}
