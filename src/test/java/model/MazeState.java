package model;

import config.*;
import config.Cell.Content;
import static config.Cell.Content.DOT;
import static config.Cell.Content.ENERGIZER;

import geometry.IntCoordinates;
import geometry.RealCoordinates;

import gui.AnimationController;
import gui.App;
import gui.PacmanController;
import gui.CellGraphicsFactory;

import model.Fruit;
import static model.ghost.Ghost.*;
import model.ghost.Ghost;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.util.Timer;
import java.util.TimerTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Iterator;

/**
 * The MazeState class represents the state of the maze in the Pacman game.
 * It contains the maze configuration, the positions of the critters, the score, etc.
 * It also contains the logic for updating the state of the maze.
 * It is one of the main classes of the model.
 * It is what registers the different critters and the fruit, and is in charge of updating their positions.
 */

public final class MazeState {
    private AnimationController animationController;
    public MazeConfig config;
    private final int height;
    private final int width;
    private boolean[][] gridState;

    private final List<Critter> critters;
    private final Fruit fruit;
    private int score;
    private final Map<Critter, RealCoordinates> initialPos;
    private int nbDotMange;

    private int nbDot = 0;
    private int lives = 3;
    private static int level;
    //level represents the maze, 1,2 or 3
    private int niveauFruit = 1;
    //niveauFruit is used to decide which fruit to display 

