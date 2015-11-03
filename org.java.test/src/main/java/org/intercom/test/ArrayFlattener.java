package org.intercom.test;

import java.util.ArrayList;

/**
 * 
 * @author joedian
 *
 */
public class ArrayFlattener {

	/**
	 * Method flattens array of objects - expects all integers
	 * @param arrayToFlatten
	 * @param arrayFlattened (reduces errors when recursion call is made)
	 * @return
	 */
	public static Object[] flattenArray(Object[] arrayToFlatten, ArrayList<Integer> arrayFlattened) {
		//base case
		if (arrayToFlatten == null)
			return null;
		try {

			for (int a = 0; a < arrayToFlatten.length; a++) {
				//if array has one object of int/Integer then return it
				if (arrayToFlatten[a].getClass().isPrimitive() || arrayToFlatten[a].getClass().equals(Integer.class)) {
					arrayFlattened.add(Integer.parseInt(arrayToFlatten[a].toString()));
				//if the sub array is a simple in[] then just flatten it and move on
				} else if (arrayToFlatten[a].getClass().equals(int[].class)) {
					arrayFlattened.addAll(flattenSimpleArray(arrayToFlatten[a]));
				//if subarray is complex probe it by recursion
				} else {
					flattenArray((Object[]) arrayToFlatten[a], arrayFlattened);
				}
			}
			//catch adhoc values and log error message, do not terminate
		} catch (Exception e) {
			System.err.println("Invalid Value Entered. Expected all values to be Integers.");
			return null;
		}
		return arrayFlattened.toArray();
	}

	//simply flatten array
	private static ArrayList<Integer> flattenSimpleArray(Object arrayToFlatten) {
		ArrayList<Integer> arrayFlattened = new ArrayList<Integer>();

		for (int i = 0; i < ((int[]) arrayToFlatten).length; i++) {
			arrayFlattened.add(((int[]) arrayToFlatten)[i]);
		}
		return arrayFlattened;
	}

}
