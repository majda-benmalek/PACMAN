package geometry;

/**
 * Represents integer coordinates in a 2D space.
 */
public record IntCoordinates(int x, int y) {
    /**
     * Converts the integer coordinates to real coordinates using the given scale.
     *
     * @param scale The scale factor to apply to the coordinates.
     * @return The real coordinates.
     */
    public RealCoordinates toRealCoordinates(double scale) {
        return new RealCoordinates(x * scale, y * scale);
    }

    /**
     * Subtracts the given coordinates from the current coordinates.
     *
     * @param other The coordinates to subtract.
     * @return The resulting coordinates after subtraction.
     */
    public IntCoordinates sub(IntCoordinates other) {
        return new IntCoordinates(x - other.x, y - other.y);
    }

    /**
     * Gets the x-coordinate.
     *
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate.
     *
     * @return The y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Calculates the Euclidean norm of the coordinates.
     *
     * @return The Euclidean norm.
     */
    public int norm(){
        return (int)Math.round(Math.sqrt(x*x+y*y));
    }

    /**
     * Adds the given coordinates to the current coordinates.
     *
     * @param other The coordinates to add.
     * @return The resulting coordinates after addition.
     */
    public IntCoordinates add(IntCoordinates other){
        return new IntCoordinates(x+other.x,y+other.y);
    }
}
