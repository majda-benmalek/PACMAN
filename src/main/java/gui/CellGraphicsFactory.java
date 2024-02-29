package gui;

import geometry.IntCoordinates;
import model.MazeState;

import static config.Cell.Content.DOT;
import static config.Cell.Content.ENERGIZER;
import static config.Cell.Content.FRUIT;
import static config.Cell.Content.NOTHING;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * The CellGraphicsFactory class is responsible for creating and updating the graphics
 * for the cells in the Pacman game.
 */
public class CellGraphicsFactory {
        private final double scale;
        //this is the constructor for the factory of cell graphics        
        public CellGraphicsFactory(double scale) {
                this.scale = scale;
        }
        //this is the factory method that does the work of creating the graphics for a cell
        public GraphicsUpdater makeGraphics(MazeState state, IntCoordinates pos) {
                //group is a node that can contain other nodes
                var group = new Group();
                group.setTranslateX(pos.x() * scale);
                group.setTranslateY(pos.y() * scale);
                var cell = state.getConfig().getCell(pos);
                var dot = new Circle();
                group.getChildren().add(dot);
                if (cell != null && cell.getInitialContent() != null) {
                        dot.setRadius(switch (cell.getInitialContent()) {
                                case DOT -> scale / 15;
                                case ENERGIZER -> scale / 5;
                                case NOTHING -> 0;
                                case FRUIT -> 0;
                                default -> 0;
                        });
                }
                
                dot.setCenterX(scale / 2);
                dot.setCenterY(scale / 2);
                dot.setFill(Color.YELLOW);
                String path=switch(state.getNiveauFruit()){
                        case 1 -> "fruits/cherry.png";
                        case 2 -> "fruits/melon.png";
                        case 3 -> "fruits/strawberry.png";
                        case 4 -> "fruits/apple.png";
                        case 5 -> "fruits/orange.png";
                        case 6 -> "fruits/lemon.png";
                        case 7 -> "fruits/key.png";
                        default -> "";
                };
                Image image = new Image(path, scale/2, scale/2, true, true);
                ImageView imageView = new ImageView(image);
                imageView.setX(scale/4);
                imageView.setY(scale/4);


                group.getChildren().add(imageView);

                if (cell != null && cell.getEastWall()) {
                        var nWall = new Rectangle();
                        nWall.setHeight(scale);
                        nWall.setWidth(scale / 10);
                        nWall.setY(0);
                        nWall.setX(9 * scale / 10);
                        nWall.setFill(Color.BLUEVIOLET);
                        group.getChildren().add(nWall);
                }
                if (cell != null && cell.getNorthWall()) {
                        var nWall = new Rectangle();
                        nWall.setHeight(scale / 10);
                        nWall.setWidth(scale);
                        nWall.setY(0);
                        nWall.setX(0);
                        nWall.setFill(Color.BLUEVIOLET);
                        group.getChildren().add(nWall);
                }
                if (cell != null && cell.getSouthWall()) {
                        var nWall = new Rectangle();
                        nWall.setHeight(scale / 10);
                        nWall.setWidth(scale);
                        nWall.setY(9 * scale / 10);
                        nWall.setX(0);
                        nWall.setFill(Color.BLUEVIOLET);
                        group.getChildren().add(nWall);
                }

                if (cell != null && cell.getGhostNorthWall()) {
                        var nWall = new Rectangle();
                        nWall.setHeight(scale / 10);
                        nWall.setWidth(scale);
                        nWall.setY(0);
                        nWall.setX(0);
                        nWall.setFill(Color.WHITE);
                        group.getChildren().add(nWall);
                }
                if (cell != null && cell.getGhostSouthWall()) {
                        var nWall = new Rectangle();
                        nWall.setHeight(scale / 10);
                        nWall.setWidth(scale);
                        nWall.setY(9 * scale / 10);
                        nWall.setX(0);
                        nWall.setFill(Color.WHITE);
                        group.getChildren().add(nWall);
                }
                if (cell != null && cell.getWestWall()) {
                        var nWall = new Rectangle();
                        nWall.setHeight(scale);
                        nWall.setWidth(scale / 10);
                        nWall.setY(0);
                        nWall.setX(0);
                        nWall.setFill(Color.BLUEVIOLET);
                        group.getChildren().add(nWall);
                }
                imageView.setVisible(false);
                return new GraphicsUpdater() {
                        @Override
                        public void update() {
                                dot.setVisible(!state.getGridState(pos));
                                if(cell.getInitialContent()==FRUIT)
                                        imageView.setVisible(model.Fruit.isEstVisible() && !state.getGridState(pos));
                                        
                        }

                        @Override
                        public Node getNode() {
                                return group;
                        }
                };

        }
        // this method is used to create the graphics for the maze through the factory.
        // the factory works by creating a group of nodes
}
