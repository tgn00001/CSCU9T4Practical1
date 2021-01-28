package com.stir.cscu9t4practical1;

public class SprintEntry extends Entry {
	private int repetitions;
	private int recovery;
	
	public SprintEntry(String n, int d, int m, int y, int h, int min, int s, float dist, int repetitions, int recovery) {
		super(n, d, m, y, h, min, s, dist);
		this.setRepetitions(repetitions);
		this.setRecovery(recovery);
	}

	/**
	 * @return the repetitions
	 */
	public int getRepetitions() {
		return repetitions;
	}

	/**
	 * @param repetitions the repetitions to set
	 */
	public void setRepetitions(int repetitions) {
		this.repetitions = repetitions;
	}

	/**
	 * @return the recovery
	 */
	public int getRecovery() {
		return recovery;
	}

	/**
	 * @param recovery the recovery to set
	 */
	public void setRecovery(int recovery) {
		this.recovery = recovery;
	}

	@Override
	public String getEntry() {
		String result = getName() + " sprinted " + getRepetitions() + "x" + getDistance() + " m in " + getHour() + ":" + getMin() + ":" + getSec()
						+ " with " + getRecovery() + " minutes recovery on " + getDay() + "/" + getMonth() + "/" + getYear() + "\n";
		return result;
	}
}
