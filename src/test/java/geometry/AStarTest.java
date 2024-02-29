import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import config.*;
import geometry.*;
import java.util.ArrayList;

public class AStarTest {

    @Test
    public void testShortestPath() {
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
        
        IntCoordinates start = new IntCoordinates(0,0);
        IntCoordinates goal = new IntCoordinates(4, 4);

        ArrayList<IntCoordinates> path = AStar.shortestPath(start, goal, mazeConfig);
        IntCoordinates arrayShouldStartAt = new IntCoordinates(0,1);
        assertNotNull(path, "Path is null");
        assertEquals(8, path.size(), "Path size is not 8");
        assertEquals(goal, path.get(0), "Path does not end at goal");
        assertEquals(arrayShouldStartAt, path.get(path.size() - 1), "Path does not start at start");
    }

    @Test
    public void testIsPath() {
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

        IntCoordinates start = new IntCoordinates(0, 0);
        IntCoordinates goal = new IntCoordinates(4, 4);

        boolean isPath = AStar.isPath(start, goal, mazeConfig);

        assertTrue(isPath);
    }

    @Test
    public void testLongestPath() {
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

        IntCoordinates start = new IntCoordinates(0, 0);
        IntCoordinates goal = new IntCoordinates(4, 4);

        ArrayList<IntCoordinates> path = AStar.longestPath(start, goal, mazeConfig);
        IntCoordinates arrayStartShouldBe = new IntCoordinates(0, 1);
        assertNotNull(path);
        assertEquals(8, path.size());
        assertEquals(goal, path.get(0));
        assertEquals(arrayStartShouldBe, path.get(path.size() - 1));
    }
}