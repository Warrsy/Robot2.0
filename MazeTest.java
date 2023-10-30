import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

import model.Maze;
import model.Position;

/**
 * JUnit test class for the {@link model.Maze} class.
 * 
 * @author Daniel Hylander
 * @since 2023-04-27
 */
public class MazeTest {

    private Maze[] maze = new Maze[5];

    private Position[] start = new Position[5];
    private ArrayList<Position[]> goal = new ArrayList<Position[]>();

    private int[] cols = new int[5];
    private int[] rows = new int[5];
    

    /**
     * Before each test initialze five diffrent mazes and their 
     * respective start and goal position with the row count and column count.
     */
    @BeforeEach
    void initEach() {
        for (int i = 0 ; i < 5 ; i ++) {
            String mazePath = "/home/warrsy/dv_java/ou2/test_files/mazeTests/";
            mazePath = mazePath + "maze" + i + ".txt";

            try {
                File mazeFile = new File(mazePath);
                Scanner mazeScanner = new Scanner(mazeFile);
                
                maze[i]= new Maze(mazeScanner);
                
            } catch (FileNotFoundException e) {
                System.out.println("Can't open file.");
            }
        }
        start[0] = new Position(1, 0);
        start[1] = new Position(6, 2);
        start[2] = new Position(2, 6);
        start[3] = new Position(3, 4);
        start[4] = new Position(15, 1);

        Position[] position1 = {new Position(8, 6)};
        goal.add(position1);

        Position[] position2 = {new Position(8, 6)};
        goal.add(position2);

        Position[] position3 = {new Position(12, 2)};
        goal.add(position3);
        
        Position[] position4 = {new Position(8, 6), new Position(12, 2)};
        goal.add(position4);

        Position[] position5 = {new Position(8, 6)};
        goal.add(position5);

        cols[0] = 12;
        cols[1] = 12;
        cols[2] = 19;
        cols[3] = 19;
        cols[4] = 19;

        rows[0] = 7;
        rows[1] = 7;
        rows[2] = 8;
        rows[3] = 7;
        rows[4] = 7;
    }

    /**
     * Test if the getStart() method from the Maze class works by checking
     * if it returns the start position for each maze.
     */
    @Test
    void startPosInMazeTest() {
        for (int i = 0 ; i < 1 ; i++) {
            assertTrue(start[0].equals(maze[0].getStart()));
        }
    }

    /**
     * Test if the getNumColumns() method from the Maze class works by checking
     * if it returns the length of the longest column for each maze.
     */
    @Test
    void ColNumInMazeTest() {
        for (int i = 0 ; i < 5 ; i++) {
            assertEquals(cols[i], maze[i].getNumColumns());
        }
    }

    /**
     * Test if the getNumRows() method from the Maze class works by checking
     * if it returns the amount of the rows for each maze.
     */
    @Test
    void RowNumInMazeTest() {
        for (int i = 0 ; i < 5 ; i++) {
            assertEquals(rows[i], maze[i].getNumRows());
        }
    }

    /**
     * Test if the isGoal() method from the Maze class works by checking
     * if isGoal() returns true for every goal in a maze, done for each maze.
     */
    @Test
    void goalsInMazeTest() {
        for (int i = 0 ; i < 5 ; i++) {
            for (int j = 0 ; j < goal.get(0).length ; j++) {
                assertTrue(maze[i].isGoal(goal.get(i)[j]));
            }
        }
    }

    /**
     * Test if the isMovable() method from the Maze class works by checking
     * if start and goal positins are movable for each maze.
     */
    @Test
    void isMovableSpecialTest() {
        for (int i = 0 ; i < 5 ; i++) {
            for (int j = 0 ; j < goal.get(i).length ; j++) {
                assertTrue(maze[i].isMovable(start[i]));
                assertTrue(maze[i].isMovable(goal.get(i)[j]));
            }
        }
    }
}
