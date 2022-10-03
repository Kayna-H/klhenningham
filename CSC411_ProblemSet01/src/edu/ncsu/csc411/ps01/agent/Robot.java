package edu.ncsu.csc411.ps01.agent;

import java.util.Stack;
import java.util.Map;

import edu.ncsu.csc411.ps01.environment.Action;
import edu.ncsu.csc411.ps01.environment.Environment;
import edu.ncsu.csc411.ps01.environment.Tile;
import edu.ncsu.csc411.ps01.environment.TileStatus;

/**
	Represents a simple-reflex agent cleaning a particular room.	
	The robot only has one sensor - the ability to retrieve the 
	the status of all its neighboring tiles, including itself.
 */

public class Robot {
	private Environment env;
	Stack<Action> tileList = new Stack<>();


	/** Initializes a Robot on a specific tile in the environment. */
	public Robot (Environment env) { this.env = env; }

	/**
	    Problem Set 01 - Modify the getAction method below in order to
	    simulate the passage of a single time-step. At each time-step, the Robot 
	    decides whether to clean the current tile or move tiles.

	    Your task for this Problem Set is to modify the method below such that
	    the Robot agent is able to clean at least 70% of the available tiles on
	    a given Environment. 5 out of the 10 graded test cases, with explanations
	    on how to create new Environments, are available under the test package.

	    This method should return a single Action from the Action class.
	    	- Action.CLEAN
	    	- Action.DO_NOTHING
	    	- Action.MOVE_UP
	    	- Action.MOVE_DOWN
	    	- Action.MOVE_LEFT
	    	- Action.MOVE_RIGHT
	 */

