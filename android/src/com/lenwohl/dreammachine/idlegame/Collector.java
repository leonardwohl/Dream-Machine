package com.lenwohl.dreammachine.idlegame;

public class Collector {
	
	private float pointsPerMinute;
	private float storedPoints;
	private float maximumStoredPoints;
	private long lastTimeUpdated;
	
	public Collector(float pointsPerMinute, float maximumStoredPoints) {
		this.pointsPerMinute = pointsPerMinute;
		this.maximumStoredPoints = maximumStoredPoints;
		this.storedPoints = 0;
		this.lastTimeUpdated = System.currentTimeMillis();
	}
	
	public void updatePoints() {
		float minutesSinceLastUpdated = (System.currentTimeMillis() - lastTimeUpdated) / 1000f / 60f;
		lastTimeUpdated = System.currentTimeMillis();
		storedPoints = Math.min(storedPoints + (pointsPerMinute * minutesSinceLastUpdated), maximumStoredPoints);
	}
	
	public float getStoredPoints() {
		updatePoints();
		return storedPoints;
	}
	
	public void emptyStoredPoints() {
		storedPoints = 0;
	}
	
	public float getPointsPerMinute() {
		return pointsPerMinute;
	}
	
	public void setPointsPerMinute(float pointsPerMinute) {
		this.pointsPerMinute = pointsPerMinute;
	}
	
	public float getMaximumStoredPoints() {
		return maximumStoredPoints;
	}
	
	public void setMaximumStoredPoints(float maximumStoredPoints) {
		this.maximumStoredPoints = maximumStoredPoints;
	}
	
}
