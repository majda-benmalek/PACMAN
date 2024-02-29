package gui;

import config.MazeConfig;

import model.ghost.Ghost;
import model.MazeState;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.geometry.Insets;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The AnimationController class is responsible for controlling the animation of the Pacman game.
 * It implements the AnimationTimer class and provides methods for starting and stopping the animation.
 * It also provides methods for transitioning to the next level, displaying the game over screen, 
 * and displaying the win screen.
 */
public class AnimationController{
    private List<GraphicsUpdater> graphicsUpdaters;
    private MazeState maze;
    private final Stage primaryStage;

    private final PauseMenu pauseMenu;

    private GameView gameView;
    private final StackPane gameComponents;
    private boolean isPaused = false;
    private boolean isInUnstoppableAnimation = false;
    private final double AppScale;
    private boolean hasntAlreadyWon = true;
    private ScoreLivesView scoreLivesView;

    public AnimationController(List<GraphicsUpdater> graphicsUpdaters, MazeState maze, Stage primaryStage, GameView gameView, StackPane root, double AppScale, ScoreLivesView scoreLivesView) {
        this.scoreLivesView = scoreLivesView;
        this.graphicsUpdaters = graphicsUpdaters;
        this.maze = maze;
        this.primaryStage = primaryStage;
        this.gameView = gameView;
        this.gameComponents = root;
        this.AppScale = AppScale;
        pauseMenu = new PauseMenu(gameView.getMaze(), root,this);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public void setPaused(boolean paused) {
        isPaused = paused;
    }
    public boolean isPaused() {
        return isPaused;
    }
    public void startPauseMenu() {
        this.startPause();
        pauseMenu.startMenu();
    }
    public void stopPauseMenu() {
        this.stopPause();
        pauseMenu.stopMenu();
    }
    public void startPause() {
        this.pauseScheduled=true;
        this.setPaused(true);
    }
    public void stopPause() {
        this.pauseScheduled=false;
        this.playScheduled=true;
        this.setPaused(false);
    }
    public boolean isInUnstoppableAnimation() {
        return isInUnstoppableAnimation;
    }
    public boolean hasntAlreadyWon() {
        return hasntAlreadyWon;
    }
    public void setHasntAlreadyWon(boolean hasntAlreadyWon) {
        this.hasntAlreadyWon = hasntAlreadyWon;
    }
    public void stopEverything(){
        this.startPause();
        this.isInUnstoppableAnimation = true;
        Platform.runLater(()->{
            App.restartApplication(primaryStage);
        });
    }

    /**
     * Displays the game over screen.
     * The game over screen is displayed for 3 seconds before the application is restarted.
     * The game over screen is displayed when the Pacman loses all of its lives.
     */
    public void gameOver(){
        this.startPause();
        this.isInUnstoppableAnimation = true;
        BorderPane layout = new BorderPane();

        layout.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        Text gameOver = new Text("Game Over");
        gameOver.setFill(Color.WHITE);
        gameOver.setFont(loadFont("src/main/resources/font.ttf", 55));
        layout.setCenter(gameOver);
        gameComponents.getChildren().add(layout);
        final Stage stage =this.primaryStage;

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                    Platform.runLater(()->App.restartApplication(stage));
            }
        };
        timer.schedule(task, 3000);
    }

    /**
     * Displays the win screen.
     * The win screen is displayed for 3 seconds before the application is restarted.
     * The win screen is displayed when the Pacman eats all of the dots in the maze.
     */
    public void win(){
            this.startPause();
            this.isInUnstoppableAnimation = true;
            BorderPane winScreen = new BorderPane();

            winScreen.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

            Text win = new Text("You win !");
            win.setFill(Color.GREEN);
            win.setFont(loadFont("src/main/resources/font.ttf", 55));

            winScreen.setCenter(win);
            gameComponents.getChildren().add(winScreen);            
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
                gameComponents.getChildren().remove(gameView.getGameRoot());
                gameComponents.getChildren().remove(winScreen);
                this.stopPause();
                this.isInUnstoppableAnimation = false;
                try {
                transitionToNextLevel(getNextLevel(this.maze.getLevel()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }));
            timeline.setCycleCount(1);
            timeline.play();
    }

    private long pauseStart;
    private boolean pauseScheduled=false;
    private boolean playScheduled=false;

    public AnimationTimer createAnimationTimer(){
        return new AnimationTimer(){
            long startAnimation = 0;

            @Override
            public void handle(long now){
                if(pauseScheduled){
                    pauseStart=now;
                    pauseScheduled=false;
                }
                if (playScheduled){
                    startAnimation+=(now-pauseStart);
                    playScheduled=false;    
                }
                if(!isPaused){
                    if (startAnimation == 0) { // ignore the first tick, just compute the first deltaT
                        startAnimation = now;
                        return;
                    }
                    long deltaT = now - startAnimation;
                    if(deltaT>22000000){
                        deltaT=22000000;
                    }
                    maze.update(deltaT);
                    startAnimation = now;
                }
                for (var updater : graphicsUpdaters) {
                    updater.update();
                }
            }
        };
    }

    /**
     * Displays the next level.
     * The next level is displayed when the Pacman eats all of the dots in the maze.
     * @param nextLevel The next level to be displayed.
     * @throws IOException
     * The exception is thrown when the maze cannot be created.
     * @see MazeConfig#makeMaze(int)
     */
    public void transitionToNextLevel(int nextLevel) throws IOException {
        MazeState maze = new MazeState(Objects.requireNonNull(MazeConfig.makeMaze(nextLevel)));
        maze.setScore(this.maze.getScore());
        maze.setLives(this.maze.getLives());
        maze.setAnimationController(this);
        maze.setLevel(nextLevel);
        maze.setScore(this.maze.getScore());
        maze.setNiveauFruit(this.maze.getNiveauFruit());
        
        this.scoreLivesView.setMaze(maze);
        this.scoreLivesView.reset();
        for (var updater : graphicsUpdaters) {
            if(updater.equals(this.scoreLivesView.getSLUpdater())){
                graphicsUpdaters.remove(updater);
                break;
            }
        }
        this.maze = maze;
        this.gameView.getGameRoot().getChildren().clear();
        GameView gameViewNew = new GameView(maze, this.gameView.getGameRoot(), AppScale);
        this.gameView=gameViewNew;
        //gameViewNew.getGraphicsUpdaters().add(this.graphicsUpdaters.get(this.graphicsUpdaters.size()-1));
        this.graphicsUpdaters = gameViewNew.getGraphicsUpdaters();
        this.graphicsUpdaters.add(this.scoreLivesView.getSLUpdater());
        gameComponents.getChildren().add(gameView.getGameRoot());

        Ghost.setAllEnergizedWithValue(false);
        this.hasntAlreadyWon=true;
        this.setPaused(false);
    }

    /**
     * Returns the next level. The next level represents the maze, 1, 2, or 3.
     * @param x The current level.
     * The next level is the current level incremented by 1.
     * If the current level is 3, the next level is 1.
     * 
     * @return The next level.
     */
    public static int getNextLevel(int x){
        if(x==3) return 1;
        else return ++x;
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