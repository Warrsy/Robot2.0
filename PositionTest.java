import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import model.Position;

/**
 * JUnit test class for the {@link model.Position} class.
 * 
 * @author Daniel Hylander
 * @since 2023-04-27
 */
public class PositionTest {
    
    private final Position position = new Position(0, 1);

    /**
     * Test if the constructor of the Postision class works by checking
     * if it construct the expected object.
     */
    @Test
    void positionTest() {
        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 10 ; j++) {
                Position position = new Position(i, j);
                assertEquals(i, position.getX());
                assertEquals(j, position.getY());
            }
        }
    }

    /**
     * Test if the getPosSouth() method from the Postision class works by checking
     * if it returns the expected object.
     */
    @Test
    void getPosSouthTest() {
        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 10 ; j++) {
                Position position = new Position(i, j);

                int x = position.getX();
                int y = position.getY() + 1;

                position = position.getPosToSouth();

                assertEquals(x, position.getX());
                assertEquals(y, position.getY());
            }
        }
    }

    /**
     * Test if the getPosNorth() method from the Postision class works by checking
     * if it returns the expected object.
     */
    @Test
    void getPosNorthTest() {
        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 10 ; j++) {
                Position position = new Position(i, j);

                int x = position.getX();
                int y = position.getY() - 1;

                position = position.getPosToNorth();

                assertEquals(x, position.getX());
                assertEquals(y, position.getY());
            }
        }
        Position p;
        p = position.getPosToNorth();

        int x = position.getX();
        int y = position.getY() - 1;

        assertEquals(x, p.getX());
        assertEquals(y, p.getY());
    }

    /**
     * Test if the getPosWest() method from the Postision class works by checking
     * if it returns the expected object.
     */
    @Test
    void getPosWestTest() {
        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 10 ; j++) {
                Position position = new Position(i, j);
                
                int x = position.getX() - 1;
                int y = position.getY();

                position = position.getPosToWest();

                assertEquals(x, position.getX());
                assertEquals(y, position.getY());
            }
        }
    }

    /**
     * Test if the getPosEast() method from the Postision class works by checking
     * if it returns the expected object.
     */
    @Test
    void getPosEastTest() {
        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 10 ; j++) {
                Position position = new Position(i, j);
                
                int x = position.getX() + 1;
                int y = position.getY();

                position = position.getPosToEast();

                assertEquals(x, position.getX());
                assertEquals(y, position.getY());
            }
        }
    }

    /**
     * Test if the equals() method from the Postision class works by checking
     * if it returns True for it self.
     */
    @Test
    void equalsItSelfTest() {
        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 10 ; j++) {
                Position position = new Position(i, j);

                assertTrue(position.equals(position));
            }
        }
    }

    /**
     * Test if the equals() method from the Postision class works by checking
     * if it returns True for object with the same position.
     */
    @Test
    void equalsTheSameObjectTest() {
        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 10 ; j++) {
                Position position1 = new Position(i, j);
                Position position2 = new Position(i, j);
                Position position3 = new Position(i + 1, j);

                assertTrue(position1.equals(position2));
                assertTrue(position2.equals(position1));
                assertFalse(position1.equals(position3));
            }
        }
    }

    /**
     * Test if the equals() method from the Postision class works by checking
     * if it returns False for NULL objects.
     */
    @Test
    void equalsNotNullTest() {
        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 10 ; j++) {
                Position position = new Position(i, j);

                assertFalse(position.equals(null));
            }
        }
    }

    /**
     * Test if the hashCode() method from the Postision class works by checking
     * if it returns the same hashcode for the same object.
     */
    @Test
    void hashCodeSameForSameObjectTest() {

        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 10 ; j++) {
                Position position = new Position(i, j);
                int hashCode1 = position.hashCode();

                for (int k = 0 ; k < 5 ; k++) {
                    int hashCode2 = position.hashCode();
                    assertEquals(hashCode1, hashCode2);
                }
            }
        }
    }

    /**
     * Test if the hashCode() method from the Postision class works by checking
     * if it returns the diffrent hashcode for diffrent objects.
     */
    @Test
    void hashCodeDiffrentForDiffrentObjectTest() {

        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 10 ; j++) {
                Position position1 = new Position(i, j);
                Position position2 = new Position(j, i);
                int hashCode1 = position1.hashCode();
                int hashCode2 = position2.hashCode();
                
                if (hashCode1 != hashCode2) {
                    assertFalse(position1.equals(position2));
                } else {
                    assertTrue(position1.equals(position2));
                }
            }
        }
    }
}
