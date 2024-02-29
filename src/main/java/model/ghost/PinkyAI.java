package model.ghost;

import config.MazeConfig;
import geometry.*;
import model.PacMan;
import model.Direction;
import model.ghost.Ghost;
import java.util.ArrayList;
import java.util.Random;
import config.Cell;
import model.ghost.BlinkyAI;

/**
 * The PinkyAI class represents the artificial intelligence for the Pinky ghost in the Pacman game.
 * It determines the next direction for Pinky to move based on the current game state.
 */
public class PinkyAI {
    private MazeConfig config;
    private Ghost Pinky;

    /**
     * Constructs a PinkyAI object with the specified Pinky ghost and maze configuration.
     * 
     * @param Pinky  the Pinky ghost
     * @param config the maze configuration
     */
    public PinkyAI(Ghost Pinky, MazeConfig config) {
        this.config = config;
        this.Pinky = Pinky;
    }

    /**
     * Gets the next direction for Pinky to move.
     * 
     * @return the next direction for Pinky
     */
    public Direction getNextDir() {
        IntCoordinates GoalCell = getGoalCell();
        // the problem with this is that once the goal cell is r
        ArrayList<IntCoordinates> Astar = AStar.shortestPath(this.Pinky.currCellI(), GoalCell,
                this.config);
        int length = -1;
        if (Astar != null)
            length = Astar.size() - 1;
        if (length < 0) {
            BlinkyAI blink = new BlinkyAI(this.Pinky, this.config);
            return blink.getNextDir();
        }
        return Direction.fromIntCoordinates(Astar.get(length).sub(this.Pinky.currCellI()));

    }

    /**
     * Gets the goal cell for Pinky based on Pacman's current position and direction.
     * 
     * @return the goal cell for Pinky
     */
    private IntCoordinates getGoalCell() {
        Direction pacmanDirection = PacMan.INSTANCE.getDirection();
        IntCoordinates pacmanCoordinates = PacMan.INSTANCE.currCellI();
        IntCoordinates targetCoordinates;

        if (pacmanDirection == Direction.NONE) {
            targetCoordinates = new IntCoordinates(pacmanCoordinates.getX(), pacmanCoordinates.getY() - 4);
        } else {
            if (pacmanDirection == Direction.NORTH) {
                targetCoordinates = new IntCoordinates(pacmanCoordinates.getX(), pacmanCoordinates.getY() - 4);
            } else if (pacmanDirection == Direction.SOUTH) {
                targetCoordinates = new IntCoordinates(pacmanCoordinates.getX(), pacmanCoordinates.getY() + 4);
            } else if (pacmanDirection == Direction.WEST) {
                targetCoordinates = new IntCoordinates(pacmanCoordinates.getX() - 4, pacmanCoordinates.getY());
            } else {
                targetCoordinates = new IntCoordinates(pacmanCoordinates.getX() + 4, pacmanCoordinates.getY());
            }
        }
        // Check if Pinky is already at the target cell
        if (isBetweenCells(pacmanCoordinates, targetCoordinates)) {
            // If Pinky is at the target cell, set the target cell to Pacman's current cell
            targetCoordinates = pacmanCoordinates;
        }
        // Check if the target cell is within the bounds of the game grid
        if (this.config.isInBounds(targetCoordinates)) {
            return targetCoordinates;
        } else {
            return PacMan.INSTANCE.currCellI();
        }
    }

    /**
     * Checks if Pinky is between the target cell and Pacman's current cell.
     * 
     * @param pCoordinates         the coordinates of Pacman's current cell
     * @param targetCoordinates    the coordinates of the target cell
     * 
     * @return true if Pinky is between the target cell and Pacman's current cell, false otherwise
     */
    public boolean isBetweenCells(IntCoordinates pCoordinates, IntCoordinates targetCoordinates) {
        IntCoordinates pinkyCoordinates = this.Pinky.currCellI();
        // Check if Pinky is between the target cell and Pacman's current cell
        if (pCoordinates.getX() == targetCoordinates.getX() && pCoordinates.getX() == pinkyCoordinates.getX()) {
            if (pinkyCoordinates.getY() > pCoordinates.getY() && pinkyCoordinates.getY() < targetCoordinates.getY()) {
                return true;
            } else if (pinkyCoordinates.getY() < pCoordinates.getY()
                    && pinkyCoordinates.getY() > targetCoordinates.getY()) {
                return true;
            }
        } else if (pCoordinates.getY() == targetCoordinates.getY()
                && pCoordinates.getY() == pinkyCoordinates.getY()) {
            if (pinkyCoordinates.getX() > pCoordinates.getX() && pinkyCoordinates.getX() < targetCoordinates.getX()) {
                return true;
            } else if (pinkyCoordinates.getX() < pCoordinates.getX()
                    && pinkyCoordinates.getX() > targetCoordinates.getX()) {
                return true;
            }
        }
        return false;
    }

}
