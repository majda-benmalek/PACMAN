import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import config.*;
public class CellTest {

    @Test
    public void testGetInitialContent() {
        Cell cell = new Cell(false, false, false, false, false, false, Cell.Content.DOT);
        assertEquals(Cell.Content.DOT, cell.getInitialContent());
    }

    @Test
    public void testSetEastWall() {
        Cell cell = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
        cell.setEastWall(true);
        assertTrue(cell.getEastWall());
    }

    @Test
    public void testSetInitialContent() {
        Cell cell = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
        cell.setInitialContent(Cell.Content.FRUIT);
        assertEquals(Cell.Content.FRUIT, cell.getInitialContent());
    }

    @Test
    public void testSetNorthWall() {
        Cell cell = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
        cell.setNorthWall(true);
        assertTrue(cell.getNorthWall());
    }

    @Test
    public void testSetSouthWall() {
        Cell cell = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
        cell.setSouthWall(true);
        assertTrue(cell.getSouthWall());
    }

    @Test
    public void testSetWestWall() {
        Cell cell = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
        cell.setWestWall(true);
        assertTrue(cell.getWestWall());
    }

    @Test
    public void testSetGhostNorthWall() {
        Cell cell = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
        cell.setGhostNorthWall(true);
        assertTrue(cell.getGhostNorthWall());
    }

    @Test
    public void testSetGhostSouthWall() {
        Cell cell = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
        cell.setGhostSouthWall(true);
        assertTrue(cell.getGhostSouthWall());
    }

    @Test
    public void testHasDot() {
        Cell cellWithDot = new Cell(false, false, false, false, false, false, Cell.Content.DOT);
        Cell cellWithoutDot = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);

        assertTrue(cellWithDot.hasDot());
        assertFalse(cellWithoutDot.hasDot());
    }

    @Test
    public void testHasEnergizer() {
        Cell cellWithEnergizer = new Cell(false, false, false, false, false, false, Cell.Content.ENERGIZER);
        Cell cellWithoutEnergizer = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);

        assertTrue(cellWithEnergizer.hasEnergizer());
        assertFalse(cellWithoutEnergizer.hasEnergizer());
    }

    @Test
    public void testHasFruit() {
        Cell cellWithFruit = new Cell(false, false, false, false, false, false, Cell.Content.FRUIT);
        Cell cellWithoutFruit = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);

        assertTrue(cellWithFruit.hasFruit());
        assertFalse(cellWithoutFruit.hasFruit());
    }
}