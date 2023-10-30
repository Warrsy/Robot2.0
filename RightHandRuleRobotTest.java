import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

import model.RightHandRuleRobot;

import model.Maze;
import model.Position;

/**
 * JUnit test class for the {@link model.RightHandRuleRobot} class.
 * Tests various aspects of the robot's behavior and movement through mazes.
 * 
 * @author Daniel Hylander
 * @since 2023-04-27
 */
public class RightHandRuleRobotTest {

    private RightHandRuleRobot[] robot = new RightHandRuleRobot[5];
    private Maze[] maze = new Maze[5];

    /**
     * Sets up the test environment by initializing five different robots
     * in five different mazes.
     * Uses maze files located in the "/mazeTests" resource directory.
     */
    @BeforeEach
    void initEach() {
        // Load maze file from resources directory
        for (int i = 0 ; i < 5 ; i ++) {
            String mazePath = getClass().getResource("/mazeTests/maze" + i + ".txt").getPath();

            try {
                // Create maze object and robot object for each maze file
                File mazeFile = new File(mazePath);
                Scanner mazeScanner = new Scanner(mazeFile);
                
                maze[i]= new Maze(mazeScanner);
                robot[i] = new RightHandRuleRobot(maze[i]);
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
            File mazeFile = new File("test_files/RightHandRuleRobot_test_mazes/move.txt");
            Scanner mazeScanner = new Scanner(mazeFile);
            
            Maze maze= new Maze(mazeScanner);
            RightHandRuleRobot robot = new RightHandRuleRobot(maze);

            robot.move();

            assertTrue(robot.getPosition().equals(new Position(1, 1)));
        } catch (FileNotFoundException e) {
            System.out.println("Can't open file.");
        }
    }

    /**
     * Test if the move() method from the RightHandRuleRobot class will always move 
     * to the right hand side if possible. 
     * Using a maze with four movable positions in a sqaure.
     */
    @Test
    void rightHandSideMoveTest() {
        try {
            File mazeFile = new File("test_files/RightHandRuleRobot_test_mazes/rightHandSide.txt");
            Scanner mazeScanner = new Scanner(mazeFile);
            
            Maze maze= new Maze(mazeScanner);
            RightHandRuleRobot robot = new RightHandRuleRobot(maze);

            for (int i = 0 ; i < 5 ; i++) {
                for (int j = 0 ; i < 5 ; j++) {
                    robot.move();
                }
                assertTrue(robot.getPosition().equals(new Position(1, 1)));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Can't open file.");
        }
    }

    /**
     * Test if the robot can traverse a multitude of diffrent 
     * mazes.
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