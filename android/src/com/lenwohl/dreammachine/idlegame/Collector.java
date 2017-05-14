package com.lenwohl.dreammachine.idlegame;

import com.badlogic.gdx.math.Vector2;

public class Collector {
	
	private float pointsPerMinute;
	private float storedPoints;
	private float maximumStoredPoints;
	private long lastTimeUpdated;
	private Vector2 gpsPosition;
	
	public Collector(float pointsPerMinute, float maximumStoredPoints, Vector2 gpsPosition) {
		this.pointsPerMinute = pointsPerMinute;
		this.maximumStoredPoints = maximumStoredPoints;
		this.gpsPosition = gpsPosition;
		storedPoints = 0;
		lastTimeUpdated = System.currentTimeMillis();
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
	
	public Vector2 getGpsPosition() {
		return gpsPosition;
	}
	
	public void setGpsPosition(Vector2 gpsPosition) {
		this.gpsPosition = gpsPosition;
	}
	
}
