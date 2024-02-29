package gui;

import model.MazeState;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.Objects;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The ScoreLivesGraphicsFactory class is responsible for creating and updating the graphics
 * for the score and lives display in the Pacman game.
 */
public class ScoreLivesGraphicsFactory {
    private final double width;
    private final double height;
    private final double scale;

    /**
     * Constructs a ScoreLivesGraphicsFactory with the specified width, height, and scale.
     *
     * @param width  the width of the graphics
     * @param height the height of the graphics
     * @param scale  the scale of the graphics
     */
    public ScoreLivesGraphicsFactory(double width, double height, double scale) {
        this.width = width;
        this.height = height;
        this.scale = scale;
    }

    /**
     * Initializes and returns an array of Circle objects representing the lives display.
     *
     * @return an array of Circle objects representing the lives display
     */
    public Circle[] initShowLives() {
        Circle[] lives = new Circle[6];
        for (int i = 0; i < lives.length; i++) {
            lives[i] = new Circle();
            lives[i].setRadius(scale / 3);
            lives[i].setCenterX(scale / 2);
            lives[i].setCenterY(scale / 2);
            lives[i].setFill(Color.YELLOW);
            lives[i].setStroke(Color.PURPLE);
        }
        return lives;
    }

    /**
     * Updates the lives display based on the specified number of lives.
     *
     * @param lives    the array of Circle objects representing the lives display
     * @param nbLives  the number of lives to be displayed
     */
    public void updateLives(Circle[] lives, int nbLives) {
        for (int i = 0; i < lives.length; i++) {
            if (i < nbLives) {
                lives[i].setFill(Color.YELLOW);
            } else {
                lives[i].setFill(Color.TRANSPARENT);
            }
        }
    }

    /**
     * Creates and returns a GraphicsUpdater object for the specified MazeState.
     *
     * @param maze the MazeState object
     * @return a GraphicsUpdater object
     */
    public GraphicsUpdater makeGraphics(MazeState maze) {
        var group = new Group();

        Text score = new Text("Score: " + maze.getScore());
        score.setFont(loadFont("src/main/resources/font.ttf", (int) scale));
        score.setFill(Color.WHITE);

        Circle[] lives = initShowLives();

        HBox hBox = new HBox();
        hBox.getChildren().addAll(lives);
        hBox.getChildren().add(score);
        hBox.setSpacing(scale / 4);
        hBox.setMargin(lives[0], new Insets(0, 0, 0, (this.width * 0.1)));
        hBox.setMargin(score, new Insets(0, 0, 0, (this.width * 0.1)));
        hBox.setAlignment(Pos.CENTER);
        hBox.setPrefHeight(this.height);

        group.getChildren().add(hBox);

        return new GraphicsUpdater() {
            @Override
            public void update() {
                score.setText("Score: " + maze.getScore());
                updateLives(lives, maze.getLives());
            }

            @Override
            public Node getNode() {
                return group;
            }
        };
    }

    private static Font loadFont(String path, int size) {
        try {
            return Font.loadFont(new FileInputStream(new File(path)), size);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
