package model;

/**
 * The Robot interface provides a template for robot classes that 
 * can traverse a maze. A robot must implement the methods defined 
 * in this interface to move and track its position in a maze and 
 * determine if it has reached the end goal of the maze.
 * 
 * @author Daniel Hylander
 * @since 2023-04-27
 */
 public interface Robot {
    /**
     * Moves the robot one step in a certain direction, depending on 
     * the robot's desired behavior.
     */
    void move();

    /**
     * Returns the the robot's current position inside the maze.
     * 
     * @return the robots current position.
     */
    Position getPosition();

    /**
     * Checks if the robot has reach the end of the maze.
     * 
     * @return true if the robot has reach the end of the maze; false otherwise.
     */
    boolean hasReachedGoal();
}