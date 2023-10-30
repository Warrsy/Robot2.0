import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

import model.MemoryRobot;

import model.Maze;
import model.Position;

/**
 * JUnit test class for the {@link model.MemoryRobot} class.
 * Tests various aspects of the robot's behavior and movement through mazes.
 * 
 * @author Daniel Hylander
 * @since 2023-04-27
 */
public class MemoryRobotTest {

    private MemoryRobot[] robot = new MemoryRobot[5];
    private Maze[] maze = new Maze[5];

    /**
     * Sets up the test environment by initializing five different robots
     * in five different mazes.
     * Uses maze files located in the "/mazeTests" resource directory.
     */
    @BeforeEach
    void initEach() {
        for (int i = 0 ; i < 5 ; i ++) {
            String mazePath = getClass().getResource("/mazeTests/maze" + i + ".txt").getPath();

            try {
                File mazeFile = new File(mazePath);
                Scanner mazeScanner = new Scanner(mazeFile);
                
                maze[i]= new Maze(mazeScanner);
                robot[i] = new MemoryRobot(maze[i]);
            } catch (FileNotFoundException e) {
                System.out.println("Can't open file.");
            }
        }
    }

    /**
     * Tests that each robot is initially positioned at the start of its respective maze.
     * Checks that the robot's position matches the start position of the maze.
     */
    @Test
    void startPosTest() {
        for (int i = 0 ; i < 5 ; i++) {
            assertTrue(maze[i].getStart().equals(robot[i].getPosition()));
            
        }
    }

    /**
     * Checks that the robot's position changes as expected after 
     * calling the move() method.
     * Uses a maze that only has one movable position.
     */
    @Test
    void moveTest() {
        try {
            File mazeFile = new File("test_files/MemoryRobot_test_mazes/move.txt");
            Scanner mazeScanner = new Scanner(mazeFile);
            
            Maze maze= new Maze(mazeScanner);
            MemoryRobot robot = new MemoryRobot(maze);

            robot.move();

            assertTrue(robot.getPosition().equals(new Position(1, 1)));
        } catch (FileNotFoundException e) {
            System.out.println("Can't open file.");
        }
    }

    /**
     * Test so the move() method does not move the robot to a visited 
     * position. 
     * Using a maze with four movable positions in a sqaure.
     */
    @Test
    void testVisitedPosition() {
        try {
            File mazeFile = new File("test_files/MemoryRobot_test_mazes/backtrack.txt");
            Scanner mazeScanner = new Scanner(mazeFile);
            
            Maze maze= new Maze(mazeScanner);
            MemoryRobot robot = new MemoryRobot(maze);
            
            Position previousPosition = robot.getPosition();

            for (int i = 0 ; i < 5 ; i++) {
                robot.move();
                assertNotEquals(previousPosition, robot.getPosition());
                previousPosition = robot.getPosition();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Can't open file.");
        }
    }

    /**
     * Test if the robot can traverse a multitude of diffrent mazes.
     */
    @Test
    void traverseMazeTest() {
        for (int i = 0 ; i < 5 ; i++) {
            while (!robot[i].hasReachedGoal()) {
                robot[i].move();
            }
            assertTrue(maze[i].isGoal(robot[i].getPosition()));
        }
    }
}