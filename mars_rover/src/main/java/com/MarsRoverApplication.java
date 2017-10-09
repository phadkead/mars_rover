package com;

import java.awt.Point;
import java.util.Scanner;

import com.rover.entities.Rover;
import com.rover.exception.CordinatesOutOfBoundriesException;
import com.rover.mover.MarsRoverMover;

public class MarsRoverApplication {

	public static void main(String[] args) {
		MarsRoverMover mover = new MarsRoverMover();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter(" ");
		Rover rover = Rover.getInstance();
		while (true) {
			System.out.println("Enter upper-right coordinates in format of: x y ");
			String[] cordinates = scanner.nextLine().split(" ");
			int upperX = Integer.parseInt(cordinates[0]);
			int upperY = Integer.parseInt(cordinates[1]);
			if (upperX <= 0 || upperY <= 0) {
				System.out.println("Please enter valid upper-right coordinates");
			} else {
				rover.setLimit(new Point(upperX, upperY));
				break;
			}
		}
		while (true) {
			try {
				System.out.println("Enter rover's current position in format of: X Y N");
				String[] currentPosition = scanner.nextLine().split(" ");
				mover.setPositions(rover, currentPosition);

				System.out.println("Enter directions to move rover: format LMLMLMLMM");
				String[] directions = scanner.nextLine().split("");
				mover.processChangeDirectionsAndMoveCommands(rover, directions);

				System.out.println("Command executed successfully");
				System.out.println("The new position of rover is:" + rover.getCoordinates().x + " "
						+ rover.getCoordinates().y + " " + rover.getDirection().getValue());
			} catch (CordinatesOutOfBoundriesException e) {
				System.out.println(e.getMessage() + " Please enter valid input");
			} catch (Exception e) {
				System.out.println("Unknown Error occurred:" + e.getMessage());
			}
		}
		// scanner.close();
	}

}
