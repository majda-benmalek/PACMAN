import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import config.MazeConfig;
import geometry.IntCoordinates;
import geometry.RealCoordinates;
import model.Direction;
import model.ghost.Ghost;
import config.Cell;

public class GhostTest {

    @Test
    public void testGetPos() {
        Ghost ghost = Ghost.BLINKY;
        RealCoordinates pos = new RealCoordinates(1.5, 2.5);
        ghost.setPos(pos);
        assertEquals(pos, ghost.getPos());
    }

    @Test
    public void testIsEatable() {
        Ghost ghost = Ghost.INKY;
        assertFalse(ghost.isEatable());
        ghost.setEatable(true);
        assertTrue(ghost.isEatable());
    }

    @Test
    public void testGetDirection() {
        Ghost ghost = Ghost.PINKY;
        Direction direction = Direction.EAST;
        ghost.setDirection(direction);
        assertEquals(direction, ghost.getDirection());
    }

    @Test
    public void testGetSpeed() {
        Ghost ghost = Ghost.CLYDE;
        double speed = 2.4;
        assertEquals(speed, ghost.getSpeed());
    }

    @Test
    public void testSetPos() {
        Ghost ghost = Ghost.BLINKY;
        RealCoordinates pos = new RealCoordinates(3.5, 4.5);
        ghost.setPos(pos);
        assertEquals(pos, ghost.getPos());
    }

    @Test
    public void testSetDirection() {
        Ghost ghost = Ghost.INKY;
        Direction direction = Direction.WEST;
        ghost.setDirection(direction);
        assertEquals(direction, ghost.getDirection());
    }

