package model.ghost;

import config.Cell;
import config.MazeConfig;
import geometry.IntCoordinates;
import geometry.RealCoordinates;
import model.*;
import gui.CritterGraphicsFactory;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Pas grand chose à détailler, ça définit simplement les 4 fantômes.
 * Un comportement différent est défini pour chaque fantôme.
 * Les comportements sont définis dans les classes BlinkyAI, InkyAI, PinkyAI et ClydeAI.
 */
public enum Ghost implements Critter {
    BLINKY, INKY, PINKY, CLYDE;

    private RealCoordinates pos;
    private Direction direction;
    private final double speed = 2.4;
    private boolean energized;
    private static int mode;
    private static final double TPINTERVAL = 0.05;
    private boolean isEatable;
    private boolean isEyes;
    private int dureEatable = 10000;

    // Getters/Setters
    @Override
    public RealCoordinates getPos() {
        return this.pos;
    }
    @Override
    public boolean isEatable() {
        return isEatable;
    }
    @Override
    public void setEatable(boolean eatable) {
        this.isEatable = eatable;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }
    public static void setAllEnergizedWithValue(boolean energized){
        BLINKY.setEnergized(energized);
        INKY.setEnergized(energized);
        PINKY.setEnergized(energized);
        CLYDE.setEnergized(energized);
    }
    public static void setAllEatableWithValue(boolean eatable){
        BLINKY.setEatable(eatable);
        INKY.setEatable(eatable);
        PINKY.setEatable(eatable);
        CLYDE.setEatable(eatable);
    }
    public static void setAllEyesWithValue(boolean eyes){
        BLINKY.setEyes(eyes);
        INKY.setEyes(eyes);
        PINKY.setEyes(eyes);
        CLYDE.setEyes(eyes);
    }

    public void Energize() {
        this.energized = true;
    }
    public void setEnergized(boolean energized){
        this.energized = energized;
    }
    public void DeEnergize() {
        this.energized = false;
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }

