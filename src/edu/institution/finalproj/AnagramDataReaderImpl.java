package edu.institution.finalproj;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class AnagramDataReaderImpl implements AnagramDataReader {

	private static  String PATH = System.getProperty("user.home") + File.separator + "anagrammer_files" + File.separator;
	private static  String FILE_NAME = "anagram_data.txt";

	
	/*
	 * returns a set containing all words read from the anagram data text file
	 * 
	 * @return the set or empty set if no words are found
	 */
	@Override
	public Set<String> readData() {
		
		Set<String> wordSet = new LinkedHashSet<>();
		
		try (Scanner scanner = new Scanner(new FileInputStream(PATH + FILE_NAME))){
			while (scanner.hasNext()) {
				String word = scanner.nextLine();
				wordSet.add(word);
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		
		return wordSet;
	}

}
