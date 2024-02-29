import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import config.*;
import geometry.*;
import model.*;

public class CritterTest {

    @Test
    public void testGetNextPos() {
        Critter critter = new Critter() {
            @Override
            public RealCoordinates getPos() {
                return new RealCoordinates(0, 0);
            }

            @Override
            public Direction getDirection() {
                return Direction.EAST;
            }

            @Override
            public double getSpeed() {
                return 1.0;
            }

            @Override
            public boolean isEatable() {
                return false;
            }

            @Override
            public boolean isEyes() {
                return false;
            }

            @Override
            public void setEyes(boolean eyes) {
                // do nothing
            }

            @Override
            public void setEnergized(boolean energized) {
                // do nothing
            }

            @Override
            public void setPos(RealCoordinates realCoordinates) {
                // do nothing
            }

            @Override
            public void setDirection(Direction direction) {
                // do nothing
            }

            @Override
            public void setEatable(boolean eatable) {
                // do nothing
            }

            @Override
            public RealCoordinates currCellR() {
                return new RealCoordinates(0, 0);
            }

            @Override
            public IntCoordinates currCellI() {
                return new IntCoordinates(0, 0);
            }

            @Override
            public boolean isGoingToCenter() {
                return false;
            }

            @Override
            public void tpToCenter() {
                // do nothing
            }

            @Override
            public boolean isCenteredDir(Direction dir) {
                return false;
            }

            @Override
            public boolean isCentered() {
                return false;
            }

            @Override
            public RealCoordinates getNextPos(long deltaTns, Direction dir, MazeConfig config) {
                switch (dir) {
                    case EAST:
                        return new RealCoordinates(1, 0);
                    case WEST:
                        return new RealCoordinates(-1, 0);
                    case NORTH:
                        return new RealCoordinates(0, -1);
                    case SOUTH:
                        return new RealCoordinates(0, 1);
                    default:
                        return new RealCoordinates(0, 0);
                }
            }
        };
        IntCoordinates pacManPos = new IntCoordinates(0, 0);
        IntCoordinates blinkyPos = new IntCoordinates(0, 0);
        IntCoordinates pinkyPos = new IntCoordinates(0, 0);
        IntCoordinates inkyPos = new IntCoordinates(0, 0);
        IntCoordinates clydePos = new IntCoordinates(0, 0);

        Cell[][] grid = new Cell[1][1];
        MazeConfig mazeConfig = new MazeConfig(grid, pacManPos, blinkyPos, pinkyPos, inkyPos, clydePos, pacManPos);

        RealCoordinates nextPosEast = critter.getNextPos(1, Direction.EAST, mazeConfig);
        RealCoordinates nextPosWest = critter.getNextPos(1, Direction.WEST, mazeConfig);
        RealCoordinates nextPosNorth = critter.getNextPos(1, Direction.NORTH, mazeConfig);
        RealCoordinates nextPosSouth = critter.getNextPos(1, Direction.SOUTH, mazeConfig);
        RealCoordinates nextPosNone = critter.getNextPos(1, Direction.NONE, mazeConfig);

        assertEquals(new RealCoordinates(1, 0), nextPosEast);
        assertEquals(new RealCoordinates(-1, 0), nextPosWest);
        assertEquals(new RealCoordinates(0, -1), nextPosNorth);
        assertEquals(new RealCoordinates(0, 1), nextPosSouth);
        assertEquals(new RealCoordinates(0, 0), nextPosNone);
    }
    @Test
    public void testNextPosNone() {
        Critter critter = new Critter() {
            @Override
            public RealCoordinates getPos() {
                return new RealCoordinates(1, 1);
            }

            @Override
            public Direction getDirection() {
                return Direction.NONE;
            }

            @Override
            public double getSpeed() {
                return 1.0;
            }

            @Override
            public boolean isEatable() {
                return false;
            }

            @Override
            public boolean isEyes() {
                return false;
            }

            @Override
            public void setEyes(boolean eyes) {
                // do nothing
            }

            @Override
            public void setEnergized(boolean energized) {
                // do nothing
            }

            @Override
            public void setPos(RealCoordinates realCoordinates) {
                // do nothing
            }

            @Override
            public void setDirection(Direction direction) {
                // do nothing
            }

            @Override
            public void setEatable(boolean eatable) {
                // do nothing
            }

            @Override
            public RealCoordinates currCellR() {
                return new RealCoordinates(1, 1);
            }

            @Override
            public IntCoordinates currCellI() {
                return new IntCoordinates(1, 1);
            }

            @Override
            public boolean isGoingToCenter() {
                return false;
            }

            @Override
            public void tpToCenter() {
                // do nothing
            }

            @Override
            public boolean isCenteredDir(Direction dir) {
                return false;
            }

            @Override
            public boolean isCentered() {
                return false;
            }

            @Override
            public RealCoordinates getNextPos(long deltaTns, Direction dir, MazeConfig config) {
                switch (dir) {
                    case EAST:
                        return new RealCoordinates(1, 0);
                    case WEST:
                        return new RealCoordinates(-1, 0);
                    case NORTH:
                        return new RealCoordinates(0, -1);
                    case SOUTH:
                        return new RealCoordinates(0, 1);
                    default:
                        return new RealCoordinates(0, 0);
                }
            }
        };
        //set the deltaTns to 1 second
        long deltaTns = 1000000000;
        critter.setDirection(Direction.NONE);
        assertEquals(critter.nextPos(deltaTns), new RealCoordinates(1, 1),"None not working");
    }
    @Test
    public void testNextPosEast() {
        Critter critter = new Critter() {
            @Override
            public RealCoordinates getPos() {
                return new RealCoordinates(1, 1);
            }

            @Override
            public Direction getDirection() {
                return Direction.EAST;
            }

            @Override
            public double getSpeed() {
                return 1.0;
            }

            @Override
            public boolean isEatable() {
                return false;
            }

            @Override
            public boolean isEyes() {
                return false;
            }

            @Override
            public void setEyes(boolean eyes) {
                // do nothing
            }

            @Override
            public void setEnergized(boolean energized) {
                // do nothing
            }

            @Override
            public void setPos(RealCoordinates realCoordinates) {
                // do nothing
            }

            @Override
            public void setDirection(Direction direction) {
                // do nothing
            }

            @Override
            public void setEatable(boolean eatable) {
                // do nothing
            }

            @Override
            public RealCoordinates currCellR() {
                return new RealCoordinates(1, 1);
            }

            @Override
            public IntCoordinates currCellI() {
                return new IntCoordinates(1, 1);
            }

            @Override
            public boolean isGoingToCenter() {
                return false;
            }

            @Override
            public void tpToCenter() {
                // do nothing
            }

            @Override
            public boolean isCenteredDir(Direction dir) {
                return false;
            }

            @Override
            public boolean isCentered() {
                return false;
            }

            @Override
            public RealCoordinates getNextPos(long deltaTns, Direction dir, MazeConfig config) {
                switch (dir) {
                    case EAST:
                        return new RealCoordinates(1, 0);
                    case WEST:
                        return new RealCoordinates(-1, 0);
                    case NORTH:
                        return new RealCoordinates(0, -1);
                    case SOUTH:
                        return new RealCoordinates(0, 1);
                    default:
                        return new RealCoordinates(0, 0);
                }
            }
        };
        //set the deltaTns to 1 second
         //set the deltaTns to 1 second
         long deltaTns = 1000000000;
         critter.setDirection(Direction.EAST);
         assertEquals(critter.nextPos(deltaTns), new RealCoordinates(2, 1),"East not working");
    }

