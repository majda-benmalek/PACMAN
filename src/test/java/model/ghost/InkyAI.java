package model.ghost;

import geometry.*;
import model.Direction;
import model.ghost.BlinkyAI;
import model.PacMan;
import config.MazeConfig;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The InkyAI class represents the artificial intelligence for the ghost Inky in the Pacman game.
 */
public class InkyAI {
    private static int mode = new Random().nextInt(2);
    private Ghost Inky;
    private MazeConfig Config;
    private static IntCoordinates currentGoal;

    /**
     * Constructs a new InkyAI object with the specified ghost and maze configuration.
     *
     * @param Inky   the ghost Inky
     * @param Config the maze configuration
     */
    public InkyAI(Ghost Inky, MazeConfig Config) {
        this.Inky = Inky;
        this.Config = Config;
        if (currentGoal == null) {
            currentGoal = chooseRandomSpotInMaze();
        }
    }

    /**
     * Gets the next direction for Inky to move.
     *
     * @return the next direction for Inky
     */
    public Direction getNextDir() {
        if (mode == 0) {
            currentGoal = PacMan.INSTANCE.currCellI();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mode = 1;
                    currentGoal = chooseRandomSpotInMaze();
                }
            }, 7000);
        } else {
            if (this.Inky.currCellI().equals(currentGoal)) {
                mode = 0;
            }
        }
        ArrayList<IntCoordinates> Astar = AStar.shortestPath(this.Inky.currCellI(), currentGoal,
                this.Config);
        int length = 0;
        try {
            length = Astar.size() - 1;
        } catch (NullPointerException e) {
            return Direction.NONE;
        }
        if (length < 0) {
            return this.Inky.getDirection();
        }
        return Direction.fromIntCoordinates(Astar.get(length).sub(this.Inky.currCellI()));
    }

    /**
     * Chooses a random spot in the maze as the current goal for Inky.
     *
     * @return the coordinates of the chosen random spot
     */
    private IntCoordinates chooseRandomSpotInMaze() {
        int randomX = new Random().nextInt(this.Config.getWidth());
        int randomY = new Random().nextInt(this.Config.getHeight());
        currentGoal = new IntCoordinates(randomX, randomY);
        while (!currentGoalIsAccessible()) {
            randomX = new Random().nextInt(this.Config.getWidth());
            randomY = new Random().nextInt(this.Config.getHeight());
            currentGoal = new IntCoordinates(randomX, randomY);
        }
        return currentGoal;
    }

    /**
     * Checks if the current goal is accessible from Inky's current position in the maze.
     *
     * @return true if the current goal is accessible, false otherwise
     */
    public boolean currentGoalIsAccessible() {
        return AStar.isPath(this.Inky.currCellI(), currentGoal, this.Config);
    }
}

