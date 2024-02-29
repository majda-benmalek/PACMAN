import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.Direction;
import geometry.IntCoordinates;
public class DirectionTest {

    @Test
    public void testGetVector() {
        assertEquals(new IntCoordinates(0, 0), Direction.NONE.getVector());
        assertEquals(new IntCoordinates(0, -1), Direction.NORTH.getVector());
        assertEquals(new IntCoordinates(1, 0), Direction.EAST.getVector());
        assertEquals(new IntCoordinates(0, 1), Direction.SOUTH.getVector());
        assertEquals(new IntCoordinates(-1, 0), Direction.WEST.getVector());
    }

    @Test
    public void testToIntCoordinates() {
        assertEquals(new IntCoordinates(0, 0), Direction.NONE.toIntCoordinates());
        assertEquals(new IntCoordinates(0, -1), Direction.NORTH.toIntCoordinates());
        assertEquals(new IntCoordinates(1, 0), Direction.EAST.toIntCoordinates());
        assertEquals(new IntCoordinates(0, 1), Direction.SOUTH.toIntCoordinates());
        assertEquals(new IntCoordinates(-1, 0), Direction.WEST.toIntCoordinates());
    }

    @Test
    public void testFromIntCoordinates() {
        assertEquals(Direction.NONE, Direction.fromIntCoordinates(new IntCoordinates(0, 0)));
        assertEquals(Direction.NORTH, Direction.fromIntCoordinates(new IntCoordinates(0, -1)));
        assertEquals(Direction.EAST, Direction.fromIntCoordinates(new IntCoordinates(1, 0)));
        assertEquals(Direction.SOUTH, Direction.fromIntCoordinates(new IntCoordinates(0, 1)));
        assertEquals(Direction.WEST, Direction.fromIntCoordinates(new IntCoordinates(-1, 0)));
        assertEquals(Direction.NONE, Direction.fromIntCoordinates(new IntCoordinates(1, 1)));
    }
}