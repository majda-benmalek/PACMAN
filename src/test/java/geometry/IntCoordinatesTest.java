import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import geometry.*;
import java.util.ArrayList;
public class IntCoordinatesTest {

    @Test
    public void testToRealCoordinatesWithScale() {
        IntCoordinates coordinates = new IntCoordinates(2, 3);
        double scale = 0.5;
        RealCoordinates expected = new RealCoordinates(1.0, 1.5);
        RealCoordinates result = coordinates.toRealCoordinates(scale);
        assertEquals(expected, result, "Incorrect real coordinates");
    }

    @Test
    public void testSub() {
        IntCoordinates coordinates1 = new IntCoordinates(5, 7);
        IntCoordinates coordinates2 = new IntCoordinates(2, 3);
        IntCoordinates expected = new IntCoordinates(3, 4);
        IntCoordinates result = coordinates1.sub(coordinates2);
        assertEquals(expected, result, "Incorrect subtraction result");
    }

    @Test
    public void testGetX() {
        IntCoordinates coordinates = new IntCoordinates(2, 3);
        int expected = 2;
        int result = coordinates.getX();
        assertEquals(expected, result, "Incorrect x-coordinate");
    }

    @Test
    public void testGetY() {
        IntCoordinates coordinates = new IntCoordinates(2, 3);
        int expected = 3;
        int result = coordinates.getY();
        assertEquals(expected, result, "Incorrect y-coordinate");
    }

    @Test
    public void testNorm() {
        IntCoordinates coordinates = new IntCoordinates(3, 4);
        int expected = 5;
        int result = coordinates.norm();
        assertEquals(expected, result, "Incorrect Euclidean norm");
    }

    @Test
    public void testAdd() {
        IntCoordinates coordinates1 = new IntCoordinates(2, 3);
        IntCoordinates coordinates2 = new IntCoordinates(5, 7);
        IntCoordinates expected = new IntCoordinates(7, 10);
        IntCoordinates result = coordinates1.add(coordinates2);
        assertEquals(expected, result, "Incorrect addition result");
    }
}
