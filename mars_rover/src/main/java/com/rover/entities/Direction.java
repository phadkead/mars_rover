package com.rover.entities;

/**
 * Direction - to - point coordinates relation
 * 
 * @author aditiphadke
 *
 */
public enum Direction {
	N("N", 0, 1), E("E", 1, 0), S("S", 0, -1), W("W", -1, 0);

	private String value;
	private int x;
	private int y;

	Direction(String value, int x, int y) {
		this.value = value;
		this.x = x;
		this.y=y;
	}

	public String getValue() {
		return value;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
