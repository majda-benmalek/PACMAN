package gui;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import config.MazeConfig;
import javafx.stage.StageStyle;
import model.MazeState;

import java.io.IOException;
import java.util.Objects;

/**
 * The main class of the Pacman application.
 * This class extends the JavaFX Application class and is responsible for starting the game.
 */
public class App extends Application {
    /**
     * The entry point of the application.
     * This method is called when the application is launched.
     * It sets up the game scene, initializes the game components, and starts the animation controller.
     *
     * @param primaryStage the primary stage of the application
     */
    @Override
    public void start(Stage primaryStage) {
        var root = new BorderPane();
        var gamePane = new Pane();
        Scene gameScene = new Scene(root);
        PacmanController pacmanController = new PacmanController();
        gameScene.setOnKeyPressed(pacmanController::keyPressedHandler);
        gameScene.setOnKeyReleased(pacmanController::keyReleasedHandler);
        MazeState maze =new MazeState(Objects.requireNonNull(MazeConfig.makeExample1()));
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        double widthScale = Math.floor(screenBounds.getWidth() / maze.getWidth());
        double heightScale = Math.floor(screenBounds.getHeight() / maze.getHeight());

        double scale = Math.min(widthScale, heightScale);
        StackPane gameComponents = new StackPane();

        var gameView = new GameView(maze, gamePane, 0.8*scale);
        gameComponents.getChildren().add(gamePane);
        StackPane.setAlignment(gamePane, Pos.CENTER);
        
        Pane SLPane = new Pane();
        ScoreLivesView SLView = new ScoreLivesView(maze, SLPane, maze.getWidth() * 0.8 * scale, maze.getHeight() * 0.6 * scale * 0.25, scale); //Dégueu, il faut rendre ça plus dynamique.
        gameView.getGraphicsUpdaters().add(SLView.getSLUpdater());
        root.setCenter(gameComponents);
        root.setBottom(SLPane);
        
        var animationController = new AnimationController(gameView.getGraphicsUpdaters(), gameView.getMaze(), primaryStage, gameView, gameComponents, 0.8*scale, SLView);
        
        pacmanController.setAnimationController(animationController);

        
        maze.setAnimationController(animationController);
        
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        var Menu=new MainMenu();

        primaryStage.setScene(Menu.startMenu(primaryStage,gameScene,animationController));
        primaryStage.show();
    }

    /**
     * Restarts the application.
     * This method is called when the application needs to be restarted.
     *
     * @param stage the stage of the application
     */
    public static void restartApplication(Stage stage) {
        try {
            // Fermer le stage actuel
            stage.close();
            // Lancer une nouvelle instance de l'application
            Platform.runLater(() -> {
                try {
                    App newApp = new App();
                    newApp.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
