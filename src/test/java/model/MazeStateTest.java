import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.MazeState;
import config.MazeConfig;
import config.Cell;
import model.Direction;
import model.Direction.*;
import model.PacMan;
import geometry.*;
import static org.junit.jupiter.api.Assertions.*;

public class MazeStateTest {

    private MazeState mazeState;

    @BeforeEach
    public void setUp() {
        IntCoordinates pacManPos = new IntCoordinates(1, 1);
        IntCoordinates blinkyPos = new IntCoordinates(0, 0);
        IntCoordinates pinkyPos = new IntCoordinates(2, 2);
        IntCoordinates inkyPos = new IntCoordinates(1, 2);
        IntCoordinates clydePos = new IntCoordinates(2, 1);
        IntCoordinates fruitPos = new IntCoordinates(0, 2);
        Cell[][] grid = new Cell[5][5];
        for (int i = 0; i < 5; i++) {
            grid[0][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[1][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[2][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[3][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[4][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
        }

        MazeConfig config = new MazeConfig(grid, pacManPos, blinkyPos, pinkyPos, inkyPos, clydePos, fruitPos);
        mazeState = new MazeState(config);
        assertEquals(0, mazeState.getScore(), "Incorrect initial score");
        assertEquals(3, mazeState.getLives(), "Incorrect initial number of lives");
        assertEquals(1, mazeState.getLevel(), "Incorrect initial level");
        assertEquals(1, mazeState.getNiveauFruit(), "Incorrect initial fruit level");
    }

    @Test
    public void testGetCritters() {
        IntCoordinates pacManPos = new IntCoordinates(1, 1);
        IntCoordinates blinkyPos = new IntCoordinates(0, 0);
        IntCoordinates pinkyPos = new IntCoordinates(2, 2);
        IntCoordinates inkyPos = new IntCoordinates(1, 2);
        IntCoordinates clydePos = new IntCoordinates(2, 1);
        IntCoordinates fruitPos = new IntCoordinates(0, 2);
        Cell[][] grid = new Cell[5][5];
        for (int i = 0; i < 5; i++) {
            grid[0][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[1][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[2][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[3][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[4][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
        }

        MazeConfig config = new MazeConfig(grid, pacManPos, blinkyPos, pinkyPos, inkyPos, clydePos, fruitPos);
        mazeState = new MazeState(config);
        

        assertEquals(1, mazeState.getCritters().get(0).getPos().getX(), "Incorrect initial position of PacMan");
        assertEquals(1, mazeState.getCritters().get(0).getPos().getY(), "Incorrect initial position of PacMan");
        assertEquals(0, mazeState.getCritters().get(0).getDirection().toIntCoordinates().getX(), "Incorrect initial direction of PacMan");
        assertEquals(0, mazeState.getCritters().get(0).getDirection().toIntCoordinates().getY(), "Incorrect initial direction of PacMan");

        assertEquals(0, mazeState.getCritters().get(2).getPos().getX(), "Incorrect initial position of Blinky");
        assertEquals(0, mazeState.getCritters().get(2).getPos().getY(), "Incorrect initial position of Blinky");
        assertEquals(0, mazeState.getCritters().get(2).getDirection().toIntCoordinates().getX(), "Incorrect initial direction of Blinky");
        assertEquals(0, mazeState.getCritters().get(2).getDirection().toIntCoordinates().getY(), "Incorrect initial direction of Blinky");
        
        assertEquals(2, mazeState.getCritters().get(4).getPos().getX(), "Incorrect initial position of Pinky");
        assertEquals(2, mazeState.getCritters().get(4).getPos().getY(), "Incorrect initial position of Pinky");
        assertEquals(0, mazeState.getCritters().get(4).getDirection().toIntCoordinates().getX(), "Incorrect initial direction of Pinky");
        assertEquals(0, mazeState.getCritters().get(4).getDirection().toIntCoordinates().getY(), "Incorrect initial direction of Pinky");

        assertEquals(1, mazeState.getCritters().get(3).getPos().getX(), "Incorrect initial position of Inky");
        assertEquals(2, mazeState.getCritters().get(3).getPos().getY(), "Incorrect initial position of Inky");
        assertEquals(0, mazeState.getCritters().get(3).getDirection().toIntCoordinates().getX(), "Incorrect initial direction of Inky");
        assertEquals(0, mazeState.getCritters().get(3).getDirection().toIntCoordinates().getY(), "Incorrect initial direction of Inky");

        assertEquals(2, mazeState.getCritters().get(1).getPos().getX(), "Incorrect initial position of Clyde");
        assertEquals(1, mazeState.getCritters().get(1).getPos().getY(), "Incorrect initial position of Clyde");
        assertEquals(0, mazeState.getCritters().get(1).getDirection().toIntCoordinates().getX(), "Incorrect initial direction of Clyde");
        assertEquals(0, mazeState.getCritters().get(1).getDirection().toIntCoordinates().getY(), "Incorrect initial direction of Clyde");

        
    }

    // @Test
    // public void testSetConfig() {
    //     IntCoordinates pacManPos = new IntCoordinates(1, 1);
    //     IntCoordinates blinkyPos = new IntCoordinates(0, 0);
    //     IntCoordinates pinkyPos = new IntCoordinates(2, 2);
    //     IntCoordinates inkyPos = new IntCoordinates(1, 2);
    //     IntCoordinates clydePos = new IntCoordinates(2, 1);
    //     IntCoordinates fruitPos = new IntCoordinates(0, 2);
    //     Cell[][] grid = new Cell[5][5];
    //     for (int i = 0; i < 5; i++) {
    //         grid[0][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
    //         grid[1][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
    //         grid[2][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
    //         grid[3][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
    //         grid[4][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
    //     }

    //     MazeConfig config = new MazeConfig(grid, pacManPos, blinkyPos, pinkyPos, inkyPos, clydePos, fruitPos);
    //     mazeState = new MazeState(config);

    //     IntCoordinates pacManPos2 = new IntCoordinates(3, 3);
    //     IntCoordinates blinkyPos2 = new IntCoordinates(2, 2);
    //     IntCoordinates pinkyPos2 = new IntCoordinates(1, 3);
    //     IntCoordinates inkyPos2 = new IntCoordinates(1, 4);
    //     IntCoordinates clydePos2 = new IntCoordinates(3, 1);
    //     IntCoordinates fruitPos2 = new IntCoordinates(4, 4);
    //     Cell[][] grid2 = new Cell[5][5];
    //     for (int i = 0; i < 5; i++) {
    //         grid2[0][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
    //         grid2[1][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
    //         grid2[2][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
    //         grid2[3][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
    //         grid2[4][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
    //     }

    //     MazeConfig config2 = new MazeConfig(grid2, pacManPos2, blinkyPos2, pinkyPos2, inkyPos2, clydePos2, fruitPos2);
    //     mazeState.setConfig(config2);

    //     //we want to assert that the config has been changed
    //     //we can do that by checking if the critters are in the right position

    //     assertEquals(2, mazeState.getCritters().get(2).getPos().getX(), "Incorrect initial position of Blinky");
    //     assertEquals(2, mazeState.getCritters().get(2).getPos().getY(), "Incorrect initial position of Blinky");
        
    //     assertEquals(1, mazeState.getCritters().get(4).getPos().getX(), "Incorrect initial position of Pinky");
    //     assertEquals(3, mazeState.getCritters().get(4).getPos().getY(), "Incorrect initial position of Pinky");

    //     assertEquals(1, mazeState.getCritters().get(3).getPos().getX(), "Incorrect initial position of Inky");
    //     assertEquals(4, mazeState.getCritters().get(3).getPos().getY(), "Incorrect initial position of Inky");

    //     assertEquals(3, mazeState.getCritters().get(1).getPos().getX(), "Incorrect initial position of Clyde");
    //     assertEquals(1, mazeState.getCritters().get(1).getPos().getY(), "Incorrect initial position of Clyde");

    //     assertEquals(3, mazeState.getCritters().get(0).getPos().getX(), "Incorrect initial position of PacMan");
    //     assertEquals(3, mazeState.getCritters().get(0).getPos().getY(), "Incorrect initial position of PacMan");

    // }
    @Test
    public void testGetFruit() {
        
        
    }

    @Test
    public void testGetNiveauFruit() {
        IntCoordinates pacManPos = new IntCoordinates(1, 1);
        IntCoordinates blinkyPos = new IntCoordinates(0, 0);
        IntCoordinates pinkyPos = new IntCoordinates(2, 2);
        IntCoordinates inkyPos = new IntCoordinates(1, 2);
        IntCoordinates clydePos = new IntCoordinates(2, 1);
        IntCoordinates fruitPos = new IntCoordinates(0, 2);
        Cell[][] grid = new Cell[5][5];
        for (int i = 0; i < 5; i++) {
            grid[0][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[1][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[2][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[3][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[4][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
        }

        MazeConfig config = new MazeConfig(grid, pacManPos, blinkyPos, pinkyPos, inkyPos, clydePos, fruitPos);
        mazeState = new MazeState(config);

        assertEquals(1, mazeState.getNiveauFruit(), "Incorrect initial fruit level");
    }

    @Test
    public void testSetNiveauFruit() {
        IntCoordinates pacManPos = new IntCoordinates(1, 1);
        IntCoordinates blinkyPos = new IntCoordinates(0, 0);
        IntCoordinates pinkyPos = new IntCoordinates(2, 2);
        IntCoordinates inkyPos = new IntCoordinates(1, 2);
        IntCoordinates clydePos = new IntCoordinates(2, 1);
        IntCoordinates fruitPos = new IntCoordinates(0, 2);
        Cell[][] grid = new Cell[5][5];
        for (int i = 0; i < 5; i++) {
            grid[0][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[1][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[2][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[3][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[4][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
        }

        MazeConfig config = new MazeConfig(grid, pacManPos, blinkyPos, pinkyPos, inkyPos, clydePos, fruitPos);
        mazeState = new MazeState(config);
        
        assertEquals(1, mazeState.getNiveauFruit(), "Incorrect initial fruit level");

        mazeState.setNiveauFruit(2);
        assertEquals(2, mazeState.getNiveauFruit(), "Incorrect initial fruit level");
    }

    @Test
    public void testGetWidth() {
        IntCoordinates pacManPos = new IntCoordinates(1, 1);
        IntCoordinates blinkyPos = new IntCoordinates(0, 0);
        IntCoordinates pinkyPos = new IntCoordinates(2, 2);
        IntCoordinates inkyPos = new IntCoordinates(1, 2);
        IntCoordinates clydePos = new IntCoordinates(2, 1);
        IntCoordinates fruitPos = new IntCoordinates(0, 2);
        Cell[][] grid = new Cell[5][6];
        for (int i = 0; i < 5; i++) {
            for(int j = 0; j < 6; j++){
                grid[i][j] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            }
        }

        MazeConfig config = new MazeConfig(grid, pacManPos, blinkyPos, pinkyPos, inkyPos, clydePos, fruitPos);
        mazeState = new MazeState(config);

        assertEquals(6, mazeState.getWidth(), "Incorrect initial width");
    }

    @Test
    public void testGetHeight() {
        IntCoordinates pacManPos = new IntCoordinates(1, 1);
        IntCoordinates blinkyPos = new IntCoordinates(0, 0);
        IntCoordinates pinkyPos = new IntCoordinates(2, 2);
        IntCoordinates inkyPos = new IntCoordinates(1, 2);
        IntCoordinates clydePos = new IntCoordinates(2, 1);
        IntCoordinates fruitPos = new IntCoordinates(0, 2);
        Cell[][] grid = new Cell[5][6];
        for (int i = 0; i < 5; i++) {
            for(int j = 0; j < 6; j++){
                grid[i][j] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            }
        }

        MazeConfig config = new MazeConfig(grid, pacManPos, blinkyPos, pinkyPos, inkyPos, clydePos, fruitPos);
        mazeState = new MazeState(config);

        assertEquals(5, mazeState.getHeight(), "Incorrect initial height");
    }

    @Test
    public void testGetConfig() {
        MazeConfig config = createMazeConfig();
        mazeState = new MazeState(config);

        assertEquals(config, mazeState.getConfig(), "Incorrect initial config");
    }

    @Test
    public void testGetGridState() {
        MazeConfig config = createMazeConfig();
        mazeState = new MazeState(config);
        boolean[][]gridState=new boolean[5][6];
        assertEquals(false, mazeState.getGridState(new IntCoordinates(0,0)), "Incorrect initial grid state");
    }

    @Test
    public void testGetLevel() {
        MazeConfig config = createMazeConfig();
        mazeState = new MazeState(config);

        assertEquals(1, mazeState.getLevel(), "Incorrect initial level");
    }

    @Test
    public void testSetLevel() {
        MazeConfig config = createMazeConfig();
        mazeState = new MazeState(config);

        assertEquals(1, mazeState.getLevel(), "Incorrect initial level");
        mazeState.setLevel(2);
        assertEquals(2, mazeState.getLevel(), "Incorrect initial level");
    }

    @Test
    public void testGetScore() {
        MazeConfig config = createMazeConfig();
        mazeState = new MazeState(config);

        assertEquals(0, mazeState.getScore(), "Incorrect initial score");

        mazeState.setScore(10);
        assertEquals(10, mazeState.getScore(), "Incorrect initial score");
            
    }


    @Test
    public void testSetLives() {
        MazeConfig config = createMazeConfig();
        mazeState = new MazeState(config);

        assertEquals(3, mazeState.getLives(), "Incorrect initial number of lives");
        mazeState.setLives(2);
        assertEquals(2, mazeState.getLives(), "Incorrect initial number of lives");

    }


    @Test
    public void testPacMovement() {
        MazeConfig config = createMazeConfig();
        MazeState mazeState = new MazeState(config);

        // Test moving in the same direction
        Direction nextDir = Direction.EAST;
        long deltaTns = 300000000;
        mazeState.pacMovement(nextDir, deltaTns);
        assertEquals(new RealCoordinates(1.9,1), PacMan.INSTANCE.getPos(), "Incorrect position after moving right");
    }

    private MazeConfig createMazeConfig() {
        IntCoordinates pacManPos = new IntCoordinates(1, 1);
        IntCoordinates blinkyPos = new IntCoordinates(0, 0);
        IntCoordinates pinkyPos = new IntCoordinates(2, 2);
        IntCoordinates inkyPos = new IntCoordinates(1, 2);
        IntCoordinates clydePos = new IntCoordinates(2, 1);
        IntCoordinates fruitPos = new IntCoordinates(0, 2);
        Cell[][] grid = new Cell[5][5];
        for (int i = 0; i < 5; i++) {
            grid[0][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[1][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[2][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[3][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
            grid[4][i] = new Cell(false, false, false, false, false, false, Cell.Content.NOTHING);
        }

    return new MazeConfig(grid, pacManPos, blinkyPos, pinkyPos, inkyPos, clydePos, fruitPos);
}

    @Test
    public void testUpdate() {
        // Test the update() method
        // Add your assertions here
    }

    @Test
    public void testPlayerLost() {
        // Test the playerLost() method
        // Add your assertions here
    }

    @Test
    public void testAddScore() {
        // Test the addScore() method
        // Add your assertions here
    }

    @Test
    public void testAddLives() {
        // Test the addLives() method
        // Add your assertions here
    }

    @Test
    public void testResetCritter() {
        // Test the resetCritter() method
        // Add your assertions here
    }

    @Test
    public void testResetCritters() {
        // Test the resetCritters() method
        // Add your assertions here
    }

    @Test
    public void testResetGrid() {
        // Test the resetGrid() method
        // Add your assertions here
    }
}