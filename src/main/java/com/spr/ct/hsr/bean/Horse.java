package com.spr.ct.hsr.bean;

public class Horse {

	private int horseId;
	private String horseName;
	private int odds;
	private boolean won;

	/**
	 * @return the horseId
	 */
	public int getHorseId() {
		return horseId;
	}

	/**
	 * @param horseId
	 *            the horseId to set
	 */
	public void setHorseId(int horseId) {
		this.horseId = horseId;
	}

	/**
	 * @return the horseName
	 */
	public String getHorseName() {
		return horseName;
	}

	/**
	 * @param horseName
	 *            the horseName to set
	 */
	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	/**
	 * @return the odds
	 */
	public int getOdds() {
		return odds;
	}

	/**
	 * @param odds
	 *            the odds to set
	 */
	public void setOdds(int odds) {
		this.odds = odds;
	}

	/**
	 * @return the won
	 */
	public boolean hasWon() {
		return won;
	}

	/**
	 * @param won
	 *            the won to set
	 */
	public void setWon(boolean won) {
		this.won = won;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + horseId;
		result = prime * result + ((horseName == null) ? 0 : horseName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Horse other = (Horse) obj;
		if (horseId != other.horseId)
			return false;
		if (horseName == null) {
			if (other.horseName != null)
				return false;
		} else if (!horseName.equals(other.horseName))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Horse [horseId=" + horseId + ", horseName=" + horseName + ", odds=" + odds + ", won=" + won + "]";
	}

}
