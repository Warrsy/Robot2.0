package model;
import java.util.Arrays;
import java.util.Collections;

/**
 * A class representing a Robot that traverses a maze by moveing in random 
 * directions until it finds the goal.
 * 
 * @author Daniel Hylander
 * @since 2023-04-27
 */
public class RandomRobot implements Robot
{
    private Position position;
    private Position previousPosition;

    private Maze maze;

    /**
     * Constructus and initializes a robot inside the {@code maze} at the start position.
     * The start position is the position return by {@code maze.getStart()}.
     * 
     * @param maze the maze that the robot will traverse.
     * @see Maze
     */
    public RandomRobot(Maze maze)
    {
        position = maze.getStart();
        previousPosition = position;

        this.maze = maze;
    }

    /**
     * Moves the robot one step in a random direction, excluding the position it last visited. 
     * If the robot cannot move to any new position it will go back to the last visited position.
     */
    public void move()
    {
        Position[] possibleDirections = {
            position.getPosToEast(),
            position.getPosToNorth(),
            position.getPosToSouth(),
            position.getPosToWest()
        };
        Collections.shuffle(Arrays.asList(possibleDirections));
 
        Position nextPosition = findFirstMovableDirection(possibleDirections);
        
        setPosition(nextPosition);
    }
    
    /**
     * Finds the first direction that is movable, excluding the position it last visited. 
     * If no direction is found, the last visited position is returned.
     * 
     * @param possibleDirections - the possible directions that the robot can move.
     * @return the first direction that is movable.
     */
    private Position findFirstMovableDirection(Position[] possibleDirections) 
    {
        Position position;
        int index = 0;
    
        do {
            position = possibleDirections[index];
            index++;
        } while ((!maze.isMovable(position) || position.equals(previousPosition)) 
                     && index < possibleDirections.length);
    
        if (!maze.isMovable(position)) {
            position = previousPosition;
        }

        return position;
    }
    
    /**
     * Sets the robot's position to the given 'position' inside the maze.
     * 
     * @param position the position to move the robot to.
     */
    private void setPosition(Position position) 
    {
        this.previousPosition = this.position;
        this.position = position;
  
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