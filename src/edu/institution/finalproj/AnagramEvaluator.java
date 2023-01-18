package edu.institution.finalproj;

import java.util.List;

public interface AnagramEvaluator {
	
	/*
	 * @param anagram the anagram to evaluate
	 * @param option the command line option. values are "nf" or "words". if null,
	 * default to "words"
	 * @return the list of words derived from the supplied anagram
	 */

	List<String> evaluate(String anagram, String option);
}
