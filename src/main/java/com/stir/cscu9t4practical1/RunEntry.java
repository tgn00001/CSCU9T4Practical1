// This class holds information about a single training session
package com.stir.cscu9t4practical1;

public class RunEntry extends Entry{
	public RunEntry(String n, int d, int m, int y, int h, int min, int s, float dist) {
		super(n, d, m, y, h, min, s, dist);
	} // constructor
	
	@Override
	public String getEntry() {
		String result = getName() + " ran " + getDistance() + " km in " + getHour() + ":" + getMin() + ":" + getSec()
				+ " on " + getDay() + "/" + getMonth() + "/" + getYear() + "\n";
		return result;
	} // getEntry
	
} // Entry