    @Test
    public void testIsEyes() {
        Ghost ghost = Ghost.PINKY;
        assertFalse(ghost.isEyes());
        ghost.setEyes(true);
        assertTrue(ghost.isEyes());
    }
    public MazeConfig makeMaze(){
        IntCoordinates pacManPos = new IntCoordinates(1, 1);
        IntCoordinates blinkyPos = new IntCoordinates(0, 0);
        IntCoordinates pinkyPos = new IntCoordinates(2, 2);
        IntCoordinates inkyPos = new IntCoordinates(0, 2);
        IntCoordinates clydePos = new IntCoordinates(2, 0);
        IntCoordinates fruitPos = new IntCoordinates(0, 2);

        Cell[][] grid = new Cell[4][4];
        for (int i = 0; i < 4; i++) {
            grid[0][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[1][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[2][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[3][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
        }
        MazeConfig mazeConfig = new MazeConfig(grid, pacManPos, blinkyPos, pinkyPos, inkyPos, clydePos, fruitPos);
        return mazeConfig;
    }
    @Test
    public void testCanMove() {
        Ghost ghost = Ghost.CLYDE;
        MazeConfig mazeConfig = makeMaze();
        // Set up the maze configuration with walls
        mazeConfig.getCell(new IntCoordinates(2, 0)).setWestWall(true);
        mazeConfig.getCell(new IntCoordinates(2, 0)).setNorthWall(true);
        mazeConfig.getCell(new IntCoordinates(2, 0)).setEastWall(true);
        mazeConfig.getCell(new IntCoordinates(2, 0)).setSouthWall(true);

        assertTrue(ghost.canMove(Direction.WEST, mazeConfig));
        assertTrue(ghost.canMove(Direction.NORTH, mazeConfig));
        assertTrue(ghost.canMove(Direction.EAST, mazeConfig));
        assertTrue(ghost.canMove(Direction.SOUTH, mazeConfig));
    }

    @Test
    public void testTpToCenter() {
        Ghost ghost = Ghost.BLINKY;
        RealCoordinates pos = new RealCoordinates(2.5, 3.5);
        ghost.setPos(pos);
        ghost.tpToCenter();
        assertEquals(new RealCoordinates(2.5, 3.5), ghost.getPos());
    }

    @Test
    public void testIsCenteredDir() {
        Ghost ghost = Ghost.INKY;
        ghost.setPos(new RealCoordinates(1, 2.5));
        assertTrue(ghost.isCenteredDir(Direction.NORTH));
        assertFalse(ghost.isCenteredDir(Direction.EAST));
        
    }

    @Test
    public void testIsCentered() {
        Ghost ghost = Ghost.PINKY;
        ghost.setPos(new RealCoordinates(3.0, 4.0));
        assertTrue(ghost.isCentered());
        ghost.setPos(new RealCoordinates(3.5, 4.0));
        assertFalse(ghost.isCentered());
    }

    @Test
    public void testGetNextPos() {
        Ghost ghost = Ghost.CLYDE;
        MazeConfig mazeConfig = makeMaze();
        // Set up the maze configuration with walls
        mazeConfig.getCell(new IntCoordinates(0, 0)).setWestWall(true);
        mazeConfig.getCell(new IntCoordinates(0, 1)).setNorthWall(true);
        mazeConfig.getCell(new IntCoordinates(1, 0)).setEastWall(true);
        mazeConfig.getCell(new IntCoordinates(1, 1)).setSouthWall(true);

        ghost.setPos(new RealCoordinates(2.5, 3.5));
        RealCoordinates nextPos = ghost.getNextPos(1000000000, Direction.WEST, mazeConfig);
        assertEquals(new RealCoordinates(2.5, 3.5), nextPos);

        ghost.setPos(new RealCoordinates(2.5, 3.5));
        nextPos = ghost.getNextPos(1000000000, Direction.NORTH, mazeConfig);
        assertEquals(new RealCoordinates(2.5, 3.5), nextPos);

        ghost.setPos(new RealCoordinates(2.5, 3.5));
        nextPos = ghost.getNextPos(1000000000, Direction.EAST, mazeConfig);
        assertEquals(new RealCoordinates(2.5, 3.5), nextPos);

        ghost.setPos(new RealCoordinates(2.5, 3.5));
        nextPos = ghost.getNextPos(1000000000, Direction.SOUTH, mazeConfig);
        assertEquals(new RealCoordinates(2.5, 3.5), nextPos);
        assertFalse(ghost.isCentered());
        assertFalse(ghost.isEyes());
        assertFalse(ghost.isEatable());
    }

    @Test
    public void testGetNextDir() {
        Ghost ghost = Ghost.BLINKY;
        MazeConfig mazeConfig = makeMaze();
        // Set up the maze configuration with walls
        mazeConfig.getCell(new IntCoordinates(0, 0)).setWestWall(true);
        mazeConfig.getCell(new IntCoordinates(0, 1)).setNorthWall(true);
        mazeConfig.getCell(new IntCoordinates(1, 0)).setEastWall(true);
        mazeConfig.getCell(new IntCoordinates(1, 1)).setSouthWall(true);

        ghost.setPos(new RealCoordinates(2.0, 3.0));
        ghost.setDirection(Direction.WEST);
        Direction nextDir = Direction.WEST;
        assertEquals(Direction.WEST, nextDir);

        ghost.setPos(new RealCoordinates(2.0, 3.0));
        ghost.setDirection(Direction.NORTH);
        nextDir = Direction.NORTH;
        assertEquals(Direction.NORTH, nextDir);

        ghost.setPos(new RealCoordinates(2.0, 3.0));
        ghost.setDirection(Direction.EAST);
        nextDir = Direction.EAST;
        assertEquals(Direction.EAST, nextDir);

        ghost.setPos(new RealCoordinates(2.0, 3.0));
        ghost.setDirection(Direction.SOUTH);
        nextDir = Direction.SOUTH;
        assertEquals(Direction.SOUTH, nextDir);
    }

    @Test
    public void warpTest(){
        MazeConfig mazeConfig = makeMaze();
        Ghost ghost = Ghost.BLINKY;
        RealCoordinates pos = new RealCoordinates(-1, 1);
        ghost.warp(pos, 4, 4);
        var expect = new RealCoordinates(3, 1);
        assertEquals(expect, ghost.getPos());
        

    }
    
}