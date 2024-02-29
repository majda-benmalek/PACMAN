package model.ghost;

import config.MazeConfig;
import geometry.*;
import model.Direction;
import model.PacMan;
import java.util.ArrayList;
import model.ghost.Ghost;

/**
 * The RunawayAI class represents the artificial intelligence for the "Runaway" ghost in the Pacman game.
 * It determines the next direction for the ghost to move based on the current game state and maze configuration.
 */
public class RunawayAI {
    private MazeConfig config;
    private Ghost Runaway;

    /**
     * Constructs a RunawayAI object with the specified Runaway ghost and maze configuration.
     *
     * @param Runaway The Runaway ghost.
     * @param config The maze configuration.
     */
    public RunawayAI(Ghost Runaway, MazeConfig config) {
        this.config = config;
        this.Runaway = Runaway;
    }

    /**
     * Gets the next direction for the Runaway ghost to move.
     * It uses the A* algorithm to find the longest path to Pacman.
     * 
     * @return The next direction for the Runaway ghost.
     */
    public Direction getNextDir() {
        ArrayList<IntCoordinates> Astar = AStar.longestPath(this.Runaway.currCellI(), PacMan.INSTANCE.currCellI(),
                this.config);
        int length = 0;
        try {
            length = Astar.size() - 1;
        } catch (NullPointerException e) {
            return Direction.NONE;
        }
        if (length < 0) {
            return this.Runaway.getDirection();
        }
        return Direction.fromIntCoordinates(Astar.get(length).sub(this.Runaway.currCellI()));
    }
}