    @Test
    public void testNextPosWest() {
        Critter critter = new Critter() {
            @Override
            public RealCoordinates getPos() {
                return new RealCoordinates(1, 1);
            }

            @Override
            public Direction getDirection() {
                return Direction.WEST;
            }

            @Override
            public double getSpeed() {
                return 1.0;
            }

            @Override
            public boolean isEatable() {
                return false;
            }

            @Override
            public boolean isEyes() {
                return false;
            }

            @Override
            public void setEyes(boolean eyes) {
                // do nothing
            }

            @Override
            public void setEnergized(boolean energized) {
                // do nothing
            }

            @Override
            public void setPos(RealCoordinates realCoordinates) {
                // do nothing
            }

            @Override
            public void setDirection(Direction direction) {
                // do nothing
            }

            @Override
            public void setEatable(boolean eatable) {
                // do nothing
            }

            @Override
            public RealCoordinates currCellR() {
                return new RealCoordinates(1, 1);
            }

            @Override
            public IntCoordinates currCellI() {
                return new IntCoordinates(1, 1);
            }

            @Override
            public boolean isGoingToCenter() {
                return false;
            }

            @Override
            public void tpToCenter() {
                // do nothing
            }

            @Override
            public boolean isCenteredDir(Direction dir) {
                return false;
            }

            @Override
            public boolean isCentered() {
                return false;
            }

            @Override
            public RealCoordinates getNextPos(long deltaTns, Direction dir, MazeConfig config) {
                switch (dir) {
                    case EAST:
                        return new RealCoordinates(1, 0);
                    case WEST:
                        return new RealCoordinates(-1, 0);
                    case NORTH:
                        return new RealCoordinates(0, -1);
                    case SOUTH:
                        return new RealCoordinates(0, 1);
                    default:
                        return new RealCoordinates(0, 0);
                }
            }
        };
        //set the deltaTns to 1 second
         //set the deltaTns to 1 second
         long deltaTns = 1000000000;
         critter.setDirection(Direction.WEST);
         assertEquals(critter.nextPos(deltaTns), new RealCoordinates(0, 1),"West not working");
    }
    @Test
    public void testNextPosNorth() {
        Critter critter = new Critter() {
            @Override
            public RealCoordinates getPos() {
                return new RealCoordinates(1, 1);
            }

            @Override
            public Direction getDirection() {
                return Direction.NORTH;
            }

            @Override
            public double getSpeed() {
                return 1.0;
            }

            @Override
            public boolean isEatable() {
                return false;
            }

            @Override
            public boolean isEyes() {
                return false;
            }

            @Override
            public void setEyes(boolean eyes) {
                // do nothing
            }

            @Override
            public void setEnergized(boolean energized) {
                // do nothing
            }

            @Override
            public void setPos(RealCoordinates realCoordinates) {
                // do nothing
            }

            @Override
            public void setDirection(Direction direction) {
                // do nothing
            }

            @Override
            public void setEatable(boolean eatable) {
                // do nothing
            }

            @Override
            public RealCoordinates currCellR() {
                return new RealCoordinates(1, 1);
            }

            @Override
            public IntCoordinates currCellI() {
                return new IntCoordinates(1, 1);
            }

            @Override
            public boolean isGoingToCenter() {
                return false;
            }

            @Override
            public void tpToCenter() {
                // do nothing
            }

            @Override
            public boolean isCenteredDir(Direction dir) {
                return false;
            }

            @Override
            public boolean isCentered() {
                return false;
            }

            @Override
            public RealCoordinates getNextPos(long deltaTns, Direction dir, MazeConfig config) {
                switch (dir) {
                    case EAST:
                        return new RealCoordinates(1, 0);
                    case WEST:
                        return new RealCoordinates(-1, 0);
                    case NORTH:
                        return new RealCoordinates(0, -1);
                    case SOUTH:
                        return new RealCoordinates(0, 1);
                    default:
                        return new RealCoordinates(0, 0);
                }
            }
        };
        //set the deltaTns to 1 second
         //set the deltaTns to 1 second
         long deltaTns = 1000000000;
         critter.setDirection(Direction.NORTH);
         assertEquals(critter.nextPos(deltaTns), new RealCoordinates(1, 0),"North not working");
    }
    @Test
    public void testNextPosSouth() {
        Critter critter = new Critter() {
            @Override
            public RealCoordinates getPos() {
                return new RealCoordinates(1, 1);
            }

            @Override
            public Direction getDirection() {
                return Direction.SOUTH;
            }

            @Override
            public double getSpeed() {
                return 1.0;
            }

            @Override
            public boolean isEatable() {
                return false;
            }

            @Override
            public boolean isEyes() {
                return false;
            }

            @Override
            public void setEyes(boolean eyes) {
                // do nothing
            }

            @Override
            public void setEnergized(boolean energized) {
                // do nothing
            }

            @Override
            public void setPos(RealCoordinates realCoordinates) {
                // do nothing
            }

            @Override
            public void setDirection(Direction direction) {
                // do nothing
            }

            @Override
            public void setEatable(boolean eatable) {
                // do nothing
            }

            @Override
            public RealCoordinates currCellR() {
                return new RealCoordinates(1, 1);
            }

            @Override
            public IntCoordinates currCellI() {
                return new IntCoordinates(1, 1);
            }

            @Override
            public boolean isGoingToCenter() {
                return false;
            }

            @Override
            public void tpToCenter() {
                // do nothing
            }

            @Override
            public boolean isCenteredDir(Direction dir) {
                return false;
            }

            @Override
            public boolean isCentered() {
                return false;
            }

            @Override
            public RealCoordinates getNextPos(long deltaTns, Direction dir, MazeConfig config) {
                switch (dir) {
                    case EAST:
                        return new RealCoordinates(1, 0);
                    case WEST:
                        return new RealCoordinates(-1, 0);
                    case NORTH:
                        return new RealCoordinates(0, -1);
                    case SOUTH:
                        return new RealCoordinates(0, 1);
                    default:
                        return new RealCoordinates(0, 0);
                }
            }
        };
        //set the deltaTns to 1 second
         //set the deltaTns to 1 second
         long deltaTns = 1000000000;
         critter.setDirection(Direction.SOUTH);
         assertEquals(critter.nextPos(deltaTns), new RealCoordinates(1, 2),"South not working");
    }

    
    @Test
    public void testIsCentered() {
        Critter critter = new Critter() {
            @Override
            public RealCoordinates getPos() {
                return new RealCoordinates(0.5, 0.5);
            }

            @Override
            public Direction getDirection() {
                return Direction.NONE;
            }

            @Override
            public double getSpeed() {
                return 1.0;
            }

            @Override
            public boolean isEatable() {
                return false;
            }

            @Override
            public boolean isEyes() {
                return false;
            }

            @Override
            public void setEyes(boolean eyes) {
                // do nothing
            }

            @Override
            public void setEnergized(boolean energized) {
                // do nothing
            }

            @Override
            public void setPos(RealCoordinates realCoordinates) {
                // do nothing
            }

            @Override
            public void setDirection(Direction direction) {
                // do nothing
            }

            @Override
            public void setEatable(boolean eatable) {
                // do nothing
            }

            @Override
            public RealCoordinates currCellR() {
                return new RealCoordinates(0, 0);
            }

            @Override
            public IntCoordinates currCellI() {
                return new IntCoordinates(0, 0);
            }

            @Override
            public boolean isGoingToCenter() {
                return false;
            }

            @Override
            public void tpToCenter() {
                // do nothing
            }

            @Override
            public boolean isCenteredDir(Direction dir) {
                return false;
            }

            @Override
            public boolean isCentered() {
                return true;
            }

            @Override
            public RealCoordinates getNextPos(long deltaTns, Direction dir, MazeConfig config) {
                return new RealCoordinates(0.5, 0.5);
            }
        };

        boolean isCentered = critter.isCentered();

        assertTrue(isCentered);
    }
    @Test
    public void testCreateCritter() {
        Critter critter = new Critter() {
            @Override
            public RealCoordinates getPos() {
                return new RealCoordinates(0, 0);
            }

            @Override
            public Direction getDirection() {
                return Direction.EAST;
            }

            @Override
            public double getSpeed() {
                return 1.0;
            }

            @Override
            public boolean isEatable() {
                return false;
            }

            @Override
            public boolean isEyes() {
                return false;
            }

            @Override
            public void setEyes(boolean eyes) {
                // do nothing
            }

            @Override
            public void setEnergized(boolean energized) {
                // do nothing
            }

            @Override
            public void setPos(RealCoordinates realCoordinates) {
                // do nothing
            }

            @Override
            public void setDirection(Direction direction) {
                // do nothing
            }

            @Override
            public void setEatable(boolean eatable) {
                // do nothing
            }

            @Override
            public RealCoordinates currCellR() {
                return new RealCoordinates(0, 0);
            }

            @Override
            public IntCoordinates currCellI() {
                return new IntCoordinates(0, 0);
            }

            @Override
            public boolean isGoingToCenter() {
                return false;
            }

            @Override
            public void tpToCenter() {
                // do nothing
            }

            @Override
            public boolean isCenteredDir(Direction dir) {
                return false;
            }

            @Override
            public boolean isCentered() {
                return false;
            }

            @Override
            public RealCoordinates getNextPos(long deltaTns, Direction dir, MazeConfig config) {
                return new RealCoordinates(1, 0);
            }
        };

        assertNotNull(critter);
        assertEquals(new RealCoordinates(0, 0), critter.getPos());
        assertEquals(Direction.EAST, critter.getDirection());
        assertEquals(1.0, critter.getSpeed());
        assertFalse(critter.isEatable());
        assertFalse(critter.isEyes());
        assertFalse(critter.isCentered());
    }
    @Test
    public void testNextPos() {
        Critter critter = new Critter() {
            @Override
            public RealCoordinates getPos() {
                return new RealCoordinates(0, 0);
            }

            @Override
            public Direction getDirection() {
                return Direction.EAST;
            }

            @Override
            public double getSpeed() {
                return 1.0;
            }

            @Override
            public boolean isEatable() {
                return false;
            }

            @Override
            public boolean isEyes() {
                return false;
            }

            @Override
            public void setEyes(boolean eyes) {
                // do nothing
            }

            @Override
            public void setEnergized(boolean energized) {
                // do nothing
            }

            @Override
            public void setPos(RealCoordinates realCoordinates) {
                // do nothing
            }

            @Override
            public void setDirection(Direction direction) {
                // do nothing
            }

            @Override
            public void setEatable(boolean eatable) {
                // do nothing
            }

            @Override
            public RealCoordinates currCellR() {
                return new RealCoordinates(0, 0);
            }

            @Override
            public IntCoordinates currCellI() {
                return new IntCoordinates(0, 0);
            }

            @Override
            public boolean isGoingToCenter() {
                return false;
            }

            @Override
            public void tpToCenter() {
                // do nothing
            }

            @Override
            public boolean isCenteredDir(Direction dir) {
                return false;
            }

            @Override
            public boolean isCentered() {
                return false;
            }
            @Override
            public RealCoordinates getNextPos(long deltaTns, Direction dir, MazeConfig config) {
                return new RealCoordinates(0, 0);
            }
        };

        // Set up the test data
        long deltaTns = 1000000000; // 1 second

        // Calculate the expected next position
        RealCoordinates expectedNextPos = critter.getPos().plus(RealCoordinates.EAST_UNIT.times(critter.getSpeed() * deltaTns * 1E-9));

        // Call the method under test
        RealCoordinates actualNextPos = critter.nextPos(deltaTns);

        // Assert the result
        assertEquals(expectedNextPos, actualNextPos, "Next position calculation is incorrect");
    }
}