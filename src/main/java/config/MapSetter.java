package config;

import gui.AnimationController;
import gui.App;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

/**
 * The MapSetter class is responsible for setting up the labyrinth selection screen.
 * It extends the Application class and provides methods for creating buttons, images, and applying styles.
 */
public class MapSetter extends Application{
    private AnimationController animationController;
    private final File path;
    private static Button[] buttons;
    public static int chosenLabyrinth = 1;

    /**
     * Constructs a MapSetter object with the default path.
     */
    public MapSetter() {
        this.path = new File("src/main/resources/maps/");
    }

    /**
     * Constructs a MapSetter object with the specified AnimationController and default path.
     * @param animationController The AnimationController object to be used.
     */
    public MapSetter(AnimationController animationController) {
        this.path = new File("src/main/resources/maps/");
        this.animationController = animationController;
    }

    /**
     * Starts the labyrinth selection screen.
     * @param primaryStage The primary stage of the JavaFX application.
     */
    public void start(Stage primaryStage) {
        // Create the title text
        Text title = new Text("LABYRINTH SELECTION");
        // Set the font for the title text
        title.setFont(loadFont("src/main/resources/font.ttf", 50));
        // Create a gradient color for the title text
        Stop[] stops = new Stop[] { new Stop(0, Color.rgb(200, 4, 200)), new Stop(1, Color.YELLOW) };
        LinearGradient lg = new LinearGradient(0, 0, 1.2, 0, true, CycleMethod.REPEAT, stops);
        // Set the gradient color for the title text
        title.setFill(lg);

        // Create image views for the labyrinths
        ImageView l1 = createImage(1);
        ImageView l2 = createImage(2);
        ImageView l3 = createImage(3);

        // Bind the image view sizes to the scene size
        var sceneWidth = primaryStage.widthProperty();
        var sceneHeight = primaryStage.heightProperty();
        DoubleBinding scaleLabyrinth = new DoubleBinding() {
            {
                super.bind(sceneWidth);
                super.bind(sceneHeight);
            }

            @Override
            protected double computeValue() {
                return Math.min((sceneWidth.get() * 0.25), (sceneHeight.get() * 0.25));
            }
        };

        l1.fitWidthProperty().bind(scaleLabyrinth);
        l1.fitHeightProperty().bind(scaleLabyrinth);
        l2.fitWidthProperty().bind(scaleLabyrinth);
        l2.fitHeightProperty().bind(scaleLabyrinth);
        l3.fitWidthProperty().bind(scaleLabyrinth);
        l3.fitHeightProperty().bind(scaleLabyrinth);

        // Create buttons for the labyrinths
        Button lab1 = createButton(l1, 1);
        Button lab2 = createButton(l2, 2);
        Button lab3 = createButton(l3, 3);

        buttons = new Button[] { lab1, lab2, lab3 };

        // Create the quit button to go back to the home page
        Button quit = new Button(">Click here to go back to home Page");
        quit.setOnAction(e -> {
            animationController.stopEverything();
        });
        quit.setFont(loadFont("src/main/resources/font.ttf", 16));
        VBox buttondiv = new VBox(quit);
        HBox labyrinthbloc = new HBox(lab1, lab2, lab3);

        VBox wrapper = new VBox(title, labyrinthbloc, buttondiv);
        buttondiv.setAlignment(Pos.CENTER);
        labyrinthbloc.setAlignment(Pos.CENTER);
        wrapper.setAlignment(Pos.CENTER);

        // Create the labyrinth selection scene
        Scene LabSelectScene = new Scene(wrapper, 700, 700);
        LabSelectScene.getStylesheets().add(Objects.requireNonNull("css/LabSelectStyle.css"));
        buttondiv.setSpacing(20);

        // Apply styles to the labyrinth selection elements
        setStyleSelectionLabyrinth(title, buttons, labyrinthbloc, quit, buttondiv, wrapper);

        primaryStage.setScene(LabSelectScene);
    }

    /**
     *Returns a Button with the specified ImageView and labyrinth number.
     * @param i The ImageView to be used.
     * @param num The labyrinth number to be used.
     *
     * @return The Button with the specified ImageView and labyrinth number.
     */
    private static Button createButton(ImageView i, int num) {
        Button b = new Button();
        b.setGraphic(i);
        b.setOnAction(e -> {
            chosenLabyrinth = num;
            updateClass();
        });
        return b;
    }

    /**
     * Updates the style of the labyrinth selection buttons.
     * 
     * It sets the style of the selected labyrinth to "labyrinthselected" 
     * and the style of the other labyrinths to "otherlabyrinth".
     * What this does is that it changes the color of the selected labyrinth to purple
     * and the color of the other labyrinths to yellow.
     * 
     * This method is called when a labyrinth is selected.
     */
    private static void updateClass() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].getStyleClass().clear();
            if (i == chosenLabyrinth - 1)
                buttons[i].getStyleClass().add("labyrinthselected");
            else
                buttons[i].getStyleClass().add("otherlabyrinth");
        }
    }

    /**
     * Returns an ImageView with the specified labyrinth number.
     * @param num The labyrinth number to be used.
     * 
     * @return The ImageView with the specified labyrinth number.
     */
    private static ImageView createImage(int num) {
        ImageView i = new ImageView("MapThumbs/Map" + num + ".png");
        return i;
    }

    /**
     * Applies styles to the labyrinth selection elements.
     * @param title The title text to be used.
     * @param selections The buttons to be used.
     * @param labyrinthbloc The labyrinth bloc to be used.
     * @param home The home button to be used.
     * @param buttondiv The button div to be used.
     * @param wrapper The wrapper to be used.
     * 
     * What this does is that it sets the style for the labyrinth selection elements.
     * It basically prepares the terrain for the css file to apply the styles.
     */
    private static void setStyleSelectionLabyrinth(Text title, Button[] selections, HBox labyrinthbloc, Button home,
            VBox buttondiv, VBox wrapper) {

        labyrinthbloc.getStyleClass().add("labyrinthbloc");
        home.getStyleClass().add("button");
        buttondiv.getStyleClass().add("buttondiv");
        wrapper.getStyleClass().add("wrapper");
        updateClass();
    }
    
    /**
     * Loads a font from the specified path and size.
     * @param path The path to the font file.
     * @param size The size of the font.
     * 
     * @return The font loaded from the specified path and size.
     */
    private static Font loadFont(String path, int size) {
        try {
            return Font.loadFont(new FileInputStream(new File(path)), size);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}