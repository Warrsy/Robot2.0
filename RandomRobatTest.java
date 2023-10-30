import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

import model.Maze;
import model.Position;
import model.RandomRobot;

/**
 * JUnit test class for the {@link model.RandomRobot} class.
 * Tests various aspects of the robot's behavior and movement through mazes.
 * 
 * @author Daniel Hylander
 * @since 2023-04-27
 */
public class RandomRobatTest {
    
    private RandomRobot[] robot = new RandomRobot[5];
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
                robot[i] = new RandomRobot(maze[i]);
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
            File mazeFile = new File("test_files/RandomRobot_test_mazes/move.txt");
            Scanner mazeScanner = new Scanner(mazeFile);
            
            Maze maze= new Maze(mazeScanner);
            RandomRobot robot = new RandomRobot(maze);

            robot.move();

            assertTrue(robot.getPosition().equals(new Position(1, 1)));
        } catch (FileNotFoundException e) {
            System.out.println("Can't open file.");
        }
    }

    /**
     * Test if the move() method can backtrack the robot if no new 
     * walkable postions exist.
     * Using a maze with two movable positions.
     */
    @Test
    void backtrackTest() {
        try {
            File mazeFile = new File("test_files/RandomRobot_test_mazes/backtrack.txt");
            Scanner mazeScanner = new Scanner(mazeFile);
            
            Maze maze= new Maze(mazeScanner);
            RandomRobot robot = new RandomRobot(maze);

            for (int i = 0 ; i < 5 ; i++) {
                for (int j = 0 ; i < 3 ; j++) {
                    robot.move();
                }
                assertTrue(robot.getPosition().equals(new Position(1, 1)));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Can't open file.");
        }
    }

    /**
     * Test if the robot can traverse a multitude of diffrent mazes.
     */
    @Test
    void robotTraverseMazeTest() {
        for (int i = 0 ; i < 5 ; i++) {
            while (!robot[i].hasReachedGoal()) {
                robot[i].move();
            }
            assertTrue(maze[i].isGoal(robot[i].getPosition()));
        }
    }
}