	/**
  		Replace this docstring comment with an explanation of your implementation.
	 */
	public Action getAction () {
		// This example code demonstrates the available methods and actions,
		// such as retrieving its senses (neighbor tiles), getting the status of
		// those tiles, and returning the different available Actions

		// env.getNeighboringTiles(this) will return a Map with key-value pairs
		// for each neighbor, using a String key for a Tile value
		Map<String, Tile> positions = env.getNeighborTiles(this);
		Tile self = positions.get("self");
		Tile above = positions.get("above"); // Either a Tile or null
		Tile below = positions.get("below"); // Either a Tile or null
		Tile left = positions.get("left");   // Either a Tile or null
		Tile right = positions.get("right"); // Either a Tile or null

		// getStatus will return TileStatus of the agent's current Position,
		// which can be either DIRTY, CLEAN, or IMPASSABLE
		if (self.getStatus() == TileStatus.DIRTY) {
			//System.out.println("self: " + self + " " + (self.getStatus() == TileStatus.DIRTY));
			return Action.CLEAN;
		} 

		//why is tileList empty?
		if (self.getStatus() == TileStatus.CLEAN) {

			if (above != null && above.getStatus() == TileStatus.DIRTY) {
				//add to queue with position
				//save action
				tileList.push(Action.MOVE_UP);
				//System.out.println(tileList.size());

				//System.out.println(tileList.push(Action.MOVE_UP));
				return Action.MOVE_UP;

			}
			else if (below != null && below.getStatus() == TileStatus.DIRTY) {
				//System.out.println("below: " + below + " " + (below.getStatus() == TileStatus.DIRTY));

				tileList.push(Action.MOVE_DOWN);
				//System.out.println(tileList.push(Action.MOVE_DOWN));
				//System.out.println(tileList.size());

				return Action.MOVE_DOWN;
			}
			else if (right != null && right.getStatus() == TileStatus.DIRTY) {

				tileList.push(Action.MOVE_RIGHT);
				//System.out.println(tileList.size());

				//System.out.println("right: " + right + " " + (right.getStatus() == TileStatus.DIRTY));
				//System.out.println(tileList.push(Action.MOVE_RIGHT));

				return Action.MOVE_RIGHT;
			}
			else if (left != null && left.getStatus() == TileStatus.DIRTY) {
				tileList.push(Action.MOVE_LEFT);
				//System.out.println(tileList.size());

				//System.out.println(tileList.push(Action.MOVE_LEFT));
				//System.out.println("left: " + left + " " + (left.getStatus() == TileStatus.DIRTY));
				return Action.MOVE_LEFT;
			}

			else {
				//System.out.println("entered else statement");
				//System.out.println(tileList.size());


				//while surrounding tiles are clean
				//while stack has stuff 
				while (!tileList.isEmpty()) {
					//System.out.println("entered while loop statement");

					Action action = tileList.pop();

					//this may run into some npes - would it? an action has to be able to go back to where it came from
					//counteract action and reevaluate self
					if (action == Action.MOVE_DOWN) {
						//System.out.println("above: " + above + " " + (above.getStatus() == TileStatus.DIRTY));
						//System.out.print("moving up");
						return Action.MOVE_UP;
					}

					else if (action == Action.MOVE_UP) {
						//System.out.println("below: " + below + " " + (below.getStatus() == TileStatus.DIRTY));
						//System.out.print("moving down");
						return Action.MOVE_DOWN;
					}

					else if (action == Action.MOVE_LEFT) {
						//System.out.println("right: " + right + " " + (right.getStatus() == TileStatus.DIRTY));
						//System.out.print("moving right");
						return Action.MOVE_RIGHT;
					}

					else if (action == Action.MOVE_RIGHT) {
						//System.out.println("left: " + left + " " + (left.getStatus() == TileStatus.DIRTY));
						//System.out.print("moving left");
						return Action.MOVE_LEFT;
					}				
				}
			}
		}
		return Action.DO_NOTHING;
		//this is the only thing running
		//		// Currently the agent randomly decides an action... you can do better :D
		//int rand = (int)(Math.random() * 5);
		//	    if (rand == 0) return Action.MOVE_UP;
		//	    else if (rand == 1) return Action.MOVE_RIGHT;
		//	    else if (rand == 2) return Action.MOVE_DOWN;
		//	    else if (rand == 3) return Action.MOVE_LEFT;
		//	    else return Action.DO_NOTHING;

		//if(below.getStatus() == TileStatus.DIRTY) return Action.MOVE_DOWN;
		//		else if(right.getStatus() == TileStatus.DIRTY) return Action.MOVE_RIGHT;
		//		else if(above.getStatus() == TileStatus.DIRTY) return Action.MOVE_UP;
		//		else if(left.getStatus() == TileStatus.DIRTY) return Action.MOVE_LEFT;
		//		else return Action.DO_NOTHING;

		//check if it's not null first
		//		if (above != null && above.getStatus() == TileStatus.DIRTY) {
		//			//System.out.println("above: " + above + " " + (above.getStatus() == TileStatus.DIRTY));
		//			return Action.MOVE_UP;
		//		}
		//		else if (below != null && below.getStatus() == TileStatus.DIRTY) {
		//			//System.out.println("below: " + below + " " + (below.getStatus() == TileStatus.DIRTY));
		//			return Action.MOVE_DOWN;
		//		}
		//		else if (right != null && right.getStatus() == TileStatus.DIRTY) {
		//			//System.out.println("right: " + right + " " + (right.getStatus() == TileStatus.DIRTY));
		//			return Action.MOVE_RIGHT;
		//		}
		//		else if (left != null && left.getStatus() == TileStatus.DIRTY) {
		//	 		//System.out.println("left: " + left + " " + (left.getStatus() == TileStatus.DIRTY));
		//			return Action.MOVE_LEFT;
		//		}

		//need to reset popped position as self
		//		
		//		//reevaluate position
		//		if (below != null && below.getStatus() == TileStatus.DIRTY) {
		//			System.out.println("below: " + below + " " + (below.getStatus() == TileStatus.DIRTY));
		//
		//			return Action.MOVE_DOWN;
		//		}
		//
		//		else if (right != null && right.getStatus() == TileStatus.DIRTY) {
		//			System.out.println("right: " + right + " " + (right.getStatus() == TileStatus.DIRTY));
		//			return Action.MOVE_RIGHT;
		//		}
		//
		//		else if (above != null && above.getStatus() == TileStatus.DIRTY) {
		//			System.out.println("above: " + above + " " + (above.getStatus() == TileStatus.DIRTY));
		//			return Action.MOVE_UP;
		//		}
		//
		//		else if (left != null && left.getStatus() == TileStatus.DIRTY) {
		//			System.out.println("left: " + left + " " + (left.getStatus() == TileStatus.DIRTY));
		//			return Action.MOVE_LEFT;
		//		}
		//	}
	}

	@Override
	public String toString() {
		return "Robot [neighbors=" + env.getNeighborTiles(this) + "]";
	}


}