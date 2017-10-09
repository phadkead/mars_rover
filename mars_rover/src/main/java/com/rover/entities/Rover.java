package com.rover.entities;

import static com.rover.entities.ControlCommands.*;
import static com.rover.entities.Direction.*;

import java.awt.Point;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * Represents Plateau's position
 * 
 * @author aditiphadke
 *
 */
public class Rover {

	private static final Rover instance = new Rover();
	
	private Rover(){
		
	}
	
	public static Rover getInstance(){
		return instance;
	}
	
	/** current x,y cordinates */
	private Point coordinates;

	/** currently pointing to direction **/
	private Direction direction;

	private Point limit;

//	public Rover(Point coordinates, Direction direction) {
//		this.coordinates = coordinates;
//		this.direction = direction;
//	}

	public Point getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Point coordinates) {
		this.coordinates = coordinates;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public static Table<Direction, ControlCommands, Direction> directionTransition = HashBasedTable.create();

	static {
		directionTransition.put(N, L, W);
		directionTransition.put(N, R, E);

		directionTransition.put(S, L, E);
		directionTransition.put(S, R, W);

		directionTransition.put(E, L, N);
		directionTransition.put(E, R, S);

		directionTransition.put(W, L, S);
		directionTransition.put(W, R, N);

	}

	public void setLimit(Point limit) {
		this.limit = limit;
	}

	public Point getLimit() {
		return limit;
	}

}
