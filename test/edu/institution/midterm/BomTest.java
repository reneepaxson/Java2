package edu.institution.midterm;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.junit.Assert;

public class BomTest {

	PartManagerImpl partMan = new PartManagerImpl();
	String path = System.getProperty("user.home") + File.separator + "bom.json";

	@Test
	public void testImport() {

		// test nonexistent file import
		// Assert.assertEquals(0, partMan.importPartStore("X"));
		// test json file import
		Assert.assertEquals(79, partMan.importPartStore(path));

	}

	@Test
	public void testCostPart() {
		partMan.importPartStore(path);

		// test fake part
		Assert.assertEquals(null, partMan.costPart("x"));
		// test part
		Assert.assertTrue((415.16f) == partMan.costPart("290B7266J4").getPrice());
		// test part recall
		partMan.costPart("290B7266J4");
	}

	@Test
	public void testRetrieve() {
		partMan.importPartStore(path);
		// test fake part
		Assert.assertEquals(null, partMan.retrievePart("x"));
		// test part
		Assert.assertTrue("290B7266J1".equals(partMan.retrievePart("290B7266J1").getPartNumber()));

	}

	@Test
	public void testAssemblies() {
		// test empty part store
		Assert.assertNull(partMan.getFinalAssemblies());

		partMan.importPartStore(path);
		List<Part> assemblies = partMan.getFinalAssemblies();

		// test sort by checking the first and last entries
		Assert.assertEquals("20-0001", assemblies.get(0).getPartNumber());
		Assert.assertEquals("290B7266J6", assemblies.get(3).getPartNumber());
	}

	@Test
	public void testPurchaseParts() {
		// test empty part store
		Assert.assertNull(partMan.getPurchasePartsByPrice());

		partMan.importPartStore(path);
		List<Part> purchase = partMan.getPurchasePartsByPrice();

		// test sort by checking the first and last entries
		Assert.assertEquals("290B381041", purchase.get(0).getPartNumber());
		Assert.assertEquals("40-0057", purchase.get(51).getPartNumber());
	}

}
