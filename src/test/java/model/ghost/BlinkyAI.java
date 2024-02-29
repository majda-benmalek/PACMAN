package model.ghost;

import config.MazeConfig;
import geometry.*;
import model.PacMan;
import model.Direction;
import model.ghost.Ghost;
import java.util.ArrayList;

/**
 * The BlinkyAI class represents the artificial intelligence for the ghost Blinky in the Pacman game.
 * It determines the next direction for Blinky to move based on the current game state.
 */
public class BlinkyAI {
    private MazeConfig config;
    private Ghost Blinky;

    /**
     * Constructs a BlinkyAI object with the specified ghost and maze configuration.
     *
     * @param Blinky The ghost controlled by this AI.
     * @param config The maze configuration used for pathfinding.
     */
    public BlinkyAI(Ghost Blinky, MazeConfig config) {
        this.config = config;
        this.Blinky = Blinky;
    }

    /**
     * Gets the next direction for Blinky to move.
     *
     * It uses the A* algorithm to find the shortest path to Pacman.
     * If Pacman is not visible, Blinky will move randomly.
     * If Pacman is visible, Blinky will move towards Pacman.
     * 
     * @return The next direction for Blinky.
     */
    public Direction getNextDir() {
        ArrayList<IntCoordinates> Astar = AStar.shortestPath(this.Blinky.currCellI(), PacMan.INSTANCE.currCellI(),
                this.config);
        int length = 0;
        try {
            length = Astar.size() - 1;
        } catch (NullPointerException e) {
            return Direction.NONE;
        }
        if (length < 0) {
            return this.Blinky.getDirection();
        }
        return Direction.fromIntCoordinates(Astar.get(length).sub(this.Blinky.currCellI()));
    }
}