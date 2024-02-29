package model.ghost;

import geometry.AStar;
import config.MazeConfig;
import model.ghost.Ghost;
import java.util.ArrayList;
import model.Direction;
import geometry.IntCoordinates;
/**
 * The GoHomeAI class represents an artificial intelligence algorithm that determines the next direction for a ghost to go back to its home position.
 */
public class GoHomeAI {
    private MazeConfig config;
    private Ghost ghost;

    /**
     * Constructs a GoHomeAI object with the specified ghost and maze configuration.
     * 
     * @param ghost  the ghost to apply the AI algorithm to
     * @param config the maze configuration
     */
    public GoHomeAI(Ghost ghost, MazeConfig config) {
        this.config = config;
        this.ghost = ghost;
    }

    /**
     * Gets the next direction for the ghost to move towards its home position.
     * It uses the A* algorithm to find the shortest path to the ghost's home position.
     * 
     * @return the next direction for the ghost
     */
    public Direction getNextDir() {
        var GoalCell = new IntCoordinates(0, 0);
        if (ghost == Ghost.BLINKY) {
            GoalCell = config.getBlinkyPos();
        } else if (ghost == Ghost.INKY) {
            GoalCell = config.getInkyPos();
        } else if (ghost == Ghost.PINKY) {
            GoalCell = config.getPinkyPos();
        } else if (ghost == Ghost.CLYDE) {
            GoalCell = config.getClydePos();
        }
        ArrayList<IntCoordinates> Astar = AStar.shortestPath(this.ghost.currCellI(), GoalCell, this.config);
        int length = 0;
        try {
            length = Astar.size() - 1;
        } catch (NullPointerException e) {
            return this.ghost.getDirection();
        }
        if (length < 0) {
            return this.ghost.getDirection();
        }
        return Direction.fromIntCoordinates(Astar.get(length).sub(this.ghost.currCellI()));
    }
}
