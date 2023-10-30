package model;

import java.util.Objects;

/**
 * A point representing the location of a robot in {@code (x, y)} coordinate space 
 * inside a maze, specified in integer precision.
 * 
 * @author Daniel Hylander
 * @since 2023-04-27
 */
public class Position
{
    private int x;
    private int y;

    /**
     * Constructs and initializes a point at the specified
     * {@code (x, y)} location in the maze.
     * 
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of the robot, in integer precision.
     * 
     * @return the x-coordinate of the robot.
     */
    public int getX()
    {
        return x;
    }

    /**
     * Returns the x-coordinate of the robot, in integer precision.
     *
     * @return the x-coordinate of the robot.
     */
    public int getY()
    {
        return y;
    }

    /**
     * Returns a new position object representing the location south of 
     * the robot.
     * 
     * @return the position south of the robot.
     */
    public Position getPosToSouth()
    {
        return new Position(x, y + 1);
    }

    /**
     * Returns a new position object representing the location north of 
     * the robot.
     * 
     * @return the position north of the robot.
     */
    public Position getPosToNorth()
    {
        return new Position(x, y - 1);
    }

    /**
     * Returns a new position object representing the location west of 
     * the robot.
     * 
     * @return the position west of the robot.
     */
    public Position getPosToWest()
    {
        return new Position(x - 1, y);
    }

    /**
     * Returns a new position object representing the location east 
     * of the robot.
     * 
     * @return the position east of the robot.
     */
    public Position getPosToEast()
    {
        return new Position(x + 1, y);
    }

    /**
     * Indicates whether some other object is "equal to" this one. 
     * 
     * The equals method implements an equivalence relation on non-null object references:
     * <ul>
     *      <li>
     *      It is reflexive: for any non-null reference value x, x.equals(x) should 
     *      return true.
     *      </li>
     *      <li>
     *      It is symmetric: for any non-null reference values x and y, x.equals(y) 
     *      should return true if and only if y.equals(x) returns true.
     *      </li>
     *      <li>
     *      It is transitive: for any non-null reference values x, y, and z, if 
     *      x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) 
     *      should return true.
     *      </li>
     *      <li>
     *      It is consistent: for any non-null reference values x and y, multiple 
     *      invocations of x.equals(y) consistently return true or consistently return 
     *      false, provided no information used in equals comparisons on the objects 
     *      is modified.
     *      </li>
     *      <li>
     *      For any non-null reference value x, x.equals(null) should return false.
     *      </li>
     * </ul>
     *
     * The equals method for class Object implements the most discriminating possible 
     * equivalence relation on objects; that is, for any non-null reference values x 
     * and y, this method returns true if and only if x and y refer to the same object 
     * (x == y has the value true). 
     * 
     * @param o the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Position)) {
            return false;
        }

        Position other = (Position) o;
        return this.x == other.x && this.y == other.y;
    }

    /**
     * Returns a hash code value for the object. This method is supported for the 
     * benefit of hash tables such as those provided by HashMap.
     * 
     * The general contract of hashCode is:
     * <ul>
     *      <li>
     *      Whenever it is invoked on the same object more than once during an execution 
     *      of a Java application, the hashCode method must consistently return the same 
     *      integer, provided no information used in equals comparisons on the object is 
     *      modified. This integer need not remain consistent from one execution of an 
     *      application to another execution of the same application.
     *      </li>
     *      <li>
     *      If two objects are equal according to the equals(Object) method, then calling 
     *      the hashCode method on each of the two objects must produce the same integer 
     *      result.
     *      </li>
     *      <li>
     *      It is not required that if two objects are unequal according to the 
     *      equals(java.lang.Object) method, then calling the hashCode method on each of 
     *      the two objects must produce distinct integer results. However, the programmer 
     *      should be aware that producing distinct integer results for unequal objects 
     *      may improve the performance of hash tables.
     *      </li>
     * </ul>
     * 
     * This implementation return a hash code base on the {@code x} and {@code y} coordinates 
     * of the Position object.
     * 
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }
}