    @Override
    public void setPos(RealCoordinates pos) {
        this.pos = pos;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public void setEyes(boolean eyes) {
        this.isEyes = eyes;
    }
    @Override
    public boolean isEyes() {
        return isEyes;
    }
    // Methods
    @Override
    public RealCoordinates currCellR() {
        return new RealCoordinates(Math.round((float) this.pos.x()), Math.round((float) this.pos.y()));
    }

    @Override
    public IntCoordinates currCellI() {
        return new IntCoordinates(Math.round((float) this.pos.x()), Math.round((float) this.pos.y()));
    }

    /**
     * Check if the critter is going to the center of the cell
     * @return true if the critter is going to the center of the cell
     */
    @Override
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
     * Check if the critter can move in the given direction
     * @param dir the direction to check
     * @param config the maze configuration
     * 
     * @return true if the critter can move in the given direction
     */
    public boolean canMove(Direction dir, MazeConfig config) {
        return switch (dir) {
            case NORTH -> !config.getCell(this.currCellI()).getNorthWall();
            case SOUTH -> !config.getCell(this.currCellI()).getSouthWall();
            case EAST -> !config.getCell(this.currCellI()).getEastWall();
            case WEST -> !config.getCell(this.currCellI()).getWestWall();
            default -> false;
        };
    }

    /**
     * Teleport the critter to the center of the cell if it is going to the center
     * and if it is close enough to the center
     * This is used to avoid the critter to be stuck in a wall or to go through a wall
     */
    @Override
    public void tpToCenter() {
        RealCoordinates currCell = this.currCellR();
        if (this.isGoingToCenter() && this.pos.dist(currCell) < TPINTERVAL) {
            this.setPos(currCell);
        }
    }

    /**
     * Check if the critter is centered in the given direction
     * @param dir the direction to check
     * @return true if the critter is centered in the given direction
     */
    @Override
    public boolean isCenteredDir(Direction dir) {
        return switch (dir) {
            case EAST, WEST ->
                Math.round(this.pos.y()) == this.pos.y(); // On peut comparer des double car ils sont censé être égaux
                                                          // grâce tpToCenter
            case SOUTH, NORTH -> Math.round(this.pos.x()) == this.pos.x();
            default -> true;
        };
    }

    /**
     * Check if the critter is centered
     * @return true if the critter is centered
     */
    public boolean isCentered() {
        return (Math.round(this.pos.x()) == this.pos.x()) && (Math.round(this.pos.y()) == this.pos.y());
    }

    /**
     * Get the next position of the critter
     * @param deltaTns the time since the last update
     * @param dir the direction of the critter
     * @param config the maze configuration
     * @return the next position of the critter
     */
    public RealCoordinates getNextPos(long deltaTns, Direction dir, MazeConfig config) {
        if (this.isCenteredDir(dir)) {
            RealCoordinates nextPos = // Calcul de la position suivante
                    getPos().plus((switch (dir) {
                        case NONE -> RealCoordinates.ZERO;
                        case NORTH -> RealCoordinates.NORTH_UNIT;
                        case EAST -> RealCoordinates.EAST_UNIT;
                        case SOUTH -> RealCoordinates.SOUTH_UNIT;
                        case WEST -> RealCoordinates.WEST_UNIT;
                    }).times(this.getSpeed() * deltaTns * 1E-9));
            switch (dir) { // Ajustement en fonction des murs, on ne veut pas dépasser un mur
                case WEST:
                    if (config.getCell(this.currCellI()).getWestWall()) {
                        return new RealCoordinates(Math.max(nextPos.x(), Math.floor(this.pos.x())), this.pos.y());
                    } else {
                        return nextPos;
                    }
                case EAST:
                    if (config.getCell(this.currCellI()).getEastWall()) {
                        return new RealCoordinates(Math.min(nextPos.x(), Math.ceil(this.pos.x())), this.pos.y());
                    } else {
                        return nextPos;
                    }
                case NORTH:
                    if (config.getCell(this.currCellI()).getNorthWall()) {
                        return new RealCoordinates(this.pos.x(), Math.max(nextPos.y(), Math.floor(this.pos.y())));
                    } else {
                        if(config.getCell(this.currCellI()).getGhostNorthWall()){
                            setEatable(false);
                            setEyes(false);
                        }
                        return nextPos;
                    }
                case SOUTH:
                    if (config.getCell(this.currCellI()).getSouthWall()) {
                        return new RealCoordinates(this.pos.x(), Math.min(nextPos.y(), Math.ceil(this.pos.y())));
                    } else {
                        return nextPos;
                    }
                default:
                    return this.pos;
            }
        } else {
            return this.pos;
        }
    }

    /**
     * Get the next direction of the critter
     * @param config the maze configuration
     * If the Ghost is in the center of a cell, it will choose a direction
     * If the Ghost is not in the center of a cell, it will keep its direction
     * 
     * Here are the possible directions for each Ghost:
     * If pacman is energized:
     * All the ghosts will run away from pacman
     * 
     * If pacman is not energized:
     * Blinky will chase pacman
     * Inky will alternate between chasing pacman and going to a random cell
     * Pinky will go to the cell 4 cells in front of pacman
     * Clyde will always choose a random Cell, go there and then choose another random cell
     * 
     * If pacman has eaten a Ghost:
     * The Ghost becomes eyes and goes back to its home
     *  
     */
    public Direction getNextDir(MazeConfig config) { // test implementation
        if (this.isCentered()) {
            if(this.isEyes()){
                if(!this.checkIfHome(config)){
                    GoHomeAI goHome = new GoHomeAI(this, config);
                    return goHome.getNextDir();
                }else{
                    this.setEyes(false);
                    return getNormalDir(config);
                }
            } else if (this.energized) {

                RunawayAI run = new RunawayAI(this, config);
                return run.getNextDir();  
            }else {
                return getNormalDir(config);
            }
        } else {
            return this.direction;
        }
    }

    /**
     * Check if the Ghost is at its initial position
     * @param config the maze configuration
     * @return true if the Ghost is at its initial position
     */
    private boolean checkIfHome(MazeConfig config){
        if(this == BLINKY){
            return this.currCellI().equals(config.getBlinkyPos());
        }else if(this == INKY){
            return this.currCellI().equals(config.getInkyPos());
        }else if(this == PINKY){
            return this.currCellI().equals(config.getPinkyPos());
        }else if(this == CLYDE){
            return this.currCellI().equals(config.getClydePos());
        }else{
            return false;
        }
    }

    /**
     * Get the next direction of the critter
     * @param config the maze configuration
     * @return the next direction of the critter, in normal mode
     */
    public Direction getNormalDir(MazeConfig config) {
            if (this == BLINKY) {
                BlinkyAI blink = new BlinkyAI(this, config);
                return blink.getNextDir();
            } else if (this == INKY) {
                InkyAI inky = new InkyAI(this, config);
                return inky.getNextDir();
            } else if (this == PINKY) {
                PinkyAI pink = new PinkyAI(this, config);
                return pink.getNextDir();
            } else if (this == CLYDE) {
                ClydeAI clyde = new ClydeAI(this, config);
                return clyde.getNextDir();
            } else
                return Direction.NONE;
       
    }
    
    /**
     * Warp the critter if it is going out of the maze
     * @param CritterNextPos the next position of the critter
     * @param width the width of the maze
     * @param height the height of the maze
     * If the critter goes over the width or the height of the maze, 
     * it will be teleported to the other side of the maze
     * If it goes below 0, it will be teleported to the other side of the maze
     */
    public void warp(RealCoordinates CritterNextPos,int width, int height){
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
}