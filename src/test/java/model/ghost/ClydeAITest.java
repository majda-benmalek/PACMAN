import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import config.*;
import geometry.*;
import model.ghost.*;
import model.Direction;
public class ClydeAITest {

    @Test
    public void testChooseRandomSpotInMaze() {
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

        Ghost clyde = Ghost.CLYDE;
        ClydeAI clydeAI = new ClydeAI(clyde, mazeConfig);

        IntCoordinates randomSpot = clydeAI.chooseRandomSpotInMaze();

        assertNotNull(randomSpot, "Random spot is null");
        assertTrue(randomSpot.getX() >= 0 && randomSpot.getX() < mazeConfig.getWidth(), "Random spot X coordinate is out of bounds");
        assertTrue(randomSpot.getY() >= 0 && randomSpot.getY() < mazeConfig.getHeight(), "Random spot Y coordinate is out of bounds");
    }

    @Test
    public void testCurrentGoalIsAccessible() {
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

        Ghost clyde = Ghost.CLYDE;
        ClydeAI clydeAI = new ClydeAI(clyde, mazeConfig);

        boolean isAccessible = clydeAI.currentGoalIsAccessible();

        assertTrue(isAccessible, "Current goal is not accessible");
    }

    @Test
    public void testGetNextDir() {
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

        Ghost clyde = Ghost.CLYDE;
        clyde.setPos(new RealCoordinates(clydePos.getX(), clydePos.getY()));
        ClydeAI clydeAI = new ClydeAI(clyde, mazeConfig);

        Direction nextDir = clydeAI.getNextDir();

        assertNotNull(nextDir, "Next direction is null");
        assertNotEquals(Direction.NONE, nextDir, "Next direction is NONE");
    }
}