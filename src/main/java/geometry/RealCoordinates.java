package geometry;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a point in the real plane.
 * The coordinates are stored as double-precision floating point numbers.
 */
public record RealCoordinates(double x, double y) {

    public static final RealCoordinates ZERO = new RealCoordinates(0, 0);
    public static final RealCoordinates NORTH_UNIT = new RealCoordinates(0, -1);
    public static final RealCoordinates EAST_UNIT = new RealCoordinates(1, 0);
    public static final RealCoordinates SOUTH_UNIT = new RealCoordinates(0, 1);
    public static final RealCoordinates WEST_UNIT = new RealCoordinates(-1, 0);


    /**
     * Adds the given coordinates to the current coordinates.
     * @param other The coordinates to add.
     * 
     * @return The resulting coordinates after addition.
     */
    public RealCoordinates plus(RealCoordinates other) {
        return new RealCoordinates(x + other.x, y + other.y);
    }

    /**
     * Multiplies the current coordinates by the given multiplier.
     * @param multiplier The multiplier.
     * 
     * @return The resulting coordinates after multiplication.
     */
    public RealCoordinates times(double multiplier) {
        return new RealCoordinates(x * multiplier, y * multiplier);
    }

    /**
     *
     * @return the coordinates of all integer squares that a unit square with current coordinates would intersect
      */
    public Set<IntCoordinates> intNeighbours() {
        return new HashSet<>(List.of(
                new IntCoordinates((int) Math.floor(x), (int) Math.floor(y)),
                new IntCoordinates((int) Math.floor(x), (int) Math.ceil(y)),
                new IntCoordinates((int) Math.ceil(x), (int) Math.floor(y)),
                new IntCoordinates((int) Math.ceil(x), (int) Math.ceil(y))
        )
        );
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    
    /**
     * Rounds the current coordinates to the nearest integer coordinates.
     * 
     * @return The nearest integer coordinates.
     */
    public IntCoordinates round() {
        return new IntCoordinates((int) Math.round(x), (int) Math.round(y));
    }
    
    //Round down the coordinates to the previous integer
    public RealCoordinates floorX() {
        return new RealCoordinates((int) Math.floor(x), y);
    }

    public RealCoordinates floorY() {
        return new RealCoordinates(x, (int) Math.floor(y));
    }


    //Round up the coordinates to the next integer
    public RealCoordinates ceilX() {
        return new RealCoordinates((int) Math.ceil(x), y);
    }

    public RealCoordinates ceilY() {
        return new RealCoordinates(x, (int) Math.ceil(y));
    }

    /**
     * Computes the distance between the current coordinates and the given coordinates.
     * @param currCell The coordinates to compute the distance to.
     * 
     * @return The distance between the current coordinates and the given coordinates.
     */
    public double dist(RealCoordinates currCell){
        return (Math.sqrt(Math.pow((this.getX()-currCell.getX()),2)+Math.pow((this.getY()-currCell.getY()),2)));
    }

    //fonction qui s'ocuppe de la téléportation des critter quand ils sortent de la map.
    public RealCoordinates warp(int width, int height) {
        var rx = x;
        var ry = y;
        if (Math.round(rx) < 0)
            rx += width;
        if (Math.round(ry) < 0)
            ry += height;
        if (Math.round(rx) >= width)
            rx -= width;
        if (Math.round(rx) >= height)
            ry -= height;
        return new RealCoordinates(rx, ry);
    }
}
