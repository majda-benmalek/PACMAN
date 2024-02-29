package gui;

import config.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.geometry.Pos;

import javafx.scene.effect.DropShadow;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import javafx.scene.Scene;

import javafx.stage.Stage;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight; 
import javafx.scene.text.Text;


/**
 * The MainMenu class represents the main menu of the game.
 * It implements the Menu interface.
 * This class provides methods to create and configure the main menu scene.
 */
public class MainMenu implements Menu {
    public Pane root;

    /**
     * Starts the main menu.
     * @param primaryStage the primary stage of the application
     * @param gameScene the game scene
     * @param animationController the animation controller
     * 
     * @return the main menu scene
     */
    public Scene startMenu(Stage primaryStage, Scene gameScene, AnimationController animationController) {
        Pane rootPane=new Pane();
        ImageView logoImage = new ImageView(new Image("Logo.png", 500, 400, true, true));
        ImageView ghostImage = new ImageView(new Image("ghosts.jpg", 200, 150, true, true));
        Font uwu = this.loadFont("src/main/resources/font.ttf", 50);

        Stop[] stops = new Stop[] { 
            new Stop(0, Color.rgb(254, 237, 1)),
            new Stop(0.25, Color.rgb(228, 14, 24)),
            new Stop(0.5, Color.rgb(249, 178, 52)),
            new Stop(0.75, Color.rgb(234, 145, 189)),
            new Stop(1, Color.rgb(88, 192, 205))
        };
        LinearGradient lg = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        //do a stops 2 where the colors are inverted
        Stop[] stops2 = new Stop[] { 
            new Stop(0, Color.rgb(88, 192, 205)),
            new Stop(0.25, Color.rgb(234, 145, 189)),
            new Stop(0.5, Color.rgb(249, 178, 52)),
            new Stop(0.75, Color.rgb(228, 14, 24)),
            new Stop(1, Color.rgb(254, 237, 1))
        };
        LinearGradient lg2 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops2);

        Stop[] stops3 = new Stop[] { 
            new Stop(0, Color.rgb(254, 237, 1)),
            new Stop(0.5, Color.rgb(228, 14, 24))

        };
        LinearGradient lg3 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops3);

        Stop[] stops4 = new Stop[] { 
            new Stop(0, Color.rgb(228, 14, 24)),
            new Stop(1, Color.rgb(254, 237, 1))
        };
        LinearGradient lg4 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops4);

        Text playText= new Text("PLAY");
        Text changeMapText= new Text("CHANGE MAP");
        Text quitText= new Text("QUIT");
        playText.setFont(loadFont("src/main/resources/font.ttf", 55));
        changeMapText.setFont(loadFont("src/main/resources/font.ttf", 40));
        quitText.setFont(loadFont("src/main/resources/font.ttf", 30));
        playText.setFill(lg3);
        changeMapText.setFill(lg);
        quitText.setFill(Color.rgb(255, 2, 70));
        playText.setEffect(new javafx.scene.effect.DropShadow(4, Color.rgb(251, 207, 7)));
        changeMapText.setEffect(new javafx.scene.effect.DropShadow(4, Color.WHITE));
        quitText.setEffect(new javafx.scene.effect.DropShadow(4, Color.rgb(230, 30, 37)));
        setHoverEffect(playText, lg4, lg3);
        setHoverEffect(changeMapText, lg2, lg);
        setHoverEffect(quitText, Color.RED, Color.rgb(255, 2, 70));

        playText.setOnMouseClicked(e->{
            primaryStage.setScene(gameScene);
            primaryStage.centerOnScreen();
            animationController.createAnimationTimer().start();
        });
        changeMapText.setOnMouseClicked(e->{
            MapSetter mapSetter= new MapSetter(animationController);
            mapSetter.start(primaryStage);
        });
        quitText.setOnMouseClicked(e->{
            System.exit(0);
        });
        VBox menu= new VBox(25);
        menu.setAlignment(Pos.CENTER);
        menu.setStyle("-fx-background-color: black;");
        menu.getChildren().addAll(logoImage, ghostImage, playText, changeMapText, quitText);
        menu.prefWidthProperty().bind(rootPane.widthProperty());
        menu.prefHeightProperty().bind(rootPane.heightProperty());
        rootPane.getChildren().add(menu);
        Scene scene= new Scene(rootPane, 550, 650);
        return scene;
    }

}