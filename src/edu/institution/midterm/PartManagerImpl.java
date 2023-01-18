package edu.institution.midterm;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;

public class PartManagerImpl implements PartManager {

	private Map<String, Part> partMap = new HashMap<>();

	@Override
	public int importPartStore(String filePath) {
		// imports the part store from an external file
		try {
			String jsonData = new String(Files.readAllBytes(Paths.get(filePath)));

			Gson gson = new Gson();
			Part[] parts = gson.fromJson(jsonData, Part[].class);

			for (Part part : parts) {
				// System.out.println(part.getBillOfMaterial());
				partMap.put(part.getPartNumber(), part);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// return number of imported parts
		return partMap.size();
	}

	@Override
	public Part costPart(String partNumber) {

		// computes the cost of manufacturing the part associated w
		// the supplied part number. along w computing the manufacturing
		if (!(partMap.containsKey(partNumber))) {
			System.out.println("No item with that number: " + partNumber);
			return null;
		}
		float extendedPrice = 0;
		// if part doesn't have a set price, load its bomList
		if (Float.valueOf(retrievePart(partNumber).getPrice()) == 0.0f) {
			List<BomEntry> bomList = retrievePart(partNumber).getBillOfMaterial();
			// System.out.println(partNumber+ ": "+bomList);
			for (BomEntry bom : bomList) {
				// if individual part in bom list doesn't have a set price, call the method
				// recursively
				if (Float.valueOf(retrievePart(bom.getPartNumber()).getPrice()) == 0.0f) {
					// extendedprice = extp + (bom.quantity * bom.price[costPart(bom.number)]

					extendedPrice += (bom.getQuantity()
							* retrievePart(costPart(bom.getPartNumber()).getPartNumber()).getPrice());
					extendedPrice = roundForMoney(extendedPrice);
					// set calculated price to that specific part
					retrievePart(bom.getPartNumber()).setPrice(extendedPrice);
					System.out.println(bom.getPartNumber() + ": $" + retrievePart(bom.getPartNumber()).getPrice());
					continue;
				}
				// if individual part in bom list does have a set price, add it to extended
				// price
				extendedPrice += (bom.getQuantity() * retrievePart(bom.getPartNumber()).getPrice());
				extendedPrice = roundForMoney(extendedPrice);
				retrievePart(partNumber).setPrice(extendedPrice);
				System.out.println(bom.getPartNumber() + ": $" + extendedPrice);

				continue;
			}
			retrievePart(partNumber).setPrice(extendedPrice);
			System.out.println(partNumber + ": $" + retrievePart(partNumber).getPrice());
		} else {
			extendedPrice = retrievePart(partNumber).getPrice();
			extendedPrice = roundForMoney(extendedPrice);

			System.out.println(partNumber + ": $" + retrievePart(partNumber).getPrice());
		}

		return retrievePart(partNumber);
	}

	private float roundForMoney(float x) {
		return new BigDecimal(x).setScale(2, RoundingMode.HALF_UP).floatValue();
	}

	@Override
	public Part retrievePart(String partNumber) {
		if (!(partMap.containsKey(partNumber))) {
			return null;
		}
		return partMap.get(partNumber);
	}

	@Override
	public List<Part> getFinalAssemblies() {
		if (partMap.isEmpty()) {
			return null;
		}
		List<Part> assemblyParts = new ArrayList<Part>();
		partMap.forEach((key, value) -> {
			if (value.getPartType().equalsIgnoreCase("ASSEMBLY")) {
				assemblyParts.add(value);
			}
		});

		return assemblyParts;
	}

	@Override
	public List<Part> getPurchasePartsByPrice() {
		if (partMap.isEmpty()) {
			return null;
		}
		List<Part> purchaseParts = new ArrayList<Part>();
		partMap.forEach((key, value) -> {
			if (value.getPartType().equalsIgnoreCase("PURCHASE")) {
				purchaseParts.add(value);
			}
		});
		Collections.sort(purchaseParts, new Comparator<Part>() {
			@Override
			public int compare(Part p1, Part p2) {
				return Float.compare(p2.getPrice(), p1.getPrice());
			}
		});

		return purchaseParts;
	}

}
