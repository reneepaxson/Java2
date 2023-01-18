package edu.institution.midterm;

import java.util.List;

public interface PartManager {

	int importPartStore(String filePath);
	// imports the part store from an external file
	// accepts the file path to the file which contains the parts to import
	// returns the number of parts imported

	Part costPart(String partNumber);
	// computes the cost to manufacture the part associated w the supplied part
	// number

	Part retrievePart(String partNumber);
	// retrieves the part associated with the supplied part number from the part
	// store
	// accepts a part number which identifies the part to retrieve
	// returns the Part instance associated with the supplied part number or null if
	// not found

	List<Part> getFinalAssemblies();
	// returns all final assembly parts sorted alphabetically by their part number
	// final assemblies have a part type "ASSEMBLY"

	List<Part> getPurchasePartsByPrice();
	// returns all purchased parts sorted by their price, highest price to lowest
	// purchase parts have a part type "PURCHASE"
}
