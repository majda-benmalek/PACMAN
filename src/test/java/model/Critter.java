package model;

/**
 * Interface Java sealed to implement the Critter hierarchy.
 * @see ghost#Ghost
 * @see PacMan
 */

import geometry.IntCoordinates;
import geometry.RealCoordinates;
import config.MazeConfig;

import java.util.Timer;

/**
 * The Critter interface represents a creature in the game, such as a Ghost or Pacman.
 */
public interface Critter {

    // Getters/Setters

    /**
     * Gets the position of the critter.
     * @return the position of the critter
     */
    RealCoordinates getPos();

    /**
     * Gets the direction of the critter.
     * @return the direction of the critter
     */
    Direction getDirection();

    /**
     * Gets the speed of the critter.
     * @return the speed of the critter
     */
    double getSpeed();

    /**
     * Checks if the critter is eatable.
     * @return true if the critter is eatable, false otherwise
     */
    boolean isEatable();

    /**
     * Checks if the critter has eyes.
     * @return true if the critter has eyes, false otherwise
     */
    boolean isEyes();

    /**
     * Sets the value indicating whether the critter has eyes.
     * @param eyes true if the critter has eyes, false otherwise
     */
    void setEyes(boolean eyes);

    /**
     * Sets the value indicating whether the critter is energized.
     * @param energized true if the critter is energized, false otherwise
     */
    void setEnergized(boolean energized);

    /**
     * Sets the position of the critter.
     * @param realCoordinates the new position of the critter
     */
    void setPos(RealCoordinates realCoordinates);

    /**
     * Sets the direction of the critter.
     * @param direction the new direction of the critter
     */
    void setDirection(Direction direction);
    
    /**
     * Sets the value indicating whether the critter is eatable.
     * @param eatable true if the critter is eatable, false otherwise
     */
    void setEatable(boolean eatable);

    // Methods

    /**
     * Calculates the next position of the critter based on the time since the last update.
     * @param deltaTNanoSeconds time since the last update in nanoseconds
     * @return the next position of the critter if there is no wall
     */
    default RealCoordinates nextPos(long deltaTNanoSeconds) {
        return getPos().plus((switch (getDirection()) {
            case NONE -> RealCoordinates.ZERO;
            case NORTH -> RealCoordinates.NORTH_UNIT;
            case EAST -> RealCoordinates.EAST_UNIT;
            case SOUTH -> RealCoordinates.SOUTH_UNIT;
            case WEST -> RealCoordinates.WEST_UNIT;
        }).times(getSpeed() * deltaTNanoSeconds * 1E-9));
    }

    /**
     * Returns the real coordinates of the cell on which the critter is located.
     * @return the real coordinates of the cell on which the critter is located
     */
    RealCoordinates currCellR();

    /**
     * Returns the integer coordinates of the cell on which the critter is located.
     * @return the integer coordinates of the cell on which the critter is located
     */
    IntCoordinates currCellI();

    /**
     * Checks if the critter is going towards the center of the cell.
     * @return true if the critter is going towards the center, false otherwise
     */
    boolean isGoingToCenter();

    /**
     * Teleports the critter to the center of the cell if it is close enough.
     */
    void tpToCenter();

    /**
     * Checks if the critter is centered on the axis on which it is not moving.
     * @param dir the direction to check
     * @return true if the critter is centered on the axis, false otherwise
     */
    boolean isCenteredDir(Direction dir);

    /**
     * Checks if the critter is centered on the cell.
     * @return true if the critter is centered on the cell, false otherwise
     */
    boolean isCentered();

    /**
     * Calculates the next position of the critter if it moves in the specified direction.
     * @param deltaTns time since the last update in nanoseconds
     * @param dir the direction in which the critter is moving
     * @param config the maze configuration
     * @return the next position of the critter if it moves in the specified direction
     */
    RealCoordinates getNextPos(long deltaTns, Direction dir, MazeConfig config);
}
