package model;

import java.util.Stack;
import java.util.EmptyStackException;
import java.util.HashMap;


/**
 * A class representing a Robot that traverses a maze by a depth 
 * first search until it finds the goal.
 * 
 * @author Daniel Hylander
 * @since 2023-04-27
 */
public class MemoryRobot implements Robot
{ 
    private Position position;

    private Stack<Position> pathSoFar = new Stack<>();
    private HashMap<Position, Position> visitedPosistions = new HashMap<>();

    private Maze maze;

   /**
     * Constructus and initializes a robot inside the {@code maze} at 
     * the start position with the robot orientated to the North. The 
     * start position is the position return by {@code maze.getStart()}.
     * 
     * @param maze the maze that the robot will traverse.
     * @see Maze
     */
    public MemoryRobot(Maze maze) {
        this.maze = maze;
        position = maze.getStart();
    }


    /**
     * Moves robot one step forward in using depthfirst search of the maze.
     */
    public void move() {
        Position[] possibleDirections = {
            position.getPosToEast(),
            position.getPosToNorth(),
            position.getPosToSouth(),
            position.getPosToWest()
        };

        Position nextPosition = firstMovableDirectionNotVisited(possibleDirections);

        visitedPosistions.putIfAbsent(nextPosition, nextPosition);
        position = nextPosition;
    }


    /**
     * Finds the first direction that is movable, excluding the positions visited before. 
     * If no direction is found, the last visited position is returned.
     * 
     * @param possibleDirections - the possible directions that the robot can move.
     * @return the first direction that is movable.
     */
    private Position firstMovableDirectionNotVisited(Position[] possibleDirections) {
        Position position = this.position;

        for (int i = 0 ; i < 4 ; i++) {
            position = possibleDirections[i];

            if (maze.isMovable(position) && 
                !visitedPosistions.containsKey(position)) {
                pathSoFar.add(this.position);

                return position;
            }
        }

        try {
            return pathSoFar.pop();
        } catch (EmptyStackException e) {
            return position;
        }
    }


    /**
     * Returns the the robot's current position inside the maze.
     * 
     * @return the robots current position.
     */
    public Position getPosition()
    {
        return position;
    }

    /**
     * Checks if the robot has reach the end of the maze.
     * 
     * @return true if the robot has reach the end of the maze; false otherwise.
     */
    public boolean hasReachedGoal()
    {
        return maze.isGoal(position);
    }
}