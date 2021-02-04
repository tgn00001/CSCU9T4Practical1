// An implementation of a Training Record as an ArrayList
package com.stir.cscu9t4practical1;

import java.text.DecimalFormat;
import java.time.LocalDate;
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
			return "ERROR: please enter a name to search for.";
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
	 * 
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
	
	/**
	 * Finds all entries for a given name in the last week, and sums their distance values.
	 * 
	 * @param name the name to search for
	 * @return a String of all entries with this name in the last week, and the total distance of all entries
	 */
	public String getLastWeeksEntries(String name) {
		// LocalDate only stores dates, using a class that stores time as well (like Calendar) complicates the comparison
		LocalDate today = LocalDate.now();
		LocalDate lastWeek = today.minusWeeks(1);
		
		ListIterator<Entry> iter = tr.listIterator();
		if (name.contentEquals(""))
			return "ERROR: please enter a name to search for.";
		String  result = "";			
		double totalDistance = 0;		// the sum of all distances for the entries found
		
		Pattern p = Pattern.compile(".*" + name.toLowerCase() + ".*");
		while (iter.hasNext()) {
			Entry current = iter.next();
			Matcher m = p.matcher(current.getName().toLowerCase());
			
			// only count an entry if it matches the name AND its date is a week ago (+1 day) at the earliest and today at the latest
			if (m.find() && (current.getDate().compareTo(today) <= 0) && (current.getDate().compareTo(lastWeek) > 0)) {
				result += current.getEntry();
				
				// add distance to totalDistance, keeping in mind that SprintEntry stores m, not km
				if (current instanceof SprintEntry)	totalDistance += ((SprintEntry) current).getRepetitions() * (current.getDistance() / 1000);
				else 								totalDistance += current.getDistance();
			}
		}
		if (result.equals(""))	return "No entries found for \"" + name + "\"";
		else					{
			// format output so precision is only to the metre
			DecimalFormat kmFormat = new DecimalFormat("0.000");
			String km = kmFormat.format(totalDistance);
			
			// convert distance to m, format m output as well
			totalDistance *= 1000;
			DecimalFormat mFormat = new DecimalFormat("0");
			String m = mFormat.format(totalDistance);
			
			// output both km and m
			result += "Weekly distance is " + km + " km, or " + m + " m.\n";
			
			return result;
		}
	}
	
	/**
	 * Checks whether or not the record is empty.
	 * 
	 * @return true if the record has no entries, false if it does
	 */
	public boolean isEmpty() {
		return tr.isEmpty();
	}

} // TrainingRecord