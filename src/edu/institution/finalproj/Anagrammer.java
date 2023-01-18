package edu.institution.finalproj;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.*;

public class Anagrammer {

	public static void main(String[] args) {
		AnagramEvaluator evaluator = new AnagramEvaluatorImpl();

		Options options = new Options();
		options.addOption("a", "anagram", true, "Supplies the anagram to evaluate");
		options.addOption("nf", "no-filter", false, "Outputs all anagram values with no filter");
		options.addOption("words", "filter-words", false, "Outputs only  values that are known words");
		options.addOption("h", "help", false, "Displays the help for the program");

		CommandLineParser parser = new DefaultParser();

		try {
			CommandLine cmd = parser.parse(options, args);
			List<String> anagrams = new ArrayList<String>();

			if (cmd.hasOption("a") || cmd.hasOption("anagram")) {

				String anagram = cmd.getOptionValue("a").toUpperCase();

				if (cmd.hasOption("nf") || cmd.hasOption("no-filter")) {

					System.out.println("Anagrammer -nf " + anagram + "\n");
					anagrams = evaluator.evaluate(anagram, "nf");

					for (String s : anagrams) {
						System.out.println(s);
					}
					System.out.println("-- " + anagrams.size() + " value(s) found");

				} else {

					System.out.println("Anagrammer -words " + anagram + "\n");
					anagrams = evaluator.evaluate(anagram, "words");

					for (String s : anagrams) {
						System.out.println(s);
					}
					System.out.println("-- " + anagrams.size() + " value(s) found");
				}

			} else if (cmd.hasOption("nf") || cmd.hasOption("no-filter") || cmd.hasOption("words")
					|| cmd.hasOption("filter-words")) {

				System.out.println("Missing required option: -a");
			}

			if (cmd.hasOption("h")) {
				System.out.println("usage: anagrammer \nOptions: \n"
						+ "  -a, --anagram <word> \t\tsupplies the anagram to evaluate\n"
						+ "  -h, --help \t\t\tdisplays the help text for this program\n"
						+ "  -nf, --no-filter \t\toutput all anagram values with no filter\n"
						+ "  -words, --filter-words \toutput only values that are known words\n");
			}
		} catch (ParseException e) {
			System.err.println("Parsing failed. Reason: " + e.getMessage());
		}
		
		
	}
	

}
