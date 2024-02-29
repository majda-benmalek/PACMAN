package gui;

import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.scene.text.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The Menu interface represents a menu in a graphical user interface.
 * It provides methods for getting the width and height of the screen,
 * setting hover effects on text nodes, and loading fonts.
 */
public interface Menu {
    /**
     * Returns the width of the screen.
     *
     * @return the width of the screen
     */
    default double getWidth(){
        Rectangle2D screenBounds= Screen.getPrimary().getBounds();
        return screenBounds.getWidth();
    }

    /**
     * Returns the height of the screen.
     *
     * @return the height of the screen
     */
    default double getHeight(){
        Rectangle2D screenBounds= Screen.getPrimary().getBounds();
        return screenBounds.getHeight();
    }

    /**
     * Sets the hover effect on a text node.
     *
     * @param textNode the text node to apply the hover effect to
     * @param colorHover the color to use when the mouse enters the text node
     * @param colorExit the color to use when the mouse exits the text node
     */
    default void setHoverEffect(Text textNode , Paint colorHover , Paint colorExit){
        textNode.setOnMouseEntered(e->{
            textNode.setFill(colorHover);
        });
        textNode.setOnMouseExited(e->{
            textNode.setFill(colorExit);
        });
    }

    /**
     * Sets the hover effect on a text node with a default exit color of black.
     *
     * @param textNode the text node to apply the hover effect to
     * @param colorHover the color to use when the mouse enters the text node
     */
    default void setHoverEffect(Text textNode, Paint colorHover){
        textNode.setOnMouseEntered(e->{
            textNode.setFill(colorHover);
        });
        textNode.setOnMouseExited(e->{
            textNode.setFill(Color.BLACK);
        });
    }

    /**
     * Loads a font from the specified file path with the given size.
     *
     * @param path the file path of the font file
     * @param size the size of the font
     * 
     * If the font file is not found, the method will print the stack trace
     * 
     * @return the loaded font
     */
    default Font loadFont(String path, int size) {
        try {
            return Font.loadFont(new FileInputStream(new File(path)), size);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
