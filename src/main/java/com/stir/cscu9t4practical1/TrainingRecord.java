// An implementation of a Training Record as an ArrayList
package com.stir.cscu9t4practical1;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrainingRecord {
	private List<Entry> tr;

	public TrainingRecord() {
		tr = new ArrayList<Entry>();
	} // constructor

	/**
	 * Adds an entry to the record.
	 * 
	 * @param e the entry to add
	 * @throws DuplicateElementException if there is an entry with this name on this date already.
	 */
	public void addEntry(Entry e) throws DuplicateElementException{
		if (doesEntryExist(e.getName(), e.getDay(), e.getMonth(), e.getYear()))	throw new DuplicateElementException();
		else 				tr.add(e);
	} // addClass

	/**
	 * Look up the entry of a given date.
	 * 
	 * @param d day
	 * @param m month
	 * @param y year
	 * @return the latest entry on the given date
	 */
	public String lookupEntry(int d, int m, int y) {
		ListIterator<Entry> iter = tr.listIterator();
		String result = "No entries found";
		while (iter.hasNext()) {
			Entry current = iter.next();
			if (current.getDay() == d && current.getMonth() == m && current.getYear() == y)
				result = current.getEntry();
		}
		return result;
	} // lookupEntry

	/**
	 * Look up all entries on a given date
	 * 
	 * @param d day
	 * @param m month
	 * @param y year
	 * @return a String of all entries on a given date
	 */
	public String lookupEntries(int d, int m, int y) {
		ListIterator<Entry> iter = tr.listIterator();
		String result = "";
		while (iter.hasNext()) {
			Entry current = iter.next();
			if (current.getDay() == d && current.getMonth() == m && current.getYear() == y)
				result = result + current.getEntry();
		}
		if (result.equals("")) {
			result = "Sorry couldn't find anything for this date";
		}
		return result;
	}
	
	/**
	 * Checks if an entry exists for the athlete named "n" on date "d"/"m"/"y" in the training record.
	 * 
	 * @param n name of the athlete
	 * @param d day
	 * @param m month
	 * @param y year
	 * @return boolean true if found, false if not
	 */
	public boolean doesEntryExist(String n, int d, int m, int y) {
		ListIterator<Entry> iter = tr.listIterator();
		boolean found = false;
		while (iter.hasNext() && !found) {
			Entry current = iter.next();
			if (current.getDay() == d && current.getMonth() == m
					&& current.getYear() == y && current.getName().equals(n)) {
				found = true;
			}
		}
		return found;
	}
	
	/**
	 * Find and return all entries with a name containing the search string.
	 * 
	 * @param searchString the name of the athlete, or part of it
	 * @return the entries under this name
	 */
	public String findAllEntriesForName(String searchString) {
		ListIterator<Entry> iter = tr.listIterator();
		if (searchString.contentEquals(""))
			return "ERROR: Please enter a name to search for.";
		String  result = "";
		Pattern p = Pattern.compile(".*" + searchString.toLowerCase() + ".*");
		while (iter.hasNext()) {
			Entry current = iter.next();
			Matcher m = p.matcher(current.getName().toLowerCase());
			if (m.find())
				result += current.getEntry();
		}
		if (result.equals(""))	return "No entries found for \"" + searchString + "\"";
		else					return result;
	}
 
	/**
	 * Counts the number of entries in the record.
	 * 
	 * @return the number of entries
	 */
	public int getNumberOfEntries() {
		return tr.size();
	}

	/**
	 * Clears all entries from the record.
	 */
	public void clearAllEntries() {
		tr.clear();
	}

	/**
	 * Removes the entry for the given name on the given date.
	 * @param n name of the athlete
	 * @param d day
	 * @param m month
	 * @param y year
	 * @return Diagnostic message.
	 */
	public String removeEntry(String n, int d, int m, int y) {
			ListIterator<Entry> iter = tr.listIterator();
			boolean removed = false;
			String toBeRemoved = n + " on " + d + "/" + m + "/" + y;
			while (iter.hasNext()) {
				Entry current = iter.next();
				if (current.getName().equals(n) && current.getDay() == d && current.getMonth() == m && current.getYear() == y) {
					iter.remove();
					removed = true;
				}
			}
			if (!removed) {
				return "No entry found for " + toBeRemoved;
			}
			else return "Removed entry for " + toBeRemoved;
			
	}

} // TrainingRecord