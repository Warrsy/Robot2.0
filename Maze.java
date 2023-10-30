package model;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Represents a maze object with start and goal position that can 
 * be checked for walkability. A maze is a two-dimensional grid of 
 * cells, where each cell can be either open or blocked. In this 
 * program, a maze is represented by a file containing a grid of 
 * characters.
 *
 * @author Daniel Hylander
 * @since 2023-04-27
 */
public class Maze
{
    private int rows = 0;
    private int columns = 0;

    private ArrayList<char[]> maze = new ArrayList<char[]>();

    private Position start = null;

    private boolean foundStart = false;
    private boolean foundGoal = false;

    /**
     * Constructs an initalizes a maze from the given Scanner object.
     * 
     * The given {@code mazeFile} should have the following format:
     * <ul>
     *      <li>Maze walls are marked with '*'.</li>
     *      <li>Walkable tiles are marked with spaces ' '.</li>
     *      <li>One start position, marked with 'S'.</li>
     *      <li>At least one goal position, marked with 'G'.</li>
     * </ul>
     * 
     * @param mazeFile the file containing the maze.
     * @throws RuntimeException if the given {@code mazeFile} is not formatted correctly.
     */
    public Maze(Scanner mazeFile) throws RuntimeException
    {
        while (mazeFile.hasNextLine()) {
            String line = mazeFile.nextLine();
            
            char[] array = new char[line.length()];
            System.arraycopy(line.toCharArray(), 0, array, 0, line.length());
            maze.add(array);
            
            findStartAndGoal(line, rows);
            
            rows++;
            if (line.length() > columns) {
                columns = line.length();
            }
        }
        
        if (!foundStart) {
            throw new RuntimeException("INNCORRECT FORMAT: Maze missing a start position.");
        } else if (!foundGoal) {
            throw new RuntimeException("INNCORRECT FORMAT: Maze missing a goal position.");
        }
    }
    
    /*
     * Finds the start and goal position in the given line and updates in accordingly.
     * 
     * @param line - the line to search for start and goal positions.
     * @param y - the y index for the current line in the maze.
     * @throws RuntimeException - if the maze contains multiple start positions or the file is 
     * not formatted correctly.
     */
    private void findStartAndGoal(String line, int y) throws RuntimeException
    { 
        for (int x = 0 ; x < line.length() ; x++) {
            char c = line.charAt(x);

            if (c == 'S') {
                if (foundStart) {
                    throw new RuntimeException(
                        "INNCORRECT FORMAT: Maze contains multiple start positions.");
                }

                start = new Position(x, y);
                foundStart = true;
            }
    
            if (c == 'G') {
                foundGoal = true;
            }
        }
    }

    /**
     * Checks if the {@code position} is walkable, which means it is either a traversable tile
     * or goal.
     * 
     * @param position the position to check.
     * @return true if the position is walkable; false otherwise.
     */
    public boolean isMovable(Position position)
    {
        try {
            char tile = maze.get(position.getY())[position.getX()];
            return (tile == ' ' || tile == 'G' || tile == 'S');
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Checks if the given {@code position} is the goal, which means it's the end of the maze.
     * 
     * @param position the position to check is the goal.
     * @return true if the position is the goal; false otherwise.
     */
    public boolean isGoal(Position position)
    {
        try {
            char tile = maze.get(position.getY())[position.getX()];
            return (tile == 'G');
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Retruns the start position of the maze.
     * 
     * @return the start position.
     */
    public Position getStart()
    {
        return start;
    }

    /**
     * Returns the number of columns in the maze.
     * 
     * @return the number of columns.
     */
    public int getNumColumns()
    {
        return columns;
    }

    /**
     * Returns the number of rows in the maze.
     * 
     * @return the number of rows.
     */
    public int getNumRows()
    {
        return rows;
    }
}