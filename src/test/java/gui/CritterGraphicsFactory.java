package gui;

import config.MazeConfig;
import model.Critter;
import model.ghost.Ghost;
import model.PacMan;
import model.Direction;
import model.MazeState;

import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.Group;

import javax.imageio.ImageIO;

public final class CritterGraphicsFactory {
    private final double scale;
    private int compteur;
    private int vitesseAnimation=20;
    
    public CritterGraphicsFactory(double scale) {
        this.scale = scale;
    }

    public GraphicsUpdater makeGraphics(Critter critter) {
        var size = 0.7;
        var url = (critter instanceof PacMan) ? "PacmanFaceReflet.gif" :
        switch ((Ghost) critter) {
            case BLINKY -> "ghost_blinky.png";
            case CLYDE -> "ghost_clyde.png";
            case INKY -> "ghost_inky.png";
            case PINKY -> "ghost_pinky.png";
        };  
            
        Image blueGhost = new Image("ghost_blue.png", scale*size, scale*size, true, true);    //image des famtomes quand ils deviennent bleus

        Image Down= new Image("PacmanBasReflet.gif", scale*size, scale*size, true, true);
        Image Up= new Image("PacmanHautReflet.gif", scale*size, scale*size, true, true);
        Image Right= new Image("PacmanDroiteReflet.gif", scale*size, scale*size, true, true);
        Image Left= new Image("PacmanGaucheReflet.gif", scale*size, scale*size, true, true);
        Image neutre = new Image("PacmanFaceReflet.gif", scale*size, scale*size, true, true);  
      
        var UwU =new ImageView(blueGhost);
        var critterImageView = new ImageView(new Image(url, scale * size, scale * size, true, true));
        var ghostBlue = new ImageView(new Image("ghost_blue.png", scale * size, scale * size, true, true));
        var eyes = new ImageView(new Image("Eye.png", scale * size, scale * size, true, true));
        ghostBlue.setVisible(false);
        eyes.setVisible(false);
        return new GraphicsUpdater() {
            @Override
            public void update() {
                if (!(critter instanceof PacMan)) {
                    ghostBlue.setVisible(critter.isEatable());
                    eyes.setVisible(critter.isEyes() && !critter.isEatable());
                    critterImageView.setVisible(!critter.isEyes() && !critter.isEatable());
                }
                critterImageView.setTranslateX((critter.getPos().x() + (1 - size) / 2) * scale);
                critterImageView.setTranslateY((critter.getPos().y() + (1 - size) / 2) * scale);
                ghostBlue.setTranslateX((critter.getPos().x() + (1 - size) / 2) * scale);
                ghostBlue.setTranslateY((critter.getPos().y() + (1 - size) / 2) * scale);
                eyes.setTranslateX((critter.getPos().x() + (1 - size) / 2) * scale);
                eyes.setTranslateY((critter.getPos().y() + (1 - size) / 2) * scale);

                if (critter instanceof PacMan) {
                    var dir = PacMan.INSTANCE.getDirection(); // c'est la que pacman tourne
                    switch (PacMan.INSTANCE.getDirection()) {
                        case NORTH:critterImageView.setImage(Up);
                            break;
                        case SOUTH:critterImageView.setImage(Down);
                            break;
                        case EAST:critterImageView.setImage(Right);
                            break;
                        case WEST:critterImageView.setImage(Left);
                            break;
                        case NONE:critterImageView.setImage(neutre);
                            break;
                    }
                }else{
                    switch(critter.getDirection()){
                       case EAST:critterImageView.setScaleX(-1);
                            break;
                        case WEST:critterImageView.setScaleX(1);
                            break;
                        default:
                            break;
                    }
                }
                
            }
            @Override
            public Node getNode() {
                Group group = new Group(critterImageView, ghostBlue, eyes);
                return group;
            }  
        } ;   

    }
}
