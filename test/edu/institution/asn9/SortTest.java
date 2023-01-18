package edu.institution.asn9;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

public class SortTest {
	SortAlgorithmMetrics sortAlgorithmMetrics = new SortAlgorithmMetrics();
	String path = System.getProperty("user.home") + File.separator + "asn9-numbers2.txt";
	@Test
	public void testImport() {
		Assert.assertNull(sortAlgorithmMetrics.retrieveMetrics("x"));
		
		Assert.assertNotNull(sortAlgorithmMetrics.retrieveMetrics(path));
		
		Assert.assertEquals(5, sortAlgorithmMetrics.retrieveMetrics(path).size());
		Assert.assertTrue(sortAlgorithmMetrics.retrieveMetrics(path).get(0).getExecutionTime()< sortAlgorithmMetrics.retrieveMetrics(path).get(4).getExecutionTime());
		
	}

}
