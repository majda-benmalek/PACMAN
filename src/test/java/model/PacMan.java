package model;

import config.MazeConfig;
import geometry.IntCoordinates;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import config.Cell;
import geometry.RealCoordinates;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Timer;
import java.util.TimerTask;
import model.ghost.Ghost;

/**
 * Implémente PacMan comme un singleton.
 * fonctionnalités :
 * 1. Gestion du temps d'énergie : un timer qui se décrémente à chaque tick
 * et qui désactive l'état énergisé quand il atteint 0.
 * (voir https://stackoverflow.com/questions/4044726/how-to-set-a-timer-in-java)
 *
 */
/**
 * The PacMan class represents the Pac-Man character in the game.
 * It implements the Critter interface.
 */
public final class PacMan implements Critter {

    private RealCoordinates pos;
    private Direction direction = Direction.NONE;
    private Direction nextDir = Direction.NONE;
    private boolean energized;
    static final double TPINTERVAL = 0.08;
    private Timer timer;
    private int dureEnergized = 10000;// seconds in milliseconds

    private PacMan() {
    }

    public static final PacMan INSTANCE = new PacMan();

    // Getters/Setters

    /**
     * Gets the current position of Pac-Man.
     *
     * @return The current position of Pac-Man.
     */
    public RealCoordinates getPos() {
        return this.pos;
    }

    /**
     * Gets the speed of Pac-Man.
     *
     * @return The speed of Pac-Man.
     */
    @Override
    public double getSpeed() {
        return isEnergized() ? 3.5 : 3.;
    }

    /**
     * Gets the current direction of Pac-Man.
     *
     * @return The current direction of Pac-Man.
     */
    @Override
    public Direction getDirection() {
        return direction;
    }

    /**
     * Gets the timer used for Pac-Man's energized state.
     *
     * @return The timer used for Pac-Man's energized state.
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * Cancels the timer used for Pac-Man's energized state.
     */
    public void cancelTimer() {
        if (this.timer != null)
            this.timer.cancel();
    }

    /**
     * Sets the position of Pac-Man.
     *
     * @param pos The new position of Pac-Man.
     */
    @Override
    public void setPos(RealCoordinates pos) {
        this.pos = pos;
    }

