package com.rover.mover;

import static com.rover.entities.ControlCommands.L;
import static com.rover.entities.ControlCommands.R;
import static com.rover.entities.Direction.E;
import static com.rover.entities.Direction.N;
import static com.rover.entities.Direction.S;
import static com.rover.entities.Direction.W;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Test;

import com.rover.entities.Rover;
import com.rover.exception.CordinatesOutOfBoundriesException;

public class MarsRoverMoverTest {

	private MarsRoverMover marsrover = new MarsRoverMover();

//	@Before
//	public void setup() {
//		rover = Rover rover = Rover.getInstance();;
//	}

	@Test
	public void shouldNoGoOutOfBoundries() {

		assertFalse(marsrover.isWithinBoundries(new Point(5, 5), 3, 4));

		assertFalse(marsrover.isWithinBoundries(new Point(-1, 2), 3, 4));

		assertTrue(marsrover.isWithinBoundries(new Point(1, 1), 3, 4));

		assertTrue(marsrover.isWithinBoundries(new Point(0, 0), 3, 4));

	}

	@Test
	public void shouldProcessDirections() throws CordinatesOutOfBoundriesException {
		// given
		String[] directions = new String[] { "L", "M" };
		Rover rover = Rover.getInstance();
		rover.setCoordinates(new Point(1, 2));
		rover.setDirection( N);
		rover.setLimit(new Point(10, 10));
		// when
		marsrover.processChangeDirectionsAndMoveCommands(rover, directions);
		// then
		assertEquals("W", rover.getDirection().getValue());
		assertEquals(0, rover.getCoordinates().x);
		assertEquals(2, rover.getCoordinates().y);

		// given
		String[] directions1 = new String[] { "R", "M", "M", "R" };
		Rover rover1 = Rover.getInstance();
		rover1.setCoordinates(new Point(1, 3));
		rover1.setDirection( N);
		
		rover1.setLimit(new Point(10, 10));
		// when
		marsrover.processChangeDirectionsAndMoveCommands(rover1, directions1);
		// then
		assertEquals("S", rover1.getDirection().getValue());
		assertEquals(3, rover1.getCoordinates().x);
		assertEquals(3, rover1.getCoordinates().y);
	}

	@Test
	public void shouldPointToCorrectDirection() {

		// given
		Rover rover = Rover.getInstance();
		rover.setCoordinates(new Point(1, 2));
		rover.setDirection( N);
		// when
		marsrover.changeDirection(rover, L);
		// then
		assertEquals("W", rover.getDirection().getValue());

		// given
		rover.setDirection(N);
		// when
		marsrover.changeDirection(rover, R);
		// then
		assertEquals("E", rover.getDirection().getValue());

		// given
		rover.setDirection(S);
		// when
		marsrover.changeDirection(rover, R);
		// then
		assertEquals("W", rover.getDirection().getValue());

		// given
		rover.setDirection(S);
		// when
		marsrover.changeDirection(rover, L);
		// then
		assertEquals("E", rover.getDirection().getValue());

		// given
		rover.setDirection(W);
		// when
		marsrover.changeDirection(rover, R);
		// then
		assertEquals("N", rover.getDirection().getValue());

		// given
		rover.setDirection(W);
		// when
		marsrover.changeDirection(rover, L);
		// then
		assertEquals("S", rover.getDirection().getValue());

		// given
		rover.setDirection(E);
		// when
		marsrover.changeDirection(rover, R);
		// then
		assertEquals("S", rover.getDirection().getValue());

		// given
		rover.setDirection(E);
		// when
		marsrover.changeDirection(rover, L);
		// then
		assertEquals("N", rover.getDirection().getValue());

	}

	@Test
	public void shouldProcessMoveCommandInAllDirections() throws CordinatesOutOfBoundriesException {
		// given
		Rover rover = Rover.getInstance();
		rover.setCoordinates(new Point(1, 2));
		rover.setLimit(new Point(10, 10));
		rover.setDirection(N);
		// when
		marsrover.moveForward(rover);
		// then
		assertEquals(new Point(1, 3), rover.getCoordinates());

		// given
		rover.setCoordinates(new Point(1, 3));
		rover.setDirection(E);
		// when
		marsrover.moveForward(rover);
		// then
		assertEquals(new Point(2, 3), rover.getCoordinates());

		// given
		rover.setCoordinates(new Point(1, 3));
		rover.setDirection(S);
		// when
		marsrover.moveForward(rover);
		// then
		assertEquals(new Point(1, 2), rover.getCoordinates());

		// given
		rover.setCoordinates(new Point(1, 3));
		rover.setDirection(W);
		// when
		marsrover.moveForward(rover);
		// then
		assertEquals(new Point(0, 3), rover.getCoordinates());

	}

	@Test(expected = CordinatesOutOfBoundriesException.class)
	public void shouldNotGoOutOfBoundries() throws CordinatesOutOfBoundriesException {
		// given
		Rover rover = Rover.getInstance();
		rover.setCoordinates(new Point(10, 10));
		rover.setLimit(new Point(10, 10));
		rover.setDirection(N);
		// when
		marsrover.moveForward(rover);
		// then

	}
}
