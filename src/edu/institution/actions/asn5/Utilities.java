package edu.institution.actions.asn5;

import java.util.ArrayList;
import java.util.List;

public class Utilities {

	public <T> List<T> removeDuplicates(List<T> items) {
		// this method accepts a list of items and removes the duplicates
		// from the list. after this method completes, the supplied list
		// should not contain any duplicate items
		
		if (items == null) {
			return null;
		}
		ArrayList<T> newList = new ArrayList<T>();
		for (T element : items) {
			if (!(newList.contains(element))) {
				newList.add(element);
			}
		}

		items = newList;
		return items;
	}

	public <E> E linearSearch(List<E> list, E key) {
		// implement a generic method to do a linear search. your linear search
		// method should accept a list containing a generic type and a key record
		// to search for in the generic list
		// your linear search should return the record associated with the supplied
		// key or null if the key does not exist in the supplied list
		if (list == null || key == null) {
			return null;
		}
		
		for (int i = 0; i < list.size(); i++) {
			if (key.equals(list.get(i))) {
				return list.get(i);
			}
		}
		return null;
	}

}