    /**
     * Sets the direction of Pac-Man.
     *
     * @param direction The new direction of Pac-Man.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Sets Pac-Man's energized state.
     * If Pac-Man is already energized, it cancels the current energized state and starts a new one.
     * If Pac-Man is not energized, it starts a new energized state.
     */
    public void setEnergized() {
        PacMan pacman = this;
        if (pacman.energized) {
            pacman.cancelTimer();
            Ghost.setAllEnergizedWithValue(false);
            Ghost.setAllEatableWithValue(false);
            Ghost.setAllEyesWithValue(false);
        }
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                pacman.energized = false;
                Ghost.setAllEnergizedWithValue(false);
                Ghost.setAllEatableWithValue(false);
            }
        };
        pacman.energized = true;
        Ghost.setAllEnergizedWithValue(true);
        Ghost.setAllEatableWithValue(true);
        timer.schedule(task, dureEnergized);
        this.timer = timer;
    }

    /**
     * Sets Pac-Man's energized state.
     *
     * @param energized The new energized state of Pac-Man.
     */
    public void setEnergized(boolean energized) {
        this.energized = energized;
    }

    /**
     * Checks if Pac-Man is currently energized.
     *
     * @return true if Pac-Man is energized, false otherwise.
     */
    public boolean isEnergized() {
        // TODO handle timeout!
        return energized;
    }

    // Methods

    /**
     * Gets the current cell of Pac-Man as RealCoordinates.
     *
     * @return The current cell of Pac-Man as RealCoordinates.
     */
    public RealCoordinates currCellR() {
        return new RealCoordinates(Math.round((float) this.getPos().x()), Math.round((float) this.getPos().y()));
    }

    /**
     * Gets the current cell of Pac-Man as IntCoordinates.
     *
     * @return The current cell of Pac-Man as IntCoordinates.
     */
    public IntCoordinates currCellI() {
        return new IntCoordinates(Math.round((float) this.pos.x()), Math.round((float) this.pos.y()));
    }

    /**
     * Checks if Pac-Man is going towards the center of the current cell.
     *
     * @return true if Pac-Man is going towards the center, false otherwise.
     */
    public boolean isGoingToCenter() {
        RealCoordinates center = this.currCellR();
        switch (this.direction) {
            case NORTH -> {
                return this.pos.y() > center.y();
            }
            case SOUTH -> {
                return this.pos.y() < center.y();
            }
            case EAST -> {
                return this.pos.x() < center.x();
            }
            case WEST -> {
                return this.pos.x() > center.x();
            }
            default -> {
                return true;
            }
        }
    }

    /**
     * Teleports Pac-Man to the center of the current cell if it is close enough.
     */
    @Override
    public void tpToCenter() {
        RealCoordinates currCell = this.currCellR();
        if (this.isGoingToCenter() && this.getPos().dist(currCell) < TPINTERVAL) {
            this.setPos(currCell);
        }
    }

    /**
     * Checks if Pac-Man is centered in a given direction.
     *
     * @param dir The direction to check.
     * @return true if Pac-Man is centered in the given direction, false otherwise.
     */
    @Override
    public boolean isCenteredDir(Direction dir) {
        return switch (dir) {
            case EAST, WEST ->
                Math.round(this.pos.y()) == this.pos.y(); // We can compare doubles as they are expected to be equal thanks to tpToCenter
            case SOUTH, NORTH -> Math.round(this.pos.x()) == this.pos.x();
            default -> true;
        };
    }

    /**
     * Checks if Pac-Man is centered in any direction.
     *
     * @return true if Pac-Man is centered, false otherwise.
     */
    @Override
    public boolean isCentered() {
        return (Math.round(this.getPos().x()) == this.getPos().x())
                && (Math.round(this.getPos().y()) == this.getPos().y());
    }

    /**
     * Calculates the next position of Pac-Man based on the elapsed time, direction, and maze configuration.
     * It is called at each tick.
     * It checks if pacman can move in a given direction, if yes, 
     * it calculates the next position and checks that it doesn't go through a wall.
     * If not, it calculates the next position in the current direction and checks that it doesn't go through a wall.
     * If pacman is centered in a direction, it can change direction.
     * @param deltaTns The elapsed time since the last tick in nanoseconds.
     * @param dir      The current direction of Pac-Man.
     * @param config   The maze configuration.
     * @return The next position of Pac-Man.
     */ 
    @Override
    public RealCoordinates getNextPos(long deltaTns, Direction dir, MazeConfig config) {
        if (this.isCenteredDir(dir)) {
            RealCoordinates nextPos = // Calculating the next position
                    getPos().plus((switch (dir) {
                        case NONE -> RealCoordinates.ZERO;
                        case NORTH -> RealCoordinates.NORTH_UNIT;
                        case EAST -> RealCoordinates.EAST_UNIT;
                        case SOUTH -> RealCoordinates.SOUTH_UNIT;
                        case WEST -> RealCoordinates.WEST_UNIT;
                    }).times(this.getSpeed() * deltaTns * 1E-9));
            switch (dir) { // Adjusting based on walls, we don't want to go through a wall
                case WEST:
                    if (config.getCell(this.currCellI()).getWestWall()) {
                        return new RealCoordinates(Math.max(nextPos.x(), Math.floor(this.getPos().x())),
                                this.getPos().y());
                    } else {
                        return nextPos;
                    }
                case EAST:
                    if (config.getCell(this.currCellI()).getEastWall()) {
                        return new RealCoordinates(Math.min(nextPos.x(), Math.ceil(this.getPos().x())),
                                this.getPos().y());
                    } else {
                        return nextPos;
                    }
                case NORTH:
                    if (config.getCell(this.currCellI()).getNorthWall() || config.getCell(this.currCellI()).getGhostNorthWall()) {
                        return new RealCoordinates(this.getPos().x(),
                                Math.max(nextPos.y(), Math.floor(this.getPos().y())));
                    } else {
                        return nextPos;
                    }
                case SOUTH:
                    if (config.getCell(this.currCellI()).getSouthWall() || config.getCell(this.currCellI()).getGhostSouthWall()) {
                        return new RealCoordinates(this.getPos().x(),
                                Math.min(nextPos.y(), Math.ceil(this.getPos().y())));
                    } else {
                        return nextPos;
                    }
                default:
                    return this.getPos();
            }
        } else {
            return this.getPos();
        }
    }

    /**
     * Checks if Pac-Man can set the given direction based on the maze configuration.
     *
     * @param dir    The direction to check.
     * @param config The maze configuration.
     * @return true if Pac-Man can set the given direction, false otherwise.
     */
    public boolean canSetDirection(Direction dir, MazeConfig config) {
        Cell currCell = config.getCell(this.currCellI());
        switch (dir) {
            case NORTH:
                return (!currCell.getNorthWall() && !currCell.getGhostNorthWall());
            case SOUTH:
                return (!currCell.getSouthWall() && !currCell.getGhostSouthWall());
            case EAST:
                return (!currCell.getEastWall());
            case WEST:
                return (!currCell.getWestWall());
            default:
                return true;
        }
    }

    /**
     * Sets the next direction of Pac-Man.
     *
     * @param dir The next direction of Pac-Man.
     */
    public void setNextDir(Direction dir) {
        this.nextDir = dir;
    }

    /**
     * Gets the next direction of Pac-Man.
     * If Pac-Man is centered in the next direction, it returns the next direction.
     * Otherwise, it returns the current direction.
     *
     * @return The next direction of Pac-Man.
     */
    public Direction getNextDir() {
        if (this.isCenteredDir(nextDir)) {
            return nextDir;
        } else {
            return this.direction;
        }
    }

    /**
     * Warps Pac-Man to the opposite side of the maze if it goes out of bounds.
     *
     * @param CritterNextPos The next position of Pac-Man.
     * @param width          The width of the maze.
     * @param height         The height of the maze.
     */
    public void warp(RealCoordinates CritterNextPos, int width, int height) {
        if (CritterNextPos.x() < 0) {
            this.setPos(new RealCoordinates(width - 1, CritterNextPos.y()));
        }
        if (CritterNextPos.x() >= width - 0.5) {
            this.setPos(new RealCoordinates(0, CritterNextPos.y()));
        }
        if (CritterNextPos.y() < 0) {
            this.setPos(new RealCoordinates(CritterNextPos.x(), height - 1));
        }
        if (CritterNextPos.y() >= height - 0.5) {
            this.setPos(new RealCoordinates(CritterNextPos.x(), 0));
        }
    }

    /**
     * Checks if Pac-Man is eatable.
     *
     * @return false, as Pac-Man is not eatable.
     */
    public boolean isEatable() {
        return false;
    }

    /**
     * Sets Pac-Man's eatable state.
     *
     * @param eatable The new eatable state of Pac-Man.
     */
    public void setEatable(boolean eatable) {
    }

    /**
     * Checks if Pac-Man is in the eyes state.
     *
     * @return false, as Pac-Man is not in the eyes state.
     */
    public boolean isEyes() {
        return false;
    }

    /**
     * Sets Pac-Man's eyes state.
     *
     * @param eyes The new eyes state of Pac-Man.
     */
    public void setEyes(boolean eyes) {
    }

}