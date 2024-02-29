import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import config.*;
import geometry.*;

public class MazeConfigTest {
    
    @Test
    public void testMazeConfigConstructor() {
        Cell[][] grid = new Cell[3][3];
        IntCoordinates pacManPos = new IntCoordinates(1, 1);
        IntCoordinates blinkyPos = new IntCoordinates(0, 0);
        IntCoordinates pinkyPos = new IntCoordinates(2, 2);
        IntCoordinates inkyPos = new IntCoordinates(1, 2);
        IntCoordinates clydePos = new IntCoordinates(2, 1);
        IntCoordinates fruitPos = new IntCoordinates(0, 2);

        MazeConfig mazeConfig = new MazeConfig(grid, pacManPos, blinkyPos, pinkyPos, inkyPos, clydePos, fruitPos);

        assertArrayEquals(grid, mazeConfig.getGrid());
        assertEquals(pacManPos, mazeConfig.getPacManPos());
        assertEquals(blinkyPos, mazeConfig.getBlinkyPos());
        assertEquals(pinkyPos, mazeConfig.getPinkyPos());
        assertEquals(inkyPos, mazeConfig.getInkyPos());
        assertEquals(clydePos, mazeConfig.getClydePos());
        assertEquals(fruitPos, mazeConfig.getFruitPos());
    }

    @Test
    public void testGetCell() {
        Cell[][] grid = new Cell[3][3];
        IntCoordinates pacManPos = new IntCoordinates(1, 1);
        IntCoordinates blinkyPos = new IntCoordinates(0, 0);
        IntCoordinates pinkyPos = new IntCoordinates(2, 2);
        IntCoordinates inkyPos = new IntCoordinates(1, 2);
        IntCoordinates clydePos = new IntCoordinates(2, 1);
        IntCoordinates fruitPos = new IntCoordinates(0, 2);

        MazeConfig mazeConfig = new MazeConfig(grid, pacManPos, blinkyPos, pinkyPos, inkyPos, clydePos, fruitPos);

        Cell expectedCell= grid[1][1];

        IntCoordinates pos = new IntCoordinates(1, 1);
        Cell actualCell = mazeConfig.getCell(pos);

        assertEquals(expectedCell, actualCell);
    }

    @Test
    public void testIsInBounds() {
        Cell[][] grid = new Cell[3][3];
        IntCoordinates pacManPos = new IntCoordinates(1, 1);
        IntCoordinates blinkyPos = new IntCoordinates(0, 0);
        IntCoordinates pinkyPos = new IntCoordinates(2, 2);
        IntCoordinates inkyPos = new IntCoordinates(1, 2);
        IntCoordinates clydePos = new IntCoordinates(2, 1);
        IntCoordinates fruitPos = new IntCoordinates(0, 2);

        MazeConfig mazeConfig = new MazeConfig(grid, pacManPos, blinkyPos, pinkyPos, inkyPos, clydePos, fruitPos);

        IntCoordinates pos1 = new IntCoordinates(0, 0);
        IntCoordinates pos2 = new IntCoordinates(2, 2);
        IntCoordinates pos3 = new IntCoordinates(3, 3);

        assertTrue(mazeConfig.isInBounds(pos1));
        assertTrue(mazeConfig.isInBounds(pos2));
        assertFalse(mazeConfig.isInBounds(pos3));
    }
    @Test
    public void testLectureDuFichier() { // Replace with the actual file path
        MazeConfig mazeConfig=null;
        try{
        mazeConfig = MazeConfig.makeMaze(1);
        }catch(Exception e){
            fail("Exception thrown");
        }
        // Assert the properties of the mazeConfig object
        //assertNotNull(mazeConfig.getGrid());
        assertNotNull(mazeConfig.getPacManPos());
        assertNotNull(mazeConfig.getBlinkyPos());
        assertNotNull(mazeConfig.getPinkyPos());
        assertNotNull(mazeConfig.getInkyPos());
        assertNotNull(mazeConfig.getClydePos());
        assertNotNull(mazeConfig.getFruitPos());
    }

    @Test
    public void testLectureDuFichierWithInvalidPath() {
        MazeConfig mazeConfig=null;
        try{
        mazeConfig = MazeConfig.makeMaze(8);
        }catch(Exception e){
            mazeConfig=null;
        }
        // Assert that the mazeConfig object is null
        assertNull(mazeConfig);
    }
}