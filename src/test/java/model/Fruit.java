package model;

import geometry.RealCoordinates;
import geometry.IntCoordinates;
import java.util.concurrent.TimeUnit;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The Fruit class represents a fruit in the game.
 * It has a position, visibility status, and a timer for visibility duration.
 */
public class Fruit {
    private IntCoordinates pos; // The position of the fruit

    public static boolean estVisible; // The visibility status of the fruit

    private Timer timer; // The timer for visibility duration

    private int dureVisible = 15000; // The duration of visibility in milliseconds

    /**
     * Constructs a Fruit object with the given position.
     * The fruit is initially visible and a timer is started to make it invisible after 4 seconds.
     * @param pos The position of the fruit
     */
    public Fruit(IntCoordinates pos) {
        this.pos = pos;
        this.estVisible = true;
        this.timer = new Timer();
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setVisible(false);
            }
        }, 4000);
    }

    /**
     * Sets the position of the fruit.
     * @param newPos The new position of the fruit
     */
    public void setPos(IntCoordinates newPos) {
        pos = newPos;
    }

    /**
     * Returns the position of the fruit.
     * @return The position of the fruit
     */
    public IntCoordinates getPos() {
        return pos;
    }

    /**
     * Returns the timer for visibility duration.
     * @return The timer for visibility duration
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * Cancels the timer for visibility duration.
     */
    public void cancelTimer() {
        if (this.timer != null)
            this.timer.cancel();
    }

    /**
     * Returns the visibility status of the fruit.
     * @return The visibility status of the fruit
     */
    public static boolean isEstVisible() {
        return estVisible;
    }

    /**
     * Sets the visibility status of the fruit.
     * If the fruit is set to be visible, a new timer is started to make it invisible after the specified duration.
     * @param estVisible The visibility status of the fruit
     */
    public void setVisible(boolean estVisible) {
        this.estVisible = estVisible;
        if (estVisible) {
            this.cancelTimer();
            this.timer = new Timer();
            this.timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    setVisible(false);
                }
            }, dureVisible);
        }
    }
    public String toString() {
       return "Fruit: "+this.getPos();
    }
}