    private List<Integer> desScoresPourGainLive = new ArrayList<>();// un int pour gain lives
    private static List<Integer> nBDotpourFruit = new ArrayList<>();
    /**
     * Constructs a MazeState object with the specified maze configuration.
     * 
     * @param config the maze configuration
     */
    public MazeState(MazeConfig config) {
        this.level = MapSetter.chosenLabyrinth;
        this.config = config;
        height = config.getHeight();
        width = config.getWidth();
        critters = List.of(PacMan.INSTANCE, Ghost.CLYDE, Ghost.BLINKY, Ghost.INKY, Ghost.PINKY);
        fruit= new Fruit(config.getFruitPos());
        gridState = new boolean[height][width];
        initialPos = Map.of(
            PacMan.INSTANCE, config.getPacManPos().toRealCoordinates(1.0),
            BLINKY, config.getBlinkyPos().toRealCoordinates(1.0),
            INKY, config.getInkyPos().toRealCoordinates(1.0),
            CLYDE, config.getClydePos().toRealCoordinates(1.0),
            PINKY, config.getPinkyPos().toRealCoordinates(1.0)
        );
        resetCritters();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                IntCoordinates place = new IntCoordinates(j, i);
                if (config.getCell(place).hasEnergizer() || config.getCell(place).hasDot()) {
                    nbDot++;
                }
            }
        }
        nBDotpourFruit.add((int)(nbDot*0.3));
        nBDotpourFruit.add((int)(nbDot*0.5));
        desScoresPourGainLive.add((int)(nbDot*0.3));
        desScoresPourGainLive.add((int)(nbDot*0.5));
        desScoresPourGainLive.add(nbDot*3);
        
        score = 0;
        nbDotMange = 0;

    }

    // SETTERS/GETTERS


    /**
     * Returns the list of critters in the maze.
     * @return the list of critters in the maze
     */
    public List<Critter> getCritters() {
        return critters;
    }

    /**
     * Sets the maze configuration.
     * @param config the maze configuration
     */
    public void setConfig(MazeConfig config) {
        this.config = config;
    }

    /**
     * Returns the Fruit object in the maze.
     * @return the Fruit object in the maze
     */
   public Fruit getFruit() { return fruit; }

    public int getNiveauFruit() { return niveauFruit; }
    public void setNiveauFruit(int niveauFruit) { this.niveauFruit = niveauFruit; }
    public double getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public MazeConfig getConfig() {
        return config;
    }

    public boolean getGridState(IntCoordinates pos) {
        return gridState[pos.y()][pos.x()];
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int l) {
        lives = l;
    }

    public void setScore(int s) {
        score = s;
    }
    public void setAnimationController(AnimationController animationController){
        this.animationController=animationController;
    }

    /**
     * Is in charge of the movement of the PacMan critter.
     * @param nextDir the direction the PacMan critter is trying to go to
     * @param deltaTns the time elapsed since the last update
     * 
     * @return the next position of the PacMan critter
     */
    public void pacMovement(Direction nextDir, long deltaTns){
        var critter=PacMan.INSTANCE;
        var pacnextPos = critter.getNextPos(deltaTns, critter.getDirection(), this.config);
        critter.warp(pacnextPos, width, height);
        pacnextPos = critter.getNextPos(deltaTns, critter.getDirection(), this.config);
        if (PacMan.INSTANCE.canSetDirection(nextDir, this.config)) {
            critter.setDirection(nextDir);
            pacnextPos = critter.getNextPos(deltaTns, critter.getDirection(), this.config);
            critter.setPos(pacnextPos);
        } else {
            critter.setPos(pacnextPos);
        }
    }

    /**
     * Updates the state of the maze.
     * It is called at every frame of the animation.
     * It is in charge of updating the positions of the critters, checking if the PacMan critter has eaten a dot,
     * checking if the PacMan critter has eaten a ghost, checking if the PacMan critter has eaten a fruit, 
     * checking if the PacMan critter has won, etc.
     * @param deltaTns the time elapsed since the last update
     */
    public void update(long deltaTns) {
        // FIXME: too many things in this method. Maybe some responsibilities can be
        // delegated to other methods or classes?
        for (var critter : critters) {
            // ici on update le pacman pour potentiellement changer sa direction
            // si le déplacment est possible.
            critter.tpToCenter();
           
            if (critter == PacMan.INSTANCE) {
                var nextDir = ((PacMan)critter).getNextDir();
                pacMovement(nextDir,deltaTns);
            } else {
                var nextDir = ((Ghost)critter).getNextDir(this.config);
                var critNextPos=critter.getNextPos(deltaTns, nextDir,this.config);
                ((Ghost)critter).setPos(critNextPos);
                ((Ghost)critter).warp(critNextPos,width,height);
                ((Ghost)critter).setDirection(nextDir);
            }
        }
        // FIXME Pac-Man rules should somehow be in Pacman class
        var pacPos = PacMan.INSTANCE.getPos().round();

        if (!gridState[pacPos.y()][pacPos.x()]){
            if (config.getCell(pacPos).hasDot()) {
                addScore(1);
                nbDotMange++;
                gridState[pacPos.y()][pacPos.x()] = true;
            }
            if (config.getCell(pacPos).hasEnergizer()) {
                PacMan.INSTANCE.setEnergized();
            
                addScore(5);
                nbDotMange++;
                gridState[pacPos.y()][pacPos.x()] = true;
            }if(config.getCell(pacPos).hasFruit()&&fruit.estVisible){
                addScore(10);
                gridState[pacPos.y()][pacPos.x()] = true;
            }
        }
        

        

        //gère l'affichage du fruit
        Iterator<Integer> iteratorr = nBDotpourFruit.iterator();
        while (iteratorr.hasNext()){
            Integer i = iteratorr.next();
            if(nbDotMange==i){
                fruit.setVisible(true);
                iteratorr.remove();
            }
        }



        //verifier si tous les dots est manger il peut aller nouveua niveauFruit
        if(nbDotMange==nbDot && animationController.hasntAlreadyWon()){
            if(this.niveauFruit>=7)
                this.niveauFruit=1;
            else
                this.niveauFruit++;
            animationController.setHasntAlreadyWon(false);
            animationController.win();
        }

        // verifier score pour gain lives

        Iterator<Integer> iterator = desScoresPourGainLive.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (score >= i && i != 0) {
                addLives();
                iterator.remove();
            }
        }
        

        //manger ghosts
        for (var critter : critters) {
            if (critter instanceof Ghost && critter.getPos().round().equals(pacPos)) {
                if(!critter.isEyes()){
                    if (PacMan.INSTANCE.isEnergized()&& critter.isEatable()) {
                        addScore(10);
                        critter.setEatable(false);
                        ((Ghost)critter).DeEnergize();
                        critter.setEyes(true);
                    }else{
                        playerLost();
                        return;
                    }
                }
            }
        }
    }

    /**
     * It is called when the PacMan critter has lost a life.
     * It is in charge of resetting the positions of the critters, and of decrementing the number of lives.
     * If the PacMan critter has no more lives, it calls the gameOver() method of the AnimationController object.
     */
    private void playerLost() {
        PacMan.INSTANCE.setEnergized(false);
        if (lives != 1) {
            lives--;
            resetCritters();
        } else {
            animationController.gameOver();
        }

    }

    /**
     * It is called when Pacman gains some points.
     * @param increment the number of points gained
     */
    private void addScore(int increment) {
        score += increment;
    }

    /**
     * It is called when pacman gains a life.
     * It increments the number of lives.
     */
    private void addLives() {
        if(lives<=6){
            lives++;
        }
    }

    /**
     * It is in charge of resetting the Critter object passed as a parameter.
     * It resets its direction, its position, and its energized and eatable states.
     * @param critter the Critter object to reset
     */
    private void resetCritter(Critter critter) {
        critter.setDirection(Direction.NONE);
        critter.setPos(initialPos.get(critter));
        if (critter instanceof PacMan) {
            PacMan.INSTANCE.setNextDir(Direction.NONE);
            PacMan.INSTANCE.setEnergized(false);
        }else{
            ((Ghost)critter).DeEnergize();
        }
    }

    /**
     * It is in charge of resetting the Critters objects.
     * It calls the resetCritter() method for each Critter object in the maze.
     * It also resets the energized and eatable states of the Critter objects.
     * It is called when the PacMan critter has lost a life.
     */
    public void resetCritters() {
        for (var critter : critters) {
            resetCritter(critter);
            critter.setEnergized(false);
            critter.setEatable(false);
        }
    }

    /**
     * It is made to reset the GridState.
     * It sets all the cells of the grid to false.
     * It calls the resetCritters() method.
     */
    public void resetGrid() {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                gridState[j][i] = false;
            }
        }
        resetCritters();
    }
}
