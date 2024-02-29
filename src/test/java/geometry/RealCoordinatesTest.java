import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import geometry.*;

public class RealCoordinatesTest {

    @Test
    public void testPlus() {
        RealCoordinates coord1 = new RealCoordinates(2.5, 3.5);
        RealCoordinates coord2 = new RealCoordinates(1.5, 2.5);
        RealCoordinates expected = new RealCoordinates(4.0, 6.0);
        RealCoordinates result = coord1.plus(coord2);
        assertEquals(expected, result);
    }

    @Test
    public void testTimes() {
        RealCoordinates coord = new RealCoordinates(2.5, 3.5);
        double multiplier = 2.0;
        RealCoordinates expected = new RealCoordinates(5.0, 7.0);
        RealCoordinates result = coord.times(multiplier);
        assertEquals(expected, result);
    }

    @Test
    public void testIntNeighbours() {
        RealCoordinates coord = new RealCoordinates(2.5, 3.5);
        Set<IntCoordinates> expected = Set.of(
                new IntCoordinates(2, 3),
                new IntCoordinates(2, 4),
                new IntCoordinates(3, 3),
                new IntCoordinates(3, 4)
        );
        Set<IntCoordinates> result = coord.intNeighbours();
        assertEquals(expected, result);
    }

    @Test
    public void testRound() {
        RealCoordinates coord = new RealCoordinates(2.5, 3.5);
        IntCoordinates expected = new IntCoordinates(3, 4);
        IntCoordinates result = coord.round();
        assertEquals(expected, result);
    }

    @Test
    public void testFloorX() {
        RealCoordinates coord = new RealCoordinates(2.5, 3.5);
        RealCoordinates expected = new RealCoordinates(2.0, 3.5);
        RealCoordinates result = coord.floorX();
        assertEquals(expected, result);
    }

    @Test
    public void testFloorY() {
        RealCoordinates coord = new RealCoordinates(2.5, 3.5);
        RealCoordinates expected = new RealCoordinates(2.5, 3.0);
        RealCoordinates result = coord.floorY();
        assertEquals(expected, result);
    }

    @Test
    public void testCeilX() {
        RealCoordinates coord = new RealCoordinates(2.5, 3.5);
        RealCoordinates expected = new RealCoordinates(3.0, 3.5);
        RealCoordinates result = coord.ceilX();
        assertEquals(expected, result);
    }

    @Test
    public void testCeilY() {
        RealCoordinates coord = new RealCoordinates(2.5, 3.5);
        RealCoordinates expected = new RealCoordinates(2.5, 4.0);
        RealCoordinates result = coord.ceilY();
        assertEquals(expected, result);
    }

    @Test
    public void testDist() {
        RealCoordinates coord1 = new RealCoordinates(2.0, 3.0);
        RealCoordinates coord2 = new RealCoordinates(5.0, 7.0);
        double expected = 5.0;
        double result = coord1.dist(coord2);
        assertEquals(expected, result);
    }

    @Test
    public void testWarp() {
        RealCoordinates coord = new RealCoordinates(-1.0, -1.0);
        int width = 5;
        int height = 5;
        RealCoordinates expected = new RealCoordinates(4.0, 4.0);
        RealCoordinates result = coord.warp(width, height);
        assertEquals(expected, result);
    }
}
