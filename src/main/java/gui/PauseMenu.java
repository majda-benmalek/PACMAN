package gui;

import model.MazeState;

import javafx.application.Platform;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The PauseMenu class represents the pause menu of the game.
 * It implements the Menu interface.
 * This class provides methods to create and configure the pause menu scene.
 */
public class PauseMenu implements Menu{
    private final StackPane root;
    private final MazeState maze;
    private final AnimationController animationController;
    private Pane pauseLayout;
    private VBox vBox;
    
    /**
     * Creates a pause menu with the specified maze state, root pane, and animation controller.
     * @param maze the maze state
     * @param root the root pane
     * @param animationController the animation controller
     * @see MazeState
     * @see StackPane
     * @see AnimationController
     * @return a pause menu with the specified maze state, root pane, and animation controller
     */
    public PauseMenu(MazeState maze, StackPane root, AnimationController animationController) {
        this.maze = maze;
        this.root  = root;
        this.animationController = animationController;
    }
    /**
     * Creates and configures the pause menu scene. 
     */
    public void startMenu(){
        BorderPane layout = new BorderPane();

        double width = root.getWidth();
        double height = root.getHeight();

        double fontScale=(50*root.getWidth())/1020;
        layout.setMinWidth(width*0.3);
        layout.setMinHeight(height*0.3);
        layout.setMaxWidth(width*0.7);
        layout.setMaxHeight(height*0.7);

        Text pauseMenuText = new Text("Pause");
        pauseMenuText.setFont(loadFont("src/main/resources/font.ttf",(int)fontScale));
        pauseMenuText.setFill(Color.WHITE);

        Text exitText = new Text("Exit to Main Menu");
        exitText.setFont(loadFont("src/main/resources/font.ttf",(int)fontScale));
        exitText.setFill(Color.WHITE);
        setHoverEffect(exitText, Color.RED, Color.WHITE);
        
        exitText.setOnMouseClicked(e->{
            Platform.runLater(()->App.restartApplication(animationController.getPrimaryStage()));
        });

        Text indicatorText = new Text("Press ESC to resume");
        indicatorText.setFont(loadFont("src/main/resources/font.ttf",(int)fontScale));
        indicatorText.setFill(Color.YELLOW);
        setHoverEffect(indicatorText, Color.BLUE, Color.YELLOW);
        indicatorText.setOnMouseClicked(e->{
            animationController.stopPauseMenu();
        });
        Text score = new Text("Score: "+maze.getScore());
        score.setFont(loadFont("src/main/resources/font.ttf",(int)fontScale));
        score.setFill(Color.YELLOW);

        Text lives = new Text("Lives: "+maze.getLives());
        lives.setFont(loadFont("src/main/resources/font.ttf",(int)fontScale));
        lives.setFill(Color.RED);
        VBox components = new VBox();
        components.getChildren().addAll(score, lives);
        components.setAlignment(Pos.CENTER);
        components.setSpacing(30*root.getHeight()/1020);

        VBox vBox = new VBox();
        vBox.maxHeightProperty().bind(root.maxHeightProperty());
        vBox.setSpacing((90*root.getHeight())/1020);
        vBox.getChildren().addAll(pauseMenuText,exitText,indicatorText);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().add(components);

        layout.setCenter(vBox);
        pauseLayout = layout;
        this.vBox = vBox;
        root.getChildren().add(layout);
    }
    public void stopMenu(){
        root.getChildren().remove(pauseLayout);
    }
}
