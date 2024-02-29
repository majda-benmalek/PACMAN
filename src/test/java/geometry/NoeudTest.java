import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import config.*;
import geometry.*;
import java.util.ArrayList;

public class NoeudTest {

    @Test
    public void testGetVoisins() {
        Cell[][] grid = new Cell[5][5];
        IntCoordinates pacManPos = new IntCoordinates(1, 1);
        IntCoordinates blinkyPos = new IntCoordinates(0, 0);
        IntCoordinates pinkyPos = new IntCoordinates(2, 2);
        IntCoordinates inkyPos = new IntCoordinates(1, 2);
        IntCoordinates clydePos = new IntCoordinates(2, 1);
        IntCoordinates fruitPos = new IntCoordinates(0, 2);
        for (int i = 0; i < 5; i++) {
            grid[0][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[1][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[2][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[3][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[4][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
        }
        MazeConfig mazeConfig = new MazeConfig(grid, pacManPos, blinkyPos, pinkyPos, inkyPos, clydePos, fruitPos);

        Noeud node = new Noeud(new IntCoordinates(1, 1), null);
        ArrayList<Noeud> voisins = node.getVoisins(mazeConfig);
        assertEquals(4, voisins.size(), "Incorrect number of neighbors");
        for (Noeud voisin : voisins) {
            assertTrue(voisin.getCoordinates().getX() == 1 || voisin.getCoordinates().getY() == 1,
                    "Incorrect neighbor");
        }
    }

    @Test
    public void testCompareParHeuristique() {
        Noeud node1 = new Noeud(new IntCoordinates(1, 1), null);
        node1.setHeuristique(10);
        Noeud node2 = new Noeud(new IntCoordinates(2, 2), null);
        node2.setHeuristique(5);

        int result1 = node1.compareParHeuristique(node2);
        int result2 = node2.compareParHeuristique(node1);

        assertEquals(-1, result1, "Incorrect comparison result");
        assertEquals(1, result2, "Incorrect comparison result");
    }

    @Test
    public void testManhattanDistance() {
        Noeud node1 = new Noeud(new IntCoordinates(1, 1), null);
        Noeud node2 = new Noeud(new IntCoordinates(4, 4), null);

        int distance = node1.manhattanDistance(node2);

        assertEquals(6, distance, "Incorrect Manhattan distance");
    }

    @Test
    public void testToString() {
        Noeud node = new Noeud(new IntCoordinates(1, 1), null);

        String result = node.toString();

        assertEquals("1, 1", result, "Incorrect string representation");
    }
}
