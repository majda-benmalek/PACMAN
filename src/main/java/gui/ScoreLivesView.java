package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.MazeState;

/**
 * The ScoreLivesView class represents the view component for displaying the score and lives in the Pacman game.
 * It provides methods to set and update the graphics, reset the view, and get the graphics factory and updater.
 */
public class ScoreLivesView {
    private MazeState maze;
    private final Pane SLRoot;
    private GraphicsUpdater SLUpdater;
    private ScoreLivesGraphicsFactory SLFactory;

    /**
     * Constructs a ScoreLivesView object with the specified maze, root pane, width, height, and scale.
     *
     * @param maze   the MazeState object representing the current state of the maze
     * @param SLRoot the root pane where the graphics will be displayed
     * @param width  the preferred width of the root pane
     * @param height the preferred height of the root pane
     * @param scale  the scale factor for the graphics
     */
    public ScoreLivesView(MazeState maze, Pane SLRoot, double width, double height, double scale) {
        this.maze = maze;
        this.SLRoot = SLRoot;

        SLRoot.setPrefWidth(width);
        SLRoot.setPrefHeight(height);
        SLRoot.setStyle("-fx-background-color: #2D006A");

        ScoreLivesGraphicsFactory SLFactory = new ScoreLivesGraphicsFactory(width, height, scale);
        this.SLFactory = SLFactory;
        this.setGraphics(SLFactory.makeGraphics(maze));
    }

    /**
     * Resets the view by clearing the root pane and recreating the graphics.
     */
    public void reset() {
        this.SLRoot.getChildren().clear();
        this.setGraphics(SLFactory.makeGraphics(maze));
    }

    /**
     * Sets the graphics updater for the view.
     *
     * @param updater the GraphicsUpdater object responsible for updating the graphics
     */
    public void setGraphics(GraphicsUpdater updater) {
        this.SLRoot.getChildren().add(updater.getNode());
        this.SLUpdater = updater;
    }

    /**
     * Returns the ScoreLivesGraphicsFactory object used by the view.
     *
     * @return the ScoreLivesGraphicsFactory object
     */
    public ScoreLivesGraphicsFactory getSLFactory() {
        return SLFactory;
    }

    /**
     * Returns the GraphicsUpdater object used by the view.
     *
     * @return the GraphicsUpdater object
     */
    public GraphicsUpdater getSLUpdater() {
        return SLUpdater;
    }

    /**
     * Sets the maze state for the view.
     * @param maze the MazeState object representing the current state of the maze
     */
    public void setMaze(MazeState maze) {
        this.maze = maze;
    }
}