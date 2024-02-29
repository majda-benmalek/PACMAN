package model.ghost;

import config.MazeConfig;
import geometry.*;
import model.Direction;
import model.ghost.Ghost;
import java.util.ArrayList;


import java.util.Random;

/**
 * The ClydeAI class represents the artificial intelligence for the Clyde ghost in the Pacman game.
 * It determines the next direction for Clyde to move based on the current game state and maze configuration.
 */
public class ClydeAI {
    private MazeConfig config;
    private Ghost Clyde;
    private static IntCoordinates currentGoal;

    /**
     * Constructs a ClydeAI object with the specified Clyde ghost and maze configuration.
     * @param Clyde The Clyde ghost.
     * @param config The maze configuration.
     */
    public ClydeAI(Ghost Clyde, MazeConfig config) {
        this.config = config;
        this.Clyde = Clyde;
        if (currentGoal == null) {
            currentGoal = chooseRandomSpotInMaze();
        }
    }

    /**
     * Gets the next direction for Clyde to move.
     * It uses the A* algorithm to find the shortest path to the current goal.
     * The current goal is randomly chosen from the maze.
     * If the current goal is not accessible, a new goal is chosen.
     * If clyde reaches the current goal, a new goal is chosen.
     * 
     * @return The next direction for Clyde to move.
     */
    public Direction getNextDir() {
        if (this.Clyde.currCellI().equals(currentGoal)) {
            currentGoal = chooseRandomSpotInMaze();
        }
        ArrayList<IntCoordinates> Astar = AStar.shortestPath(this.Clyde.currCellI(), currentGoal,
                this.config);
        int length = 0;
        try {
            length = Astar.size() - 1;
        } catch (NullPointerException e) {
            return Direction.NONE;
        }
        if (length < 0) {
            return this.Clyde.getDirection();
        }
        return Direction.fromIntCoordinates(Astar.get(length).sub(this.Clyde.currCellI()));
    }

    /**
     * Chooses a random spot in the maze that is accessible.
     * @return The randomly chosen spot in the maze.
     */
    public IntCoordinates chooseRandomSpotInMaze() {
        int randomX = new Random().nextInt(this.config.getWidth());
        int randomY = new Random().nextInt(this.config.getHeight());
        currentGoal= new IntCoordinates(randomX, randomY);
        while(!currentGoalIsAccessible()){
            randomX = new Random().nextInt(this.config.getWidth());
            randomY = new Random().nextInt(this.config.getHeight());
            currentGoal= new IntCoordinates(randomX, randomY);
        }
        return currentGoal;
    }
    /**
     * Checks if the current goal is accessible.
     * @return True if the current goal is accessible, false otherwise.
     */
    public boolean currentGoalIsAccessible() {     
        return AStar.isPath(this.Clyde.currCellI(), currentGoal, this.config);
    }
}
