import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.Fruit;
import geometry.IntCoordinates;

public class FruitTest {

    @Test
    public void testFruitVisibility() {
        IntCoordinates pos = new IntCoordinates(2, 3);
        Fruit fruit = new Fruit(pos);

        assertTrue(Fruit.isEstVisible(), "Fruit should be initially visible");

        fruit.setVisible(false);
        assertFalse(Fruit.isEstVisible(), "Fruit should be invisible after calling setVisible(false)");

        fruit.setVisible(true);
        assertTrue(Fruit.isEstVisible(), "Fruit should be visible after calling setVisible(true)");
    }

    @Test
    public void testFruitPosition() {
        IntCoordinates pos = new IntCoordinates(2, 3);
        Fruit fruit = new Fruit(pos);

        assertEquals(pos, fruit.getPos(), "Fruit position should match the initial position");

        IntCoordinates newPos = new IntCoordinates(4, 5);
        fruit.setPos(newPos);
        assertEquals(newPos, fruit.getPos(), "Fruit position should match the new position");
    }

    @Test
    public void testFruitTimer() throws InterruptedException {
        IntCoordinates pos = new IntCoordinates(2, 3);
        Fruit fruit = new Fruit(pos);

        assertTrue(Fruit.isEstVisible(), "Fruit should be initially visible");

        Thread.sleep(5000); // Wait for 5 seconds

        assertFalse(Fruit.isEstVisible(), "Fruit should be invisible after the timer expires");
    }
}