package model;

import java.util.HashMap;

/**
 * A class representing a Robot that traverses a maze by always moveing
 * like he has his hand on the right side of the wall inside the maze.
 * 
 * @author Daniel Hylander
 * @since 2023-04-27
 */
public class RightHandRuleRobot implements Robot
{
    private Position position;

    private String orientation;
    private String rightHandPosition;

    private Maze maze;


    /**
     * Constructus and initializes a robot inside the {@code maze} at 
     * the start position with the robot orientated to the North. The 
     * start position is the position return by {@code maze.getStart()}.
     * 
     * @param maze the maze that the robot will traverse.
     * @see Maze
     */
    public RightHandRuleRobot(Maze maze)
    {
        position = maze.getStart();

        orientation = "North";
        rightHandPosition = "East";

        this.maze = maze;
    }


    /**
     * Moves one the robot step by turning it orientation till it finds 
     * a walkable position in its forward orientation.
     */
    public void move()
    {
        HashMap<String, Position> possiblePositions = new HashMap<>();
        possiblePositions.put("North", this.position.getPosToNorth());
        possiblePositions.put("East", this.position.getPosToEast());
        possiblePositions.put("South", this.position.getPosToSouth());
        possiblePositions.put("West", this.position.getPosToWest());

        if (rightHandPositionMovable(possiblePositions.get(rightHandPosition))) {
            setOrientation(rightHandPosition);
        }
        else {
            findFirstMovableOrientation(possiblePositions);
        }
        
        this.position = possiblePositions.get(orientation);
    }
    

    /**
     * Checks if the position at the robots right hand is movable.
     * 
     * @param position the position at the robots right hand
     * @return true if the position is movable; else false.
     */
    private boolean rightHandPositionMovable(Position position)
    {
        return maze.isMovable(position);
    }


    /**
     * Sets the robot's current orientation to the given 
     * {@code orientation}, and uppdates its right hand position.
     * 
     * @param orientation orientation to turn the robot to.
     */
    private void setOrientation(String orientation) 
    {
        switch (orientation) {
            case "North":
                rightHandPosition = "East";
                break;
            case "East":
                rightHandPosition = "South";
                break;
            case "South":
                rightHandPosition = "West";
                break;
            case "West":
                rightHandPosition = "North";
                break;
        }

        this.orientation = orientation;
    }
    

    /**
     * Turns the robot tills the position infornt of it is movable.
     * 
     * @param possibleDirections the possible directions that the robot can move.
     */
    private void findFirstMovableOrientation(HashMap<String, Position> possibleDirections)
    {
        while (!maze.isMovable(possibleDirections.get(orientation))) {
            switch (orientation) {
                case "North":
                    this.setOrientation("West");
                    break;
                case "East":
                    this.setOrientation("North");
                    break;
                case "South":
                    this.setOrientation("East");
                    break;
                case "West":
                    this.setOrientation("South");
                    break;
            }
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