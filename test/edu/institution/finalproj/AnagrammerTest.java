package edu.institution.finalproj;

import org.junit.Test;
import org.junit.Assert;

public class AnagrammerTest {
	AnagramDataReader dataReader = new AnagramDataReaderImpl();

	Anagrammer anagrammer = new Anagrammer();
	AnagramEvaluator evaluator = new AnagramEvaluatorImpl();
	
	@Test
	public void testImport() {
		//test that the data reader imports the entirety of the file
		Assert.assertEquals(373295,dataReader.readData().size());
		
	}
	
	@Test
	public void testEvaluator() {
		Assert.assertEquals(6, evaluator.evaluate("dog", "nf").size());
	}
	
	
	@Test
	public void testAnagrammer() {
		String[] args = {"-a","dog","-nf"};
		anagrammer.main(args);
		
		String[] args1 = {"-a","dog","-words"};
		anagrammer.main(args1);
		
		String[] args2 = {"-h"};
		anagrammer.main(args2);
		
		String[] args3 = {"-nf"};
		anagrammer.main(args3);
		
		
		
	}
	
	
	

}
