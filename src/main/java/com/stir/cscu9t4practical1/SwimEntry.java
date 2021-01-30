package com.stir.cscu9t4practical1;

public class SwimEntry extends Entry {
	private String where;
	
	public SwimEntry(String n, int d, int m, int y, int h, int min, int s, float dist, String where) {
		super(n, d, m, y, h, min, s, dist);
		if (where.equals("outdoors")) this.setWhere("outdoors");
		else if (where.equals("pool")) this.setWhere("in a pool");
		else this.where = "where";
	}

	/**
	 * @return the where
	 */
	public String getWhere() {
		return where;
	}

	/**
	 * @param where the where to set
	 */
	public void setWhere(String where) {
		this.where = where;
	}

	@Override
	public String getEntry() {
		String result = getName() + " swam " + getDistance() + " km " + getWhere() + " in " + getHour() + ":" + getMin() + ":" + getSec()
						+ " on " + getDay() + "/" + getMonth() + "/" + getYear() + "\n";
		return result;
	}
}
