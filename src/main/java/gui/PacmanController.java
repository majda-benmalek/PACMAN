package gui;

/**
 * Classe pour le controleur du Pacman
 * keyPressedHandler : récupère la touche appuyée et change la direction du Pacman
 * avec event.getCode() qui renvoie la touche appuyée
 * keyReleasedHandler : rien à faire ???
 */

import model.Direction;
import model.PacMan;

import javafx.scene.input.KeyEvent;

/**
 * The PacmanController class handles the user input for controlling the Pacman game.
 */
public class PacmanController {
    private AnimationController animationController;

    /**
     * Handles the key pressed event.
     *
     * @param event the KeyEvent object representing the key press event
     * if the key pressed is ESCAPE, the game is paused
     * else, the direction of the Pacman is changed
     * if the key pressed is LEFT, the direction of the Pacman is changed to WEST
     * if the key pressed is RIGHT, the direction of the Pacman is changed to EAST
     * if the key pressed is UP, the direction of the Pacman is changed to NORTH
     * if the key pressed is DOWN, the direction of the Pacman is changed to SOUTH
     * if the key pressed is not one of the above, the direction of the Pacman is not changed (do nothing)
     */
    public void keyPressedHandler(KeyEvent event) {
        switch(event.getCode()) {
            case ESCAPE ->{
                if(animationController.isPaused() &&!animationController.isInUnstoppableAnimation()){
                    animationController.stopPauseMenu();
                }
                else if(!animationController.isInUnstoppableAnimation()){
                    animationController.startPauseMenu();
                }
            }
            default ->{
                PacMan.INSTANCE.setNextDir(
                        switch (event.getCode()) {
                            case LEFT -> Direction.WEST;
                            case RIGHT -> Direction.EAST;
                            case UP -> Direction.NORTH;
                            case DOWN -> Direction.SOUTH;
                            default -> PacMan.INSTANCE.getDirection(); // do nothing
                        });
            }
        }
    }

    /**
     * Handles the key released event.
     *
     * @param event the KeyEvent object representing the key release event
     */
    public void keyReleasedHandler(KeyEvent event) {
        // Nothing to do?
    }

    /**
     * Returns the AnimationController object associated with this PacmanController.
     *
     * @return the AnimationController object
     */
    public AnimationController getAnimationController(){
        return animationController;
    }

    /**
     * Sets the AnimationController object for this PacmanController.
     *
     * @param animationController the AnimationController object to be set
     */
    public void setAnimationController(AnimationController animationController){
        this.animationController = animationController;
    }
}
