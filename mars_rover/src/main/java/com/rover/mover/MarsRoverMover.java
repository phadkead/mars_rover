package com.rover.mover;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rover.entities.ControlCommands;
import com.rover.entities.Direction;
import com.rover.entities.Rover;
import com.rover.exception.CordinatesOutOfBoundriesException;

/**
 * Helper to move rover
 * @author aditiphadke
 *
 */
public class MarsRoverMover {
	
	private MarsRoverMover(){
	}
	
	private static final MarsRoverMover INSTANCE = new MarsRoverMover();
	
	public static MarsRoverMover getInstance(){
		return INSTANCE;
	}
	private static final Logger LOGGER = LoggerFactory.getLogger(MarsRoverMover.class);

	public void setPositions(Rover rover, String[] positions) throws CordinatesOutOfBoundriesException {
		int x = Integer.parseInt(positions[0]);
		int y = Integer.parseInt(positions[1]);
		if (!isWithinBoundries(new Point(x, y), rover.getLimit().x, rover.getLimit().y)) {
			throw new CordinatesOutOfBoundriesException("Rover is out of boundries!!");
		}
		String direction = positions[2];
		rover.setCoordinates(new Point(x, y));
		rover.setDirection(Direction.valueOf(direction));
	}

	// TODO inject this plateau?
	public void processChangeDirectionsAndMoveCommands(Rover rover, String[] directions)
			throws CordinatesOutOfBoundriesException {
		List<ControlCommands> list = new ArrayList<>();
		for (int i = 0; i < directions.length; i++) {
			list.add(ControlCommands.valueOf(directions[i]));
		}
		for (ControlCommands controlCommands : list) {
			if (controlCommands == ControlCommands.M) {
				moveForward(rover);
			} else {
				changeDirection(rover, controlCommands);
			}
		}
	}

	public void moveForward(Rover rover) throws CordinatesOutOfBoundriesException {
		Direction addPoint = Direction.valueOf(rover.getDirection().getValue());
		rover.getCoordinates().translate(addPoint.getX(), addPoint.getY());
		if (!isWithinBoundries(rover.getCoordinates(), rover.getLimit().x, rover.getLimit().y)) {
			throw new CordinatesOutOfBoundriesException("Rover is trying to go out of boundries!!");
		}
	}

	public boolean isWithinBoundries(Point point, int maxXCordinate, int maxYCordinate) {
		if (checkUpperBoundaries(point, maxXCordinate, maxYCordinate) && checkLowerBounds(point)) {
			return true;
		}
		return false;
	}

	private boolean checkLowerBounds(Point point) {
		return point.x >= 0 && point.y >= 0;
	}

	private boolean checkUpperBoundaries(Point point, int maxXCordinate, int maxYCordinate) {
		return point.x <= maxXCordinate && point.y <= maxYCordinate;
	}

	public void changeDirection(Rover rover, ControlCommands controlCommand) {
		Direction newDirection = Rover.directionTransition.get(rover.getDirection(), controlCommand);
		LOGGER.info("Moving plateau to direction: {}", newDirection.getValue());
		rover.setDirection(newDirection);
	}

}
